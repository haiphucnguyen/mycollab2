/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.people;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
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
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<ProjectMember>() {
					@Override
					public void onEdit(ProjectMember data) {
						EventBus.getInstance().fireEvent(
								new ProjectMemberEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final ProjectMember data) {

						ConfirmDialog.show(
								view.getWindow(),
								"Please Confirm:",
								"Are you sure to delete this item: "
										+ ((SimpleProjectMember) data)
												.getMemberFullName(), "Yes",
								"No", new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											ProjectMemberService projectMemberService = AppContext
													.getSpringBean(ProjectMemberService.class);
											projectMemberService.removeWithSession(
													data.getId(),
													AppContext.getUsername());
											EventBus.getInstance()
													.fireEvent(
															new ProjectMemberEvent.GotoList(
																	this, null));
										}
									}
								});
					}

					@Override
					public void onClone(ProjectMember data) {
						ProjectMember cloneData = (ProjectMember) data.copy();
						cloneData.setId(null);
						EventBus.getInstance()
								.fireEvent(
										new ProjectMemberEvent.GotoEdit(this,
												cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new ProjectMemberEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(ProjectMember data) {
						ProjectMemberService projectMemberService = AppContext
								.getSpringBean(ProjectMemberService.class);
						ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATHER));
						Integer nextId = projectMemberService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ProjectMemberEvent.GotoRead(this,
											nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the last record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(ProjectMember data) {
						ProjectMemberService riskeService = AppContext
								.getSpringBean(ProjectMemberService.class);
						ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = riskeService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ProjectMemberEvent.GotoRead(this,
											nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the first record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectMemberService prjMemberService = AppContext
				.getSpringBean(ProjectMemberService.class);
		SimpleProjectMember prjMember = null;
		if (data.getParams() instanceof Integer) {
			prjMember = prjMemberService.findMemberById((Integer) data
					.getParams());

		} else if (data.getParams() instanceof String) {
			String username = (String) data.getParams();
			prjMember = prjMemberService.findMemberByUsername(username,
					CurrentProjectVariables.getProjectId());
		}

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
