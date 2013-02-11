/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.view.parameters.ProjectRoleScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class UserGroupViewImpl extends AbstractView implements UserGroupView {
	private static final long serialVersionUID = 1L;
	private ProjectMemberPresenter userPresenter;
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
		userPresenter = PresenterResolver
				.getPresenter(ProjectMemberPresenter.class);
		myProjectTab.addTab(userPresenter.getView(), "Users");

		rolePresenter = PresenterResolver
				.getPresenter(ProjectRolePresenter.class);
		myProjectTab.addTab(rolePresenter.getView(), "Roles");

		myProjectTab
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {
					@Override
					public void tabChanged(DetachedTabs.TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable(ProjectContants.PROJECT_NAME);

						if ("Users".equals(caption)) {
							ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
							criteria.setProjectId(new NumberSearchField(project
									.getId()));
							userPresenter
									.go(UserGroupViewImpl.this,
											new ScreenData.Search<ProjectMemberSearchCriteria>(
													criteria));
						} else if ("Roles".equals(caption)) {
							ProjectRoleSearchCriteria criteria = new ProjectRoleSearchCriteria();
							criteria.setProjectId(new NumberSearchField(project
									.getId()));
							rolePresenter.go(UserGroupViewImpl.this,
									new ProjectRoleScreenData.Search(criteria));
						}
					}
				});

	}
	
	@Override
	public Component gotoSubView(String name) {
		View component = (View) myProjectTab.selectTab(name);
		return component;
	}
}
