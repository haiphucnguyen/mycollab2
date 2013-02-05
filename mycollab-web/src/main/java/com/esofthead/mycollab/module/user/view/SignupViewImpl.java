package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.GenericForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.NullValidator;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormFieldFactory;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@ViewComponent
public class SignupViewImpl extends AbstractView implements SignupView {

	private static final long serialVersionUID = 1L;
	private final GenericForm form;
	private final User user = new User();

	public SignupViewImpl() {
		super();

		form = new GenericForm();

		form.setFormFieldFactory(new SignupFieldFactory());
		form.setFormLayoutFactory(new SignupFormLayoutFactory());

		BeanItem<User> item = new BeanItem<User>(user);
		form.setItemDataSource(item);

		this.addComponent(form);

		HorizontalLayout formBtnBar = new HorizontalLayout();
		form.getFooter().addComponent(formBtnBar);

		Button okBtn = new Button("Register", form, "commit");
		okBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		((CustomLayout) form.getLayout()).addComponent(okBtn, "loginButton");
		okBtn.addListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				user.setUsername(user.getEmail());
				try {
					UserService userService = AppContext
							.getSpringBean(UserService.class);
				} catch (Exception e) {
					UserError message = new UserError(e.getMessage());
					SignupViewImpl.this.form.setComponentError(message);
				}
			}
		});

		Button signInBtn = new Button("Sign In");
		signInBtn.addStyleName(UIConstants.THEME_LINK);

		((CustomLayout) form.getLayout()).addComponent(signInBtn, "signupLink");
		signInBtn.addListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ShellEvent.LogOut(this, null));
			}
		});

		Button tosBtn = new Button("Terms of Service");
		tosBtn.setStyleName(UIConstants.THEME_LINK);
		tosBtn.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

			}
		});
		((CustomLayout) form.getLayout())
				.addComponent(tosBtn, "termsofservice");

		Button privacyBtn = new Button("Privacy Policies");
		privacyBtn.setStyleName(UIConstants.THEME_LINK);
		privacyBtn.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {

			}
		});
		((CustomLayout) form.getLayout()).addComponent(privacyBtn,
				"privacypolicies");

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

	private static class SignupFormLayoutFactory implements IFormLayoutFactory {

		private CustomLayout SignupFormLayout;

		@Override
		public Layout getLayout() {
			SignupFormLayout = new CustomLayout("signupForm");
			SignupFormLayout.addStyleName("customSignUpForm");
			return SignupFormLayout;
		}

		@Override
		public void attachField(Object propertyId, Field field) {
			if (propertyId.equals("firstname")) {
				SignupFormLayout.addComponent(field, "firstNameField");
			} else if (propertyId.equals("lastname")) {
				SignupFormLayout.addComponent(field, "lastNameField");
			} else if (propertyId.equals("password")) {
				SignupFormLayout.addComponent(field, "passwordField");
			} else if (propertyId.equals("email")) {
				SignupFormLayout.addComponent(field, "emailField");
			}
		}

	}
}
