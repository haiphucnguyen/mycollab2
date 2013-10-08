/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.web.AppContext;
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
						VersionService versionService = ApplicationContextUtil
								.getSpringBean(VersionService.class);
						versionService.removeWithSession(data.getId(),
								AppContext.getUsername(),
								AppContext.getAccountId());
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
						VersionService componentService = ApplicationContextUtil
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
							NotificationUtil.showGotoLastRecordNotification();
						}

					}

					@Override
					public void gotoPrevious(Version data) {
						VersionService componentService = ApplicationContextUtil
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
							NotificationUtil.showGotoFirstRecordNotification();
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canRead(ProjectRolePermissionCollections.VERSIONS)) {
			if (data.getParams() instanceof Integer) {
				VersionService componentService = ApplicationContextUtil
						.getSpringBean(VersionService.class);
				Version version = componentService.findById(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (version != null) {
					VersionContainer versionContainer = (VersionContainer) container;
					versionContainer.removeAllComponents();
					versionContainer.addComponent(view.getWidget());
					view.previewItem(version);

					ProjectBreadcrumb breadcrumb = ViewManager
							.getView(ProjectBreadcrumb.class);
					breadcrumb.gotoVersionRead(version);
				} else {
					NotificationUtil.showRecordNotExistNotification();
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
