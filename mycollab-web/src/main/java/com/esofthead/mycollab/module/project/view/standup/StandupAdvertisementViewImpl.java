package com.esofthead.mycollab.module.project.view.standup;

import java.net.MalformedURLException;
import java.net.URL;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Embedded;

@ViewComponent
public class StandupAdvertisementViewImpl extends AbstractView implements
		StandupAdvertisementView {
	private static final long serialVersionUID = 1L;
	
	public StandupAdvertisementViewImpl() {
		this.setWidth("100%");
		this.setHeight("512px");
		URL url = null;
		try {
			url = new URL(
					ApplicationProperties
							.getProperty(ApplicationProperties.APP_URL)
							+ "assets/ads/standup/standupAds.html");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Embedded browser = new Embedded("", new ExternalResource(url));
		browser.setWidth("100%");
		browser.setHeight("100%");
		browser.setType(Embedded.TYPE_BROWSER);
		this.addComponent(browser);
	}

}
