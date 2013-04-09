package com.esofthead.mycollab.shell.view;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.browsercookies.BrowserCookies;

import com.esofthead.mycollab.module.user.PasswordEncryptHelper;
import com.esofthead.mycollab.module.user.view.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.shell.ShellController;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabApplication;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UriFragmentUtility;
import com.vaadin.ui.UriFragmentUtility.FragmentChangedEvent;
import com.vaadin.ui.Window;

public class MainWindowContainer extends Window implements View {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(MainWindowContainer.class);

	private final Content content;

	private final UriFragmentUtility urifu;

	public MainWindowContainer() {
		urifu = new UriFragmentUtility();

		urifu.addListener(new UriFragmentUtility.FragmentChangedListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fragmentChanged(FragmentChangedEvent source) {
				log.debug("Change fragement: "
						+ source.getUriFragmentUtility().getFragment());
				try {
					String initialUrl = source.getUriFragmentUtility()
							.getFragment();
					if (AppContext.getSession() != null) {
						FragmentNavigator.navigateByFragement(initialUrl);
					} else {
						((MyCollabApplication) AppContext.getApplication())
								.setInitialUrl(initialUrl);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});

		this.setCaption("MyCollab");
		ControllerRegistry.getInstance().addController(
				new ShellController(this));

		this.setImmediate(true);
		this.addListener(new Window.ResizeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void windowResized(ResizeEvent e) {
				log.debug("Application size is changed. New value is: "
						+ MainWindowContainer.this.getBrowserWindowWidth());
				ScreenSize.setWidth(MainWindowContainer.this
						.getBrowserWindowWidth());
			}
		});
		content = new Content();
		this.setContent(content);
		content.setSizeFull();
		content.addComponent(urifu);

		log.debug("Initial fragement: " + urifu.getFragment());

		setDefaultView(true);
	}

	public void setMainContent(ComponentContainer newContent) {
		for (int i = content.getComponentCount() - 1; i >= 0; i--) {
			Component component = content.getComponent(i);
			if (!(component instanceof UriFragmentUtility)
					&& !(component instanceof BrowserCookies)) {
				content.removeComponent(component);
			}
		}
		content.addComponent(newContent);
	}

	private BrowserCookies getCookieComponent() {
		for (int i = 0; i < content.getComponentCount(); i++) {
			Component component = content.getComponent(i);
			if (component instanceof BrowserCookies) {
				return ((BrowserCookies) component);
			}
		}

		BrowserCookies cookies = new BrowserCookies();
		content.addComponent(cookies);
		return cookies;
	}

	public void unsetRememberPassword() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 3);
		Date expiryDate = cal.getTime();

		BrowserCookies cookies = getCookieComponent();
		cookies.setCookie("loginInfo", "", expiryDate);
	}

	public void rememberPassword(String username, String password) {
		// Remember password
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 7);
		Date expiryDate = cal.getTime();
		BrowserCookies cookies = getCookieComponent();

		cookies.setCookie("loginInfo",
				username + "$" + PasswordEncryptHelper.encyptText(password),
				expiryDate);
	}

	public void addFragement(String fragement) {
		log.debug("Add fragement: " + fragement);
		urifu.setFragment(fragement, false);
	}

	private final void setDefaultView(final boolean isAutoLogin) {
		final LoginPresenter presenter = PresenterResolver
				.getPresenter(LoginPresenter.class);
		LoginView loginView = presenter.getView();

		BrowserCookies cookies = getCookieComponent();
		cookies.addListener(new BrowserCookies.UpdateListener() {
			@Override
			public void cookiesUpdated(BrowserCookies bc) {
				if (isAutoLogin) {
					for (String name : bc.getCookieNames()) {
						if (name.equals("loginInfo")) {
							String loginInfo = bc.getCookie(name);
							if (loginInfo != null) {
								String[] loginParams = loginInfo.split("\\$");
								if (loginParams.length == 2) {
									presenter
											.doLogin(
													loginParams[0],
													PasswordEncryptHelper
															.decryptText(loginParams[1]),
													false);
								}
							}
						}
					}
				}

			}
		});

		this.setStyleName("loginView");
		this.setMainContent(loginView.getWidget());
	}

	@Override
	public ComponentContainer getWidget() {
		return this;
	}

	@Override
	public void addViewListener(
			@SuppressWarnings("rawtypes") ApplicationEventListener listener) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private static class Content extends CssLayout {
		private static final long serialVersionUID = 1L;

	}
}
