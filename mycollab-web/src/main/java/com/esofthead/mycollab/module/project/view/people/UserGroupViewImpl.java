/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author haiphucnguyen
 */
@ViewComponent
public class UserGroupViewImpl extends AbstractView implements UserGroupView {

    private ProjectUserPresenter userPresenter;
    private ProjectRolePresenter rolePresenter;
    private final DetachedTabs myProjectTab;
    private final CssLayout mySpaceArea = new CssLayout();

    public UserGroupViewImpl() {

        myProjectTab = new DetachedTabs.Horizontal(mySpaceArea);
        myProjectTab.setSizeFull();
        myProjectTab.setHeight(null);

        CssLayout menu = new CssLayout();
        menu.setHeight("40px");
        menu.setStyleName("sidebar-menu");
        menu.addComponent(myProjectTab);

        this.addComponent(menu);
        mySpaceArea.setStyleName("projectTabContent");
        mySpaceArea.setWidth("100%");
        mySpaceArea.setHeight(null);
        this.addComponent(mySpaceArea);
        this.setExpandRatio(mySpaceArea, 1.0f);
        this.setWidth("100%");
        buildComponents();
    }
    
    private void buildComponents() {
        myProjectTab.addTab(new VerticalLayout(), "Users");
        myProjectTab.addTab(new VerticalLayout(), "Roles");
    }
}
