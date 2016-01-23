package us.mn.dgtc.tada.activity;

import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.ViewGroup
import org.jetbrains.anko.onClick
import us.mn.dgtc.tada.countdowntimer.CountDownTimerElementManager
import us.mn.dgtc.tada.util.getRootView

class CountDownTimerArtActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rootView: ViewGroup = getRootView(this) as ViewGroup
        val countDownTimerElementManager = CountDownTimerElementManager(rootView, this)
        rootView.onClick { view ->
            countDownTimerElementManager.addACountDownTimerElement()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean = false
}
