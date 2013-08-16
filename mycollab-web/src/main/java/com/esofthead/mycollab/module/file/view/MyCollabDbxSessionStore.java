package com.esofthead.mycollab.module.file.view;

import com.dropbox.core.DbxSessionStore;

public class MyCollabDbxSessionStore implements DbxSessionStore{

	@Override
	public String get() {
		return "mycollab";
	}

	@Override
	public void set(String value) {
		
		
	}

	@Override
	public void clear() {
		
	}

}
