package com.mycollab.module.file;

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
public final class PathUtils {
    public static String buildPath(Integer sAccountId, String objectPath) {
        return ((sAccountId == null) ? "" : sAccountId + "/") + objectPath;
    }

    public static String getProjectLogoPath(Integer accountId, Integer projectId) {
        return String.format("%d/project/%d/.attachments", accountId, projectId);
    }

    public static String getEntityLogoPath(Integer accountId) {
        return String.format("%d/.assets", accountId);
    }

    public static String getProjectDocumentPath(Integer accountId, Integer projectId) {
        return String.format("%d/project/%d/.page", accountId, projectId);
    }

    public static String buildLogoPath(Integer sAccountId, String logoFileName, Integer logoSize) {
        return String.format("%d/.assets/%s_%d.png", sAccountId, logoFileName, logoSize);
    }

    public static String buildFavIconPath(Integer sAccountId, String favIconFileName) {
        return String.format("%d/.assets/%s.ico", sAccountId, favIconFileName);
    }
}
