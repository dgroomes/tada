package us.mn.dgtc.tada.color

/**
 * Created by David Groomes on 1/25/2016.
 *
 * Provides colors
 */
interface ColorProvider {

    fun getNextColor(): Int

    fun switchColorPallete()
}