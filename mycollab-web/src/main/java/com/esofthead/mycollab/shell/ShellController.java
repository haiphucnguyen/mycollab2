package com.esofthead.mycollab.shell;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.vaadin.browsercookies.BrowserCookies;

import com.esofthead.mycollab.module.project.view.ProjectMainContainer;
import com.esofthead.mycollab.module.user.presenter.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.shell.events.ShellEvent.GotoMainPage;
import com.esofthead.mycollab.shell.events.ShellEvent.GotoProjectPage;
import com.esofthead.mycollab.shell.events.ShellEvent.LogOut;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class ShellController implements Serializable {
	private static final long serialVersionUID = 1L;
	private final ComponentContainer container;

	private MainViewImpl mainView;

	public ShellController(ComponentContainer container) {
		this.container = container;

		bind();
	}

	private void bind() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoMainPage>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoMainPage.class;
					}

					@Override
					public void handle(GotoMainPage event) {
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
						Date expiryDate = cal.getTime();
						BrowserCookies cookies = new BrowserCookies();
						mainView = new MainViewImpl();
						mainView.addComponent(cookies);
						cookies.setCookie("loginInfo", AppContext.getUsername()
								+ "$" + AppContext.getSession().getPassword(),
								expiryDate);

						((Window) container).setContent(mainView);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.LogOut>() {

					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.LogOut.class;
					}

					@Override
					public void handle(LogOut event) {
						System.out.println("Logged out");
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
						Date expiryDate = cal.getTime();
						BrowserCookies cookies = new BrowserCookies();
						LoginPresenter presenter = PresenterResolver
								.getPresenter(LoginPresenter.class);
						LoginView loginView = presenter.getView();
						loginView.addComponent(cookies);
						cookies.setCookie("loginInfo", "", expiryDate);

						((Window) container).setContent(loginView.getWidget());
					}

				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoProjectPage>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoProjectPage.class;
					}

					@Override
					public void handle(GotoProjectPage event) {
						ProjectMainContainer projectDashboard = ViewManager
								.getView(ProjectMainContainer.class);
						mainView.addView(projectDashboard);

					}
				});
	}
}
