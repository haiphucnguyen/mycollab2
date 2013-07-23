/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.Collection;
import java.util.Date;

import org.vaadin.addon.customfield.FieldWrapper;

import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.core.utils.TimezoneMapper.TimezoneExt;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ProfileFormLayoutFactory;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.view.component.RoleComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DateComboboxSelectionField;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormViewField;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.TimeZoneSelection;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class UserAddViewImpl extends AbstractView implements UserAddView {

	private static final long serialVersionUID = 1L;
	private final UserAddViewImpl.EditForm editForm;
	private SimpleUser user;
	private DateComboboxSelectionField cboDateBirthday;
	private TimeZoneSelection cboTimezone;

	public UserAddViewImpl() {
		super();
		this.editForm = new UserAddViewImpl.EditForm();
		this.addComponent(this.editForm);
	}

	@Override
	public void editItem(final SimpleUser item) {
		this.user = item;
		this.editForm.setItemDataSource(new BeanItem<SimpleUser>(this.user));
	}

	@Override
	public Date getBirthday() {
		return this.cboDateBirthday.getDate();
	}

	@Override
	public TimezoneExt getTimezone() {
		return this.cboTimezone.getTimeZone();
	}

	private class EditForm extends AdvancedEditBeanForm<SimpleUser> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new UserAddViewImpl.EditForm.FormLayoutFactory());
			this.setFormFieldFactory(new UserAddViewImpl.EditForm.EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends ProfileFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(
						(UserAddViewImpl.this.user.getUsername() == null) ? "Create User"
								: (UserAddViewImpl.this.user.getFirstname()
										+ " " + UserAddViewImpl.this.user
										.getLastname()));
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<SimpleUser>(
						UserAddViewImpl.EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return this.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("isAdmin")) {
					if ((user.getIsAdmin() == null)
							|| (user.getIsAdmin() == false)) {
						AdminRoleSelectionField roleSelectionField = new AdminRoleSelectionField();
						if (user.getRoleid() != null) {
							roleSelectionField.setRoleId(user.getRoleid());
						}
						return roleSelectionField;
					} else {
						return new FormViewField("Account Owner");
					}
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
					if (UserAddViewImpl.this.user.getDateofbirth() != null) {
						UserAddViewImpl.this.cboDateBirthday
								.setDate(UserAddViewImpl.this.user
										.getDateofbirth());
					}
					return UserAddViewImpl.this.cboDateBirthday;
				} else if (propertyId.equals("timezone")) {
					UserAddViewImpl.this.cboTimezone = new TimeZoneSelection();
					if (UserAddViewImpl.this.user.getTimezone() != null) {
						UserAddViewImpl.this.cboTimezone
								.setTimeZone(TimezoneMapper
										.getTimezone(UserAddViewImpl.this.user
												.getTimezone()));
					} else {
						if (AppContext.getSession().getTimezone() != null) {
							UserAddViewImpl.this.cboTimezone
									.setTimeZone(TimezoneMapper
											.getTimezone(AppContext
													.getSession().getTimezone()));
						}
					}
					return UserAddViewImpl.this.cboTimezone;
				} else if (propertyId.equals("country")) {
					final CountryComboBox cboCountry = new CountryComboBox();
					cboCountry.addListener(new Property.ValueChangeListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void valueChange(
								final Property.ValueChangeEvent event) {
							UserAddViewImpl.this.user
									.setCountry((String) cboCountry.getValue());
						}
					});
					return cboCountry;
				}
				return null;
			}
		}

		private class AdminRoleSelectionField extends FieldWrapper<Integer>
				implements FieldSelection {
			private static final long serialVersionUID = 1L;

			private RoleComboBox roleBox;

			public AdminRoleSelectionField() {
				super(new TextField(""), Integer.class);

				roleBox = new RoleComboBox();
				roleBox.addListener(new Property.ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(Property.ValueChangeEvent event) {
						Object roleObj = roleBox.getValue();
						if (roleObj != null && (roleObj instanceof Integer)) {
							user.setRoleid((Integer) roleObj);
						}
					}
				});
				this.setCompositionRoot(roleBox);
			}

			public void setRoleId(int roleId) {
				roleBox.setValue(roleId);
			}

			@Override
			public Class<Integer> getType() {
				return Integer.class;
			}

			@Override
			public void fireValueChange(Object data) {
				Integer roleIdVal = (Integer) data;
				if (roleIdVal != null) {
					this.getWrappedField().setValue(roleIdVal);
				} else {
					this.getWrappedField().setValue(null);
				}

			}
		}
	}

	@Override
	public HasEditFormHandlers<SimpleUser> getEditFormHandlers() {
		return this.editForm;
	}
}
