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
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
@ViewComponent
public class LeadAddViewImpl extends AbstractPageView implements LeadAddView {

	private static final long serialVersionUID = 1L;
	private AdvancedEditBeanForm<SimpleLead> editForm;
	private SimpleLead lead;

	public LeadAddViewImpl() {
		super();
		editForm = new AdvancedEditBeanForm<SimpleLead>();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(SimpleLead item) {
		this.lead = item;
		this.editForm.setFormLayoutFactory(new FormLayoutFactory());
		this.editForm
				.setBeanFormFieldFactory(new LeadEditFormFieldFactory<SimpleLead>(
						editForm));
		this.editForm.setBean(lead);
	}

	@Override
	public HasEditFormHandlers<SimpleLead> getEditFormHandlers() {
		return editForm;
	}

	class FormLayoutFactory extends LeadFormLayoutFactory {

		private static final long serialVersionUID = 1L;

		public FormLayoutFactory() {
			super((lead.getId() == null) ? "Create Lead" : lead.getLastname());
		}

		private Layout createButtonControls() {
			final HorizontalLayout controlPanel = new HorizontalLayout();
			final Layout controlButtons = (new EditFormControlsGenerator<SimpleLead>(
					editForm)).createButtonControls();
			controlButtons.setSizeUndefined();
			controlPanel.addComponent(controlButtons);
			controlPanel.setWidth("100%");
			controlPanel.setMargin(true);
			controlPanel.setComponentAlignment(controlButtons,
					Alignment.MIDDLE_CENTER);
			return controlPanel;
		}

		@Override
		protected Layout createTopPanel() {
			return createButtonControls();
		}

		@Override
		protected Layout createBottomPanel() {
			return createButtonControls();
		}
	}
}
