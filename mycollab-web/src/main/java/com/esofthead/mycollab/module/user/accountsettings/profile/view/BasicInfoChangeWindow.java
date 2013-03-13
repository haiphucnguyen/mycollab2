package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import java.util.Date;

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
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings("serial")
public class BasicInfoChangeWindow extends Window {

	private TextField txtFirstName;
	private TextField txtLastName;
	private TextField txtEmail;
	private DateField birthdayField;

	private User user;

	public BasicInfoChangeWindow(User user) {
		this.user = user;
		this.setWidth("450px");
		this.setHeight("250px");
		initUI();
		center();
		this.setCaption("Change your basic information");
	}

	private void initUI() {

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		GridFormLayoutHelper passInfo = new GridFormLayoutHelper(1, 4,
				UIConstants.DEFAULT_CONTROL_WIDTH, "150px");

		txtFirstName = (TextField) passInfo.addComponent(new TextField(),
				"First Name", 0, 0);
		txtLastName = (TextField) passInfo.addComponent(new TextField(),
				"Last Name", 0, 1);
		txtEmail = (TextField) passInfo.addComponent(new TextField(), "Email",
				0, 2);
		birthdayField = (DateField) passInfo.addComponent(new DateField(),
				"Birthday", 0, 3);
		birthdayField.setDateFormat(AppContext.getDateFormat());
		birthdayField.setResolution(PopupDateField.RESOLUTION_DAY);

		txtFirstName.setValue(user.getFirstname() == null ? "" : user
				.getFirstname());
		txtLastName.setValue(user.getLastname() == null ? "" : user
				.getLastname());
		txtEmail.setValue(user.getEmail() == null ? "" : user.getEmail());
		birthdayField.setValue(user.getDateofbirth());

		mainLayout.addComponent(passInfo.getLayout());
		mainLayout.setComponentAlignment(passInfo.getLayout(),
				Alignment.TOP_LEFT);

		Label lbSpace = new Label();
		mainLayout.addComponent(lbSpace);
		mainLayout.setExpandRatio(lbSpace, 1.0f);

		HorizontalLayout hlayoutControls = new HorizontalLayout();
		hlayoutControls.setSpacing(true);
		hlayoutControls.setMargin(true);
		Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				BasicInfoChangeWindow.this.close();
			}
		});

		cancelBtn.setStyleName("link");
		hlayoutControls.addComponent(cancelBtn);
		hlayoutControls.setComponentAlignment(cancelBtn,
				Alignment.MIDDLE_CENTER);

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
		mainLayout.setComponentAlignment(hlayoutControls,
				Alignment.MIDDLE_RIGHT);

		this.setModal(true);
		this.setContent(mainLayout);
	}

	private void showMessage(String title, String message) {
		MessageBox mb = new MessageBox(AppContext.getApplication()
				.getMainWindow(), title, MessageBox.Icon.WARN, message,
				new MessageBox.ButtonConfig(ButtonType.OK, "Ok"));
		mb.show();
	}

	private void changePassword() {

		txtLastName.removeStyleName("errorField");
		txtEmail.removeStyleName("errorField");

		if (txtLastName.getValue().equals("")) {
			showMessage("Warning!", "The last name must be not null!");
			txtLastName.addStyleName("errorField");
			return;
		}

		if (txtEmail.getValue().equals("")) {
			showMessage("Warning!", "The email must be not null!");
			txtLastName.addStyleName("errorField");
			return;
		}

		user.setFirstname((String) txtFirstName.getValue());
		user.setLastname((String) txtLastName.getValue());
		user.setEmail((String) txtEmail.getValue());
		user.setDateofbirth((Date) birthdayField.getValue());

		UserService userService = AppContext.getSpringBean(UserService.class);
		userService.updateWithSession(user, AppContext.getUsername());
		
		EventBus.getInstance().fireEvent(
				new ProfileEvent.GotoProfileView(
						BasicInfoChangeWindow.this, null));
		BasicInfoChangeWindow.this.close();
	}
}
