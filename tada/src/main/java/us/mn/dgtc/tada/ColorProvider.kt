package us.mn.dgtc.tada

import java.util.*

/**
 * Created by davidg on 1/10/16.
 *
 *
 */

val colors: Array<Int> = arrayOf(0xFFB5AC01.toInt(), 0xFFECBA09.toInt(), 0xFFE86E1C.toInt(), 0xFFD41E45.toInt(), 0xFF1B1521.toInt())

class ColorProvider {

    fun getNextColor() : Int = colors.get(Random().nextInt(colors.size))

}