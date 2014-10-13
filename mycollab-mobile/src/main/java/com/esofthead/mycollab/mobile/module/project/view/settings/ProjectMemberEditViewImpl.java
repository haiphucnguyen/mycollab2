package com.esofthead.mycollab.mobile.module.project.view.settings;

import com.esofthead.mycollab.mobile.ui.AbstractEditItemComp;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.i18n.ProjectMemberI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Field;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.2
 */
@ViewComponent
public class ProjectMemberEditViewImpl extends
		AbstractEditItemComp<SimpleProjectMember> implements
		ProjectMemberEditView {

	private static final long serialVersionUID = 1483479851089277052L;

	public ProjectMemberEditViewImpl() {
		this.addStyleName("member-edit-view");
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getDisplayName();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new ProjectMemberEditFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<SimpleProjectMember> initBeanFormFieldFactory() {
		return new ProjectMemberEditFieldGroupFactory(this.editForm);
	}

	private class ProjectMemberEditFormLayoutFactory implements
			IFormLayoutFactory {

		private static final long serialVersionUID = -6204799792781581979L;
		VerticalComponentGroup mainLayout;

		@Override
		public ComponentContainer getLayout() {
			mainLayout = new VerticalComponentGroup();
			mainLayout.setWidth("100%");
			return mainLayout;
		}

		@Override
		public void attachField(Object propertyId, Field<?> field) {
			if (propertyId.equals("projectroleid")) {
				field.setCaption(AppContext
						.getMessage(ProjectMemberI18nEnum.FORM_ROLE));
				mainLayout.addComponent(field);
			}
		}

	}

	private class ProjectMemberEditFieldGroupFactory extends
			AbstractBeanFieldGroupEditFieldFactory<SimpleProjectMember> {

		private static final long serialVersionUID = 1490026787891513129L;

		public ProjectMemberEditFieldGroupFactory(
				GenericBeanForm<SimpleProjectMember> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(Object propertyId) {
			if (propertyId.equals("projectroleid")) {
				return new ProjectRoleSelectionField();
			}
			return null;
		}

	}

	private class ProjectRoleSelectionField extends CustomField<Integer> {
		private static final long serialVersionUID = 1L;
		private ProjectRoleComboBox roleComboBox;

		public ProjectRoleSelectionField() {
			this.roleComboBox = new ProjectRoleComboBox();
			// this.roleComboBox
			// .addValueChangeListener(new Property.ValueChangeListener() {
			// private static final long serialVersionUID = 1L;
			//
			// @Override
			// public void valueChange(
			// final Property.ValueChangeEvent event) {
			// displayRolePermission((Integer) roleComboBox.getValue());
			//
			// }
			// });
		}

		@Override
		public void commit() throws SourceException, InvalidValueException {
			Integer roleId = (Integer) roleComboBox.getValue();
			if (roleId == -1) {
				beanItem.setIsadmin(Boolean.TRUE);
				this.setInternalValue(null);
			} else {
				this.setInternalValue((Integer) this.roleComboBox.getValue());
				beanItem.setIsadmin(Boolean.FALSE);
			}

			super.commit();
		}

		@Override
		public void setPropertyDataSource(
				@SuppressWarnings("rawtypes") Property newDataSource) {
			Object value = newDataSource.getValue();
			if (value instanceof Integer) {
				roleComboBox.setValue(value);
				// displayRolePermission((Integer) roleComboBox.getValue());
			} else if (value == null) {
				if (beanItem.getIsadmin() != null
						&& beanItem.getIsadmin() == Boolean.TRUE) {
					roleComboBox.setValue(-1);
					// displayRolePermission(null);
				}
			}
			super.setPropertyDataSource(newDataSource);
		}

		@Override
		public Class<Integer> getType() {
			return Integer.class;
		}

		@Override
		protected Component initContent() {
			return roleComboBox;
		}
	}
}
