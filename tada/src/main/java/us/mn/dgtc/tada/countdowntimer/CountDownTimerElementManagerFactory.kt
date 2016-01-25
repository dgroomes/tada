package us.mn.dgtc.tada.countdowntimer

import android.app.Activity
import android.view.ViewGroup
import us.mn.dgtc.tada.color.ColorProvider
import us.mn.dgtc.tada.color.ColorProviderHardCoded
import javax.inject.Inject

/**
 * Created by David Groomes on 1/25/2016.
 */
class CountDownTimerElementManagerFactory @Inject constructor(val colorProvider: ColorProvider) {

    fun create(activity: Activity, viewGroup: ViewGroup) : CountDownTimerElementManager =
        CountDownTimerElementManager(viewGroup, activity, colorProvider)
}