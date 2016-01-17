package us.mn.dgtc.tada

import android.app.Activity
import android.graphics.Point
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by davidg on 1/10/16.
 *
 * Manages CountdownTimerElement instance. Or rather, is a strategy pattern holding an algorithm to execute "on finish"
 * of the lifespan of a CountdownTimerElement
 */
class CountDownTimerElementManager(val rootView: ViewGroup, val activity: Activity) {

    val colorProvider: ColorProvider = ColorProvider() // todo dependency injection

    fun handleOnFinish(element: CountDownTimerElement) {
        rootView.removeView(element.textView)
    }

    fun addACountDownTimerElement() {
        val countDownTimerElement = createACountDownTimerElement()
        countDownTimerElement.addToViewGroup(rootView)
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