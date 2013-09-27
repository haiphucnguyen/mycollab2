/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.view.parameters.ProjectRoleScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
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
public class UserSettingViewImpl extends AbstractView implements
		UserSettingView {
	private static final long serialVersionUID = 1L;

	private ProjectUserPresenter userPresenter;
	private ProjectRolePresenter rolePresenter;
	private ProjectNotificationSettingPresenter notificationSettingPresenter;

	private final DetachedTabs myProjectTab;
	private final CssLayout mySpaceArea = new CssLayout();

	public UserSettingViewImpl() {

		this.myProjectTab = new DetachedTabs.Horizontal(this.mySpaceArea);
		this.myProjectTab.setSizeUndefined();

		final HorizontalLayout menu = new HorizontalLayout();
		menu.setWidth("100%");
		menu.setStyleName(UIConstants.THEME_TAB_STYLE3);
		menu.setHeight("40px");
		menu.addComponent(this.myProjectTab);

		this.addComponent(menu);
		this.mySpaceArea.setWidth("100%");
		this.mySpaceArea.setHeight(null);
		this.mySpaceArea.addStyleName("usergroup-view");
		this.addComponent(this.mySpaceArea);
		this.setExpandRatio(this.mySpaceArea, 1.0f);
		this.setWidth("100%");
		this.setMargin(true);
		this.buildComponents();
	}

	private void buildComponents() {
		this.userPresenter = PresenterResolver
				.getPresenter(ProjectUserPresenter.class);
		this.myProjectTab.addTab(this.userPresenter.getView(), "Users");

		this.rolePresenter = PresenterResolver
				.getPresenter(ProjectRolePresenter.class);
		this.myProjectTab.addTab(this.rolePresenter.getView(), "Roles");

		this.notificationSettingPresenter = PresenterResolver
				.getPresenter(ProjectNotificationSettingPresenter.class);
		this.myProjectTab.addTab(this.notificationSettingPresenter.getView(),
				"Notification Settings");

		this.myProjectTab
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {
					@Override
					public void tabChanged(
							final DetachedTabs.TabChangedEvent event) {
						final Button btn = event.getSource();
						final String caption = btn.getCaption();
						final SimpleProject project = CurrentProjectVariables
								.getProject();

						if ("Users".equals(caption)) {
							final ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
							criteria.setProjectId(new NumberSearchField(project
									.getId()));
							criteria.setStatus(new StringSearchField(
									ProjectMemberStatusConstants.ACTIVE));
							UserSettingViewImpl.this.userPresenter
									.go(UserSettingViewImpl.this,
											new ScreenData.Search<ProjectMemberSearchCriteria>(
													criteria));
						} else if ("Roles".equals(caption)) {
							final ProjectRoleSearchCriteria criteria = new ProjectRoleSearchCriteria();
							criteria.setProjectId(new NumberSearchField(project
									.getId()));
							UserSettingViewImpl.this.rolePresenter.go(
									UserSettingViewImpl.this,
									new ProjectRoleScreenData.Search(criteria));
						} else if ("Notification Settings".equals(caption)) {
							notificationSettingPresenter.go(
									UserSettingViewImpl.this, null);
						}
					}
				});

	}

	@Override
	public Component gotoSubView(final String name) {
		final View component = (View) this.myProjectTab.selectTab(name);
		return component;
	}
}
