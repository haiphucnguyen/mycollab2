/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.module.crm.view.CrmModulePresenter;
import com.esofthead.mycollab.module.project.view.ProjectModulePresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModulePresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;

/**
 * 
 * @author haiphucnguyen
 */
public class MainViewController implements IController {
	private static final long serialVersionUID = 1L;
	private MainView container;

	public MainViewController(MainView view) {
		this.container = view;
		bind();

	}

	private void bind() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoCrmPage>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoCrmPage.class;
					}

					@Override
					public void handle(ShellEvent.GotoCrmPage event) {
						CrmModulePresenter crmModulePresenter = PresenterResolver
								.getPresenter(CrmModulePresenter.class);
						crmModulePresenter.go(container, null);
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
					public void handle(ShellEvent.GotoProjectPage event) {
						ProjectModulePresenter prjPresenter = PresenterResolver
								.getPresenter(ProjectModulePresenter.class);
						prjPresenter.go(container, null);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoAccountPage>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoAccountPage.class;
					}

					@Override
					public void handle(ShellEvent.GotoAccountPage event) {
						AccountModulePresenter presenter = PresenterResolver
								.getPresenter(AccountModulePresenter.class);
						presenter.go(container, null);
					}
				});
	}
}
