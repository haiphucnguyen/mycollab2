/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class UserGroupViewImpl extends AbstractView implements UserGroupView {

    private ProjectUserPresenter userPresenter;
    private ProjectRolePresenter rolePresenter;
    private DetachedTabs myProjectTab;
    private CssLayout mySpaceArea = new CssLayout();

    public UserGroupViewImpl() {

        myProjectTab = new DetachedTabs.Horizontal(mySpaceArea);
        myProjectTab.setSizeFull();

        HorizontalLayout menu = new HorizontalLayout();
        menu.setHeight("40px");
        menu.addComponent(myProjectTab);

        this.addComponent(menu);
        mySpaceArea.setWidth("100%");
        mySpaceArea.setHeight(null);
        this.addComponent(mySpaceArea);
        this.setExpandRatio(mySpaceArea, 1.0f);
        this.setWidth("100%");
        buildComponents();
    }
    
    private void buildComponents() {
        userPresenter = PresenterResolver.getPresenter(ProjectUserPresenter.class);
        myProjectTab.addTab(userPresenter.getView(), "Users");
        
        rolePresenter = PresenterResolver.getPresenter(ProjectRolePresenter.class);
        myProjectTab.addTab(rolePresenter.getView(), "Roles");
    }
}
