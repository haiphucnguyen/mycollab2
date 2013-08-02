/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class MilestoneAddPresenter extends AbstractPresenter<MilestoneAddView> {

	private static final long serialVersionUID = 1L;

	public MilestoneAddPresenter() {
		super(MilestoneAddView.class);
		bind();
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.MILESTONES)) {
			MilestoneContainer milestoneContainer = (MilestoneContainer) container;
			milestoneContainer.removeAllComponents();
			milestoneContainer.addComponent(view.getWidget());

			Milestone milestone = (Milestone) data.getParams();
			view.editItem(milestone);

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			if (milestone.getId() == null) {
				breadcrumb.gotoMilestoneAdd();
			} else {
				breadcrumb.gotoMilestoneEdit(milestone);
			}
		} else {
    		MessageConstants.showMessagePermissionAlert();
    	}
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Milestone>() {
					@Override
					public void onSave(final Milestone milestone) {
						saveMilestone(milestone);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new MilestoneEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new MilestoneEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Milestone milestone) {
						saveMilestone(milestone);
						EventBus.getInstance().fireEvent(
								new MilestoneEvent.GotoAdd(this, null));
					}
				});
	}

	public void saveMilestone(Milestone milestone) {
		MilestoneService milestoneService = AppContext
				.getSpringBean(MilestoneService.class);
		milestone.setProjectid(CurrentProjectVariables.getProjectId());
		milestone.setSaccountid(AppContext.getAccountId());

		if (milestone.getId() == null) {
			milestoneService.saveWithSession(milestone,
					AppContext.getUsername());
		} else {
			milestoneService.updateWithSession(milestone,
					AppContext.getUsername());
		}

	}

}
