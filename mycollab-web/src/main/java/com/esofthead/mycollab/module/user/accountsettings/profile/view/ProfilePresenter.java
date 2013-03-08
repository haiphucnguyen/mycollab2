package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountDashboardView;
import com.esofthead.mycollab.module.user.accountsettings.view.ViewNameConstants;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.ProfileScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ProfilePresenter extends AbstractPresenter<ProfileContainer> {
	private static final long serialVersionUID = 1L;

	public ProfilePresenter() {
		super(ProfileContainer.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		AccountDashboardView accountContainer = (AccountDashboardView) container;

		accountContainer.gotoSubView(ViewNameConstants.USER_INFORMATION);

		AbstractPresenter<?> presenter = null;
		if (data == null) {
			presenter = PresenterResolver
					.getPresenter(ProfileReadPresenter.class);
		} else if (data instanceof ProfileScreenData.UploadPhoto) {
			presenter = PresenterResolver
					.getPresenter(ProfilePhotoUploadPresenter.class);
		}

		presenter.go(view.getWidget(), data);
	}

}
