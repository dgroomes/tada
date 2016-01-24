package us.mn.dgtc.tada

import dagger.Component
import us.mn.dgtc.tada.activity.CountDownTimerArtActivity
import javax.inject.Singleton

/**
 * Created by David Groomes on 1/24/2016.
 */
@Singleton
@Component(modules = arrayOf(TadaModule::class))
interface TadaComponent {

    fun inject(countDownTimerArtActivity: CountDownTimerArtActivity)

}