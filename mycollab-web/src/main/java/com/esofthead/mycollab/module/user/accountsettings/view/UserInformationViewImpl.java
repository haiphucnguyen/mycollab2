package com.esofthead.mycollab.module.user.accountsettings.view;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserInformationViewImpl extends AbstractView implements
		UserInformationView, IFormAddView<User> {

	private final EditForm formItem;

	public UserInformationViewImpl() {
		super();
		formItem = new EditForm();
	}

	@Override
	public void attach() {
		this.removeAllComponents();
		User currentUser = AppContext.getSession();
		formItem.setItemDataSource(new BeanItem<User>(currentUser));
		this.addComponent(formItem);
		this.setStyleName("userInfoContainer");
	}

	public static class EditForm extends AdvancedEditBeanForm<User> {
		private static final long serialVersionUID = 1L;

		protected GridFormLayoutHelper informationLayout;

		public EditForm() {
			super();

			VerticalLayout layout = new VerticalLayout();

			Label informationHeader = new Label("User Informations");
			informationHeader.setStyleName("h1");
			layout.addComponent(informationHeader);

			informationLayout = new GridFormLayoutHelper(1, 9);
			layout.addComponent(informationLayout.getLayout());
			layout.setComponentAlignment(informationLayout.getLayout(),
					Alignment.MIDDLE_LEFT);

			this.setWriteThrough(true);
			this.setInvalidCommitted(false);

			HorizontalLayout userInfoControls = createButtonControls();
			layout.addComponent(userInfoControls);
			layout.setComponentAlignment(userInfoControls,
					Alignment.BOTTOM_CENTER);

			setLayout(layout);

			this.setFormFieldFactory(new EditFormFieldFactory());
		}

		private HorizontalLayout createButtonControls() {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setSpacing(true);
			layout.setStyleName("editInfoControl");
			Button saveBtn = new Button(SAVE_ACTION,
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							@SuppressWarnings("unchecked")
							User item = ((BeanItem<User>) EditForm.this
									.getItemDataSource()).getBean();
							if (EditForm.this.validateForm(item)) {
								EditForm.this.fireSaveForm(item);
							}
						}
					});
			layout.addComponent(saveBtn);
			layout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);

			Button cancelBtn = new Button(CANCEL_ACTION);
			layout.addComponent(cancelBtn);
			layout.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);

			return layout;
		}

		/*
		 * Override to get control over where fields are placed.
		 */
		@Override
		protected void attachField(Object propertyId, Field field) {

			if (propertyId.equals("firstname")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("firstname"),
						field, "First Name", 0, 0);
			} else if (propertyId.equals("lastname")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("lastname"),
						field, "Last Name", 0, 1);
			} else if (propertyId.equals("middlename")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("middlename"),
						field, "Middle Name", 0, 2);
			} else if (propertyId.equals("nickname")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("nickname"),
						field, "Nickname", 0, 3);
			} else if (propertyId.equals("dateofbirth")) {
				field.setSizeUndefined();
				informationLayout.addComponent(
						propertyId.equals("dateofbirth"), field, "Birthday", 0,
						4);
			} else if (propertyId.equals("email")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("email"),
						field, "Email", 0, 5);
			} else if (propertyId.equals("website")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("website"),
						field, "Website", 0, 6);
			} else if (propertyId.equals("displayname")) {
				field.setSizeUndefined();
				informationLayout.addComponent(
						propertyId.equals("displayname"), field,
						"Display Name", 0, 7);
			} else if (propertyId.equals("company")) {
				field.setSizeUndefined();
				informationLayout.addComponent(propertyId.equals("company"),
						field, "Company", 0, 8);
			}

		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {
			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {

				if (propertyId.equals("email")) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a valid email");
					return tf;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<User> getEditFormHandlers() {
		return formItem;
	}

	@Override
	public void editItem(User user) {
		formItem.setItemDataSource(new BeanItem<User>(user));
	}

}
