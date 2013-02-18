/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectMemberAddPresenter extends
		AbstractPresenter<ProjectMemberAddView> {
	private static final long serialVersionUID = 1L;

	public ProjectMemberAddPresenter() {
		super(ProjectMemberAddView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<ProjectMember>() {
					@Override
					public void onSave(final ProjectMember projectMember) {
						saveProjectMember(projectMember);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance()
									.fireEvent(
											new ProjectMemberEvent.GotoList(
													this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance()
									.fireEvent(
											new ProjectMemberEvent.GotoList(
													this, null));
						}
					}

					@Override
					public void onSaveAndNew(final ProjectMember projectMember) {
						saveProjectMember(projectMember);
						EventBus.getInstance().fireEvent(
								new ProjectMemberEvent.GotoAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectMemberContainer userGroupContainer = (ProjectMemberContainer) container;
		userGroupContainer.removeAllComponents();
		userGroupContainer.addComponent(view.getWidget());
		view.editItem((ProjectMember) data.getParams());
	}

	public void saveProjectMember(ProjectMember projectMember) {
		ProjectMemberService projectMemberService = AppContext
				.getSpringBean(ProjectMemberService.class);
		projectMember.setProjectid(CurrentProjectVariables.getProjectId());
		projectMember.setJoindate(new GregorianCalendar().getTime());
		if (projectMember.getId() == null) {
			projectMemberService.saveWithSession(projectMember,
					AppContext.getUsername());

		} else {
			projectMemberService.updateWithSession(projectMember,
					AppContext.getUsername());
		}

	}

}
