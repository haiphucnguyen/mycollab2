package com.esofthead.mycollab.pages;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;

import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.core.utils.TimezoneMapper.TimezoneExt;
import com.esofthead.mycollab.rest.server.resource.UserHubResource;

public class SignUpPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public String selected = "10";

	public SignUpPage(final PageParameters parameters) {
		super(parameters);

		final RequiredTextField<String> email = new RequiredTextField<String>("emailfield");
		email.add(EmailAddressValidator.getInstance());
		email.setLabel(new ResourceModel("label.email"));

		final CheckBox receiveupdate = new CheckBox("receiveupdatefield");
		
		final RequiredTextField<String> subdomain = new RequiredTextField<String>("subdomainfield");
		subdomain.setLabel(new ResourceModel("label.subdomain"));

		final PasswordTextField password = new PasswordTextField("passwordfield");
		password.setLabel(new ResourceModel("label.password"));

		final PasswordTextField cpassword = new PasswordTextField("cpasswordfield");
		cpassword.setLabel(new ResourceModel("label.password"));

		final HiddenField<String> timezone = new HiddenField<String>("timezonefield");

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
					form.set("username", email.getModelObject());
					form.set("password", password.getModelObject());
					form.set("email", email.getModelObject());
					form.set("timezoneId", timezone.getModelObject());

					final String response = userResource.signup(form);

					this.getRequestCycle().scheduleRequestHandlerAfterCurrent(
							new RedirectRequestHandler(response));
				} catch (final Exception e) {
					this.error(e.getMessage());
				}
			}
		};

		this.add(form);
		form.add(new FeedbackPanel("feedback"));
		form.add(subdomain);
		form.add(email);
		form.add(password);
		form.add(cpassword);
		form.add(timezone);
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
