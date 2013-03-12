package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ProfileReadPresenter extends AbstractPresenter<ProfileReadView> {

	private static final long serialVersionUID = 1L;

	public ProfileReadPresenter() {
		super(ProfileReadView.class);
		bind();
	}

	private void bind() {
	}

	public void saveUser(User user) {
		
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProfileContainer profileContainer = (ProfileContainer) container;
		profileContainer.removeAllComponents();
		profileContainer.addComponent(view.getWidget());
		User currentUser = AppContext.getSession();
		view.editItem(currentUser);
	}
}
