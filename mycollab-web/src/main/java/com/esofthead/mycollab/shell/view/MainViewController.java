/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.module.project.view.ProjectPresenter;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;

/**
 *
 * @author haiphucnguyen
 */
public class MainViewController {

    private MainView container;

    public MainViewController(MainView view) {
        this.container = view;
        bind();

    }

    private void bind() {
        EventBus.getInstance().addListener(
                new ApplicationEventListener<ShellEvent.GotoProjectPage>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public Class<? extends ApplicationEvent> getEventType() {
                        return ShellEvent.GotoProjectPage.class;
                    }

                    @Override
                    public void handle(ShellEvent.GotoProjectPage event) {
                        ProjectPresenter prjPresenter = PresenterResolver.getPresenter(ProjectPresenter.class);
                        prjPresenter.go(container);
                    }
                });
    }
}
