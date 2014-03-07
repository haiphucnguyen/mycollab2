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

package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractProjectPageView;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class MilestoneReadViewImpl extends AbstractProjectPageView implements
		MilestoneReadView {

	private static final long serialVersionUID = 1L;
	private final MilestoneReadComp milestonePreview;

	public MilestoneReadViewImpl() {
		super("Phase Detail", "phase_selected.png");
		CssLayout contentWrapper = new CssLayout(); 
		contentWrapper.setStyleName("content-wrapper");
		
		this.milestonePreview = new MilestoneReadComp();
		
		ComponentContainer actionControls = this.milestonePreview.createButtonControls();
		if (actionControls != null) {
			actionControls.addStyleName("control-buttons");
		this.addHeaderRightContent(actionControls);
		}
		
		contentWrapper.addComponent(this.milestonePreview);
		this.addComponent(contentWrapper);
	}

	@Override
	public void previewItem(final SimpleMilestone item) {
		this.milestonePreview.previewItem(item);
	}

	@Override
	public SimpleMilestone getItem() {
		return this.milestonePreview.getBeanItem();
	}

	@Override
	public HasPreviewFormHandlers<SimpleMilestone> getPreviewFormHandlers() {
		return this.milestonePreview.getPreviewForm();
	}
}
