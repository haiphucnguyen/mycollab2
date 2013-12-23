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

import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectRoleComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class ProjectMemberEditViewImpl extends AbstractPageView implements
		ProjectMemberEditView {

	private static final long serialVersionUID = 1L;
	private final AdvancedEditBeanForm<ProjectMember> editForm;
	private ProjectMember user;

	public ProjectMemberEditViewImpl() {
		super();
		this.setMargin(new MarginInfo(false, false, true, false));
		this.editForm = new AdvancedEditBeanForm<ProjectMember>();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final ProjectMember item) {
		this.user = item;
		editForm.setFormLayoutFactory(new FormLayoutFactory());
		editForm.setBeanFormFieldFactory(new EditFormFieldFactory(editForm));
		editForm.setBean(item);
	}

	private class FormLayoutFactory extends ProjectMemberFormLayoutFactory {

		private static final long serialVersionUID = 1L;

		public FormLayoutFactory() {
//			super(((SimpleProjectMember) ProjectMemberEditViewImpl.this.user)
//					.getMemberFullName(), UserAvatarControlFactory
//					.createAvatarResource(null, 24));
		}

		private Layout createButtonControls() {
			final HorizontalLayout controlPanel = new HorizontalLayout();
			final Layout controlButtons = (new EditFormControlsGenerator<ProjectMember>(
					editForm)).createButtonControls(true, false, true);
			controlButtons.setSizeUndefined();
			controlPanel.addComponent(controlButtons);
			controlPanel.setWidth("100%");
			controlPanel.setComponentAlignment(controlButtons,
					Alignment.MIDDLE_CENTER);
			return controlPanel;
		}

		
		protected Layout createTopPanel() {
			return this.createButtonControls();
		}

		
		protected Layout createBottomPanel() {
			return this.createButtonControls();
		}
	}

	private class EditFormFieldFactory extends
			AbstractBeanFieldGroupEditFieldFactory<ProjectMember> {
		private static final long serialVersionUID = 1L;

		public EditFormFieldFactory(GenericBeanForm<ProjectMember> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(final Object propertyId) {

			if (propertyId.equals("username")) {
				return new DefaultFormViewFieldFactory.FormViewField(
						((SimpleProjectMember) ProjectMemberEditViewImpl.this.user)
								.getMemberFullName());

			} else if (propertyId.equals("isadmin")) {
				AdminRoleSelectionField roleBox = new AdminRoleSelectionField();
				if (user.getProjectroleid() != null) {
					roleBox.setRoleId(user.getProjectroleid());
				} else if (user.getIsadmin() != null
						&& user.getIsadmin() == Boolean.TRUE) {
					roleBox.setRoleId(-1);
				}
				return roleBox;
			}
			return null;
		}
	}

	private class AdminRoleSelectionField extends CustomField {
		private static final long serialVersionUID = 1L;
		private ProjectRoleComboBox roleComboBox;

		@Override
		public Object getValue() {
			Integer roleId = (Integer) AdminRoleSelectionField.this.roleComboBox
					.getValue();
			Boolean resultVal = null;
			if (roleId == -1) {
				ProjectMemberEditViewImpl.this.user.setIsadmin(Boolean.TRUE);
				ProjectMemberEditViewImpl.this.user.setProjectroleid(null);
				resultVal = Boolean.TRUE;
			} else {
				ProjectMemberEditViewImpl.this.user
						.setProjectroleid((Integer) AdminRoleSelectionField.this.roleComboBox
								.getValue());
				ProjectMemberEditViewImpl.this.user.setIsadmin(Boolean.FALSE);
				resultVal = Boolean.FALSE;
			}
			return resultVal;
		}

		public void setRoleId(int roleId) {
			roleComboBox.setValue(roleId);
		}

		@Override
		public Class<Integer> getType() {
			return Integer.class;
		}

		@Override
		protected Component initContent() {
			this.roleComboBox = new ProjectRoleComboBox();
			this.roleComboBox
					.addValueChangeListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(
								final Property.ValueChangeEvent event) {
							getValue();

						}
					});

			return roleComboBox;
		}
	}

	@Override
	public HasEditFormHandlers<ProjectMember> getEditFormHandlers() {
		return this.editForm;
	}

}
