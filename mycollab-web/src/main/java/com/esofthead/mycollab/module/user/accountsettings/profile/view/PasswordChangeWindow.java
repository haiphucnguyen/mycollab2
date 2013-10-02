package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.accountsettings.localization.UserI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.vaadin.ui.MessageBox.ButtonType;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class PasswordChangeWindow extends Window {

	private PasswordField txtNewPassword;
	private PasswordField txtConfirmPassword;

	private final User user;

	public PasswordChangeWindow(final User user) {
		this.setWidth("500px");
		this.initUI();
		this.center();
		this.user = user;
		this.setCaption(LocalizationHelper
				.getMessage(UserI18nEnum.CHANGE_PASSWORD_WINDOW_TITLE));
	}

	private void initUI() {

		final VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		final Label lbInstruct1 = new Label(
				LocalizationHelper
						.getMessage(UserI18nEnum.PASSWORD_INSTRUCT_LABEL_1));
		mainLayout.addComponent(lbInstruct1);
		mainLayout.setComponentAlignment(lbInstruct1, Alignment.MIDDLE_LEFT);

		final Label lbInstruct2 = new Label(
				LocalizationHelper
						.getMessage(UserI18nEnum.PASSWORD_INSTRUCT_LABEL_2));
		mainLayout.addComponent(lbInstruct2);
		mainLayout.setComponentAlignment(lbInstruct2, Alignment.MIDDLE_LEFT);

		final GridFormLayoutHelper passInfo = new GridFormLayoutHelper(1, 3,
				UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION, "150px");

		this.txtNewPassword = (PasswordField) passInfo.addComponent(
				new PasswordField(), "New password", 0, 0);
		this.txtConfirmPassword = (PasswordField) passInfo.addComponent(
				new PasswordField(), "Confirm new password", 0, 1);

		passInfo.getLayout().setSpacing(true);
		mainLayout.addComponent(passInfo.getLayout());
		mainLayout.setComponentAlignment(passInfo.getLayout(),
				Alignment.MIDDLE_CENTER);

		final Label lbSpace = new Label();
		mainLayout.addComponent(lbSpace);
		mainLayout.setExpandRatio(lbSpace, 1.0f);

		final HorizontalLayout hlayoutControls = new HorizontalLayout();

		final Button cancelBtn = new Button(
				LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						PasswordChangeWindow.this.close();
					}
				});

		cancelBtn.setStyleName("link");
		hlayoutControls.addComponent(cancelBtn);
		hlayoutControls.setSpacing(true);
		hlayoutControls.setMargin(true);
		hlayoutControls.setComponentAlignment(cancelBtn,
				Alignment.MIDDLE_CENTER);

		final Button sendBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						PasswordChangeWindow.this.changePassword();
					}
				});
		sendBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		hlayoutControls.addComponent(sendBtn);
		hlayoutControls.setComponentAlignment(sendBtn, Alignment.MIDDLE_CENTER);

		mainLayout.addComponent(hlayoutControls);
		mainLayout.setComponentAlignment(hlayoutControls,
				Alignment.MIDDLE_RIGHT);

		this.setModal(true);
		this.setContent(mainLayout);
	}

	private void showMessage(final String title, final String message) {
		final MessageBox mb = new MessageBox(AppContext.getApplication()
				.getMainWindow(), title, MessageBox.Icon.WARN, message,
				new MessageBox.ButtonConfig(ButtonType.OK, LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_OK_LABEL)));
		mb.show();
	}

	private void changePassword() {

		this.txtNewPassword.removeStyleName("errorField");
		this.txtConfirmPassword.removeStyleName("errorField");

		if (!this.txtNewPassword.getValue().equals(
				this.txtConfirmPassword.getValue())) {
			this.showMessage(LocalizationHelper
					.getMessage(GenericI18Enum.WARNING_WINDOW_TITLE),
					LocalizationHelper
							.getMessage(UserI18nEnum.PASSWORDS_ARE_NOT_MATCH));
			this.txtNewPassword.addStyleName("errorField");
			this.txtConfirmPassword.addStyleName("errorField");
			return;
		}

		this.user.setPassword((String) this.txtNewPassword.getValue());

		final UserService userService = ApplicationContextUtil
				.getSpringBean(UserService.class);
		userService.updateWithSession(this.user, AppContext.getUsername());

		EventBus.getInstance().fireEvent(
				new ProfileEvent.GotoProfileView(PasswordChangeWindow.this,
						null));
		PasswordChangeWindow.this.close();
	}
}
