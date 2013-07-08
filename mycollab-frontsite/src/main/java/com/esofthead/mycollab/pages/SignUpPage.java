package com.esofthead.mycollab.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
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
		username.setRequired(true);
		username.setLabel(Model.of("Username"));

		final TextField<String> firstname = new TextField<String>(
				"firstnamefield", new Model<String>());

		final TextField<String> lastname = new TextField<String>(
				"lastnamefield", new Model<String>());
		lastname.setRequired(true);
		lastname.setLabel(Model.of("Last Name"));

		final TextField<String> email = new TextField<String>("emailfield",
				new Model<String>());
		email.setRequired(true);
		email.setLabel(Model.of("Email"));

		final CheckBox receiveupdate = new CheckBox("receiveupdatefield",
				new Model<Boolean>());
		final TextField<String> subdomain = new TextField<String>(
				"subdomainfield", new Model<String>());
		subdomain.setRequired(true);
		subdomain.setLabel(Model.of("Subdomain"));

		final PasswordTextField password = new PasswordTextField(
				"passwordfield", new Model<String>());
		password.setLabel(Model.of("Password"));

		final PasswordTextField cpassword = new PasswordTextField(
				"cpasswordfield", new Model<String>());
		cpassword.setLabel(Model.of("Confirm Password"));

		final HiddenField<String> timezone = new HiddenField<String>(
				"timezonefield", new Model<String>());

		final StatelessForm<Void> form = new StatelessForm<Void>("signupform") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				final DefaultHttpClient httpClient = new DefaultHttpClient();
				final HttpPost postRequest = new HttpPost(
						"http://localhost:8080/mycollab-web/api/signup");

				final List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				nvps.add(new BasicNameValuePair("firstname", firstname
						.getModelObject()));
				nvps.add(new BasicNameValuePair("lastname", lastname
						.getModelObject()));
				nvps.add(new BasicNameValuePair("username", username
						.getModelObject()));
				nvps.add(new BasicNameValuePair("subdomain", subdomain
						.getModelObject()));
				nvps.add(new BasicNameValuePair("email", email.getModelObject()));
				nvps.add(new BasicNameValuePair("password", password
						.getModelObject()));
				nvps.add(new BasicNameValuePair("timezone", timezone
						.getModelObject()));
				nvps.add(new BasicNameValuePair("planId", parameters.get(
						"planId").toString()));

				try {
					postRequest.setEntity(new UrlEncodedFormEntity(nvps));

					final HttpResponse response = httpClient
							.execute(postRequest);
					final StatusLine status = response.getStatusLine();
					if (status.getStatusCode() == 200) {
						// redirect to user's suddomain page
						final HttpEntity entity = response.getEntity();
						final String content = EntityUtils.toString(entity);
						this.getRequestCycle()
								.scheduleRequestHandlerAfterCurrent(
										new RedirectRequestHandler(content));

					} else {
						// inform error with user
					}
				} catch (final Exception e) {
					SignUpPage.log.error("Send post request fail");
				}
			}
		};

		this.add(form);
		form.add(new FeedbackPanel("feedback"));
		form.add(username);
		form.add(subdomain);
		form.add(email);
		form.add(password);
		form.add(cpassword);
		/* form.add(email); */
		form.add(timezone);
		form.add(firstname);
		form.add(lastname);
		form.add(receiveupdate);

		final RepeatingView timezoneAreaRepeat = new RepeatingView("arearepeat");
		form.add(timezoneAreaRepeat);

		for (final String timezoneArea : TimezoneMapper.AREAS) {
			final AbstractItem areaItem = new AbstractItem(
					timezoneAreaRepeat.newChildId());
			timezoneAreaRepeat.add(areaItem);

			areaItem.add(new Label("one_area", timezoneArea));

			final RepeatingView timezoneRepeat = new RepeatingView(
					"timezonerepeat");
			areaItem.add(timezoneRepeat);

			for (final TimezoneExt oneTimezone : TimezoneMapper.timeMap
					.values()) {
				if (oneTimezone.getArea().equals(timezoneArea)) {
					final AbstractItem timezoneItem = new AbstractItem(
							timezoneRepeat.newChildId());
					timezoneRepeat.add(timezoneItem);
					timezoneItem.add(new Label("one_timezone", oneTimezone
							.getDisplayName()));
					timezoneItem.add(AttributeModifier.replace("data-tag",
							new AbstractReadOnlyModel<String>() {
								private static final long serialVersionUID = 1L;

								@Override
								public String getObject() {
									return oneTimezone.getId();
								}
							}));
				}
			}
		}

		this.add(new Label("pagetitle", "Sign Up"));
	}

}
