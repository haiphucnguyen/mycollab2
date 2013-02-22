package com.esofthead.mycollab.module.user.accountsettings.view;

import com.davengo.web.vaadin.crop.CropField;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class UserInformationViewImpl extends AbstractView implements
		UserInformationView, IFormAddView<User> {

	private final EditForm formItem;
	private final HorizontalLayout viewLayout;

	public UserInformationViewImpl() {
		super();
		viewLayout = new HorizontalLayout();
		this.setStyleName("userInfoContainer");
		this.setMargin(true);
		formItem = new EditForm();
		viewLayout.addComponent(formItem);
		this.addComponent(viewLayout);
	}

	private void displayUserAvatar() {
		VerticalLayout userAvatar = new VerticalLayout();
		CropField cropField = new CropField(new ThemeResource(
				"icons/default_user_avatar_256_256.png"));
		userAvatar.addComponent(cropField);
		viewLayout.addComponent(userAvatar);
	}

	private static class EditForm extends AdvancedEditBeanForm<User> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
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

		private class FormLayoutFactory implements IFormLayoutFactory {

			protected GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				VerticalLayout layout = new VerticalLayout();

				Label informationHeader = new Label("User Information");
				informationHeader.setStyleName("h2");
				layout.addComponent(informationHeader);

				informationLayout = new GridFormLayoutHelper(1, 8);
				layout.addComponent(informationLayout.getLayout());
				layout.setComponentAlignment(informationLayout.getLayout(),
						Alignment.MIDDLE_LEFT);

				HorizontalLayout userInfoControls = createButtonControls();
				layout.addComponent(userInfoControls);
				layout.setComponentAlignment(userInfoControls,
						Alignment.BOTTOM_CENTER);
				return layout;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("firstname")) {
					informationLayout.addComponent(field, "First Name", 0, 0);
				} else if (propertyId.equals("lastname")) {
					informationLayout.addComponent(field, "Last Name", 0, 1);
				} else if (propertyId.equals("email")) {
					informationLayout.addComponent(field, "Email", 0, 2);
				} else if (propertyId.equals("password")) {
					informationLayout.addComponent(field, "Password", 0, 3);
				} else if (propertyId.equals("dateofbirth")) {
					informationLayout.addComponent(field, "Birthday", 0, 4);
				} else if (propertyId.equals("website")) {
					informationLayout.addComponent(field, "Website", 0, 5);
				} else if (propertyId.equals("displayname")) {
					informationLayout.addComponent(field, "Display Name", 0, 6);
				} else if (propertyId.equals("company")) {
					informationLayout.addComponent(field, "Company", 0, 7);
				}
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
				} else if (propertyId.equals("password")) {
					PasswordField pf = new PasswordField();
					return pf;
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
		displayUserAvatar();
	}
}
