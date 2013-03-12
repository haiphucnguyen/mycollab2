package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import org.vaadin.easyuploads.UploadField;
import org.vaadin.easyuploads.UploadField.FieldType;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.IFormAddView;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
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
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
@ViewComponent
public class ProfileReadViewImpl extends AbstractView implements
		ProfileReadView, IFormAddView<User> {

	public static final int MAX_UPLOAD_SIZE = 20 * 1024 * 1024;

	private final PreviewForm formItem;
	private final HorizontalLayout viewLayout;
	private VerticalLayout userAvatar;

	public ProfileReadViewImpl() {
		super();
		viewLayout = new HorizontalLayout();
		viewLayout.setWidth("100%");
		viewLayout.setSpacing(true);
		this.setStyleName("userInfoContainer");
		this.setMargin(true);
		formItem = new PreviewForm();
		formItem.setWidth("200px");
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

	private class PreviewForm extends AdvancedPreviewBeanForm<User> {

		private static final long serialVersionUID = 1L;
		
		private User user;
		
		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new PreviewFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		private class FormLayoutFactory implements IFormLayoutFactory {

			protected GridFormLayoutHelper basicInformation;

			protected GridFormLayoutHelper contactInformation;

			@Override
			public Layout getLayout() {
				AddViewLayout accountAddLayout = new AddViewLayout(AppContext
						.getSession().getDisplayName(), new ThemeResource(
						"icons/48/user/profile.png"));
				accountAddLayout.setWidth("550px");
				VerticalLayout layout = new VerticalLayout();
				layout.setSpacing(true);
				
				HorizontalLayout passLayout = new HorizontalLayout();
				passLayout.setSpacing(true);
				Label lbPassword = new Label("Password");
				passLayout.addComponent(lbPassword);
				passLayout.setComponentAlignment(lbPassword, Alignment.MIDDLE_CENTER);
				Button btnChangePassword = new Button("Change", new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						getWidget().getWindow().addWindow(new PasswordChangeWindow(user));
					}
				});
				passLayout.addComponent(btnChangePassword);
				btnChangePassword.setStyleName("link");
				passLayout.setComponentAlignment(btnChangePassword, Alignment.MIDDLE_CENTER);
				layout.addComponent(passLayout);

				Label informationHeader = new Label("Basic Information");
				informationHeader.setStyleName("h2");

				Label contactHeader = new Label("Advance Information");
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
				Button btnChangeBasicInfo = new Button("Change Basic Information", new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						getWidget().getWindow().addWindow(new BasicInfoChangeWindow(user));
					}
				});
				btnChangeBasicInfo.addStyleName("link");
				layout.addComponent(btnChangeBasicInfo);
				layout.setComponentAlignment(btnChangeBasicInfo, Alignment.MIDDLE_RIGHT);
				layout.addComponent(contactHeader);
				layout.addComponent(contactInformation.getLayout());
				Button btnChangeAdvanceInfo = new Button("Change Advanced Information", new Button.ClickListener() {
					
					@Override
					public void buttonClick(ClickEvent event) {
						getWidget().getWindow().addWindow(new AdvancedInfoChangeWindow(user));
					}
				});
				btnChangeAdvanceInfo.addStyleName("link");
				layout.addComponent(btnChangeAdvanceInfo);
				layout.setComponentAlignment(btnChangeAdvanceInfo, Alignment.MIDDLE_RIGHT);

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
				} else if (propertyId.equals("dateofbirth")) {
					basicInformation.addComponent(field, "Birthday", 0, 3);
				} else if (propertyId.equals("website")) {
					contactInformation.addComponent(field, "Website", 0, 0);
				} else if (propertyId.equals("company")) {
					contactInformation.addComponent(field, "Company", 0, 1);
				}
			}
		}

		private class PreviewFormFieldFactory extends DefaultEditFormFieldFactory {

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				String value = "";
				if (propertyId.equals("firstname")) {
					value = user.getFirstname();
				} else if (propertyId.equals("lastname")) {
					value = user.getLastname();
				} else if (propertyId.equals("email")) {
					value = user.getEmail();
				} else if (propertyId.equals("dateofbirth")) {
					value = AppContext.formatDate(user.getDateofbirth());
				} else if (propertyId.equals("website")) {
					value = user.getWebsite();
					return new DefaultFormViewFieldFactory.FormUrlLinkViewField(value);
				} else if (propertyId.equals("company")) {
					value = user.getCompany();
				} 
				return new DefaultFormViewFieldFactory.LabelViewField(value);
			}
		}
	}

	@Override
	public void editItem(User user) {
		formItem.setUser(user);
		formItem.setItemDataSource(new BeanItem<User>(user));
		displayUserAvatar();
	}
}
