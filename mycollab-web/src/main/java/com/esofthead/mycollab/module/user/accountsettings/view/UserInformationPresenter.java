package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.Presenter;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class UserInformationPresenter implements Presenter {

	private final UserInformationView view;

	public UserInformationPresenter(UserInformationView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(new EditFormHandler<User>() {

			@Override
			public void onSave(final User user) {
				saveUser(user);
				// EventBus.getInstance().fireEvent(
				// new AccountEvent.GotoList(this, null));
			}

			@Override
			public void onCancel() {
				// EventBus.getInstance().fireEvent(
				// new AccountEvent.GotoList(this, null));
			}

			@Override
			public void onSaveAndNew(User bean) {
			}
		});
	}

	public void saveUser(User user) {
		UserService userService = AppContext.getSpringBean(UserService.class);
		userService.update(user);
	}

	@Override
	public void go(ComponentContainer container) {

	}

}
