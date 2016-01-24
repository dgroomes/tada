package us.mn.dgtc.tada

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by David Groomes on 1/24/2016.
 */
@Module
class SampleModule {

    @Provides
    @Singleton
    @Named("theMessage")
    fun provideTheMessage() : String {
        return "the message is this."
    }

}