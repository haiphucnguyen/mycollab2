package com.esofthead.mycollab.premium.module.project.view.standup;

import java.net.MalformedURLException;
import java.net.URL;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class StandupAdvertisementViewImpl extends AbstractPageView implements
		StandupAdvertisementView {
	private static final long serialVersionUID = 1L;

	public StandupAdvertisementViewImpl() {
		this.setWidth("100%");
		this.setHeight("512px");
		URL url = null;
		try {
			url = new URL(AppContext.getSiteUrl()
					+ "assets/ads/standup/standupAds.html");
		} catch (MalformedURLException e) {
			throw new MyCollabException(e);
		}

		BrowserFrame browser = new BrowserFrame("", new ExternalResource(url));
		browser.setWidth("100%");
		browser.setHeight("100%");
		this.addComponent(browser);
	}

}
