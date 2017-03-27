package us.mn.dgtc.tada.countdowntimer

import android.app.Activity
import us.mn.dgtc.tada.color.ColorProvider
import javax.inject.Inject

/**
 *
 */
class CountDownTimerElementManagerFactory @Inject constructor(val colorProvider: ColorProvider) {

    fun create(activity: Activity) : CountDownTimerElementManager =
        CountDownTimerElementManager(activity, colorProvider)
}