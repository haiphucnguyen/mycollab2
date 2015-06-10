package com.esofthead.mycollab.core.utils;

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
public class ArrayUtils {
    public static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length != 0;
    }
}
