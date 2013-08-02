/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.file;

import java.io.File;

import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.FileResource;

/**
 * 
 * @author haiphucnguyen
 */
public class ImagePreviewResource extends FileResource {
	private static final long serialVersionUID = 1L;

	public ImagePreviewResource(String documentPath) {
		super(
				new File(FileStorageConfig.baseContentFolder + "/"
						+ documentPath), AppContext.getApplication());
	}
}
