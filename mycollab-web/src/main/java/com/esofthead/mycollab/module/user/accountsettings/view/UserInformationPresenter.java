package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class UserInformationPresenter extends AbstractPresenter<UserInformationView> {

    private static final long serialVersionUID = 1L;

    public UserInformationPresenter() {
        super(UserInformationView.class);
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
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        // TODO Auto-generated method stub
    }
}
