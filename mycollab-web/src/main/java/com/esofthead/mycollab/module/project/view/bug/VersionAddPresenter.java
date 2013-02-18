/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author haiphucnguyen
 */
public class VersionAddPresenter extends AbstractPresenter<VersionAddView> {
	private static final long serialVersionUID = 1L;

	public VersionAddPresenter() {
		super(VersionAddView.class);

		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Version>() {
					@Override
					public void onSave(final Version item) {
						save(item);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new BugVersionEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new BugVersionEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Version item) {
						save(item);
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoAdd(this, null));
					}
				});
	}

	public void save(Version item) {
		VersionService versionService = AppContext
				.getSpringBean(VersionService.class);
		item.setSaccountid(AppContext.getAccountId());
		item.setProjectid(CurrentProjectVariables.getProjectId());

		if (item.getId() == null) {
			versionService.saveWithSession(item, AppContext.getUsername());
		} else {
			versionService.updateWithSession(item, AppContext.getUsername());
		}
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		BugContainer bugContainer = (BugContainer) container;
		bugContainer.addComponent(view.getWidget());

		Version version = (Version) data.getParams();
		view.editItem(version);

		ProjectBreadcrumb breadcrumb = ViewManager
				.getView(ProjectBreadcrumb.class);
		if (version.getId() == null) {
			breadcrumb.gotoVersionAdd();
		} else {
			breadcrumb.gotoVersionEdit(version);
		}
	}

}
