package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import org.vaadin.easyuploads.UploadField;
import org.vaadin.easyuploads.UploadField.FieldType;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.ImageUtil;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class ProfileReadViewImpl extends AbstractView implements
		ProfileReadView {

	public static final int MAX_UPLOAD_SIZE = 20 * 1024 * 1024;

	private final PreviewForm formItem;
	private final HorizontalLayout viewLayout;
	private final VerticalLayout userAvatar;

	public ProfileReadViewImpl() {
		super();
		this.viewLayout = new HorizontalLayout();
		this.viewLayout.setWidth("100%");
		this.viewLayout.setSpacing(true);
		this.setStyleName("userInfoContainer");
		this.setMargin(true);
		this.userAvatar = new VerticalLayout();
		this.userAvatar.setWidth(Sizeable.SIZE_UNDEFINED, 0);
		this.userAvatar.setSpacing(true);
		this.formItem = new PreviewForm();
		this.formItem.setWidth("100%");
		this.viewLayout.addComponent(this.formItem);
		this.addComponent(this.viewLayout);
	}

	private void displayUserAvatar() {
		this.userAvatar.removeAllComponents();
		final Embedded cropField = UserAvatarControlFactory
				.createUserAvatarEmbeddedComponent(
						AppContext.getUserAvatarId(), 100);
		this.userAvatar.addComponent(cropField);

		final UploadField avatarUploadField = new UploadField() {
			@Override
			protected void updateDisplay() {
				byte[] imageData = (byte[]) this.getValue();
				String mimeType = this.getLastMimeType();
				if (mimeType.equals("image/jpeg")) {
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
		avatarUploadField.setWidth("100px");
		this.userAvatar.addComponent(avatarUploadField);
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<User> {

		private static final long serialVersionUID = 1L;

		private User user;

		public void setUser(final User user) {
			this.user = user;
		}

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new PreviewFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		private class FormLayoutFactory implements IFormLayoutFactory {

			protected GridFormLayoutHelper basicInformation;

			protected GridFormLayoutHelper contactInformation;

			protected GridFormLayoutHelper advanceInformation;

			@Override
			public Layout getLayout() {
				final AddViewLayout accountAddLayout = new AddViewLayout(
						"User Information",
						MyCollabResource
								.newResource("icons/22/user/menu_profile.png"));
				accountAddLayout.setWidth("100%");
				final VerticalLayout layout = new VerticalLayout();

				final HorizontalLayout avatarAndPass = new HorizontalLayout();
				avatarAndPass.setSpacing(true);
				avatarAndPass.setMargin(true);
				avatarAndPass.setWidth("100%");
				avatarAndPass.addStyleName("avatar-pass-wrapper");
				avatarAndPass.addComponent(ProfileReadViewImpl.this.userAvatar);
				final Button btnChangePassword = new Button("Change Password",
						new Button.ClickListener() {

							@Override
							public void buttonClick(final ClickEvent event) {
								ProfileReadViewImpl.this
										.getWidget()
										.getWindow()
										.addWindow(
												new PasswordChangeWindow(
														PreviewForm.this.user));
							}
						});
				final VerticalLayout passLayout = new VerticalLayout();
				passLayout.setMargin(true, false, false, false);
				final Label userName = new Label(AppContext.getSession()
						.getDisplayName());
				userName.setStyleName("h1");
				passLayout.addComponent(userName);
				passLayout.addComponent(btnChangePassword);
				btnChangePassword.setStyleName("link");
				passLayout.setComponentAlignment(btnChangePassword,
						Alignment.MIDDLE_LEFT);
				avatarAndPass.addComponent(passLayout);
				avatarAndPass.setComponentAlignment(passLayout,
						Alignment.TOP_LEFT);
				avatarAndPass.setExpandRatio(passLayout, 1.0f);
				layout.addComponent(avatarAndPass);

				final CssLayout basicInformationHeader = new CssLayout();
				basicInformationHeader.setWidth("100%");
				basicInformationHeader.addStyleName("info-block-header");
				final Label basicInformationHeaderLbl = new Label(
						"Basic Information");
				basicInformationHeaderLbl.setStyleName("h2");
				basicInformationHeaderLbl.setWidth(Sizeable.SIZE_UNDEFINED, 0);
				basicInformationHeader.addComponent(basicInformationHeaderLbl);

				final CssLayout contactInformationHeader = new CssLayout();
				contactInformationHeader.setWidth("100%");
				contactInformationHeader.addStyleName("info-block-header");
				final Label contactInformationHeaderLbl = new Label(
						"Contact Information");
				contactInformationHeaderLbl.setStyleName("h2");
				contactInformationHeaderLbl
						.setWidth(Sizeable.SIZE_UNDEFINED, 0);
				contactInformationHeader
						.addComponent(contactInformationHeaderLbl);

				final CssLayout advanceInfoHeader = new CssLayout();
				advanceInfoHeader.setWidth("100%");
				advanceInfoHeader.addStyleName("info-block-header");
				final Label advanceInfoHeaderLbl = new Label(
						"Advanced Information");
				advanceInfoHeaderLbl.setStyleName("h2");
				advanceInfoHeaderLbl.setWidth(Sizeable.SIZE_UNDEFINED, 0);
				advanceInfoHeader.addComponent(advanceInfoHeaderLbl);

				this.basicInformation = new GridFormLayoutHelper(1, 6, "100%",
						"167px", Alignment.MIDDLE_LEFT);
				this.basicInformation.getLayout().setMargin(false);
				this.basicInformation.getLayout().setWidth("100%");
				this.basicInformation.getLayout().addStyleName(
						"colored-gridlayout");

				this.contactInformation = new GridFormLayoutHelper(1, 5,
						"100%", "167px", Alignment.MIDDLE_LEFT);
				this.contactInformation.getLayout().setMargin(false);
				this.contactInformation.getLayout().setWidth("100%");
				this.contactInformation.getLayout().addStyleName(
						"colored-gridlayout");

				this.advanceInformation = new GridFormLayoutHelper(1, 3,
						"100%", "167px", Alignment.MIDDLE_LEFT);
				this.advanceInformation.getLayout().setMargin(false);
				this.advanceInformation.getLayout().setWidth("100%");
				this.advanceInformation.getLayout().addStyleName(
						"colored-gridlayout");

				layout.addComponent(basicInformationHeader);
				layout.addComponent(this.basicInformation.getLayout());
				final Button btnChangeBasicInfo = new Button("Edit",
						new Button.ClickListener() {

							@Override
							public void buttonClick(final ClickEvent event) {
								ProfileReadViewImpl.this
										.getWidget()
										.getWindow()
										.addWindow(
												new BasicInfoChangeWindow(
														PreviewForm.this.user));
							}
						});
				btnChangeBasicInfo.addStyleName("link");
				basicInformationHeader.addComponent(btnChangeBasicInfo);

				layout.addComponent(contactInformationHeader);
				layout.addComponent(this.contactInformation.getLayout());
				final Button btnChangeContactInfo = new Button("Edit",
						new Button.ClickListener() {

							@Override
							public void buttonClick(final ClickEvent event) {
								ProfileReadViewImpl.this
										.getWidget()
										.getWindow()
										.addWindow(
												new ContactInfoChangeWindow(
														PreviewForm.this.user));
							}
						});
				btnChangeContactInfo.addStyleName("link");
				contactInformationHeader.addComponent(btnChangeContactInfo);

				layout.addComponent(advanceInfoHeader);
				layout.addComponent(this.advanceInformation.getLayout());
				final Button btnChangeAdvanceInfo = new Button("Edit",
						new Button.ClickListener() {

							@Override
							public void buttonClick(final ClickEvent event) {
								ProfileReadViewImpl.this
										.getWidget()
										.getWindow()
										.addWindow(
												new AdvancedInfoChangeWindow(
														PreviewForm.this.user));
							}
						});
				btnChangeAdvanceInfo.addStyleName("link");
				advanceInfoHeader.addComponent(btnChangeAdvanceInfo);

				accountAddLayout.addBody(layout);
				return accountAddLayout;
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("firstname")) {
					this.basicInformation.addComponent(field, "First Name", 0,
							0);
				} else if (propertyId.equals("lastname")) {
					this.basicInformation
							.addComponent(field, "Last Name", 0, 1);
				} else if (propertyId.equals("email")) {
					this.basicInformation.addComponent(field, "Email", 0, 2);
				} else if (propertyId.equals("dateofbirth")) {
					this.basicInformation.addComponent(field, "Birthday", 0, 3);
				} else if (propertyId.equals("timezone")) {
					this.basicInformation.addComponent(field, "Timezone", 0, 4);
				} else if (propertyId.equals("website")) {
					this.advanceInformation
							.addComponent(field, "Website", 0, 0);
				} else if (propertyId.equals("company")) {
					this.advanceInformation
							.addComponent(field, "Company", 0, 1);
				} else if (propertyId.equals("country")) {
					this.advanceInformation
							.addComponent(field, "Country", 0, 2);
				} else if (propertyId.equals("workphone")) {
					this.contactInformation.addComponent(field, "Work phone",
							0, 0);
				} else if (propertyId.equals("homephone")) {
					this.contactInformation.addComponent(field, "Home phone",
							0, 1);
				} else if (propertyId.equals("facebookaccount")) {
					this.contactInformation.addComponent(field, "Facebook", 0,
							2);
				} else if (propertyId.equals("twitteraccount")) {
					this.contactInformation
							.addComponent(field, "Twitter", 0, 3);
				} else if (propertyId.equals("skypecontact")) {
					this.contactInformation.addComponent(field, "Skype", 0, 4);
				}
			}
		}

		private class PreviewFormFieldFactory extends
				DefaultFormViewFieldFactory {

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				String value = "";

				if (propertyId.equals("email")) {
					return new DefaultFormViewFieldFactory.FormEmailLinkViewField(
							PreviewForm.this.user.getEmail());
				} else if (propertyId.equals("dateofbirth")) {
					value = AppContext.formatDate(PreviewForm.this.user
							.getDateofbirth());
					return new DefaultFormViewFieldFactory.FormViewField(value);
				} else if (propertyId.equals("timezone")) {
					value = TimezoneMapper.getTimezone(
							PreviewForm.this.user.getTimezone())
							.getDisplayName();
					return new DefaultFormViewFieldFactory.FormViewField(value);
				} else if (propertyId.equals("website")) {
					value = PreviewForm.this.user.getWebsite();
					return new DefaultFormViewFieldFactory.FormUrlLinkViewField(
							value);
				} else if (propertyId.equals("facebookaccount")) {
					return new DefaultFormViewFieldFactory.FormUrlSocialNetworkLinkViewField(
							PreviewForm.this.user.getFacebookaccount(),
							"https://www.facebook.com/"
									+ PreviewForm.this.user
											.getFacebookaccount());
				} else if (propertyId.equals("twitteraccount")) {
					return new DefaultFormViewFieldFactory.FormUrlSocialNetworkLinkViewField(
							PreviewForm.this.user.getTwitteraccount(),
							"https://www.twitter.com/"
									+ PreviewForm.this.user.getTwitteraccount());
				} else if (propertyId.equals("skypecontact")) {
					return new DefaultFormViewFieldFactory.FormUrlSocialNetworkLinkViewField(
							PreviewForm.this.user.getSkypecontact(), "skype:"
									+ PreviewForm.this.user.getSkypecontact()
									+ "?chat");
				}
				return null;
			}
		}
	}

	@Override
	public void previewItem(final User user) {
		this.formItem.setUser(user);
		this.formItem.setItemDataSource(new BeanItem<User>(user));
		this.displayUserAvatar();
	}

	@Override
	public User getItem() {
		return null;
	}
}
