package com.esofthead.mycollab.db.migration;

import com.esofthead.mycollab.core.MyCollabException;

public class IllegalQueryException extends MyCollabException {

	private static final long serialVersionUID = -4847411495350655382L;

	public IllegalQueryException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalQueryException(String message) {
		super(message);
	}

	public IllegalQueryException(Throwable cause) {
		super(cause);
	}

}
