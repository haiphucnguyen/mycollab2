/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Window;
import java.io.Serializable;

/**
 * 
 * @author haiphucnguyen
 */
public class RoleController implements IController {
	private static final long serialVersionUID = 1L;
	private RoleContainer container;

	public RoleController(RoleContainer container) {
		this.container = container;
		bindRoleEvents();
	}

	private void bindRoleEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<RoleEvent.GotoAdd>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return RoleEvent.GotoAdd.class;
					}

					@Override
					public void handle(RoleEvent.GotoAdd event) {
						RoleAddPresenter presenter = PresenterResolver
								.getPresenter(RoleAddPresenter.class);
						presenter.go(container, new ScreenData.Add<Role>(
								new Role()));
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<RoleEvent.GotoEdit>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return RoleEvent.GotoEdit.class;
					}

					@Override
					public void handle(RoleEvent.GotoEdit event) {
						RoleAddPresenter presenter = PresenterResolver
								.getPresenter(RoleAddPresenter.class);

						Role role = (Role) event.getData();
						presenter
								.go(container, new ScreenData.Edit<Role>(role));
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<RoleEvent.GotoRead>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return RoleEvent.GotoRead.class;
					}

					@Override
					public void handle(RoleEvent.GotoRead event) {
						RoleReadPresenter presenter = PresenterResolver
								.getPresenter(RoleReadPresenter.class);
						if (event.getData() instanceof SimpleRole) {
							presenter.go(
									container,
									new ScreenData.Preview<Role>((Role) event
											.getData()));
						} else if (event.getData() instanceof Integer) {
							RoleService roleService = AppContext
									.getSpringBean(RoleService.class);
							SimpleRole role = roleService
									.findRoleById((Integer) event.getData());
							if (role == null) {
								AppContext
										.getApplication()
										.getMainWindow()
										.showNotification(
												"Information",
												"The record is not existed",
												Window.Notification.TYPE_HUMANIZED_MESSAGE);
							} else {
								presenter.go(container,
										new ScreenData.Preview<Role>(role));
							}
						}
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<RoleEvent.GotoList>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return RoleEvent.GotoList.class;
					}

					@Override
					public void handle(RoleEvent.GotoList event) {
						RoleListPresenter presenter = PresenterResolver
								.getPresenter(RoleListPresenter.class);

						RoleSearchCriteria criteria = new RoleSearchCriteria();
						criteria.setsAccountId(new NumberSearchField(
								SearchField.AND, AppContext.getAccountId()));
						presenter.go(container,
								new ScreenData.Search<RoleSearchCriteria>(
										criteria));
					}
				});
	}
}
