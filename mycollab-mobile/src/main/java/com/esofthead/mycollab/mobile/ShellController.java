package com.esofthead.mycollab.mobile;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.module.user.event.UserEvent;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * Created by nghi on 1/2/14.
 */
public class ShellController implements IController {

    private final NavigationManager mainNav;

    public ShellController(NavigationManager mainNavigation) {
        this.mainNav = mainNavigation;

        bind();
    }

    private void bind() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<UserEvent.PlainLogin>() {
                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return UserEvent.PlainLogin.class;
                    }

                    @Override
                    public void handle(UserEvent.PlainLogin event) {

                    }
                });
    }
}
