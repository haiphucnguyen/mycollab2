package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import com.esofthead.mycollab.module.user.accountsettings.view.events.ProfileEvent;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
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

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings("serial")
public class PasswordChangeWindow extends Window {

	private PasswordField txtOldPassword;
	private PasswordField txtNewPassword;
	private PasswordField txtConfirmPassword;
	
	private User user;
	
	public PasswordChangeWindow(User user) {
		this.setWidth("500px");
		this.setHeight("270px");
		initUI();
		center();
		this.user = user;
		this.setCaption("Change your password");
	}
	
	private void initUI() {
		
		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		Label lbInstruct1 = new Label("* Passwords are case-sensitive and must be at least 6 characters.");
		mainLayout.addComponent(lbInstruct1);
		mainLayout.setComponentAlignment(lbInstruct1, Alignment.MIDDLE_LEFT);
		
		Label lbInstruct2 = new Label("* A good password should contain a mix of capital and lower-case letters, numbers and symbols.");
		mainLayout.addComponent(lbInstruct2);
		mainLayout.setComponentAlignment(lbInstruct2, Alignment.MIDDLE_LEFT);
		
		GridFormLayoutHelper passInfo = new GridFormLayoutHelper(1, 3,
				UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION,
				"150px");
		
		txtOldPassword = (PasswordField) passInfo.addComponent(new PasswordField(), "Old password", 0, 0);
		txtNewPassword = (PasswordField) passInfo.addComponent(new PasswordField(), "New password", 0, 1);
		txtConfirmPassword = (PasswordField) passInfo.addComponent(new PasswordField(), "Confirm new password", 0, 2);
		
		mainLayout.addComponent(passInfo.getLayout());
		mainLayout.setComponentAlignment(passInfo.getLayout(), Alignment.MIDDLE_CENTER);
		
		Label lbSpace = new Label();
		mainLayout.addComponent(lbSpace);
		mainLayout.setExpandRatio(lbSpace, 1.0f);
		
		HorizontalLayout hlayoutControls = new HorizontalLayout();
		
		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				PasswordChangeWindow.this.close();
			}
		});

		cancelBtn.setStyleName("link");
		hlayoutControls.addComponent(cancelBtn);
		hlayoutControls.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);
		
		Button sendBtn = new Button("Save", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				changePassword();
			}
		});
		sendBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		hlayoutControls.addComponent(sendBtn);
		hlayoutControls.setComponentAlignment(sendBtn, Alignment.MIDDLE_CENTER);
		
		mainLayout.addComponent(hlayoutControls);
		mainLayout.setComponentAlignment(hlayoutControls, Alignment.MIDDLE_RIGHT);
		
		this.setModal(true);
		this.setContent(mainLayout);
	}
	
	private void showMessage(String title, String message) {
		MessageBox mb = new MessageBox(AppContext.getApplication()
				.getMainWindow(), title, MessageBox.Icon.WARN,
				message,
				new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
		mb.show();
	}
	
	private void changePassword() {
		
		txtOldPassword.removeStyleName("errorField");
		txtNewPassword.removeStyleName("errorField");
		txtConfirmPassword.removeStyleName("errorField");
		
		if (!user.getPassword().equals(txtOldPassword.getValue())) {
			showMessage("Warning!", "Your old password is not correct, please check it again.");
			txtOldPassword.addStyleName("errorField");
			return;
		}
		
		if (!txtNewPassword.getValue().equals(txtConfirmPassword.getValue())) {
			showMessage("Warning!", "Your new password is not matching with confirm password, please check it again.");
			txtNewPassword.addStyleName("errorField");
			txtConfirmPassword.addStyleName("errorField");
			return;
		}
		
		user.setPassword((String) txtNewPassword.getValue());
		
		UserService userService = AppContext.getSpringBean(UserService.class);
		userService.updateWithSession(user, AppContext.getUsername());
		
		EventBus.getInstance().fireEvent(
				new ProfileEvent.GotoProfileView(
						PasswordChangeWindow.this, null));
		PasswordChangeWindow.this.close();
	}
}
