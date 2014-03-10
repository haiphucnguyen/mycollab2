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

import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.ui.components.AbstractEditItemComp;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Layout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class MilestoneAddViewImpl extends AbstractEditItemComp<Milestone>
implements MilestoneAddView {

	private static final long serialVersionUID = 1L;

	@Override
	protected String initFormTitle() {
		return (beanItem.getId() == null) ? null : beanItem.getName();
	}

	@Override
	protected String initFormHeader() {
		return (beanItem.getId() == null) ? "Create Phase" : "Phase Edit";
	}

	@Override
	protected Resource initFormIconResource() {
		return MyCollabResource
				.newResource("icons/22/project/phase_selected.png");
	}

	@Override
	protected ComponentContainer createButtonControls() {
		final Layout controlButtons = (new EditFormControlsGenerator<Milestone>(
				editForm)).createButtonControls();
		controlButtons.setSizeUndefined();
		return controlButtons;
	}

	@Override
	protected AdvancedEditBeanForm<Milestone> initPreviewForm() {
		return new AdvancedEditBeanForm<Milestone>();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new MilestoneFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Milestone> initBeanFormFieldFactory() {
		return new MilestoneEditFormFieldFactory<Milestone>(editForm);
	}
}
