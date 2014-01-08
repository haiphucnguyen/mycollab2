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

import com.esofthead.mycollab.module.project.ui.components.AbstractEditItemComp;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class VersionAddViewImpl extends AbstractEditItemComp<Version> implements
		VersionAddView {
	private static final long serialVersionUID = 1L;

	public VersionAddViewImpl() {
		this.setMargin(new MarginInfo(true, false, false, false));
	}
	
	@Override
	public HasEditFormHandlers<Version> getEditFormHandlers() {
		return this.editForm;
	}

	@Override
	protected String initFormTitle() {
		return (beanItem.getId() == null) ? "Create Version" : beanItem
				.getVersionname();
	}

	@Override
	protected Resource initFormIconResource() {
		return MyCollabResource.newResource("icons/22/project/version.png");
	}

	@Override
	protected ComponentContainer createButtonControls() {
		final HorizontalLayout controlPanel = new HorizontalLayout();
		final Layout controlButtons = (new EditFormControlsGenerator<Version>(
				editForm)).createButtonControls();
		controlButtons.setSizeUndefined();
		controlPanel.addComponent(controlButtons);
		controlPanel.setWidth("100%");
		controlPanel.setComponentAlignment(controlButtons,
				Alignment.MIDDLE_CENTER);
		controlPanel.setMargin(true);
		controlPanel.addStyleName("control-buttons");
		return controlPanel;
	}

	@Override
	protected AdvancedEditBeanForm<Version> initPreviewForm() {
		return new AdvancedEditBeanForm<Version>();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new VersionFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Version> initBeanFormFieldFactory() {
		return new EditFormFieldFactory(editForm);
	}

	private class EditFormFieldFactory extends
			AbstractBeanFieldGroupEditFieldFactory<Version> {
		private static final long serialVersionUID = 1L;

		public EditFormFieldFactory(GenericBeanForm<Version> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(final Object propertyId) {

			if (propertyId.equals("versionname")) {
				final TextField tf = new TextField();
				tf.setNullRepresentation("");
				tf.setRequired(true);
				tf.setRequiredError("Please enter a Version Name");
				return tf;
			} else if (propertyId.equals("description")) {
				final TextArea field = new TextArea("", "");
				field.setNullRepresentation("");
				return field;
			} else if (propertyId.equals("duedate")) {
				final DateField dateField = new DateField();
				dateField.setResolution(Resolution.DAY);
				return dateField;
			}

			return null;
		}
	}
}
