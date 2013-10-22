/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.Collection;
import java.util.Date;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.core.utils.TimezoneMapper.TimezoneExt;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ProfileFormLayoutFactory;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.view.component.RoleComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.CountryComboBox;
import com.esofthead.mycollab.vaadin.ui.DateComboboxSelectionField;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.TimeZoneSelection;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class UserAddViewImpl extends AbstractView implements UserAddView {

	private static final long serialVersionUID = 1L;
	private final UserAddViewImpl.AdvanceEditForm advanceEditForm;
	private final UserAddViewImpl.BasicEditForm basicEditForm;
	private SimpleUser user;
	private DateComboboxSelectionField cboDateBirthday;
	private TimeZoneSelection cboTimezone;
	private boolean isFullLayout = false;

	public UserAddViewImpl() {
		super();
		isFullLayout = false;
		this.advanceEditForm = new UserAddViewImpl.AdvanceEditForm();
		this.basicEditForm = new UserAddViewImpl.BasicEditForm();
		this.addComponent(this.basicEditForm);
	}

	@Override
	public void editItem(final SimpleUser item) {
		this.user = item;
		this.removeAllComponents();
		if (user.getIsLoadEdit() != null && user.getIsLoadEdit() == false) {
			this.addComponent(this.basicEditForm);
			this.basicEditForm.setItemDataSource(new BeanItem<SimpleUser>(
					this.user));
		} else {
			this.addComponent(this.advanceEditForm);
			this.advanceEditForm.setItemDataSource(new BeanItem<SimpleUser>(
					this.user));
		}
	}

	@Override
	public Date getBirthday() {
		return this.cboDateBirthday.getDate();
	}

	@Override
	public TimezoneExt getTimezone() {
		return this.cboTimezone.getTimeZone();
	}

	private class AdvanceEditForm extends AdvancedEditBeanForm<SimpleUser> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new UserAddViewImpl.AdvanceEditForm.FormLayoutFactory());
			this.setFormFieldFactory(new UserAddViewImpl.AdvanceEditForm.EditFormFieldFactory());
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
						UserAddViewImpl.AdvanceEditForm.this))
						.createButtonControls();
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

				if (propertyId.equals("roleid")) {
					AdminRoleSelectionField roleSelectionField = new AdminRoleSelectionField();
					if (user.getRoleid() != null) {
						roleSelectionField.setRoleId(user.getRoleid());
					} else {
						roleSelectionField.setRoleId(-1);
					}
					return roleSelectionField;
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

		private class AdminRoleSelectionField extends CustomField {
			private static final long serialVersionUID = 1L;

			private RoleComboBox roleBox;

			public AdminRoleSelectionField() {
				roleBox = new RoleComboBox();
				this.roleBox.addListener(new Property.ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(
							final Property.ValueChangeEvent event) {
						getValue();

					}
				});
				this.setCompositionRoot(roleBox);
			}

			public void setRoleId(int roleId) {
				roleBox.setValue(roleId);
			}

			public Object getValue() {
				Integer roleId = (Integer) AdminRoleSelectionField.this.roleBox
						.getValue();
				Boolean resultVal = null;
				if (roleId == -1) {
					UserAddViewImpl.this.user.setIsAccountOwner(Boolean.TRUE);
					UserAddViewImpl.this.user.setRoleid(null);
					resultVal = Boolean.TRUE;
				} else {
					UserAddViewImpl.this.user
							.setRoleid((Integer) AdminRoleSelectionField.this.roleBox
									.getValue());
					UserAddViewImpl.this.user.setIsAccountOwner(Boolean.FALSE);
					resultVal = Boolean.FALSE;
				}
				return resultVal;
			}

			@Override
			public Class<Integer> getType() {
				return Integer.class;
			}
		}
	}

	private class BasicEditForm extends AdvancedEditBeanForm<SimpleUser> {

		private static final long serialVersionUID = 1L;

		private Button moreInfoBtn;

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new UserAddViewImpl.BasicEditForm.FormLayoutFactory());
			this.setFormFieldFactory(new UserAddViewImpl.BasicEditForm.EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);

			UserAddViewImpl.this.user.setFirstname(" ");
			UserAddViewImpl.this.user.setLastname(" ");
		}

		private class FormLayoutFactory extends BasicProfileFormLayoutFactory {

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
						UserAddViewImpl.BasicEditForm.this))
						.createButtonControls();
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
				final HorizontalLayout controlPanel = new HorizontalLayout();
				controlPanel.setMargin(true);
				controlPanel.setHeight("40px");
				moreInfoBtn = new Button("More information...",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								isFullLayout = true;
								UserAddViewImpl.this
										.removeComponent(UserAddViewImpl.this.basicEditForm);
								UserAddViewImpl.this
										.addComponent(advanceEditForm);
							}
						});
				moreInfoBtn.addStyleName(UIConstants.THEME_LINK);
				controlPanel.addComponent(moreInfoBtn);
				controlPanel.setComponentAlignment(moreInfoBtn,
						Alignment.MIDDLE_LEFT);
				return controlPanel;
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("roleid")) {
					AdminRoleSelectionField roleSelectionField = new AdminRoleSelectionField();
					if (user.getRoleid() != null) {
						roleSelectionField.setRoleId(user.getRoleid());
					} else {
						roleSelectionField.setRoleId(-1);
					}
					return roleSelectionField;
				} else if (propertyId.equals("email")) {
					TextField tf = new TextField();
					tf.setRequired(true);
					tf.setRequiredError("This field must be not null");
					tf.setNullRepresentation("");
					tf.setWidth("300px");
					return tf;
				}
				return null;
			}
		}

		private class AdminRoleSelectionField extends CustomField {
			private static final long serialVersionUID = 1L;

			private RoleComboBox roleBox;

			public AdminRoleSelectionField() {
				roleBox = new RoleComboBox();
				this.roleBox.addListener(new Property.ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(
							final Property.ValueChangeEvent event) {
						getValue();

					}
				});
				this.setCompositionRoot(roleBox);
			}

			public void setRoleId(int roleId) {
				roleBox.setValue(roleId);
			}

			public Object getValue() {
				Integer roleId = (Integer) AdminRoleSelectionField.this.roleBox
						.getValue();
				Boolean resultVal = null;
				if (roleId == -1) {
					UserAddViewImpl.this.user.setIsAccountOwner(Boolean.TRUE);
					UserAddViewImpl.this.user.setRoleid(null);
					resultVal = Boolean.TRUE;
				} else {
					UserAddViewImpl.this.user.setRoleid(roleId);
					UserAddViewImpl.this.user.setIsAccountOwner(Boolean.FALSE);
					resultVal = Boolean.FALSE;
				}
				return resultVal;
			}

			@Override
			public Class<Integer> getType() {
				return Integer.class;
			}
		}
	}

	private abstract class BasicProfileFormLayoutFactory implements
			IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private final String title;
		private BasicUserInformationLayout userInformationLayout;

		public BasicProfileFormLayoutFactory(final String title) {
			this.title = title;
		}

		@Override
		public Layout getLayout() {
			final AddViewLayout userAddLayout = new AddViewLayout(this.title,
					MyCollabResource.newResource("icons/22/user/user.png"));

			final Layout topPanel = this.createTopPanel();
			if (topPanel != null) {
				userAddLayout.addTopControls(topPanel);
			}

			this.userInformationLayout = new BasicUserInformationLayout();
			this.userInformationLayout.getLayout().setWidth("100%");
			userAddLayout.addBody(this.userInformationLayout.getLayout());

			final Layout bottomPanel = this.createBottomPanel();
			if (bottomPanel != null) {
				userAddLayout.addBottomControls(bottomPanel);
			}

			return userAddLayout;
		}

		protected abstract Layout createTopPanel();

		protected abstract Layout createBottomPanel();

		@Override
		public void attachField(final Object propertyId, final Field field) {
			this.userInformationLayout.attachField(propertyId, field);
		}

		private class BasicUserInformationLayout implements IFormLayoutFactory {
			private static final long serialVersionUID = 1L;
			private GridFormLayoutHelper basicInformationLayout;

			@Override
			public Layout getLayout() {
				final VerticalLayout layout = new VerticalLayout();
				final Label organizationHeader = new Label(
						"Basic User Information");
				organizationHeader.setStyleName("h2");
				layout.addComponent(organizationHeader);

				this.basicInformationLayout = new GridFormLayoutHelper(2, 7,
						"100%", "167px", Alignment.MIDDLE_LEFT);
				this.basicInformationLayout.getLayout().setWidth("100%");
				this.basicInformationLayout.getLayout().setMargin(false);
				this.basicInformationLayout.getLayout().addStyleName(
						UIConstants.COLORED_GRIDLAYOUT);

				layout.addComponent(this.basicInformationLayout.getLayout());
				return layout;
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("email")) {
					this.basicInformationLayout.addComponent(field, "Email", 0,
							0, "167px");
				} else if (propertyId.equals("roleid")) {
					this.basicInformationLayout.addComponent(field, "Role", 1,
							0);
				}
			}
		}
	}

	@Override
	public HasEditFormHandlers<SimpleUser> getEditFormHandlers() {
		if (isFullLayout)
			return this.advanceEditForm;
		return this.basicEditForm;
	}
}
