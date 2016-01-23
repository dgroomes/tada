package us.mn.dgtc.tada.color

import spock.lang.Specification

/**
 * Created by David Groomes on 1/23/2016.
 */
class ColorProviderTest extends Specification {

    ColorProvider provider = new ColorProvider()

    void setup() {

    }

    def "getNextColor"() {
        ColorPalette currentPalette = provider.currentPalette

        when:
        Integer color = provider.nextColor

        then:
        currentPalette.colors.contains(color)
    }

    /**
     * implementation note: this is a little white box; surfaces a smell IMO
     */
    def "switchColorPalette switches to a different color palette"() {
        ColorPalette currentPaletteTimeZero = provider.currentPalette

        when:
        provider.switchColorPallete()

        then:
        provider.currentPalette != currentPaletteTimeZero
    }
}
