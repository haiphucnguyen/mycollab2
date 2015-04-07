package com.esofthead.mycollab.core.utils;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class FileUtils {
    private static long KB_SIZE = 1024;
    private static long MB_SIZE = 1024 * 1024;
    private static long GB_SIZE = 1024 * 1024 * 1024;

    public static String getVolumeDisplay(Long volume) {
        if (volume == null) {
            return "0 Kb";
        } else if (volume < KB_SIZE) {
            return volume + " Bytes";
        } else if (volume < MB_SIZE) {
            return Math.floor(volume / KB_SIZE) + " Kb";
        } else if (volume < GB_SIZE) {
            return Math.floor(volume / MB_SIZE) + " Mb";
        } else {
            return Math.floor(volume / GB_SIZE) + " Gb";
        }
    }

    public static String getVolumeDisplay2(Integer volume) {
        if (volume == null) {
            return "0 Kb";
        } else if (volume < KB_SIZE) {
            return volume + " Bytes";
        } else if (volume < MB_SIZE) {
            return Math.floor(((float) volume / KB_SIZE) * 100) / 100 + " Kb";
        } else if (volume < GB_SIZE) {
            return Math.floor(((float) volume / MB_SIZE) * 100) / 100 + " Mb";
        } else {
            return Math.floor((volume / GB_SIZE) * 100) / 100 + " Gb";
        }
    }
}
