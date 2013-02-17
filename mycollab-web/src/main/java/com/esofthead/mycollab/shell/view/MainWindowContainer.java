package com.esofthead.mycollab.shell.view;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.browsercookies.BrowserCookies;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.view.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.shell.ShellController;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.vaadin.terminal.DownloadStream;
import com.vaadin.terminal.URIHandler;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UriFragmentUtility;
import com.vaadin.ui.UriFragmentUtility.FragmentChangedEvent;
import com.vaadin.ui.Window;

public class MainWindowContainer extends Window implements View {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory
			.getLogger(MainWindowContainer.class);

	private UriFragmentUtility urifu;
	private FragmentNavigator fragmentNavigator;

	private final ShellController controller;

	public MainWindowContainer() {
		urifu = new UriFragmentUtility();
		fragmentNavigator = new FragmentNavigator();

		urifu.addListener(new UriFragmentUtility.FragmentChangedListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void fragmentChanged(FragmentChangedEvent source) {
				log.debug("Change fragement: "
						+ source.getUriFragmentUtility().getFragment());
				fragmentNavigator.navigateByFragement(source
						.getUriFragmentUtility().getFragment());
			}
		});
		this.setCaption("MyCollab");
		controller = new ShellController(this);

		this.addURIHandler(new URIHandler() {

			@Override
			public DownloadStream handleURI(URL context, String relativeUri) {
				log.debug("URI: " + relativeUri + "---" + context.getPath());
				return null;
			}
		});
		setDefaultView(true);
	}

	@Override
	public void setContent(ComponentContainer newContent) {
		super.setContent(newContent);
		log.debug(newContent + "   " + urifu);

		if (newContent != null) {
			newContent.addComponent(urifu);
		}
	}

	public void addFragement(String fragement) {
		log.debug("Add fragement: " + fragement);
		urifu.setFragment(fragement);
	}

	public final void setDefaultView(final boolean isAutoLogin) {
		final LoginPresenter presenter = PresenterResolver
				.getPresenter(LoginPresenter.class);
		LoginView loginView = presenter.getView();

		BrowserCookies cookies = new BrowserCookies();
		loginView.addComponent(cookies);
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
									try {
										presenter.doLogin(loginParams[0],
												loginParams[1]);
									} catch (MyCollabException e) {
										throw e;
									}
								}
							}
						}
					}
				}

			}
		});

		this.setStyleName("loginView");
		this.setContent(loginView.getWidget());
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
}
