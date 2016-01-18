package us.mn.dgtc.tada

import android.os.CountDownTimer

/**
 * Created by David Groomes on 1/18/2016.
 */
interface CountDownTimerMakerInterface {

    fun makeCountDownTimer(def: CountDownTimerDefinition): CountDownTimerInterface = object : CountDownTimerInterface {
        override fun onTick(millisUntilFinished: Long) {
            throw UnsupportedOperationException()
        }

        override fun onFinish() {
            throw UnsupportedOperationException()
        }

        override fun cancel() {
            throw UnsupportedOperationException()
        }

        override fun start() {
            throw UnsupportedOperationException()
        }
    }

    fun makeCountDownTimerChained(def: CountDownTimerDefinition, next: Runnable): CountDownTimerInterface = object : CountDownTimerInterface {
        override fun onTick(millisUntilFinished: Long) {
            throw UnsupportedOperationException()
        }

        override fun onFinish() {
            throw UnsupportedOperationException()
        }

        override fun cancel() {
            throw UnsupportedOperationException()
        }

        override fun start() {
            throw UnsupportedOperationException()
        }

    }

}