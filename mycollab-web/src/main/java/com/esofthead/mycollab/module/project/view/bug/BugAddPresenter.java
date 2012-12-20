package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.tracker.BugResolutionConstants;
import com.esofthead.mycollab.module.tracker.BugStatusConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
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
		BugContainer bugContainer = (BugContainer) container;
		bugContainer.removeAllComponents();
		bugContainer.addComponent(view.getWidget());
		view.editItem((SimpleBug) data.getParams());
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
									new BugEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new BugEvent.GotoList(this, null));
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
		SimpleProject project = (SimpleProject) AppContext
				.getVariable(ProjectContants.PROJECT_NAME);
		bug.setProjectid(project.getId());
		if (bug.getId() == null) {
			bug.setStatus(BugStatusConstants.OPEN);
			bug.setResolution(BugResolutionConstants.UNRESOLVED);
			bugService.saveBugExt(bug, AppContext.getUsername());
		} else {
			bugService.updateWithSession(bug, AppContext.getUsername());
		}

	}

}
