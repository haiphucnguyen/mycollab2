package com.esofthead.mycollab.core.utils;

import java.awt.*;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class ColorUtils {
    public static final String brighterColor(String hexValue) {
        Color color = Color.decode(hexValue);
        Color brighter = lighter(color, 0.2);
        return toHexString(brighter);
    }

    public final static String toHexString(Color colour) throws NullPointerException {
        String hexColour = Integer.toHexString(colour.getRGB() & 0xffffff);
        if (hexColour.length() < 6) {
            hexColour = "000000".substring(0, 6 - hexColour.length()) + hexColour;
        }
        return "#" + hexColour;
    }

    /**
     * Make a color lighter.
     *
     * @param color     Color to make lighter.
     * @param fraction  Darkness fraction.
     * @return          Lighter color.
     */
    public static Color lighter (Color color, double fraction)
    {
        int red   = (int) Math.round (color.getRed()   * (1.0 + fraction));
        int green = (int) Math.round (color.getGreen() * (1.0 + fraction));
        int blue  = (int) Math.round (color.getBlue()  * (1.0 + fraction));

        if (red   < 0) red   = 0; else if (red   > 255) red   = 255;
        if (green < 0) green = 0; else if (green > 255) green = 255;
        if (blue  < 0) blue  = 0; else if (blue  > 255) blue  = 255;

        int alpha = color.getAlpha();

        return new Color (red, green, blue, alpha);
    }
}
