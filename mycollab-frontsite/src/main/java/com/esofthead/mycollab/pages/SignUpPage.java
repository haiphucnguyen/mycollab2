package com.esofthead.mycollab.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.base.BasePage;

public class SignUpPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(SignUpPage.class);

	private final List<String> timezones = Arrays.asList(new String[] { "UTC",
			"Asia/Ho_Chi_Minh", " 	America/Los_Angeles" });
	public String selected = "UTC";

	public SignUpPage(final PageParameters parameters) {
		super(parameters);
		final TextField<String> username = new TextField<String>(
				"usernamefield", new Model<String>());
		final PasswordTextField password = new PasswordTextField(
				"passwordfield", new Model<String>());
		final PasswordTextField cpassword = new PasswordTextField(
				"cpasswordfield", new Model<String>());
		final TextField<String> email = new TextField<String>("emailfield",
				new Model<String>());
		final DropDownChoice<String> dropDownChoice = new DropDownChoice<String>(
				"timezoneselect", new PropertyModel<String>(this, "selected"),
				timezones);

		StatelessForm<Void> form = new StatelessForm<Void>("signupform") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(
						"http://localhost:8080/mycollab-web/api/signup");

				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("username", username
						.getModelObject()));
				nvps.add(new BasicNameValuePair("password", password
						.getModelObject()));
				nvps.add(new BasicNameValuePair("email", email.getModelObject()));
				nvps.add(new BasicNameValuePair("timezone", selected));
				nvps.add(new BasicNameValuePair("planId", parameters.get(
						"planId").toString()));

				try {
					postRequest.setEntity(new UrlEncodedFormEntity(nvps));

					HttpResponse response = httpClient.execute(postRequest);
					StatusLine status = response.getStatusLine();
					if (status.getStatusCode() == 200) {
						// redirect to signup success page and ask user verify
						// his signup by email
					} else {
						// inform error with user
					}
				} catch (Exception e) {
					log.error("Send post request fail");
				}
			}
		};

		add(form);
		form.add(username);
		form.add(password);
		form.add(cpassword);
		form.add(email);
		form.add(dropDownChoice);

		add(new Label("pagetitle", "Sign Up"));
	}

}
