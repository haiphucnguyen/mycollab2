package com.esofthead.mycollab.module.file_old;

import com.esofthead.mycollab.core.EngroupException;

public class FileAccessDenyException extends EngroupException {
	private static final long serialVersionUID = 1L;

	public FileAccessDenyException(String message) {
		super(message);
	}

}
