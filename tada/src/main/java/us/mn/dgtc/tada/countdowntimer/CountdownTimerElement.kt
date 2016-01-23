package us.mn.dgtc.tada.countdowntimer

import android.view.ViewGroup
import android.widget.TextView
import us.mn.dgtc.tada.execution.CallbackableRunnable
import us.mn.dgtc.tada.execution.CallbackableRunnableSerialExecutor

/**
 * Created by davidg on 1/10/16.
 *
 * Visual element that shows a timer that counts down to 0:00, and then fades away.
 */

class CountDownTimerElement(val textView: TextView,
                            private val countDownTimerElementManager: CountDownTimerElementManager) {

    private val element = this

    fun start() {
        CallbackableRunnableSerialExecutor()
                .schedule(displayToStartTime)
                .schedule(delay)
                .schedule(countDown)
                .schedule(zeroOutDisplay)
                .schedule(fadeOut)
                .schedule(sendFinish)
                .run()
    }

    private val delay = object : CallbackableCountDownTimer(1000, 10) {}

    private val displayToStartTime = object : CallbackableRunnable {
        override fun run() {
            textView.text = "10:00"
        }
    }

    private val countDown = object : CallbackableCountDownTimer(10000, 10) {
        override fun onTick(millisUntilFinished: Long) {
            val secondsRemaining = millisUntilFinished / 1000
            val secondsAsMillisRemaining = secondsRemaining * 1000
            val timeRemaining_millisPortion = millisUntilFinished - secondsAsMillisRemaining
            val timeRemaining_millisPortion_truncated = timeRemaining_millisPortion / 10
            val displayString = "$secondsRemaining:$timeRemaining_millisPortion_truncated"
            textView.text = displayString
        }
    }


    private val zeroOutDisplay = object : CallbackableRunnable {
        override fun run() {
            textView.text = "0:00"
        }
    }

    private val fadeOut = object : CallbackableCountDownTimer(3000, 10) {
        override fun onTick(millisUntilFinished: Long) {
            textView.alpha = millisUntilFinished.toFloat() / 3000.toFloat()
        }
    }

    private val sendFinish = object : CallbackableRunnable {
        override fun run() {
            countDownTimerElementManager.handleOnFinish(element)
        }
    }

    fun addToViewGroup(viewGroup: ViewGroup) = viewGroup.addView(textView)
}