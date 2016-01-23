package us.mn.dgtc.tada.countdowntimer

import android.os.CountDownTimer
import us.mn.dgtc.tada.execution.CallbackableRunnable

/**
 * Note: this is definitely a little needlessly complex
 */
abstract class CallbackableCountDownTimer(duration: Long, interval: Long) : CountDownTimer(duration, interval), CallbackableRunnable {

    private var optionalCallback: (() -> Unit)? = null;

    override fun onTick(millisUntilFinished: Long) {
    }

    override fun run() {
        this.start()
    }

    override fun onFinish() {
        if (optionalCallback != null) optionalCallback!!.invoke()
    }

    override fun run(callback: () -> Unit) {
        optionalCallback = callback
        this.start()
    }
}

fun callbackackableCountDownTimerFrom(duration: Long, interval: Long, onTick: (millisUntilFinished: Long) -> Unit) =
        object : CallbackableCountDownTimer(duration, interval) {
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished)
            }
        }