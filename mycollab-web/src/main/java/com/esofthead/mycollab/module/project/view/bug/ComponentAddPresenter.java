/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.service.ComponentService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ComponentAddPresenter extends AbstractPresenter<ComponentAddView> {
	private static final long serialVersionUID = 1L;

	public ComponentAddPresenter() {
		super(ComponentAddView.class);
	}

	@Override
	protected void postInitView() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Component>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSave(final Component item) {
						save(item);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new BugComponentEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new BugComponentEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Component item) {
						save(item);
						EventBus.getInstance().fireEvent(
								new BugComponentEvent.GotoAdd(this, null));
					}
				});
	}

	public void save(Component item) {
		ComponentService componentService = ApplicationContextUtil
				.getSpringBean(ComponentService.class);

		SimpleProject project = CurrentProjectVariables.getProject();
		item.setSaccountid(AppContext.getAccountId());
		item.setProjectid(project.getId());
		item.setStatus("Open");

		if (item.getId() == null) {
			item.setCreateduser(AppContext.getUsername());
			componentService.saveWithSession(item, AppContext.getUsername());
		} else {
			componentService.updateWithSession(item, AppContext.getUsername());
		}
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.COMPONENTS)) {
			com.esofthead.mycollab.module.project.view.bug.ComponentContainer componentContainer = (com.esofthead.mycollab.module.project.view.bug.ComponentContainer) container;
			componentContainer.removeAllComponents();
			componentContainer.addComponent(view.getWidget());

			Component component = (Component) data.getParams();
			view.editItem(component);

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);

			if (component.getId() == null) {
				breadcrumb.gotoComponentAdd();
			} else {
				breadcrumb.gotoComponentEdit(component);
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}
}
