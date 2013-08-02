/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.module.crm.view.CrmModulePresenter;
import com.esofthead.mycollab.module.crm.view.CrmModuleScreenData;
import com.esofthead.mycollab.module.file.view.FileModulePresenter;
import com.esofthead.mycollab.module.file.view.FileModuleScreenData;
import com.esofthead.mycollab.module.project.view.ProjectModulePresenter;
import com.esofthead.mycollab.module.project.view.ProjectModuleScreenData;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModulePresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModuleScreenData;
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
				new ApplicationEventListener<ShellEvent.GotoCrmModule>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoCrmModule.class;
					}

					@Override
					public void handle(ShellEvent.GotoCrmModule event) {
						CrmModulePresenter crmModulePresenter = PresenterResolver
								.getPresenter(CrmModulePresenter.class);
						CrmModuleScreenData.GotoModule screenData = new CrmModuleScreenData.GotoModule(
								(String[]) event.getData());
						crmModulePresenter.go(container, screenData);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoProjectModule>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoProjectModule.class;
					}

					@Override
					public void handle(ShellEvent.GotoProjectModule event) {
						ProjectModulePresenter prjPresenter = PresenterResolver
								.getPresenter(ProjectModulePresenter.class);
						ProjectModuleScreenData.GotoModule screenData = new ProjectModuleScreenData.GotoModule(
								(String[]) event.getData());
						prjPresenter.go(container, screenData);
					}
				});

		EventBus.getInstance()
				.addListener(
						new ApplicationEventListener<ShellEvent.GotoUserAccountModule>() {
							private static final long serialVersionUID = 1L;

							@Override
							public Class<? extends ApplicationEvent> getEventType() {
								return ShellEvent.GotoUserAccountModule.class;
							}

							@Override
							public void handle(
									ShellEvent.GotoUserAccountModule event) {
								AccountModulePresenter presenter = PresenterResolver
										.getPresenter(AccountModulePresenter.class);
								presenter.go(container,
										new AccountModuleScreenData.GotoModule(
												(String[]) event.getData()));
							}
						});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ShellEvent.GotoFileModule>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ShellEvent.GotoFileModule.class;
					}

					@Override
					public void handle(ShellEvent.GotoFileModule event) {
						FileModulePresenter fileModulePresenter = PresenterResolver
								.getPresenter(FileModulePresenter.class);
						FileModuleScreenData.GotoModule screenData = new FileModuleScreenData.GotoModule(
								(String[]) event.getData());
						fileModulePresenter.go(container, screenData);
					}
				});
	}
}
