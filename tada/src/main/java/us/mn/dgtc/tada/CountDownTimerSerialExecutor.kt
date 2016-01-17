package us.mn.dgtc.tada

import android.os.CountDownTimer
import java.util.*

/**
 * Created by David Groomes on 1/16/2016.
 *
 * Schedule CountDownTimer instances and/or Runnable instances to execute in serial.
 */
class CountDownTimerSerialExecutor() {

    private var executed = false

    private val directives : MutableList<Any> = ArrayList()

    fun run() {
        if (executed) throw IllegalStateException("#run has already been called on this executor")
        else {
            executed = true
            buildRunnableQueueRecursive(directives).run()
        }
    }

    fun schedule(def : CountDownTimerDefinition) : CountDownTimerSerialExecutor {
        directives.add(def)
        return this
    }

    fun schedule(nonTimedAction: () -> Unit ) : CountDownTimerSerialExecutor {
        directives.add(nonTimedAction)
        return this
    }
    /**
     * Recursive!!! And not tail recursive at that...
     */
    private fun buildRunnableQueueRecursive(defs : List<Any>) : Runnable {
        return when (defs.size) {
            0 -> throw IllegalArgumentException("must have at least directive")
            1 -> createTerminalOperation(defs[0])
            else -> {
                createRunnable(defs[0], buildRunnableQueueRecursive(defs.slice(1..defs.size)))
            }
        }
    }

    private fun createRunnable(actionOrDef: Any, nextRunnable: Runnable) : Runnable {
        if (actionOrDef is CountDownTimerDefinition) {
            return Runnable {
                object : CountDownTimer(actionOrDef.duration, actionOrDef.interval) {
                    override fun onFinish() {
                        nextRunnable.run()
                    }

                    override fun onTick(millisUntilFinished: Long) {
                        actionOrDef.onTick(millisUntilFinished)
                    }
                }.start()
            }
        } else if (actionOrDef is Runnable) {
            return Runnable {
                actionOrDef.run()
                nextRunnable.run()
            }
        } else throw IllegalStateException("the actionOrDef must be a CountDownTimerDefinition or a Runnable")
    }

    private fun createTerminalOperation(actionOrDef: Any) : Runnable {
        if (actionOrDef is CountDownTimerDefinition) {
            return Runnable {
                object : CountDownTimer(actionOrDef.duration, actionOrDef.interval) {
                    override fun onFinish() {}

                    override fun onTick(millisUntilFinished: Long) {
                        actionOrDef.onTick(millisUntilFinished)
                    }
                }.start()
            }
        } else if (actionOrDef is Runnable) return actionOrDef
        else throw IllegalStateException("the actionOrDef must be a CountDownTimerDefinition or a Runnable")
    }
}