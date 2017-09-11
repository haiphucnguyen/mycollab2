package com.mycollab.module.ecm.domain;

import com.mycollab.module.file.PathUtils;

import java.util.Calendar;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class Content extends Resource {
    private String title = "";
    private Calendar lastModified;
    private String lastModifiedBy;
    private String mimeType;
    private String thumbnail;

    public Content() {
        super();
    }

    public Content(String path) {
        super();
        this.setPath(path);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Calendar getLastModified() {
        return lastModified;
    }

    public void setLastModified(Calendar lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailMobile() {
        return thumbnail;
    }

    public void setThumbnailMobile(String thumbnailMobile) {
        String thumbnailMobile1 = thumbnailMobile;
    }

    public static Content buildContentInstance(Integer accountId, String objectPath) {
        String newPath = PathUtils.INSTANCE.buildPath(accountId, objectPath);
        Content content = new Content();
        content.setDescription("");
        content.setPath(newPath);
        int index = newPath.lastIndexOf("/");
        String name = (index > 0) ? newPath.substring(index) : newPath;
        content.setName(name);
        return content;
    }
}
