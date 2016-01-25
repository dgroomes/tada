package us.mn.dgtc.tada.countdowntimer

import android.app.Activity
import android.graphics.Point
import android.view.ViewGroup
import android.widget.TextView
import us.mn.dgtc.tada.TadaApplication
import us.mn.dgtc.tada.color.ColorProvider
import us.mn.dgtc.tada.color.ColorProviderHardCoded
import us.mn.dgtc.tada.execution.Oscillator
import us.mn.dgtc.tada.util.randomIntBetween
import javax.inject.Inject

/**
 * Created by davidg on 1/10/16.
 *
 * Manages CountdownTimerElement instance.
 */
class CountDownTimerElementManager(val rootView : ViewGroup,
                                   val activity: Activity,
                                   val colorProvider: ColorProvider) {


    val colorChangingOscillator : Oscillator

    init {
        TadaApplication.graph.inject(this)
        colorChangingOscillator = Oscillator({
            colorProvider.switchColorPallete()
        })
    }


    fun handleOnFinish(element: CountDownTimerElement) {
        rootView.removeView(element.textView)
        colorChangingOscillator.down()
    }

    fun addACountDownTimerElement() {
        val countDownTimerElement = createACountDownTimerElement()
        countDownTimerElement.addToViewGroup(rootView)
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
        val point: Point = Point()
        activity.windowManager.defaultDisplay.getSize(point)

        val x = randomIntBetween(-200, point.x)
        val y = randomIntBetween(-100, point.y)

        textView.x = x.toFloat()
        textView.y = y.toFloat()
    }
}