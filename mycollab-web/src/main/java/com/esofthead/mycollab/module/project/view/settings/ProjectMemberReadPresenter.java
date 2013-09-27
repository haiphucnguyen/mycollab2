/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectMemberStatusConstants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
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

						ConfirmDialogExt.show(
								view.getWindow(),
								LocalizationHelper.getMessage(
										GenericI18Enum.DELETE_DIALOG_TITLE,
										SiteConfiguration.getSiteName()),
								LocalizationHelper
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											ProjectMemberService projectMemberService = ApplicationContextUtil
													.getSpringBean(ProjectMemberService.class);
											projectMemberService.removeWithSession(
													data.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
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
						ProjectMemberService projectMemberService = ApplicationContextUtil
								.getSpringBean(ProjectMemberService.class);
						ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setStatus(new StringSearchField(
								ProjectMemberStatusConstants.ACTIVE));

						Integer nextId = projectMemberService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ProjectMemberEvent.GotoRead(this,
											nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(ProjectMember data) {
						ProjectMemberService projectMemberService = ApplicationContextUtil
								.getSpringBean(ProjectMemberService.class);
						ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						criteria.setStatus(new StringSearchField(
								ProjectMemberStatusConstants.ACTIVE));

						Integer nextId = projectMemberService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ProjectMemberEvent.GotoRead(this,
											nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.USERS)) {
			ProjectMemberService prjMemberService = ApplicationContextUtil
					.getSpringBean(ProjectMemberService.class);
			SimpleProjectMember prjMember = null;
			if (data.getParams() instanceof Integer) {
				prjMember = prjMemberService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());

			} else if (data.getParams() instanceof String) {
				String username = (String) data.getParams();
				prjMember = prjMemberService.findMemberByUsername(username,
						CurrentProjectVariables.getProjectId(),
						AppContext.getAccountId());
			}

			if (prjMember != null) {
				ProjectUserContainer userGroupContainer = (ProjectUserContainer) container;
				userGroupContainer.removeAllComponents();
				userGroupContainer.addComponent(view.getWidget());
				view.previewItem(prjMember);
				ProjectBreadcrumb breadCrumb = ViewManager
						.getView(ProjectBreadcrumb.class);
				breadCrumb.gotoUserRead(prjMember);
			} else {
				AppContext
						.getApplication()
						.getMainWindow()
						.showNotification(
								LocalizationHelper
										.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
								LocalizationHelper
										.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
								Window.Notification.TYPE_HUMANIZED_MESSAGE);
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
