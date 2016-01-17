package us.mn.dgtc.tada

import android.os.CountDownTimer
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by davidg on 1/10/16.
 *
 * Visual element that shows a timer that counts down to 0:00, and then fades away.
 */

class CountDownTimerElement(val textView: TextView,
                            private val countDownTimerElementManager: CountDownTimerElementManager) {

    fun start() {
        CountDownTimerSerialExecutor()
                .schedule(countDown)
                .schedule(zeroOutDisplay)
                .schedule(fadeOut)
                .schedule(sendFinish)
                .run()
    }

    private val countDown = CountDownTimerDefinition(10999, 10, {
        val millisUntilFinished = it
        val secondsRemaining = millisUntilFinished / 1000
        val secondsAsMillisRemaining = secondsRemaining * 1000
        val timeRemaining_millisPortion = millisUntilFinished - secondsAsMillisRemaining
        val timeRemaining_millisPortion_truncated = timeRemaining_millisPortion / 10
        val displayString = "$secondsRemaining:$timeRemaining_millisPortion_truncated"
        textView.text = displayString
    })

    private val zeroOutDisplay = { textView.text = "0:00" }

    private val fadeOut = CountDownTimerDefinition(3000, 10, { textView.alpha = it.toFloat() / 3000.toFloat() })

    private val sendFinish = { countDownTimerElementManager.handleOnFinish(this) }

    fun addToViewGroup(viewGroup: ViewGroup) = viewGroup.addView(textView)
}