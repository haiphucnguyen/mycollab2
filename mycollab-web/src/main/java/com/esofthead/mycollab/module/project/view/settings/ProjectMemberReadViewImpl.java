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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.shared.ui.MarginInfo;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class ProjectMemberReadViewImpl extends AbstractPageView implements
		ProjectMemberReadView {

	private static final long serialVersionUID = 1L;
	protected ProjectMemberPreviewBuilder.ReadView projectMemberPreview;

	public ProjectMemberReadViewImpl() {
		super();
		this.setMargin(new MarginInfo(false, false, true, false));
		this.projectMemberPreview = new ProjectMemberPreviewBuilder.ReadView();
		this.addComponent(this.projectMemberPreview);
	}

	@Override
	public void previewItem(final SimpleProjectMember item) {
		this.projectMemberPreview.previewItem(item);
	}

	@Override
	public SimpleProjectMember getItem() {
		return this.projectMemberPreview.getProjectMember();
	}

	@Override
	public HasPreviewFormHandlers<ProjectMember> getPreviewFormHandlers() {
		return this.projectMemberPreview.getPreviewForm();
	}
}
