/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectUserPresenter extends AbstractPresenter<ProjectUserContainer> {

    public ProjectUserPresenter() {
        super(ProjectUserContainer.class);
    }
    
    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        
    }
    
}
