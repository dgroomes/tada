package us.mn.dgtc.tada.execution;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Test;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import org.junit.experimental.categories.Category;
import us.mn.dgtc.tada.Sensor;
import us.mn.dgtc.tada.junit.UnitTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by David Groomes on 1/17/2016.
 */
@Category(UnitTest.class)
public class CallbackableRunnableSerialExecutorTest {

    class TestCR implements CallbackableRunnable {

        @Override
        public void run(@NotNull Function0<Unit> callback) {
            run();
            callback.invoke();
        }

        @Override
        public void run() {
            sensor.invoke();
        }
    }

    CallbackableRunnable callbackableRunnable1 = new TestCR();

    //<editor-fold desc="Spied Collaborator">
    Sensor sensor = mock(Sensor.class);
    //</editor-fold>

    //<editor-fold desc="Object Under Test">
    CallbackableRunnableSerialExecutor executor;
    //</editor-fold>

    //<editor-fold desc="Setup">
    @Before
    public void setup() {
        executor = new CallbackableRunnableSerialExecutor();
    }
    //</editor-fold>

    //<editor-fold desc="Tests">
    @Test
    public void runWithOneScheduledAction() {
        executor.schedule(callbackableRunnable1);

        // Act
        executor.run();

        // Assert
        verify(sensor, times(1)).invoke();

    }

    @Test
    public void runWithTwoScheduledActions() {
        // Arrange
        executor.schedule(callbackableRunnable1);
        executor.schedule(callbackableRunnable1);

        // Act
        executor.run();

        // Assert
        verify(sensor, times(2)).invoke();
    }

    @Test(expected =  IllegalStateException.class)
    public void runAfterRunHasAlreadyBeenCalled() {
        // Arrange
        executor.schedule(callbackableRunnable1);
        executor.run();

        // Act
        executor.run();
    }
    //</editor-fold>
}

