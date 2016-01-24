package us.mn.dgtc.tada

import android.app.Application

/**
 * Created by David Groomes on 1/24/2016.
 */
class TadaApplication : Application() {

    companion object {
        @JvmStatic lateinit public var graph: TadaComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerTadaComponent.create()
    }
}