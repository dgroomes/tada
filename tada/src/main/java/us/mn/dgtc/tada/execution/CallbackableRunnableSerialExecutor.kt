package us.mn.dgtc.tada.execution

import android.os.CountDownTimer
import java.util.*

/**
 * Created by David Groomes on 1/16/2016.
 *
 * Schedule CountDownTimer instances and/or Runnable instances to execute in serial.
 */
class CallbackableRunnableSerialExecutor() {

    private var executed = false

    private val runnables: MutableList<CallbackableRunnable> = ArrayList()

    fun schedule(callbackableRunnable: CallbackableRunnable): CallbackableRunnableSerialExecutor {
        runnables.add(callbackableRunnable)
        return this
    }

    fun run() {
        when {
            executed -> throw IllegalStateException("#run has already been called on this executor")
            runnables.isEmpty() -> throw IllegalArgumentException("must have scheduled at least one runnable")
            else -> {
                executed = true
                runInternal(runnables)
            }
        }
    }

    private fun runInternal(directives: List<CallbackableRunnable>) {
        return when (directives.size) {
            0 -> throw RuntimeException("programmer error")
            1 -> directives[0].run()
            else -> directives[0].run { runInternal(directives.drop(1)) }
        }
    }
}