package us.mn.dgtc.tada.execution;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import us.mn.dgtc.tada.Sensor;
import us.mn.dgtc.tada.junit.UnitTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by David Groomes on 1/23/2016.
 *
 * Anthropological footnote: TDD in the raw
 */
@Category(UnitTest.class)
public class OscillatorTest {

    Sensor sensor = mock(Sensor.class);

    Oscillator oscillator = new Oscillator(new Function0<Unit>() {
        @Override
        public Unit invoke() {
            sensor.invoke();
            return null;
        }
    });

    @Test
    public void upDoesNotInvokeTheCallback() {
        // Act
        oscillator.up();
        // Assert
        verify(sensor, times(0)).invoke();
    }

    @Test
    public void normalCaseUpDownShouldInvokeCallbackOnce() {
        // Act
        oscillator.up();
        oscillator.down();
        // Assert
        verify(sensor, times(1)).invoke();
    }

    @Test(expected = IllegalStateException.class)
    public void oscillatorsIndexIsInitiallySetTo0() {
        // Act
        oscillator.down();
    }

    @Test(expected = IllegalStateException.class)
    public void illegalUpDownDownThrowsAnErrorAndDoesNOTInvokeTheCallbackOnTheIllegalDown() {
        // Act
        oscillator.up();
        oscillator.down();
        oscillator.down();
        // Assert
        verify(sensor, times(1)).invoke();
    }

    @Test
    public void longerTermCaseUpDownUpDownInvokesCallbackTwice() {
        // Act
        oscillator.up();
        oscillator.down();
        oscillator.up();
        oscillator.down();
        // Assert
        verify(sensor, times(2)).invoke();
    }
}
