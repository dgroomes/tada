package us.mn.dgtc.tada.execution

/**
 * Created by David Groomes on 1/20/2016.
 */
interface CallbackableRunnable : Runnable {

    fun run(callback : () -> Unit) {
        run()
        callback()
    }
}

fun callbackableRunnableFrom(runnable : () -> Unit) = object : CallbackableRunnable {
    override fun run() {
        runnable.invoke()
    }
}