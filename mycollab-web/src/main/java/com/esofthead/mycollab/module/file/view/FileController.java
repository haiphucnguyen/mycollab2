package com.esofthead.mycollab.module.file.view;

import com.esofthead.mycollab.vaadin.mvp.IController;

public class FileController implements IController {
	private static final long serialVersionUID = 1L;

	private FileModule container;

	public FileController(FileModule container) {
		this.container = container;
	}

}
