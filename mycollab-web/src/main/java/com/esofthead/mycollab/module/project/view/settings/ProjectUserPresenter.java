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

import com.esofthead.mycollab.module.project.view.parameters.ProjectMemberScreenData;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class ProjectUserPresenter extends
		AbstractPresenter<ProjectUserContainer> {
	private static final long serialVersionUID = 1L;

	public ProjectUserPresenter() {
		super(ProjectUserContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		view.removeAllComponents();

		AbstractPresenter<?> presenter = null;

		if (data instanceof ProjectMemberScreenData.Add) {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberEditPresenter.class);
		} else if (data instanceof ProjectMemberScreenData.InviteProjectMembers) {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberInvitePresenter.class);
		} else if (data instanceof ProjectMemberScreenData.InviteProjectMembers) {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberInvitePresenter.class);
		} else if (data instanceof ProjectMemberScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberReadPresenter.class);
		} else if (data instanceof ProjectMemberScreenData.Search) {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberListPresenter.class);
		} else {
			presenter = PresenterResolver
					.getPresenter(ProjectMemberListPresenter.class);
		}

		presenter.go(view, data);
	}
}
