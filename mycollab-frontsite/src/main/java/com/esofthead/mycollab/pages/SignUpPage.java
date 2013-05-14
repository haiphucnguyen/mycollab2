package com.esofthead.mycollab.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.core.utils.TimezoneMapper.TimezoneExt;

public class SignUpPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(SignUpPage.class);

	public String selected = "10";

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
		final HiddenField<String> timezone = new HiddenField<String>(
				"timezonefield", new Model<String>());

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
				nvps.add(new BasicNameValuePair("timezone", timezone
						.getModelObject()));
				nvps.add(new BasicNameValuePair("planId", parameters.get(
						"planId").toString()));

				try {
					postRequest.setEntity(new UrlEncodedFormEntity(nvps));

					HttpResponse response = httpClient.execute(postRequest);
					StatusLine status = response.getStatusLine();
					if (status.getStatusCode() == 200) {
						// redirect to signup success page and ask user verify
						// his signup by email
						log.debug("Signup successfully. Status is "
								+ status.getReasonPhrase());
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
		form.add(timezone);

		RepeatingView repeating = new RepeatingView("repeating");
		form.add(repeating);

		Iterator<Entry<String, TimezoneExt>> it = TimezoneMapper.timeMap
				.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, TimezoneExt> pairs = it.next();
			final TimezoneExt eachTimezone = pairs.getValue();
			AbstractItem item = new AbstractItem(repeating.newChildId());

			repeating.add(item);
			item.add(new Label("one_timezone", eachTimezone.getTimezone()
					.getID()));

			item.add(AttributeModifier.replace("data-tag",
					new AbstractReadOnlyModel<String>() {
						private static final long serialVersionUID = 1L;

						@Override
						public String getObject() {
							return eachTimezone.getId();
						}
					}));
		}

		add(new Label("pagetitle", "Sign Up"));
	}

}
