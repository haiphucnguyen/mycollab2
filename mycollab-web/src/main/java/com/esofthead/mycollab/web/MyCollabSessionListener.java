package com.esofthead.mycollab.web;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.cache.LocalCacheManager;

public class MyCollabSessionListener implements HttpSessionListener {
	private static Logger log = LoggerFactory
			.getLogger(MyCollabSessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se) {

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String sessionId = se.getSession().getId();
		LocalCacheManager.removeCache(sessionId);
		log.debug("Remove cache {}", sessionId);
	}

}
