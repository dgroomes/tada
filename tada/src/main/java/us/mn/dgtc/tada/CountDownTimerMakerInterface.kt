package us.mn.dgtc.tada

import android.os.CountDownTimer

/**
 * Created by David Groomes on 1/18/2016.
 */
interface CountDownTimerMakerInterface {

    fun makeCountDownTimer(def: CountDownTimerDefinition): CountDownTimerInterface

    fun makeCountDownTimerChained(def: CountDownTimerDefinition, next: Runnable): CountDownTimerInterface
}