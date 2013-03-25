package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.BugResolutionConstants;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugRelatedItemService;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.AttachmentUploadField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class BugAddPresenter extends AbstractPresenter<BugAddView> {

	private static final long serialVersionUID = 1L;

	public BugAddPresenter() {
		super(BugAddView.class);
		bind();
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.BUGS)) {
			BugContainer bugContainer = (BugContainer) container;
			bugContainer.removeAllComponents();
			bugContainer.addComponent(view.getWidget());

			SimpleBug bug = (SimpleBug) data.getParams();
			view.editItem(bug);

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			if (bug.getId() == null) {
				breadcrumb.gotoBugAdd();
			} else {
				breadcrumb.gotoBugEdit(bug);
			}
		} else {
    		MessageConstants.showMessagePermissionAlert();
    	}
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<SimpleBug>() {
					@Override
					public void onSave(final SimpleBug bug) {
						saveBug(bug);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoDashboard(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoDashboard(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final SimpleBug bug) {
						saveBug(bug);
						EventBus.getInstance().fireEvent(
								new BugEvent.GotoAdd(this, null));
					}
				});
	}

	public void saveBug(SimpleBug bug) {
		BugService bugService = AppContext.getSpringBean(BugService.class);
		bug.setProjectid(CurrentProjectVariables.getProjectId());
		bug.setSaccountid(AppContext.getAccountId());
		if (bug.getId() == null) {
			bug.setStatus(BugStatusConstants.OPEN);
			bug.setResolution(BugResolutionConstants.NEWISSUE);
			bug.setLogby(AppContext.getUsername());
			int bugId = bugService.saveWithSession(bug,
					AppContext.getUsername());
			AttachmentUploadField uploadField = view.getAttachUploadField();
			uploadField.saveContentsToRepo(
					AttachmentConstants.PROJECT_BUG_TYPE, bugId);

			// save component
			BugRelatedItemService bugRelatedItemService = AppContext
					.getSpringBean(BugRelatedItemService.class);
			bugRelatedItemService.saveAffectedVersionsOfBug(bugId,
					view.getAffectedVersions());
			bugRelatedItemService.saveFixedVersionsOfBug(bugId,
					view.getFixedVersion());
			bugRelatedItemService.saveComponentsOfBug(bugId,
					view.getComponents());
		} else {
			bugService.updateWithSession(bug, AppContext.getUsername());
			AttachmentUploadField uploadField = view.getAttachUploadField();
			uploadField.saveContentsToRepo(
					AttachmentConstants.PROJECT_BUG_TYPE, bug.getId());

			int bugId = bug.getId();
			BugRelatedItemService bugRelatedItemService = AppContext
					.getSpringBean(BugRelatedItemService.class);
			bugRelatedItemService.updateAfftedVersionsOfBug(bugId,
					view.getAffectedVersions());
			bugRelatedItemService.updateFixedVersionsOfBug(bugId,
					view.getFixedVersion());
			bugRelatedItemService.updateComponentsOfBug(bugId,
					view.getComponents());
		}

	}
}
