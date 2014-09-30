package com.esofthead.mycollab.ondemand.module.file.view;

import javax.servlet.http.HttpSession;

import com.dropbox.core.DbxSessionStore;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class MyCollabDbxSessionStore implements DbxSessionStore {

	private HttpSession session;
	private String key;

	public MyCollabDbxSessionStore(HttpSession session, String sessionKey) {
		this.session = session;
		this.key = sessionKey;
	}

	@Override
	public String get() {
		Object v = session.getAttribute(key);
		if (v instanceof String)
			return (String) v;
		return null;
	}

	@Override
	public void set(String value) {
		session.setAttribute(key, "mycollab123456789123456789");
	}

	@Override
	public void clear() {
		session.removeAttribute(key);
	}

}
