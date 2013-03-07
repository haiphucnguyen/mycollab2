/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.io.File;

import org.vaadin.easyuploads.MultiFileUploadExt;

/**
 *
 * @author haiphucnguyen
 */
public interface AttachmentUploadComponent {

    void receiveFile(File file, String fileName,
            String mimeType, long length);
    
    void registerMultiUpload(MultiFileUploadExt multiUpload);
}
