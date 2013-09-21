package com.esofthead.mycollab.module.user.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.UserPreference;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.events.UserEvent.PlainLogin;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.module.user.service.UserPreferenceService;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
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
				doLogin(data[0], data[1], Boolean.valueOf(data[2]));
			}
		});
	}

	public void doLogin(String username, String password,
			boolean isRemmeberPassword) {
		UserService userService = ApplicationContextUtil
				.getSpringBean(UserService.class);
		SimpleUser user = userService.authentication(username, password,
				AppContext.getSubDomain(), false);

		if (isRemmeberPassword) {
			((MainWindowContainer) AppContext.getApplication().getMainWindow())
					.rememberPassword(username, password);
		}

		BillingAccountService billingAccountService = ApplicationContextUtil
				.getSpringBean(BillingAccountService.class);

		SimpleBillingAccount billingAccount = billingAccountService
				.getBillingAccountById(AppContext.getAccountId());

		log.debug("Get billing account successfully: "
				+ BeanUtility.printBeanObj(billingAccount));

		UserPreferenceService preferenceService = ApplicationContextUtil
				.getSpringBean(UserPreferenceService.class);
		UserPreference pref = preferenceService.getPreferenceOfUser(username,
				AppContext.getAccountId());

		log.debug("Login to system successfully. Save user and preference "
				+ pref + " to session");

		AppContext.getInstance().setSession(user, pref, billingAccount);
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

		AppContext.addFragment("user/login", "Login Page");
	}
}
