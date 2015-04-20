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

import com.esofthead.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.service.VersionService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.*;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@LoadPolicy(scope = ViewScope.PROTOTYPE)
public class VersionAddPresenter extends AbstractPresenter<VersionAddView> {
	private static final long serialVersionUID = 1L;

	public VersionAddPresenter() {
		super(VersionAddView.class);
	}

	@Override
	protected void postInitView() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Version>() {
					private static final long serialVersionUID = 1L;

					@Override
					public void onSave(final Version item) {
						save(item);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBusFactory.getInstance().post(
									new BugVersionEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBusFactory.getInstance().post(
									new BugVersionEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Version item) {
						save(item);
						EventBusFactory.getInstance().post(
								new BugVersionEvent.GotoAdd(this, null));
					}
				});
	}

	private void save(Version item) {
		VersionService versionService = ApplicationContextUtil
				.getSpringBean(VersionService.class);
		item.setSaccountid(AppContext.getAccountId());
		item.setProjectid(CurrentProjectVariables.getProjectId());
		item.setStatus(StatusI18nEnum.Open.name());
		if (item.getId() == null) {
			versionService.saveWithSession(item, AppContext.getUsername());
		} else {
			versionService.updateWithSession(item, AppContext.getUsername());
		}
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.VERSIONS)) {
			VersionContainer versionContainer = (VersionContainer) container;
			versionContainer.addComponent(view.getWidget());

			Version version = (Version) data.getParams();
			view.editItem(version);

			ProjectBreadcrumb breadcrumb = ViewManager
					.getCacheComponent(ProjectBreadcrumb.class);
			if (version.getId() == null) {
				breadcrumb.gotoVersionAdd();
			} else {
				breadcrumb.gotoVersionEdit(version);
			}
		} else {
			NotificationUtil.showMessagePermissionAlert();
		}
	}

}
