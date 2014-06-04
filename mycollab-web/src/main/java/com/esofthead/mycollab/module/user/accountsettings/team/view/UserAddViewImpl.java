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

package com.esofthead.mycollab.module.user.accountsettings.team.view;

import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ProfileFormLayoutFactory;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.view.component.RoleComboBox;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DateComboboxSelectionField;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.TimeZoneSelectionField;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Property;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class UserAddViewImpl extends AbstractPageView implements UserAddView {
	private static final long serialVersionUID = 1L;

	private EditUserForm editUserForm;
	private SimpleUser user;
	private DateComboboxSelectionField cboDateBirthday;
	private TimeZoneSelectionField cboTimezone;

	public UserAddViewImpl() {
		super();

		this.setMargin(new MarginInfo(false, true, false, true));

		this.editUserForm = new EditUserForm();
		this.addComponent(this.editUserForm);
	}

	@Override
	public void editItem(final SimpleUser item) {
		this.user = item;
		this.removeAllComponents();
		this.addComponent(this.editUserForm);
		this.editUserForm.setBean(this.user);
	}

	@Override
	public HasEditFormHandlers<SimpleUser> getEditFormHandlers() {
		return this.editUserForm;
	}

	private class EditUserForm extends AdvancedEditBeanForm<SimpleUser> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setBean(final SimpleUser newDataSource) {
			this.setFormLayoutFactory(new AdvancedFormLayoutFactory());
			this.setBeanFormFieldFactory(new AdvancedEditFormFieldFactory(
					editUserForm));
			super.setBean(newDataSource);
		}
	}

	private class BasicFormLayoutFactory implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;

		@Override
		public Layout getLayout() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void attachField(Object propertyId, Field<?> field) {
			// TODO Auto-generated method stub
			
		}

	}

	private class BasicEditFormFieldFactory extends
			AbstractBeanFieldGroupEditFieldFactory<SimpleUser> {
		private static final long serialVersionUID = 1L;

		public BasicEditFormFieldFactory(GenericBeanForm<SimpleUser> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(Object propertyId) {
			if (propertyId.equals("roleid")) {
				return new AdminRoleSelectionField();
			} else if (propertyId.equals("email")) {
				final TextField tf = new TextField();
				tf.setNullRepresentation("");
				tf.setRequired(true);
				tf.setRequiredError("This field must be not null");
				return tf;
			}

			return null;
		}

	}

	private class AdvancedFormLayoutFactory extends ProfileFormLayoutFactory {

		private static final long serialVersionUID = 1L;
		private Button moreInfoBtn;

		public AdvancedFormLayoutFactory() {
			super(
					(UserAddViewImpl.this.user.getUsername() == null) ? "New User"
							: (user.getDisplayName()));
			this.setAvatarLink(user.getAvatarid());
		}

		@Override
		public Layout getLayout() {
			final AddViewLayout formAddLayout = new AddViewLayout(
					initFormHeader(),
					MyCollabResource.newResource("icons/24/project/user.png"));

			final ComponentContainer topLayout = createButtonControls();
			if (topLayout != null) {
				formAddLayout.addHeaderRight(topLayout);
			}

			formAddLayout.setTitle(initFormTitle());

			userInformationLayout = new UserInformationLayout();

			formAddLayout.addBody(userInformationLayout.getLayout());

			final ComponentContainer bottomPanel = createBottomPanel();
			if (bottomPanel != null) {
				formAddLayout.addBottomControls(bottomPanel);
			}

			return formAddLayout;
		}

		protected String initFormHeader() {
			return (user.getUsername() == null) ? "New User" : "Edit User";
		}

		protected String initFormTitle() {
			return (user.getUsername() == null) ? null : user.getDisplayName();
		}

		private Layout createButtonControls() {
			final HorizontalLayout controlPanel = new HorizontalLayout();
			final Layout controlButtons = (new EditFormControlsGenerator<SimpleUser>(
					editUserForm)).createButtonControls();
			controlButtons.setSizeUndefined();
			controlPanel.addComponent(controlButtons);
			controlPanel.setComponentAlignment(controlButtons,
					Alignment.MIDDLE_CENTER);
			return controlPanel;
		}

		@Override
		protected Layout createBottomPanel() {
			final HorizontalLayout controlPanel = new HorizontalLayout();
			controlPanel.setMargin(true);
			controlPanel.setStyleName("more-info");
			controlPanel.setHeight("40px");
			controlPanel.setWidth("100%");
			moreInfoBtn = new Button("More information...",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {

						}
					});
			moreInfoBtn.addStyleName(UIConstants.THEME_LINK);
			controlPanel.addComponent(moreInfoBtn);
			controlPanel.setComponentAlignment(moreInfoBtn,
					Alignment.MIDDLE_LEFT);
			return controlPanel;
		}
	}

	private class AdvancedEditFormFieldFactory extends
			AbstractBeanFieldGroupEditFieldFactory<SimpleUser> {
		private static final long serialVersionUID = 1L;

		public AdvancedEditFormFieldFactory(GenericBeanForm<SimpleUser> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(final Object propertyId) {

			if (propertyId.equals("roleid")) {
				return new AdminRoleSelectionField();
			} else if (propertyId.equals("firstname")
					|| propertyId.equals("lastname")
					|| propertyId.equals("email")) {
				final TextField tf = new TextField();
				tf.setNullRepresentation("");
				tf.setRequired(true);
				tf.setRequiredError("This field must be not null");
				return tf;
			} else if (propertyId.equals("dateofbirth")) {
				UserAddViewImpl.this.cboDateBirthday = new DateComboboxSelectionField();
				return UserAddViewImpl.this.cboDateBirthday;
			} else if (propertyId.equals("timezone")) {
				UserAddViewImpl.this.cboTimezone = new TimeZoneSelectionField();
				if (UserAddViewImpl.this.user.getTimezone() != null) {
					UserAddViewImpl.this.cboTimezone.setTimeZone(TimezoneMapper
							.getTimezone(UserAddViewImpl.this.user
									.getTimezone()));
				} else {
					if (AppContext.getSession().getTimezone() != null) {
						UserAddViewImpl.this.cboTimezone
								.setTimeZone(TimezoneMapper
										.getTimezone(AppContext.getSession()
												.getTimezone()));
					}
				}
				return UserAddViewImpl.this.cboTimezone;
			} else if (propertyId.equals("country")) {
				final CountryComboBox cboCountry = new CountryComboBox();
				cboCountry
						.addValueChangeListener(new Property.ValueChangeListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void valueChange(
									final Property.ValueChangeEvent event) {
								UserAddViewImpl.this.user
										.setCountry((String) cboCountry
												.getValue());
							}
						});
				return cboCountry;
			}
			return null;
		}
	}

	private class AdminRoleSelectionField extends CustomField<Integer> {
		private static final long serialVersionUID = 1L;

		private RoleComboBox roleBox;

		public AdminRoleSelectionField() {
			roleBox = new RoleComboBox();
			this.roleBox
					.addValueChangeListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(
								final Property.ValueChangeEvent event) {
							getValue();

						}
					});
		}

		@Override
		public void setPropertyDataSource(Property newDataSource) {
			Object value = newDataSource.getValue();
			if (value == null) {
				Object itemId = roleBox.getItemIds().iterator().next();
				roleBox.setValue(itemId);
			} else if (value instanceof Integer) {
				roleBox.setValue(value);
			}
			super.setPropertyDataSource(newDataSource);
		}

		@Override
		public void commit() throws SourceException, InvalidValueException {
			Integer roleId = (Integer) roleBox.getValue();
			if (roleId == -1) {
				user.setIsAccountOwner(Boolean.TRUE);
			} else {
				user.setIsAccountOwner(Boolean.FALSE);
			}
			setInternalValue(roleId);
			super.commit();
		}

		@Override
		public Class<Integer> getType() {
			return Integer.class;
		}

		@Override
		protected Component initContent() {
			return roleBox;
		}
	}

}
