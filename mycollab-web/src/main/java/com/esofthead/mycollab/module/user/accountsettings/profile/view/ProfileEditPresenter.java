package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.module.crm.events.AccountEvent;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ProfileEditPresenter extends
		AbstractPresenter<ProfileEditView> {

	private static final long serialVersionUID = 1L;

	public ProfileEditPresenter() {
		super(ProfileEditView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(new EditFormHandler<User>() {
			@Override
			public void onSave(final User user) {
				saveUser(user);
				EventBus.getInstance().fireEvent(
						new AccountEvent.GotoList(this, null));
			}

			@Override
			public void onCancel() {
				EventBus.getInstance().fireEvent(
						new AccountEvent.GotoList(this, null));
			}

			@Override
			public void onSaveAndNew(User bean) {
			}
		});
	}

	public void saveUser(User user) {

	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		User currentUser = AppContext.getSession();
		view.editItem(currentUser);
	}
}
