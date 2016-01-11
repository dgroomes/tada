package us.mn.dgtc.tada;

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import org.jetbrains.anko.onTouch

class CountDownTimerArtActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rootView: ViewGroup = getRootView(this) as ViewGroup
        val countDownTimerElementManager = CountDownTimerElementManager(rootView, this)
        rootView.onTouch { view, motionEvent ->
            countDownTimerElementManager.addACountDownTimerElement()
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean = false

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
}
