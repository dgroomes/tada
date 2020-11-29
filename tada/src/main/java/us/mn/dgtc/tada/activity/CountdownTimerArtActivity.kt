package us.mn.dgtc.tada.activity;

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.Menu
import android.view.ViewGroup
import us.mn.dgtc.tada.R
import us.mn.dgtc.tada.TadaApplication

/**
 *
 */
class CountDownTimerArtActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @SuppressLint("InflateParams")
        val view = layoutInflater.inflate(R.layout.activity_count_down_timer_art, null) as ViewGroup
        setContentView(view)
        val countDownTimerElementManager =
                TadaApplication
                        .graph
                        .countDownTimerElementManagerFactory()
                        .create(this)
        view.setOnClickListener {
            countDownTimerElementManager.addACountDownTimerElement()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean = false
}
