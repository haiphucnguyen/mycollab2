/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectMemberReadPresenter extends
		AbstractPresenter<ProjectMemberReadView> {
	private static final long serialVersionUID = 1L;

	public ProjectMemberReadPresenter() {
		super(ProjectMemberReadView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (data.getParams() instanceof Integer) {
			ProjectMemberService prjMemberService = AppContext
					.getSpringBean(ProjectMemberService.class);
			SimpleProjectMember prjMember = prjMemberService
					.findMemberById((Integer) data.getParams());
			if (prjMember != null) {
				ProjectMemberContainer userGroupContainer = (ProjectMemberContainer) container;
				userGroupContainer.removeAllComponents();
				userGroupContainer.addComponent(view.getWidget());
				view.previewItem(prjMember);
			} else {
				AppContext
						.getApplication()
						.getMainWindow()
						.showNotification("Information",
								"The record is not existed",
								Window.Notification.TYPE_HUMANIZED_MESSAGE);
			}
		}

	}
}
