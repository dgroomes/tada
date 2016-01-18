package us.mn.dgtc.tada

/**
 * Created by David Groomes on 1/18/2016.
 */
interface CountDownTimerInterface {
    fun onTick(millisUntilFinished : Long)

    fun onFinish()

    fun cancel()

    fun start()
}