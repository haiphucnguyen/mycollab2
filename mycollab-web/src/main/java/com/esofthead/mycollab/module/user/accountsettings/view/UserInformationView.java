package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface UserInformationView extends View {

	HasEditFormHandlers<User> getEditFormHandlers();

}
