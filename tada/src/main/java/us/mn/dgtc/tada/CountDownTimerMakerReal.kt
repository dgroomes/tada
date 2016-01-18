package us.mn.dgtc.tada

import android.os.CountDownTimer

class CountDownTimerMakerReal : CountDownTimerMakerInterface {

    override fun makeCountDownTimer(def: CountDownTimerDefinition): CountDownTimerInterface = object : CountDownTimerInterface {
        private val backingCountDownTimer = object : CountDownTimer(def.duration, def.interval) {
            override fun onFinish() {
                // noop
            }

            override fun onTick(millisUntilFinished: Long) {
                def.onTick(millisUntilFinished)
            }
        }

        override fun onTick(millisUntilFinished: Long) {
            backingCountDownTimer.onTick(millisUntilFinished)
        }

        override fun onFinish() {
            backingCountDownTimer.onFinish()
        }

        override fun cancel() {
            backingCountDownTimer.cancel()
        }

        override fun start() {
            backingCountDownTimer.start()
        }
    }

    override fun makeCountDownTimerChained(def: CountDownTimerDefinition, next: Runnable): CountDownTimerInterface = object : CountDownTimerInterface {

        private val backingCountDownTimer = object : CountDownTimer(def.duration, def.interval) {
            override fun onFinish() {
                next.run()
            }

            override fun onTick(millisUntilFinished: Long) {
                def.onTick(millisUntilFinished)
            }
        }

        override fun onTick(millisUntilFinished: Long) {
            backingCountDownTimer.onTick(millisUntilFinished)
        }

        override fun onFinish() {
            backingCountDownTimer.onFinish()
        }

        override fun cancel() {
            backingCountDownTimer.cancel()
        }

        override fun start() {
            backingCountDownTimer.start()
        }
    }
}