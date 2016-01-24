package us.mn.dgtc.tada

import android.app.Application

/**
 * Created by David Groomes on 1/24/2016.
 */
class MyApplication : Application() {

    companion object {
        @JvmStatic lateinit public var graph: SampleComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerSampleComponent.builder().sampleModule(SampleModule()).build()
    }
}