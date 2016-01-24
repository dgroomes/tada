package us.mn.dgtc.tada.execution

import kotlin.Unit
import kotlin.jvm.functions.Function0
import org.jetbrains.annotations.NotNull
import spock.lang.Specification
import us.mn.dgtc.tada.Sensor
import us.mn.dgtc.tada.execution.CallbackableRunnable
import us.mn.dgtc.tada.execution.CallbackableRunnableSerialExecutor

/**
 * Created by David Groomes on 1/17/2016.
 */
class CallbackableRunnableSerialExecutorTest extends Specification {

    class TestCR implements CallbackableRunnable {

        @Override
        void run(@NotNull Function0<Unit> callback) {
            run()
            callback.invoke()
        }

        @Override
        void run() {
            sensor.invoke()
        }
    }

    def callbackableRunnable1 = new TestCR()

    //<editor-fold desc="Helper Methods">
    def spiedObjectToString = {

    }
    //</editor-fold>

    //<editor-fold desc="Spied Collaborator">
    Sensor sensor = Mock(Sensor)
    //</editor-fold>

    //<editor-fold desc="Object Under Test">
    CallbackableRunnableSerialExecutor executor
    //</editor-fold>

    //<editor-fold desc="Setup">
    def setup() {
        executor = new CallbackableRunnableSerialExecutor()
    }
    //</editor-fold>

    //<editor-fold desc="Tests">
    def "#run: with one scheduled action"() {
        executor.schedule(callbackableRunnable1)
        when:
        executor.run()
        then:
        1 * sensor.invoke()

    }

    def "#run: with two scheduled actions"() {
        executor.schedule(callbackableRunnable1)
        executor.schedule(callbackableRunnable1)
        when:
        executor.run()
        then:
        2 * sensor.invoke()
    }

    def "#run: after #run has already been called"() {
        executor.schedule(callbackableRunnable1)
        executor.run()
        when:
        executor.run()
        then:
        thrown IllegalStateException
    }
    //</editor-fold>
}

