package us.mn.dgtc.tada

import spock.lang.Specification

/**
 * Created by David Groomes on 1/17/2016.
 */
class CountDownTimerSerialExecutorTest extends Specification {

    //<editor-fold desc="Helper Methods">
    def spiedObjectToString = {
        sensor.invoke()
    }
    //</editor-fold>

    //<editor-fold desc="Spied Collaborator">
    Sensor sensor = Mock(Sensor)
    //</editor-fold>

    //<editor-fold desc="Object Under Test">
    CountDownTimerSerialExecutor executor
    //</editor-fold>

    //<editor-fold desc="Setup">
    def setup() {
        executor = new CountDownTimerSerialExecutor(new CountDownTimerMakerMock())
    }
    //</editor-fold>

    //<editor-fold desc="Tests">
    def "#run: with no actions scheduled"() {
        when:
        executor.run()
        then:
        thrown IllegalArgumentException
    }

    def "#run: with one scheduled action"() {
        executor.schedule(spiedObjectToString)
        when:
        executor.run()
        then:
        1 * sensor.invoke()

    }

    def "#run: with two scheduled actions"() {
        executor.schedule(spiedObjectToString)
        executor.schedule(spiedObjectToString)
        when:
        executor.run()
        then:
        2 * sensor.invoke()
    }

    def "#run: after #run has already been called"() {
        executor.schedule(spiedObjectToString)
        executor.run()
        when:
        executor.run()
        then:
        thrown IllegalStateException
    }

    def '#run: with one CountDownTimer action'() {
        executor.schedule(new CountDownTimerDefinition(10, 1, spiedObjectToString))
        when:
        executor.run()
        then:
        1 * sensor.invoke()
    }

    def '#run: with two CountDownTimer action'() {
        executor.schedule(new CountDownTimerDefinition(10, 1, spiedObjectToString))
        executor.schedule(new CountDownTimerDefinition(10, 1, spiedObjectToString))
        when:
        executor.run()
        then:
        2 * sensor.invoke()
    }
    //</editor-fold>
}

