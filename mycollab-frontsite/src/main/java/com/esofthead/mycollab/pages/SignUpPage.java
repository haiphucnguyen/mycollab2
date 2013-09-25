package com.esofthead.mycollab.pages;

import javax.servlet.http.Cookie;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.time.Time;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.restlet.data.Form;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.SiteConfiguration;
import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.core.utils.TimezoneMapper.TimezoneExt;
import com.esofthead.mycollab.rest.server.resource.UserHubResource;

public class SignUpPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(SignUpPage.class);

	public String selected = "10";

	public SignUpPage(final PageParameters parameters) {
		super(parameters);

		final RequiredTextField<String> email = new RequiredTextField<String>(
				"emailfield", new Model<String>());
		email.add(EmailAddressValidator.getInstance());

		final CheckBox receiveupdate = new CheckBox("receiveupdatefield",
				new Model<Boolean>());

		final RequiredTextField<String> subdomain = new RequiredTextField<String>(
				"subdomainfield", new Model<String>());

		final PasswordTextField password = new PasswordTextField(
				"passwordfield", new Model<String>());

		final PasswordTextField cpassword = new PasswordTextField(
				"cpasswordfield", new Model<String>());

		final HiddenField<String> timezone = new HiddenField<String>(
				"timezonefield", new Model<String>());

		final StatelessForm<Void> form = new StatelessForm<Void>("signupform") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				final ClientResource clientResource = new ClientResource(
						SiteConfiguration.getSignupUrl());
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

					log.debug("Submit form {}",
							SiteConfiguration.getSignupUrl());
					final String response = userResource.signup(form);
					log.debug("Response of site: {}", response);

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

		form.add(new StatelessLink<Void>("signupGoogle") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				try {
					// Create an instance of SocialAuthConfgi object
					SocialAuthConfig config = SocialAuthConfig.getDefault();

					// load configuration. By default load the configuration
					// from
					// oauth_consumer.properties.
					// You can also pass input stream, properties object or
					// properties file name.
					config.load();

					// Create an instance of SocialAuthManager and set config
					SocialAuthManager manager = new SocialAuthManager();
					manager.setSocialAuthConfig(config);

					// URL of YOUR application which will be called after
					// authentication
					String successUrl = "http://localhost:7070/oauth2/google";

					// get Provider URL to which you should redirect for
					// authentication.
					// id can have values "facebook", "twitter", "yahoo" etc. or
					// the
					// OpenID URL
					String url = manager.getAuthenticationUrl("google",
							successUrl);

					log.debug("Redirect url {}", url);

					// Store in session
					getSession().setAttribute("authManager", manager);
					getRequestCycle().scheduleRequestHandlerAfterCurrent(
							new RedirectRequestHandler(url));
				} catch (Exception e) {
					throw new MyCollabException(e);
				}
			}
		});

		this.add(new Label("pagetitle", "Sign Up"));
	}

}
