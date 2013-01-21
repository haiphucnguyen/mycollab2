/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
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

						ConfirmDialog.show(view.getWindow(), "Please Confirm:",
								"Are you sure to delete selected items: ",
								"Yes", "No", new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											MilestoneService riskService = AppContext
													.getSpringBean(MilestoneService.class);
											riskService.removeWithSession(
													data.getId(),
													AppContext.getUsername());
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
								NumberSearchField.GREATHER));
						Integer nextId = milestoneService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new MilestoneEvent.GotoRead(this, nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the last record",
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
							view.getWindow().showNotification("Information",
									"You are already in the first record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (data.getParams() instanceof Integer) {
			MilestoneService riskService = AppContext
					.getSpringBean(MilestoneService.class);
			SimpleMilestone milestone = riskService
					.findMilestoneById((Integer) data.getParams());
			if (milestone != null) {
				MilestoneContainer riskContainer = (MilestoneContainer) container;
				riskContainer.removeAllComponents();
				riskContainer.addComponent(view.getWidget());
				view.previewItem(milestone);

				ProjectBreadcrumb breadcrumb = ViewManager
						.getView(ProjectBreadcrumb.class);
				breadcrumb.gotoMilestoneRead(milestone);
			} else {
				AppContext
						.getApplication()
						.getMainWindow()
						.showNotification("Information",
								"The record is not existed",
								Window.Notification.TYPE_HUMANIZED_MESSAGE);
				return;
			}
		} else {
			throw new MyCollabException("Unhanddle this case yet");
		}
	}
}
