package com.esofthead.mycollab.shell;

import com.esofthead.mycollab.module.user.presenter.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.shell.events.ShellEvent.GotoMainPage;
import com.esofthead.mycollab.shell.events.ShellEvent.LogOut;
import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.shell.view.MainViewPresenter;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.vaadin.browsercookies.BrowserCookies;

public class ShellController implements Serializable {

    private static final long serialVersionUID = 1L;
    private final ComponentContainer container;

    public ShellController(ComponentContainer container) {
        this.container = container;

        bind();
    }

    private void bind() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<ShellEvent.GotoMainPage>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ShellEvent.GotoMainPage.class;
                    }

                    @Override
                    public void handle(GotoMainPage event) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
                        Date expiryDate = cal.getTime();
                        BrowserCookies cookies = new BrowserCookies();

                        MainViewPresenter mainViewPresenter = PresenterResolver.getPresenter(MainViewPresenter.class);
                        MainView mainView = mainViewPresenter.getView();
                        mainView.addComponent(cookies);
                        cookies.setCookie("loginInfo", AppContext.getUsername()
                                + "$" + AppContext.getSession().getPassword(),
                                expiryDate);

                        ((Window) container).setContent(mainView);
                        mainViewPresenter.go(container);
                    }
                });

        EventBus.getInstance().addListener(
                new ApplicationEventListener<ShellEvent.LogOut>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ShellEvent.LogOut.class;
                    }

                    @Override
                    public void handle(LogOut event) {
                        System.out.println("Logged out");
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
                        Date expiryDate = cal.getTime();
                        BrowserCookies cookies = new BrowserCookies();
                        LoginPresenter presenter = PresenterResolver
                                .getPresenter(LoginPresenter.class);
                        LoginView loginView = presenter.getView();
                        loginView.addComponent(cookies);
                        cookies.setCookie("loginInfo", "", expiryDate);

                        ((Window) container).setContent(loginView.getWidget());
                    }
                });
    }
}
