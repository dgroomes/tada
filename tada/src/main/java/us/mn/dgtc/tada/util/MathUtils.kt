
package us.mn.dgtc.tada.util

import java.util.*

/**
 * Created by davidg on 1/10/16.
 */
/**
 * Careful: Needs review. If precision is not a requirement, then by all means use this.
 */
fun randomIntBetween(lowerBound: Int, upperBound: Int): Int =
        Random().nextInt(upperBound - lowerBound) + lowerBound