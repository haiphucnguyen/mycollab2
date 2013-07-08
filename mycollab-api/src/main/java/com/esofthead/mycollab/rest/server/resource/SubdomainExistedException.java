package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.core.MyCollabException;

public class SubdomainExistedException extends MyCollabException {
	private static final long serialVersionUID = 1L;

	public SubdomainExistedException(String errorMsg) {
		super(errorMsg);
	}
}
