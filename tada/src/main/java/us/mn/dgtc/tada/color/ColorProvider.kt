package us.mn.dgtc.tada.color

import us.mn.dgtc.tada.util.randomIntBetween

/**
 * Created by davidg on 1/10/16.
 *
 * todo: dependency injection
 */

/**
 * Colors courtesy colourlovers.com
 */
val colorPalettes = setOf(
        ColorPalette("prettyColors", arrayOf(0xFFB5AC01.toInt(), 0xFFECBA09.toInt(), 0xFFE86E1C.toInt(), 0xFFD41E45.toInt(), 0xFF1B1521.toInt())),
        ColorPalette("rainbow", arrayOf(0xFF482344.toInt(), 0xFF2B5166.toInt(), 0xFF429867.toInt(), 0xFFFAB243.toInt(), 0xFFE02130.toInt())),
        ColorPalette("gianGoldfish", arrayOf(0xFF69D2E7.toInt(), 0xFFA7DBD8.toInt(), 0xFFE0E4CC.toInt(), 0xFFF38630.toInt(), 0xFFFA6900.toInt()))
).toMapBy { it.name }

fun ColorPalette.getRandomColor(): Int = this.colors.get(java.util.Random().nextInt(this.colors.size))

fun <T> Set<T>.getRandom(): T = when (size) {
    0 -> throw IllegalStateException("set is empty, cannot get a random element")
    1 -> first()
    else -> elementAt(randomIntBetween(0, size - 1))
}

/**
 * Not thread safe (I'll accept the risk...)
 */
class ColorProvider {

    var currentPalette: ColorPalette = colorPalettes.values.first()

    fun getNextColor(): Int = currentPalette.getRandomColor()

    fun switchColorPallete() {
        currentPalette = colorPalettes.values
                .toSet()
                .minus(currentPalette)
                .getRandom()
    }
}