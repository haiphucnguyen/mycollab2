package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import org.vaadin.easyuploads.UploadField;
import org.vaadin.easyuploads.UploadField.FieldType;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class ProfileReadViewImpl extends AbstractView implements
		ProfileReadView, IFormAddView<User> {

	public static final int MAX_UPLOAD_SIZE = 20 * 1024 * 1024;

	private final EditForm formItem;
	private final HorizontalLayout viewLayout;
	private VerticalLayout userAvatar;

	public ProfileReadViewImpl() {
		super();
		viewLayout = new HorizontalLayout();
		viewLayout.setWidth("100%");
		viewLayout.setSpacing(true);
		this.setStyleName("userInfoContainer");
		this.setMargin(true);
		formItem = new EditForm();
		viewLayout.addComponent(formItem);
		this.addComponent(viewLayout);
		userAvatar = new VerticalLayout();
		viewLayout.addComponent(userAvatar);
	}

	private void displayUserAvatar() {
		userAvatar.removeAllComponents();
		Embedded cropField = UserAvatarControlFactory
				.createUserAvatarEmbeddedControl(AppContext.getUsername(), 100);
		userAvatar.addComponent(cropField);

		final UploadField avatarUploadField = new UploadField() {
			@Override
			protected void updateDisplay() {
				byte[] imageData = (byte[]) getValue();
				String mimeType = getLastMimeType();
				if (mimeType.equals("image/jpeg")) {
					// convert jpg to png file format
					imageData = ImageUtil.convertJpgToPngFormat(imageData);
					if (imageData == null) {
						throw new MyCollabException(
								"Do not support image format for avatar");
					} else {
						mimeType = "image/png";
					}
				}

				if (mimeType.equals("image/png")) {
					EventBus.getInstance().fireEvent(
							new ProfileEvent.GotoUploadPhoto(
									ProfileReadViewImpl.this, imageData));
				} else {
					throw new MyCollabException(
							"Upload file does not have valid image format. The supported formats are jpg/png");
				}
			}
		};
		avatarUploadField.setFieldType(FieldType.BYTE_ARRAY);
		avatarUploadField.setMaxUploadSize(MAX_UPLOAD_SIZE);
		userAvatar.addComponent(avatarUploadField);
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

			protected GridFormLayoutHelper basicInformation;

			protected GridFormLayoutHelper contactInformation;

			@Override
			public Layout getLayout() {
				AddViewLayout accountAddLayout = new AddViewLayout(AppContext
						.getSession().getDisplayName(), new ThemeResource(
						"icons/48/user/profile.png"));
				accountAddLayout.setWidth("600px");
				VerticalLayout layout = new VerticalLayout();
				layout.setSpacing(true);

				Label informationHeader = new Label("User Information");
				informationHeader.setStyleName("h2");

				Label contactHeader = new Label("Contact Information");
				contactHeader.setStyleName("h2");

				if (ScreenSize.hasSupport1024Pixels()) {
					basicInformation = new GridFormLayoutHelper(1, 5,
							UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION,
							"90px");
					contactInformation = new GridFormLayoutHelper(1, 2,
							UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION,
							"90px");
					informationHeader
							.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION);
					contactHeader
							.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION);
				} else if (ScreenSize.hasSupport1280Pixels()) {
					basicInformation = new GridFormLayoutHelper(1, 5);
					contactInformation = new GridFormLayoutHelper(1, 2);
				}

				layout.addComponent(informationHeader);
				layout.addComponent(basicInformation.getLayout());
				layout.setComponentAlignment(basicInformation.getLayout(),
						Alignment.MIDDLE_LEFT);
				layout.addComponent(contactHeader);
				layout.addComponent(contactInformation.getLayout());

				HorizontalLayout userInfoControls = createButtonControls();
				layout.addComponent(userInfoControls);
				accountAddLayout.addBody(layout);
				return accountAddLayout;
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("firstname")) {
					basicInformation.addComponent(field, "First Name", 0, 0);
				} else if (propertyId.equals("lastname")) {
					basicInformation.addComponent(field, "Last Name", 0, 1);
				} else if (propertyId.equals("email")) {
					basicInformation.addComponent(field, "Email", 0, 2);
				} else if (propertyId.equals("password")) {
					basicInformation.addComponent(field, "Password", 0, 3);
				} else if (propertyId.equals("dateofbirth")) {
					basicInformation.addComponent(field, "Birthday", 0, 4);
				} else if (propertyId.equals("website")) {
					contactInformation.addComponent(field, "Website", 0, 0);
				} else if (propertyId.equals("company")) {
					contactInformation.addComponent(field, "Company", 0, 1);
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
