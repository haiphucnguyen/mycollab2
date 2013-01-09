/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import java.io.Serializable;

/**
 *
 * @author haiphucnguyen
 */
public class UserController implements Serializable {

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

        EventBus.getInstance().addListener(
                new ApplicationEventListener<UserEvent.GotoEdit>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return UserEvent.GotoEdit.class;
                    }

                    @Override
                    public void handle(UserEvent.GotoEdit event) {
                        UserAddPresenter presenter = PresenterResolver
                                .getPresenter(UserAddPresenter.class);

                        SimpleUser user = (SimpleUser) event.getData();
                        presenter.go(container, new ScreenData.Edit<User>(
                                user));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<UserEvent.GotoRead>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return UserEvent.GotoRead.class;
                    }

                    @Override
                    public void handle(UserEvent.GotoRead event) {
                        UserReadPresenter presenter = PresenterResolver
                                .getPresenter(UserReadPresenter.class);
                        presenter.go(container, new ScreenData.Preview<SimpleUser>(
                                (SimpleUser) event.getData()));
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<UserEvent.GotoList>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return UserEvent.GotoList.class;
                    }

                    @Override
                    public void handle(UserEvent.GotoList event) {
                        UserListPresenter presenter = PresenterResolver
                                .getPresenter(UserListPresenter.class);

                        UserSearchCriteria criteria = new UserSearchCriteria();
                        criteria.setSaccountid(new NumberSearchField(
                                SearchField.AND, AppContext.getAccountId()));
                        presenter.go(container,
                                new ScreenData.Search<UserSearchCriteria>(
                                criteria));
                    }
                });
    }
}
