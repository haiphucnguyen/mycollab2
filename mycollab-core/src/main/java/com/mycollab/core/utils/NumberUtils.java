package com.mycollab.core.utils;

import java.math.BigDecimal;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class NumberUtils {
    public static double roundDouble(int scale, Double value) {
        if (value == null) {
            return 0d;
        }
        return new BigDecimal(String.valueOf(value)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static Number zeroIfNull(Number value) {
        return (value != null) ? value : 0;
    }
}
