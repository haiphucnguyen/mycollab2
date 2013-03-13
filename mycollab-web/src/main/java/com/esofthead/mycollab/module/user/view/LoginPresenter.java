package com.esofthead.mycollab.module.user.view;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.browsercookies.BrowserCookies;

import com.esofthead.mycollab.common.domain.UserPreference;
import com.esofthead.mycollab.common.service.UserPreferenceService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.events.UserEvent.PlainLogin;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.gwt.server.AbstractWebApplicationContext;
import com.vaadin.ui.ComponentContainer;

public class LoginPresenter extends AbstractPresenter<LoginView> {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(LoginPresenter.class);

	public LoginPresenter() {
		super(LoginView.class);
		bind();
	}

	private void bind() {
		view.addViewListener(new ApplicationEventListener<UserEvent.PlainLogin>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return UserEvent.PlainLogin.class;
			}

			@Override
			public void handle(PlainLogin event) {
				String[] data = (String[]) event.getData();
				doLogin(data[0], data[1], false);
			}
		});
	}

	public void doLogin(String username, String password,
			boolean isPasswordEncrypt) {
		UserService userService = AppContext.getSpringBean(UserService.class);
		SimpleUser user = userService.authentication(username, password,
				isPasswordEncrypt);

		// Remember password
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		Date expiryDate = cal.getTime();
		BrowserCookies cookies = new BrowserCookies();
		view.addComponent(cookies);
		cookies.setCookie("loginInfo", username + "$"
				+ password, expiryDate);

		UserPreferenceService preferenceService = AppContext
				.getSpringBean(UserPreferenceService.class);
		UserPreference pref = preferenceService.getPreferenceOfUser(username);

		log.debug("Login to system successfully. Save user and preference "
				+ pref + " to session");

		AppContext.setSession(user, pref);
		EventBus.getInstance().fireEvent(
				new ShellEvent.GotoMainPage(this, null));

		// Tracking user service
		AbstractWebApplicationContext context = (AbstractWebApplicationContext) AppContext
				.getApplication().getContext();

		log.debug("User agent: " + context.getBrowser().getBrowserApplication()
				+ "  " + context.getBrowser().getAddress());
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		container.removeAllComponents();
		container.addComponent(view.getWidget());

		AppContext.addFragment("user/login");
	}
}
