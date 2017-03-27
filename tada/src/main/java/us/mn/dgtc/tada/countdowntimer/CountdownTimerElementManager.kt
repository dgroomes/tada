package us.mn.dgtc.tada.countdowntimer

import android.app.Activity
import android.view.ViewGroup
import android.widget.TextView
import us.mn.dgtc.tada.color.ColorProvider
import us.mn.dgtc.tada.execution.Oscillator
import us.mn.dgtc.tada.util.getRootView
import us.mn.dgtc.tada.util.randomIntBetween

/**
 * Created by davidg on 1/10/16.
 *
 * Manages CountdownTimerElement instance.
 */
class CountDownTimerElementManager(val activity: Activity,
                                   val colorProvider: ColorProvider) {


    val colorChangingOscillator = Oscillator({
        colorProvider.switchColorPallete()
    })
    val viewContainer : ViewGroup

    init {
        val rootView = activity.getRootView()
        if (rootView is ViewGroup) {
            viewContainer = rootView
        } else {
            throw IllegalArgumentException("Expected View to be an instance of ViewGroup but is not. CountDownTimerElementManager requires a ViewGroup")
        }
    }


    fun handleOnFinish(element: CountDownTimerElement) {
        viewContainer.removeView(element.textView)
        colorChangingOscillator.down()
    }

    fun addACountDownTimerElement() {
        val countDownTimerElement = createACountDownTimerElement()
        countDownTimerElement.addToViewGroup(viewContainer)
        colorChangingOscillator.up()
        countDownTimerElement.start()
    }

    private fun createACountDownTimerElement() : CountDownTimerElement {
        val timerTextView = TextView(activity)
        assignLocation(timerTextView)
        timerTextView.setTextColor(colorProvider.getNextColor())
        timerTextView.textSize = randomIntBetween(10, 55).toFloat()
        timerTextView.setBackgroundColor(0)
        return CountDownTimerElement(timerTextView, this)
    }

    /**
     * not super great random location assigner
     */
    private fun assignLocation(textView: TextView) {
        val x = randomIntBetween(-200, viewContainer.measuredWidth)
        val y = randomIntBetween(-100, viewContainer.measuredHeight)
        textView.x = x.toFloat()
        textView.y = y.toFloat()
    }
}