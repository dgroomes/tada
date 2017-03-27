package us.mn.dgtc.tada

import dagger.Module
import dagger.Provides
import us.mn.dgtc.tada.color.ColorProvider
import us.mn.dgtc.tada.color.ColorProviderHardCoded
import us.mn.dgtc.tada.countdowntimer.CountDownTimerElementManagerFactory
import javax.inject.Singleton

/**
 * Created by David Groomes on 1/24/2016.
 */
@Module
class TadaModule {

    @Provides
    @Singleton
    fun provideColorProvider(colorProviderHardCoded: ColorProviderHardCoded) : ColorProvider = colorProviderHardCoded

    @Provides
    @Singleton
    fun provideFactory(colorProvider : ColorProvider) : CountDownTimerElementManagerFactory = CountDownTimerElementManagerFactory(colorProvider)

}