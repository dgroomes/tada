package us.mn.dgtc.tada

import android.os.CountDownTimer
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by davidg on 1/10/16.
 *
 * Implementation notes: This probably shouldn't extend CountDownTimer because it isn't really an "is a" relationship.
 * That detracts from the abstraction. EDIT: definitely follow up on this. We're exposing and using the #start method of CountDownTimer,
 * CountdownTimerElement should really just have its own #start method. What if there's a new requirement?
 */

class CountDownTimerElement(val textView: TextView,
                            private val countDownTimerElementManager: CountDownTimerElementManager) : CountDownTimer(10999, 10) {

    override fun onTick(millisUntilFinished: Long) {
        val toPrint = toSecondsAndMilliseconds(millisUntilFinished)
        textView.text = toPrint
    }

    private fun toSecondsAndMilliseconds(millisUntilFinished: Long): String {
        val secondsRemaining = millisUntilFinished / 1000
        val secondsAsMillisRemaining = secondsRemaining * 1000
        val displayString = "$secondsRemaining:${millisUntilFinished - secondsAsMillisRemaining}"
        return displayString
    }

    override fun onFinish() {
        countDownTimerElementManager.handleOnFinish(this)
    }

    fun addToViewGroup(viewGroup : ViewGroup) {
        viewGroup.addView(textView)
    }
}