package com.esofthead.mycollab.module.user.accountsettings.profile.view;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

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
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.steinwedel.vaadin.MessageBox;
import de.steinwedel.vaadin.MessageBox.ButtonType;

@SuppressWarnings("serial")
public class ContactInfoChangeWindow extends Window {

	private TextField txtWorkPhone;
	private TextField txtHomePhone;
	private TextField txtFaceBook;
	private TextField txtTwitter;
	private TextField txtSkype;
	private Validator validation;

	private User user;

	public ContactInfoChangeWindow(User user) {
		this.user = user;
		this.setWidth("450px");
		this.setHeight("300px");
		validation = AppContext.getSpringBean(LocalValidatorFactoryBean.class);
		initUI();
		center();
		this.setCaption("Change your contact information");
	}

	private void initUI() {

		VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);

		GridFormLayoutHelper passInfo = new GridFormLayoutHelper(1, 6,
				UIConstants.DEFAULT_CONTROL_WIDTH, "150px");

		txtWorkPhone = (TextField) passInfo.addComponent(new TextField(),
				"Work phone", 0, 0);
		txtHomePhone = (TextField) passInfo.addComponent(new TextField(),
				"Home phone", 0, 1);
		txtFaceBook = (TextField) passInfo.addComponent(new TextField(),
				"Facebook", 0, 2);
		txtTwitter = (TextField) passInfo.addComponent(new TextField(),
				"Twitter", 0, 3);
		txtSkype = (TextField) passInfo.addComponent(new TextField(), "Skype",
				0, 4);

		txtWorkPhone.setValue(user.getWorkphone() == null ? "" : user
				.getWorkphone());
		txtHomePhone.setValue(user.getHomephone() == null ? "" : user
				.getHomephone());
		txtFaceBook.setValue(user.getFacebookaccount() == null ? "" : user
				.getFacebookaccount());
		txtTwitter.setValue(user.getTwitteraccount() == null ? "" : user
				.getTwitteraccount());
		txtSkype.setValue(user.getSkypecontact() == null ? "" : user
				.getSkypecontact());

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
				ContactInfoChangeWindow.this.close();
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

	public boolean validateForm(Object data) {

		Set<ConstraintViolation<Object>> violations = validation.validate(data);
		if (violations.size() > 0) {
			StringBuilder errorMsg = new StringBuilder();

			for (@SuppressWarnings("rawtypes")
			ConstraintViolation violation : violations) {
				errorMsg.append(violation.getMessage()).append("<br/>");

				if (violation.getPropertyPath() != null
						&& !violation.getPropertyPath().toString().equals("")) {
					if (violation.getPropertyPath().toString()
							.equals("workphone")) {
						txtWorkPhone.addStyleName("errorField");
					}

					if (violation.getPropertyPath().toString()
							.equals("homephone")) {
						txtHomePhone.addStyleName("errorField");
					}
				}

			}

			MessageBox mb = new MessageBox(AppContext.getApplication()
					.getMainWindow(), "Error!", MessageBox.Icon.ERROR,
					errorMsg.toString(), new MessageBox.ButtonConfig(
							ButtonType.OK, "Ok"));
			mb.show();

			return false;
		}

		return true;
	}

	private void changePassword() {

		txtWorkPhone.removeStyleName("errorField");
		txtHomePhone.removeStyleName("errorField");
		
		user.setWorkphone((String) txtWorkPhone.getValue());
		user.setHomephone((String) txtHomePhone.getValue());
		user.setFacebookaccount((String) txtFaceBook.getValue());
		user.setTwitteraccount((String) txtTwitter.getValue());
		user.setSkypecontact((String) txtSkype.getValue());

		if (validateForm(user)) {
			UserService userService = AppContext
					.getSpringBean(UserService.class);
			userService.updateWithSession(user, AppContext.getUsername());

			EventBus.getInstance().fireEvent(
					new ProfileEvent.GotoProfileView(
							ContactInfoChangeWindow.this, null));
			ContactInfoChangeWindow.this.close();
		}

	}
}
