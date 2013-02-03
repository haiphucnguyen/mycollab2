/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectMemberPresenter extends AbstractPresenter<ProjectMemberContainer> {
    public ProjectMemberPresenter() {
        super(ProjectMemberContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        view.removeAllComponents();
        
        AbstractPresenter presenter = null;
        
        presenter = PresenterResolver.getPresenter(ProjectMemberListPresenter.class);
        presenter.go(view, data);
    }
}
