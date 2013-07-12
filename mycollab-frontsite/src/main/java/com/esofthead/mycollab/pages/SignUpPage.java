package com.esofthead.mycollab.pages;

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
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.core.utils.TimezoneMapper.TimezoneExt;
import com.esofthead.mycollab.rest.server.resource.UserHubResource;

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
				final ClientResource clientResource = new ClientResource(
						"http://localhost:8080/mycollab-web/api/signup");
				final UserHubResource userResource = clientResource
						.wrap(UserHubResource.class);

				try {

					final Form form = new Form();
					form.set("subdomain", subdomain.getModelObject());
					form.set("planId", parameters.get("planId").toString());
					form.set("username", username.getModelObject());
					form.set("password", password.getModelObject());
					form.set("email", email.getModelObject());
					form.set("timezoneId", timezone.getModelObject());
					form.set("firstname", firstname.getModelObject());
					form.set("lastname", lastname.getModelObject());

					final String response = userResource.doPost(form);

					this.getRequestCycle().scheduleRequestHandlerAfterCurrent(
							new RedirectRequestHandler(response));
				} catch (final Exception e) {
					this.error(e.getMessage());
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
