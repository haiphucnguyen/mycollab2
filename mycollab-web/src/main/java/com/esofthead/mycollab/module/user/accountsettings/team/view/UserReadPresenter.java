/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class UserReadPresenter extends AbstractPresenter<UserReadView> {
	private static final long serialVersionUID = 1L;

	public UserReadPresenter() {
        super(UserReadView.class);

        bind();
    }

    private void bind() {
        view.getPreviewFormHandlers().addFormHandler(
                new DefaultPreviewFormHandler<User>() {
                    @Override
                    public void onEdit(User data) {
                        EventBus.getInstance().fireEvent(
                                new UserEvent.GotoEdit(this, data));
                    }

                    @Override
                    public void onDelete(User data) {
                        UserService taskService = AppContext
                                .getSpringBean(UserService.class);
                        taskService.removeWithSession(data.getUsername(),
                                AppContext.getUsername());
                        EventBus.getInstance().fireEvent(
                                new UserEvent.GotoList(this, null));
                    }

                    @Override
                    public void onClone(User data) {
                        User cloneData = (User) data.copy();
                        cloneData.setUsername(null);
                        EventBus.getInstance().fireEvent(
                                new UserEvent.GotoAdd(this, cloneData));
                    }

                    @Override
                    public void onCancel() {
                        EventBus.getInstance().fireEvent(
                                new UserEvent.GotoList(this, null));
                    }
                });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        UserContainer userContainer = (UserContainer) container;
        userContainer.removeAllComponents();
        userContainer.addComponent(view.getWidget());

        if (data.getParams() instanceof SimpleUser) {
            view.previewItem((SimpleUser) data.getParams());
        }
    }
}
