package com.esofthead.mycollab.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.mobile.MobileUI;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class MyCollabUIProvider extends UIProvider {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(MyCollabUIProvider.class);

	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		String userAgent = event.getRequest().getHeader("user-agent")
				.toLowerCase();
		log.debug("User agent: {}", userAgent);
		if (userAgent.contains("webkit")) {
			return MobileUI.class;
		} else {
			return MyCollabApplication.class;
		}
	}

}
