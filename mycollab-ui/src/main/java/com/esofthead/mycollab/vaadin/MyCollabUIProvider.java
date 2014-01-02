package com.esofthead.mycollab.vaadin;

import com.esofthead.mycollab.core.MyCollabException;
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

	@Override
	public Class<? extends UI> getUIClass(UIClassSelectionEvent event) {
		String userAgent = event.getRequest().getHeader("user-agent")
				.toLowerCase();

		String uiClass = "";

		if (userAgent.contains("mobile")) {
			uiClass = "com.esofthead.mycollab.mobile.MobileApplication";
		} else {
			uiClass = "com.esofthead.mycollab.web.DesktopApplication";
		}

		try {
			return (Class<? extends UI>) Class.forName(uiClass);
		} catch (ClassNotFoundException e) {
			throw new MyCollabException(e);
		}
	}

}
