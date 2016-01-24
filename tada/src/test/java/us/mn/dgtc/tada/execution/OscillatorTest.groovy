package us.mn.dgtc.tada.execution

import spock.lang.Specification
import us.mn.dgtc.tada.Sensor

/**
 * Created by David Groomes on 1/23/2016.
 *
 * Anthropological footnote: TDD in the raw
 */
class OscillatorTest extends Specification {

    Sensor sensor = Mock(Sensor)

    Oscillator oscillator = new Oscillator({sensor.invoke()})

    def setup() {}

    def "'up' does not invoke the callback"() {
        when:
        oscillator.up()
        then:
        0 * sensor.invoke()
    }

    def "normal case 'up down' should invoke callback once"() {
        when:
        oscillator.up()
        oscillator.down();
        then:
        1 * sensor.invoke()
    }

    def "oscillator's index is initially set to 0"() {
        when:
        oscillator.down()
        then:
        thrown(IllegalStateException)
    }

    def "illegal 'up down down' throws an error and does NOT invoke the callback on the illegal 'down'"() {
        when:
        oscillator.up()
        oscillator.down()
        oscillator.down()
        then:
        1 * sensor.invoke()
        thrown(IllegalStateException)
    }

    def "longer term case'up down up down' invokes callback twice"() {
        when:
        oscillator.up()
        oscillator.down()
        oscillator.up()
        oscillator.down()
        then:
        2 * sensor.invoke()
    }
}
