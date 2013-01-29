package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.common.domain.UserPreference;
import com.esofthead.mycollab.common.service.UserPreferenceService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.module.user.events.UserEvent.PlainLogin;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.gwt.server.AbstractWebApplicationContext;
import com.vaadin.ui.ComponentContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPresenter extends AbstractPresenter<LoginView> {

    private static final long serialVersionUID = 1L;
    
    private static Logger log = LoggerFactory.getLogger(LoginPresenter.class);

    public LoginPresenter() {
        super(LoginView.class);
        bind();
    }

    private void bind() {
        view.addViewListener(new ApplicationEventListener<UserEvent.PlainLogin>() {
            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return UserEvent.PlainLogin.class;
            }

            @Override
            public void handle(PlainLogin event) {
                String[] data = (String[]) event.getData();
                doLogin(data[0], data[1]);
            }
        });
    }

    public void doLogin(String username, String password) {
        try {
            UserService userService = AppContext
                    .getSpringBean(UserService.class);
            SimpleUser user = userService.authentication(
                    username, password);
            UserPreferenceService preferenceService = AppContext.getSpringBean(UserPreferenceService.class);
            UserPreference pref = preferenceService.getPreferenceOfUser(username);
            
            log.debug("Login to system successfully. Save user and preference " + pref + " to session");
            
            AppContext.setSession(user, pref);
            EventBus.getInstance().fireEvent(
                    new ShellEvent.GotoMainPage(this, null));
            
            //Tracking user service
            AbstractWebApplicationContext context = (AbstractWebApplicationContext) AppContext.getApplication()
                .getContext();
            
            System.out.println("User agent: " + context.getBrowser().getBrowserApplication() + "  " + context.getBrowser().getAddress());
        } catch (MyCollabException e) {
            throw e;
        }
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        container.removeAllComponents();
        container.addComponent(view.getWidget());
    }
}
