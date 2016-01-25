package us.mn.dgtc.tada

import dagger.Component
import us.mn.dgtc.tada.activity.CountDownTimerArtActivity
import us.mn.dgtc.tada.countdowntimer.CountDownTimerElementManager
import us.mn.dgtc.tada.countdowntimer.CountDownTimerElementManagerFactory
import javax.inject.Singleton

/**
 * Created by David Groomes on 1/24/2016.
 */
@Singleton
@Component(modules = arrayOf(TadaModule::class))
interface TadaComponent {

    fun inject(countDownTimerElementManager: CountDownTimerElementManager)

    fun countDownTimerElementManagerFactory() : CountDownTimerElementManagerFactory
}