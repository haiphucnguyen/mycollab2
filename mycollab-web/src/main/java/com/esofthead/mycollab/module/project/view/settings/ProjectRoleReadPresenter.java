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

package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabApplication;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectRoleReadPresenter extends
		AbstractPresenter<ProjectRoleReadView> {
	private static final long serialVersionUID = 1L;

	public ProjectRoleReadPresenter() {
		super(ProjectRoleReadView.class);
	}

	@Override
	protected void postInitView() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleProjectRole>() {
					@Override
					public void onEdit(SimpleProjectRole data) {
						EventBus.getInstance().fireEvent(
								new ProjectRoleEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(SimpleProjectRole data) {
						ProjectRoleService projectRoleService = ApplicationContextUtil
								.getSpringBean(ProjectRoleService.class);
						projectRoleService.removeWithSession(data.getId(),
								AppContext.getUsername(),
								AppContext.getAccountId());
						EventBus.getInstance().fireEvent(
								new ProjectRoleEvent.GotoList(this, null));
					}

					@Override
					public void onClone(SimpleProjectRole data) {
						SimpleProjectRole cloneData = (SimpleProjectRole) data
								.copy();
						cloneData.setRolename(null);
						EventBus.getInstance().fireEvent(
								new ProjectRoleEvent.GotoAdd(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new ProjectRoleEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(SimpleProjectRole data) {
						ProjectRoleService projectRoleService = ApplicationContextUtil
								.getSpringBean(ProjectRoleService.class);
						ProjectRoleSearchCriteria criteria = new ProjectRoleSearchCriteria();
						SimpleProject project = (SimpleProject) MyCollabApplication
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = projectRoleService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance()
									.fireEvent(
											new ProjectRoleEvent.GotoRead(this,
													nextId));
						} else {
							NotificationUtil.showGotoLastRecordNotification();
						}

					}

					@Override
					public void gotoPrevious(SimpleProjectRole data) {
						ProjectRoleService projectRoleService = ApplicationContextUtil
								.getSpringBean(ProjectRoleService.class);
						ProjectRoleSearchCriteria criteria = new ProjectRoleSearchCriteria();
						SimpleProject project = (SimpleProject) MyCollabApplication
								.getVariable("project");
						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, project.getId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = projectRoleService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance()
									.fireEvent(
											new ProjectRoleEvent.GotoRead(this,
													nextId));
						} else {
							NotificationUtil.showGotoFirstRecordNotification();
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectRoleContainer roleContainer = (ProjectRoleContainer) container;

		if (data.getParams() instanceof Integer) {
			ProjectRoleService projectRoleService = ApplicationContextUtil
					.getSpringBean(ProjectRoleService.class);
			SimpleProjectRole role = projectRoleService.findById(
					(Integer) data.getParams(), AppContext.getAccountId());
			if (role == null) {
				NotificationUtil.showRecordNotExistNotification();
				return;
			} else {
				roleContainer.removeAllComponents();
				roleContainer.addComponent(view.getWidget());
				view.previewItem(role);

				ProjectBreadcrumb breadCrumb = ViewManager
						.getView(ProjectBreadcrumb.class);
				breadCrumb.gotoRoleRead(role);
			}
		} else {
			throw new MyCollabException("Do not support screen data: " + data);
		}
	}
}
