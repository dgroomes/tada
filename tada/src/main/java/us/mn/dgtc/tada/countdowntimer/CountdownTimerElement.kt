package us.mn.dgtc.tada.countdowntimer

import android.view.ViewGroup
import android.widget.TextView
import us.mn.dgtc.tada.execution.CallbackableRunnableSerialExecutor
import us.mn.dgtc.tada.execution.callbackableRunnableFrom

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

    private val delay = callbackackableCountDownTimerFrom(1000, 10) {}

    private val displayToStartTime = callbackableRunnableFrom {
        textView.text = "10:00"
    }

    private val countDown = callbackackableCountDownTimerFrom(10000, 10) {
        val secondsRemaining = it / 1000
        val secondsAsMillisRemaining = secondsRemaining * 1000
        val timeRemaining_millisPortion = it - secondsAsMillisRemaining
        val timeRemaining_millisPortion_truncated = timeRemaining_millisPortion / 10
        val displayString = "$secondsRemaining:$timeRemaining_millisPortion_truncated"
        textView.text = displayString
    }


    private val zeroOutDisplay = callbackableRunnableFrom {
        textView.text = "0:00"
    }

    private val fadeOut = callbackackableCountDownTimerFrom(3000, 10) {
        textView.alpha = it.toFloat() / 3000.toFloat()
    }

    private val sendFinish = callbackableRunnableFrom {
        countDownTimerElementManager.handleOnFinish(element)
    }

    fun addToViewGroup(viewGroup: ViewGroup) = viewGroup.addView(textView)
}