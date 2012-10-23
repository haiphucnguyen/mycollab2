/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.file.domain;

import java.io.IOException;
import java.util.Calendar;

public class SimpleFile extends Content {

    private long size;

    private String documentType;

    private String documentStatus;

    private String[] tags;

    private String name;

    private String mimeType;

    private Calendar lastModified;

    public Calendar getLastModified() {
        return lastModified;
    }

    public void setLastModified(Calendar lastModified) {
        this.lastModified = lastModified;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(String documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public static SimpleFile convertFileToSimpleFile(FileItem tempFile) {
        SimpleFile file = new SimpleFile();
        file.setCreationDate(tempFile.getCreationDate());
        file.setDescription(tempFile.getDescription());
        file.setDocumentStatus(tempFile.getDocumentStatus());
        file.setDocumentType(tempFile.getDocumentType());
        file.setModifiedDate(tempFile.getModifiedDate());

        file.setTags(tempFile.getTags());

        file.setOwner(tempFile.getOwner());
        file.setPath(tempFile.getPath());

        file.getPermissions().addAll(tempFile.getPermissions());

        if (tempFile.getResource() != null) {
            file.setMimeType(tempFile.getResource().getMimeType());
            file.setLastModified(tempFile.getResource().getLastModified());

            // set size of file
            if (tempFile.getResource().getData() != null) {
                try {
                    file.setSize(tempFile.getResource().getData().available());
                } catch (IOException e) {
                    file.setSize(0);
                }
            }
        }

        return file;
    }
}
