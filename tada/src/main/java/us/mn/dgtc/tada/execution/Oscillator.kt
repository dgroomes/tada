package us.mn.dgtc.tada.execution

/**
 * Created by David Groomes on 1/23/2016.
 *
 * This class keeps track of an index, which oscillates up and down, bounded by 0 on the lower end. Every time it hits 0,
 * the callback is invoked.
 *
 * implementation note: is the order of execution of a synchronized method guaranteed to match the order of the threads that
 * invoked it? I don't think so, which means this class doesn't work like I want it. Am I confused?
 *
 * implementation note: Should I use something like CountDownLatch? Or some other standard class?
 */
class Oscillator(private val callback : () -> Unit) {

    private enum class VerticalDirection() {
        UP, DOWN
    }

    private var index = 0

    @Synchronized
    private fun moveIndex(direction : VerticalDirection) {
        when (direction) {
            VerticalDirection.DOWN -> {
                if (index == 0) {
                    throw IllegalStateException("tried to move the Oscillator down but index is already at the bottom (i.e. 0)")
                }
                else if (index == 1){
                    callback()
                    index = 0
                } else {
                    index--
                }
            }
            VerticalDirection.UP -> index++
        }
    }

    fun down() = moveIndex(VerticalDirection.DOWN)

    fun up() = moveIndex(VerticalDirection.UP)
}