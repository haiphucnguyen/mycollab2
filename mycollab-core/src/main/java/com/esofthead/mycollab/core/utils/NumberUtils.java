package com.esofthead.mycollab.core.utils;

import java.math.BigDecimal;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class NumberUtils {
    public static double roundDouble(int scale, double value) {
        return new BigDecimal(String.valueOf(value)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
