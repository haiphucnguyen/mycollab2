package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.module.user.presenter.LoginPresenter;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface LoginView extends View {
	void setPresenter(LoginPresenter presenter);
}
