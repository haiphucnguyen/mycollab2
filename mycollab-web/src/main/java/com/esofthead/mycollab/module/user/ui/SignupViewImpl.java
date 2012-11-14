package com.esofthead.mycollab.module.user.ui;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.SecurityService;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;


public class SignupViewImpl extends Window {
	private static final long serialVersionUID = 1L;

	@Autowired
	private SecurityService securityService;

	private Form form;

	private User user = new User();

	public SignupViewImpl() {
		super();
		this.setName("signup");
		this.setCaption("Signup Form");

		form = new Form();
		form.setCaption("Signup");

		form.setFormFieldFactory(new SignupFieldFactory());

		BeanItem<User> item = new BeanItem<User>(user);
		form.setItemDataSource(item);

		this.addComponent(form);

		HorizontalLayout formBtnBar = new HorizontalLayout();
		form.getFooter().addComponent(formBtnBar);

		Button okBtn = new Button("Ok", form, "commit");
		formBtnBar.addComponent(okBtn);
		okBtn.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				user.setUsername(user.getEmail());
				try {
					securityService.registerUser(user,
							SecurityService.FREE_BILLING);
				} catch (Exception e) {
					UserError message = new UserError(e.getMessage());
					SignupViewImpl.this.form.setComponentError(message);
				}
			}
		});

		Button resetBtn = new Button("Reset", form, "discard");
		formBtnBar.addComponent(resetBtn);
		resetBtn.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				user = new User();
			}
		});

		Button cancelBtn = new Button("Cancel");
		formBtnBar.addComponent(cancelBtn);
		cancelBtn.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				SignupViewImpl.this.open(new ExternalResource(getApplication()
						.getURL()));
			}
		});
	}

	private static class SignupFieldFactory implements FormFieldFactory {
		private static final long serialVersionUID = 1L;

		@Override
		public Field createField(Item item, Object propertyId,
				Component uiContext) {
			String pid = (String) propertyId;
			Field field = null;

			if ("firstname".equals(pid)) {
				field = new TextField("First Name");
				field.addValidator(new NullValidator("Not allow null value",
						false));
			} else if ("lastname".equals(pid)) {
				field = new TextField("Last Name");
			} else if ("password".equals(pid)) {
				field = new PasswordField("Password");
			} else if ("email".equals(pid)) {
				field = new TextField("Email");
				field.addValidator(new EmailValidator(
						"Please enter a valid email address"));
			}

			if (field != null && field instanceof TextField) {
				((TextField) field).setNullRepresentation("");
			}
			return field;
		}

	}
}
