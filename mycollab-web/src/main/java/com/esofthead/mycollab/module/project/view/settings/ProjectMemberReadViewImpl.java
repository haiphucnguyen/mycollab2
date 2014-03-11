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

import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProjectMemberReadViewImpl extends AbstractPageView implements
ProjectMemberReadView {

	private static final long serialVersionUID = 1L;
	protected ProjectMemberReadComp projectMemberPreview;

	public ProjectMemberReadViewImpl() {
		super();
		this.setStyleName("projectmember-view");
		this.projectMemberPreview = new ProjectMemberReadComp();
		this.addComponent(this.projectMemberPreview);
	}

	@Override
	public void previewItem(final SimpleProjectMember item) {
		this.projectMemberPreview.previewItem(item);
	}

	@Override
	public SimpleProjectMember getItem() {
		return this.projectMemberPreview.getBeanItem();
	}

	@Override
	public HasPreviewFormHandlers<SimpleProjectMember> getPreviewFormHandlers() {
		return this.projectMemberPreview.getPreviewForm();
	}
}
