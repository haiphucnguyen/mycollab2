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
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
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
public class ComponentAddViewImpl extends AbstractEditItemComp<Component>
		implements ComponentAddView {

	private static final long serialVersionUID = 1L;
	
	public ComponentAddViewImpl() {
		this.setMargin(new MarginInfo(true, false, false, false));
	}

	@Override
	protected String initFormTitle() {
		return (beanItem.getId() == null) ? "Create Component" : beanItem
				.getComponentname();
	}

	@Override
	protected Resource initFormIconResource() {
		return MyCollabResource.newResource("icons/22/project/menu_bug.png");
	}

	@Override
	protected ComponentContainer createButtonControls() {
		final HorizontalLayout controlPanel = new HorizontalLayout();
		final Layout controlButtons = (new EditFormControlsGenerator<Component>(
				editForm)).createButtonControls();
		controlButtons.setSizeUndefined();
		controlPanel.addComponent(controlButtons);
		controlPanel.setWidth("100%");
		controlPanel.setMargin(true);
		controlPanel.setComponentAlignment(controlButtons,
				Alignment.MIDDLE_CENTER);
		controlPanel.addStyleName("control-buttons");
		return controlPanel;
	}

	@Override
	protected AdvancedEditBeanForm<Component> initPreviewForm() {
		return new AdvancedEditBeanForm<Component>();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new ComponentFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<Component> initBeanFormFieldFactory() {
		return new EditFormFieldFactory(editForm);
	}

	@Override
	public HasEditFormHandlers<Component> getEditFormHandlers() {
		return this.editForm;
	}

	private class EditFormFieldFactory extends
			AbstractBeanFieldGroupEditFieldFactory<Component> {
		private static final long serialVersionUID = 1L;

		public EditFormFieldFactory(GenericBeanForm<Component> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(final Object propertyId) {

			if (propertyId.equals("componentname")) {
				final TextField tf = new TextField();
				tf.setNullRepresentation("");
				tf.setRequired(true);
				tf.setRequiredError("Please enter a Component Name");
				return tf;
			} else if (propertyId.equals("description")) {
				final TextArea field = new TextArea("", "");
				field.setNullRepresentation("");
				return field;
			} else if (propertyId.equals("userlead")) {
				final ProjectMemberSelectionField userBox = new ProjectMemberSelectionField();
				return userBox;
			}

			return null;
		}
	}

}
