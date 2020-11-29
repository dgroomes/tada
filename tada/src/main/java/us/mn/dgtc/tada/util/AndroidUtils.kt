package us.mn.dgtc.tada.util

import android.R
import android.app.Activity
import android.view.View

/**
 * Created by davidg on 1/10/16.
 */
fun Activity.getRootView() = this.findViewById(R.id.content) as View
