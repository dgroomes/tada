package us.mn.dgtc.tada.color

import java.util.*

/**
 * Created by davidg on 1/10/16.
 *
 *
 */

val colors: Array<Int> = arrayOf(0xFFB5AC01.toInt(), 0xFFECBA09.toInt(), 0xFFE86E1C.toInt(), 0xFFD41E45.toInt(), 0xFF1B1521.toInt())
val rainbow: Array<Int> = arrayOf(0xFF482344.toInt(), 0xFF2B5166.toInt(), 0xFF429867.toInt(), 0xFFFAB243.toInt(), 0xFFE02130.toInt())

class ColorProvider {

    fun getNextColor() : Int = rainbow.get(Random().nextInt(rainbow.size))

}