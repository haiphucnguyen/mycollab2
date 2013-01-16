package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.view.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.shell.ShellController;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;
import org.vaadin.browsercookies.BrowserCookies;

public class MainWindowContainer extends Window implements View {

    private static final long serialVersionUID = 1L;
    private final ShellController controller;

    public MainWindowContainer() {
        this.setCaption("MyCollab");

        controller = new ShellController(this);

        setDefaultView();
    }
    
    public final void setDefaultView() {
        final LoginPresenter presenter = PresenterResolver
                .getPresenter(LoginPresenter.class);
        LoginView loginView = presenter.getView();

        BrowserCookies cookies = new BrowserCookies();
        loginView.addComponent(cookies);
        cookies.addListener(new BrowserCookies.UpdateListener() {
            @Override
            public void cookiesUpdated(BrowserCookies bc) {
                for (String name : bc.getCookieNames()) {
                    if (name.equals("loginInfo")) {
                        String loginInfo = bc.getCookie(name);
                        if (loginInfo != null) {
                            String[] loginParams = loginInfo.split("\\$");
                            if (loginParams.length == 2) {
                                try {
                                    presenter.doLogin(loginParams[0],
                                            loginParams[1]);
                                } catch (MyCollabException e) {
                                    throw e;
                                }
                            }
                        }
                    }
                }

            }
        });
        this.setContent(loginView.getWidget());
    }

    @Override
    public ComponentContainer getWidget() {
        return this;
    }

    @Override
    public void addViewListener(ApplicationEventListener listener) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
