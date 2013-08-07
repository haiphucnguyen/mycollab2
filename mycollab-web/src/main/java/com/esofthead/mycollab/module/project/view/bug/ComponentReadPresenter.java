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
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public class ComponentReadPresenter extends
		AbstractPresenter<ComponentReadView> {

	private static final long serialVersionUID = 1L;

	public ComponentReadPresenter() {
		super(ComponentReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleComponent>() {
					@Override
					public void onEdit(SimpleComponent data) {
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(SimpleComponent data) {
						ComponentService riskService = AppContext
								.getSpringBean(ComponentService.class);
						riskService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoList(this, null));
					}

					@Override
					public void onClone(SimpleComponent data) {
						Component cloneData = (Component) data.copy();
						cloneData.setId(null);
						EventBus.getInstance()
								.fireEvent(
										new BugComponentEvent.GotoEdit(this,
												cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(SimpleComponent data) {
						ComponentService componentService = AppContext
								.getSpringBean(ComponentService.class);
						ComponentSearchCriteria criteria = new ComponentSearchCriteria();
						criteria.setProjectid(new NumberSearchField(
								SearchField.AND, CurrentProjectVariables
										.getProjectId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = componentService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance()
									.fireEvent(
											new BugComponentEvent.GotoRead(
													this, nextId));
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
					public void gotoPrevious(SimpleComponent data) {
						ComponentService componentService = AppContext
								.getSpringBean(ComponentService.class);
						ComponentSearchCriteria criteria = new ComponentSearchCriteria();
						criteria.setProjectid(new NumberSearchField(
								SearchField.AND, CurrentProjectVariables
										.getProjectId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = componentService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance()
									.fireEvent(
											new BugComponentEvent.GotoRead(
													this, nextId));
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
				.canRead(ProjectRolePermissionCollections.COMPONENTS)) {
			if (data.getParams() instanceof Integer) {
				ComponentService componentService = AppContext
						.getSpringBean(ComponentService.class);
				SimpleComponent component = componentService
						.findById((Integer) data.getParams());
				if (component != null) {
					ComponentContainer componentContainer = (ComponentContainer) container;
					componentContainer.removeAllComponents();
					componentContainer.addComponent(view.getWidget());
					view.previewItem(component);

					ProjectBreadcrumb breadcrumb = ViewManager
							.getView(ProjectBreadcrumb.class);
					breadcrumb.gotoComponentRead(component);
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
