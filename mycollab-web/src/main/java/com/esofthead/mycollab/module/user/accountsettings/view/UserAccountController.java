package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ProfilePresenter;
import com.esofthead.mycollab.module.user.accountsettings.team.view.UserAddPresenter;
import com.esofthead.mycollab.module.user.accountsettings.team.view.UserPermissionManagementPresenter;
import com.esofthead.mycollab.module.user.accountsettings.team.view.UserPresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.ProfileScreenData;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.RoleScreenData;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.UserScreenData;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.service.RoleService;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Window;

public class UserAccountController implements IController {
	private static final long serialVersionUID = 1L;

	private AccountDashboardView container;

	public UserAccountController(AccountDashboardView container) {
		this.container = container;

		bindProfileEvents();
		bindRoleEvents();
		bindUserEvents();
	}

	private void bindProfileEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProfileEvent.GotoUploadPhoto>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProfileEvent.GotoUploadPhoto.class;
					}

					@Override
					public void handle(ProfileEvent.GotoUploadPhoto event) {
						ProfilePresenter presenter = PresenterResolver
								.getPresenter(ProfilePresenter.class);
						presenter.go(container,
								new ProfileScreenData.UploadPhoto(
										(byte[]) event.getData()));
					}
				});
	}

	private void bindUserEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<UserEvent.GotoAdd>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return UserEvent.GotoAdd.class;
					}

					@Override
					public void handle(UserEvent.GotoAdd event) {
						UserPresenter presenter = PresenterResolver
								.getPresenter(UserPresenter.class);
						presenter.go(container, new UserScreenData.Add(
								new User()));
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<UserEvent.GotoEdit>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return UserEvent.GotoEdit.class;
					}

					@Override
					public void handle(UserEvent.GotoEdit event) {
						UserAddPresenter presenter = PresenterResolver
								.getPresenter(UserAddPresenter.class);

						SimpleUser user = (SimpleUser) event.getData();
						presenter.go(container, new UserScreenData.Edit(user));
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<UserEvent.GotoRead>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return UserEvent.GotoRead.class;
					}

					@Override
					public void handle(UserEvent.GotoRead event) {
						UserPermissionManagementPresenter presenter = PresenterResolver
								.getPresenter(UserPermissionManagementPresenter.class);
						presenter.go(container, new UserScreenData.Read(
								(SimpleUser) event.getData()));
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<UserEvent.GotoList>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return UserEvent.GotoList.class;
					}

					@Override
					public void handle(UserEvent.GotoList event) {
						UserPermissionManagementPresenter presenter = PresenterResolver
								.getPresenter(UserPermissionManagementPresenter.class);

						UserSearchCriteria criteria = new UserSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(
								SearchField.AND, AppContext.getAccountId()));
						presenter.go(container, new UserScreenData.Search(
								criteria));
					}
				});
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
						UserPermissionManagementPresenter presenter = PresenterResolver
								.getPresenter(UserPermissionManagementPresenter.class);
						presenter.go(container, new RoleScreenData.Add(
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
						UserPermissionManagementPresenter presenter = PresenterResolver
								.getPresenter(UserPermissionManagementPresenter.class);

						Role role = (Role) event.getData();
						presenter.go(container, new RoleScreenData.Edit(role));
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
						UserPermissionManagementPresenter presenter = PresenterResolver
								.getPresenter(UserPermissionManagementPresenter.class);
						if (event.getData() instanceof SimpleRole) {
							presenter.go(container, new RoleScreenData.Read(
									(Role) event.getData()));
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
										new RoleScreenData.Read(role));
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
						UserPermissionManagementPresenter presenter = PresenterResolver
								.getPresenter(UserPermissionManagementPresenter.class);

						RoleSearchCriteria criteria = new RoleSearchCriteria();
						criteria.setsAccountId(new NumberSearchField(
								SearchField.AND, AppContext.getAccountId()));
						presenter.go(container, new RoleScreenData.Search(
								criteria));
					}
				});
	}
}
