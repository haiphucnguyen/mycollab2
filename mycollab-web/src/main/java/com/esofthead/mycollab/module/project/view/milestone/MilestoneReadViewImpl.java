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
package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class MilestoneReadViewImpl extends AbstractPageView implements
		MilestoneReadView {

	private static final long serialVersionUID = 1L;
	private final MilestonePreviewBuilder.ReadView milestonePreview;

	public MilestoneReadViewImpl() {
		super();
		this.milestonePreview = new MilestonePreviewBuilder.ReadView();
		this.addComponent(this.milestonePreview);
		this.setMargin(true);
	}

	@Override
	public void previewItem(final SimpleMilestone item) {
		this.milestonePreview.previewItem(item);
	}

	@Override
	public SimpleMilestone getItem() {
		return this.milestonePreview.getMilestone();
	}

	@Override
	public HasPreviewFormHandlers<Milestone> getPreviewFormHandlers() {
		return this.milestonePreview.getPreviewForm();
	}
}
