package com.esofthead.mycollab.shell;

import com.esofthead.mycollab.module.user.view.ForgotPasswordPresenter;
import com.esofthead.mycollab.module.user.view.LoginPresenter;
import com.esofthead.mycollab.module.user.view.LoginView;
import com.esofthead.mycollab.module.user.view.SignupPresenter;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.shell.events.ShellEvent.GotoMainPage;
import com.esofthead.mycollab.shell.events.ShellEvent.GotoSignupPage;
import com.esofthead.mycollab.shell.events.ShellEvent.LogOut;
import com.esofthead.mycollab.shell.view.MainView;
import com.esofthead.mycollab.shell.view.MainViewPresenter;
import com.esofthead.mycollab.shell.view.MainWindowContainer;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Window;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.browsercookies.BrowserCookies;

public class ShellController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static Logger log = LoggerFactory.getLogger(ShellController.class);
    private final MainWindowContainer container;

    public ShellController(MainWindowContainer container) {
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

                        if (mainView.getParent() == null || mainView.getParent() == container) {
                            ((Window) container).setContent(mainView);
                        } else {
                            log.debug("Do nothing. The main view parent is " + mainView.getParent() + " --- " + container);
                        }
                        
                        mainViewPresenter.go(container, null);
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
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
                        Date expiryDate = cal.getTime();
                        BrowserCookies cookies = new BrowserCookies();
                        LoginPresenter presenter = PresenterResolver
                                .getPresenter(LoginPresenter.class);
                        LoginView loginView = presenter.getView();
                        loginView.addComponent(cookies);
                        cookies.setCookie("loginInfo", "", expiryDate);
                        
                        AppContext.clearSession();

                        ((MainWindowContainer) container).setDefaultView(false);
                    }
                });
        
        EventBus.getInstance().addListener(new ApplicationEventListener<ShellEvent.GotoSignupPage>() {

            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return ShellEvent.GotoSignupPage.class;
            }

            @Override
            public void handle(GotoSignupPage event) {
                SignupPresenter presenter = PresenterResolver.getPresenter(SignupPresenter.class);
                presenter.go(container, null);
            }
            
        });
        
        EventBus.getInstance().addListener(new ApplicationEventListener<ShellEvent.GotoForgotPasswordPage>() {

            @Override
            public Class<? extends ApplicationEvent> getEventType() {
                return ShellEvent.GotoForgotPasswordPage.class;
            }

            @Override
            public void handle(ShellEvent.GotoForgotPasswordPage event) {
                ForgotPasswordPresenter presenter = PresenterResolver.getPresenter(ForgotPasswordPresenter.class);
                presenter.go(container, null);
            }
            
        });
    }
}
