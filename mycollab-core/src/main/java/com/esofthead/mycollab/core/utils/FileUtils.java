package com.esofthead.mycollab.core.utils;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

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
            return Math.floor(((float) volume / KB_SIZE) * 100) / 100 + " Kb";
        } else if (volume < GB_SIZE) {
            return Math.floor(((float) volume / MB_SIZE) * 100) / 100 + " Mb";
        } else {
            return Math.floor((volume / GB_SIZE) * 100) / 100 + " Gb";
        }
    }

    /**
     *
     * @param baseFolder
     * @param relativePaths
     * @return null if can not get the folder
     */
    public static File getDesireFile(String baseFolder, String... relativePaths) {
        File file;
        for (String relativePath:relativePaths) {
            file = new File(baseFolder, relativePath);
            if (file.exists()) {
                return file;
            }
        }
        return null;
    }

    public static Reader getReader(String templateFile) {
        try {
            return new InputStreamReader(FileUtils.class.getClassLoader()
                    .getResourceAsStream(templateFile), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new InputStreamReader(FileUtils.class.getClassLoader()
                    .getResourceAsStream(templateFile));
        }
    }
}
