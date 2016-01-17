package us.mn.dgtc.tada

/**
 * Created by David Groomes on 1/16/2016.
 *
 * Meant for chaining (that's why there's no #onFinish parameter, since the next CountDownTimer is supposed to start on finish of the previous one)
 *
 * todo reconsider if this is really necessary; i think we can easily get away without it; all else equal, the fewer classes the better
 */
data class CountDownTimerDefinition(val duration : Long, val interval : Long, val onTick : (Long) -> Unit) {}