/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
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
public class MilestoneReadPresenter extends
		AbstractPresenter<MilestoneReadView> {

	private static final long serialVersionUID = 1L;

	public MilestoneReadPresenter() {
		super(MilestoneReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Milestone>() {
					@Override
					public void onEdit(Milestone data) {
						EventBus.getInstance().fireEvent(
								new MilestoneEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(final Milestone data) {
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
											MilestoneService milestoneService = AppContext
													.getSpringBean(MilestoneService.class);
											milestoneService.removeWithSession(
													data.getId(),
													AppContext.getUsername(),
													AppContext.getAccountId());
											EventBus.getInstance()
													.fireEvent(
															new MilestoneEvent.GotoList(
																	this, null));
										}
									}
								});
					}

					@Override
					public void onClone(Milestone data) {
						Milestone cloneData = (Milestone) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new MilestoneEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new MilestoneEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(Milestone data) {
						MilestoneService milestoneService = AppContext
								.getSpringBean(MilestoneService.class);
						MilestoneSearchCriteria criteria = new MilestoneSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = milestoneService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new MilestoneEvent.GotoRead(this, nextId));
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
					public void gotoPrevious(Milestone data) {
						MilestoneService milestoneService = AppContext
								.getSpringBean(MilestoneService.class);
						MilestoneSearchCriteria criteria = new MilestoneSearchCriteria();
						SimpleProject project = (SimpleProject) AppContext
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = milestoneService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new MilestoneEvent.GotoRead(this, nextId));
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
				.canRead(ProjectRolePermissionCollections.MILESTONES)) {
			if (data.getParams() instanceof Integer) {
				MilestoneService riskService = AppContext
						.getSpringBean(MilestoneService.class);
				SimpleMilestone milestone = riskService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (milestone != null) {
					MilestoneContainer milestoneContainer = (MilestoneContainer) container;
					milestoneContainer.removeAllComponents();
					milestoneContainer.addComponent(view.getWidget());
					view.previewItem(milestone);

					ProjectBreadcrumb breadcrumb = ViewManager
							.getView(ProjectBreadcrumb.class);
					breadcrumb.gotoMilestoneRead(milestone);
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
					return;
				}
			} else {
				throw new MyCollabException("Unhanddle this case yet");
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
