package us.mn.dgtc.tada

import android.os.CountDownTimer
import java.util.*

/**
 * Created by David Groomes on 1/16/2016.
 *
 * Schedule CountDownTimer instances and/or Runnable instances to execute in serial.
 */
class CountDownTimerSerialExecutor(val countDownTimerMaker: CountDownTimerMakerInterface) {

    private var executed = false

    private val directives: MutableList<Any> = ArrayList()

    fun run() {
        if (executed) throw IllegalStateException("#run has already been called on this executor")
        else {
            executed = true
            buildRunnableQueueRecursive(directives).run()
        }
    }

    fun schedule(def: CountDownTimerDefinition): CountDownTimerSerialExecutor {
        directives.add(def)
        return this
    }

    fun schedule(nonTimedAction: () -> Unit): CountDownTimerSerialExecutor {
        directives.add(nonTimedAction)
        return this
    }
    /**
     * Recursive!!! And not tail recursive at that...
     */
    private fun buildRunnableQueueRecursive(defs : List<Any>) : Runnable {
        return when (defs.size) {
            0 -> throw IllegalArgumentException("must have at least one directive")
            1 -> createTerminalOperation(defs[0])
            else -> {
                createRunnable(defs[0], buildRunnableQueueRecursive(defs.slice(1..defs.size - 1)))
            }
        }
    }

    private fun createRunnable(actionOrDef: Any, nextRunnable: Runnable) : Runnable {
        if (actionOrDef is CountDownTimerDefinition) {
            return Runnable {
                countDownTimerMaker.makeCountDownTimerChained(actionOrDef, nextRunnable).start()
            }
        } else {
            return Runnable {
                val castInvokable = actionOrDef as () -> Unit
                castInvokable.invoke()
                nextRunnable.run()
            }
        }
    }

    private fun createTerminalOperation(actionOrDef: Any) : Runnable {
        if (actionOrDef is CountDownTimerDefinition) {
            return Runnable {
                countDownTimerMaker.makeCountDownTimer(actionOrDef).start()
            }
        } else {
            val castInvokable = actionOrDef as () -> Unit
            val runnable = Runnable {
                castInvokable.invoke()
            }
            return runnable
        }
    }
}