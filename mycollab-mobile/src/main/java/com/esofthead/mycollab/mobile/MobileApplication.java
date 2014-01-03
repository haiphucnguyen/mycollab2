package com.esofthead.mycollab.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.mobile.module.user.view.LoginViewImpl;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.addon.touchkit.ui.NavigationManager;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
@Theme("mycollab-mobile")
@Widgetset("com.esofthead.mycollab.widgetset.MyCollabMobileWidgetSet")
public class MobileApplication extends UI {
	private static final long serialVersionUID = 1L;

	private static final String CURRENT_APP = "mobileApp";

	private static Logger log = LoggerFactory
			.getLogger(MobileApplication.class);

	/**
	 * Context of current logged in user
	 */
	private AppContext currentContext;

	private String initialSubDomain = "1";
	private String initialUrl = "";

	public static MobileApplication getInstance() {
		return (MobileApplication) VaadinSession.getCurrent().getAttribute(
				CURRENT_APP);
	}

	@Override
	protected void init(VaadinRequest request) {
		log.debug("Init mycollab mobile application {}", this.toString());

		initialUrl = this.getPage().getUriFragment();
		VaadinSession.getCurrent().setAttribute(CURRENT_APP, this);
		currentContext = new AppContext();
		postSetupApp(request);
		try {
			currentContext.initDomain(initialSubDomain);
		} catch (Exception e) {
			// TODO: show content notice user there is no existing domain
			return;
		}

		NavigationManager manager = new NavigationManager(new LoginViewImpl());
		setContent(manager);

	}

	private void postSetupApp(VaadinRequest request) {
		VaadinServletRequest servletRequest = (VaadinServletRequest) request;
		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			initialSubDomain = servletRequest.getServerName().split("\\.")[0];
		} else {
			initialSubDomain = servletRequest.getServerName();
		}
	}

	public AppContext getSessionData() {
		return currentContext;
	}

	public String getInitialUrl() {
		return initialUrl;
	}

	@Override
	public void close() {
		super.close();
		log.debug("Application is closed. Clean all resources");
		AppContext.clearSession();
		currentContext = null;
		VaadinSession.getCurrent().close();
	}

}
