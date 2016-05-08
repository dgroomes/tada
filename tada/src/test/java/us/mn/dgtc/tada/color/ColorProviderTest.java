package us.mn.dgtc.tada.color;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import us.mn.dgtc.tada.junit.UnitTest;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by David Groomes on 1/23/2016.
 */
@Category(UnitTest.class)
public class ColorProviderTest {

    ColorProviderHardCoded provider = new ColorProviderHardCoded();

    @Test
    public void getNextColor() {
        // Arrange
        ColorPalette currentPalette = provider.getCurrentPalette();
        List<Integer> colors = Arrays.asList(currentPalette.getColors());

        // Act
        Integer nextColor = provider.getNextColor();

        // Assert
        assertTrue(colors.contains(nextColor));
    }

    /**
     * implementation note: this is a little white box; surfaces a smell IMO
     */
    @Test
    public void switchColorPaletteSwitchesToADifferentColorPalette() {
        // Arrange
        ColorPalette currentPaletteTimeZero = provider.getCurrentPalette();

        // Act
        provider.switchColorPallete();

        // Assert
        assertThat(provider.getCurrentPalette()).isNotEqualTo(currentPaletteTimeZero);
    }
}
