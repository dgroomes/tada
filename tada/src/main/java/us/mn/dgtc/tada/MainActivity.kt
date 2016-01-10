package us.mn.dgtc.tada;

import android.app.Activity
import android.graphics.Point
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.*
import android.widget.TextView
import org.jetbrains.anko.onTouch
import java.util.*

val colors : Array<Int> = arrayOf(0xFFB5AC01.toInt(), 0xFFECBA09.toInt(), 0xFFE86E1C.toInt(), 0xFFD41E45.toInt(), 0xFF1B1521.toInt())

class MainActivity : Activity() {

    val TAG: String = "TadaTag"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setAddTextViewTouchListener()
    }

    private fun setAddTextViewTouchListener() {
        val thiz: MainActivity = this
        findViewById(android.R.id.content).onTouch { view, motionEvent ->
            val mTextField = TextView(thiz)
            assignLocation(mTextField)
            mTextField.setTextColor(pickColor())
            mTextField.setBackgroundColor(0)

            Runnable {
                object : CountDownTimer(30000, 10) {

                    override fun onTick(millisUntilFinished: Long) {
                        val toPrint =
                                toSecondsAndMilliseconds(millisUntilFinished)
//                        "${mTextField.measuredWidth}${mTextField.measuredHeight}"
                        mTextField.text = toPrint
                    }

                    private fun toSecondsAndMilliseconds(millisUntilFinished: Long): String {
                        val secondsRemaining = millisUntilFinished / 1000
                        val secondsAsMillisRemaining = secondsRemaining * 1000
                        val displayString = "$secondsRemaining:${millisUntilFinished - secondsAsMillisRemaining}"
                        return displayString
                    }

                    override fun onFinish() {
                        mTextField.text = ".";
                    }
                }.start();
            }.run()

            Log.i(TAG, "onTouch event happened!")
            val viewGroup = view as ViewGroup
            viewGroup.addView(mTextField)
            true
        }
    }

    private fun pickColor() = colors.get(Random().nextInt(colors.size))

    private fun assignLocation(mTextField: TextView) {
        val point : Point = Point()
        windowManager.defaultDisplay.getSize(point)

        val x = randomIntBetween(-200, point.x)
        val y = randomIntBetween(-100, point.y)

        mTextField.x = x.toFloat()
        mTextField.y = y.toFloat()
    }

    /**
     * Careful: Needs review. If precision is not a requirement, then by all means use this.
     */
    private fun randomIntBetween(lowerBound: Int, upperBound: Int) : Int =
            Random().nextInt(upperBound - lowerBound) + lowerBound


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun printViewHierarchy(viewGroup: ViewGroup, prefix: String) {
        Log.i(TAG, "logging view hierarchy now. viewGroup has size ${viewGroup.childCount}")
        for (i in 0..viewGroup.childCount - 1) {
            Log.i(TAG, i.toString())
            val v: View? = viewGroup.getChildAt(i);
            if (v != null) {
                val desc: String = prefix + " | " + "[" + i + "/" + (viewGroup.childCount - 1) + "] " + v.javaClass.simpleName + " " + v.id;
                Log.i(TAG, desc);
                if (v is ViewGroup) {
                    printViewHierarchy(v, desc);
                }
            }
        }
    }
}
