/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;

/**
 *
 * @author haiphucnguyen
 */
public class UserController {
    private UserContainer container;
    
    public UserController(UserContainer container) {
        this.container = container;
        bindUserEvents();
    }
    
    private void bindUserEvents() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<UserEvent.GotoAdd>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return UserEvent.GotoAdd.class;
                    }

                    @Override
                    public void handle(UserEvent.GotoAdd event) {
                        UserAddPresenter presenter = PresenterResolver
                                .getPresenter(UserAddPresenter.class);
                        presenter.go(container, new ScreenData.Add<User>(
                                new User()));
                    }
                });
    }
}
