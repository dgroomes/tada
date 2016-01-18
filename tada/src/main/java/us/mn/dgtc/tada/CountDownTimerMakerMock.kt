package us.mn.dgtc.tada

import android.os.CountDownTimer

class CountDownTimerMakerMock : CountDownTimerMakerInterface {

    override fun makeCountDownTimer(def: CountDownTimerDefinition): CountDownTimerInterface = object : CountDownTimerInterface {

        override fun onTick(millisUntilFinished: Long) {
            def.onTick(millisUntilFinished)
        }

        override fun onFinish() {

        }

        override fun cancel() {

        }

        override fun start() {
            onTick(123)
            onFinish()
        }
    }

    override fun makeCountDownTimerChained(def: CountDownTimerDefinition, next: Runnable): CountDownTimerInterface = object : CountDownTimerInterface {

        override fun onTick(millisUntilFinished: Long) {
            def.onTick(millisUntilFinished)
        }

        override fun onFinish() {
            next.run()
        }

        override fun cancel() {

        }

        override fun start() {
            onTick(123)
            onFinish()
        }
    }
}