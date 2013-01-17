/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.vaadin.ui;

import java.io.File;

/**
 *
 * @author haiphucnguyen
 */
public interface AttachmentDisplayComponent {

    void receiveFile(File file, String fileName,
            String mimeType, long length);
}
