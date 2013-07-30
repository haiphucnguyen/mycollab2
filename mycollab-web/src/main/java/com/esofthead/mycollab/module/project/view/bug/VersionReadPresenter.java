/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public class VersionReadPresenter extends AbstractPresenter<VersionReadView> {

	private static final long serialVersionUID = 1L;

	public VersionReadPresenter() {
		super(VersionReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Version>() {
					@Override
					public void onEdit(Version data) {
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(Version data) {
						VersionService versionService = AppContext
								.getSpringBean(VersionService.class);
						versionService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoList(this, null));
					}

					@Override
					public void onClone(Version data) {
						Version cloneData = (Version) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new BugVersionEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(Version data) {
						VersionService componentService = AppContext
								.getSpringBean(VersionService.class);
						VersionSearchCriteria criteria = new VersionSearchCriteria();
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, CurrentProjectVariables
										.getProjectId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = componentService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new BugVersionEvent.GotoRead(this, nextId));
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
					public void gotoPrevious(Version data) {
						VersionService componentService = AppContext
								.getSpringBean(VersionService.class);
						VersionSearchCriteria criteria = new VersionSearchCriteria();
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, CurrentProjectVariables
										.getProjectId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = componentService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new BugVersionEvent.GotoRead(this, nextId));
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
				.canRead(ProjectRolePermissionCollections.VERSIONS)) {
			if (data.getParams() instanceof Integer) {
				VersionService componentService = AppContext
						.getSpringBean(VersionService.class);
				Version version = componentService
						.findVersionById((Integer) data.getParams());
				if (version != null) {
					VersionContainer versionContainer = (VersionContainer) container;
					versionContainer.removeAllComponents();
					versionContainer.addComponent(view.getWidget());
					view.previewItem(version);

					ProjectBreadcrumb breadcrumb = ViewManager
							.getView(ProjectBreadcrumb.class);
					breadcrumb.gotoVersionRead(version);
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
