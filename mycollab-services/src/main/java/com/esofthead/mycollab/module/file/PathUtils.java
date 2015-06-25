package com.esofthead.mycollab.module.file;

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
public class PathUtils {
    public static String buildPath(Integer sAccountId, String objectPath) {
        return ((sAccountId == null) ? "" : sAccountId + "/") + objectPath;
    }

    public static String buildLogoPath(Integer sAccountId, String logoFileName, Integer logoSize) {
        return String.format("%s/.assets/%s_%d.png", sAccountId, logoFileName, logoSize);
    }

    public static String buildFavIconPath(Integer sAccountId, String favIconFileName) {
        return String.format("%s/.assets/%s", sAccountId, favIconFileName);
    }
}
