package com.esofthead.mycollab.pages;

import org.apache.wicket.Application;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.SiteConfiguration;
import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.rest.server.resource.UserHubResource;

@RequireHttps
public class SignInPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(SignInPage.class);

	public SignInPage(final PageParameters parameters) {
		super(parameters);

		final TextField<String> email = new TextField<String>("emailfield",
				new Model<String>());

		StatelessForm<Void> form = new StatelessForm<Void>("signinform");
		form.setOutputMarkupId(true);

		final MarkupContainer listContainer = new WebMarkupContainer(
				"listcontainer");
		listContainer.setOutputMarkupId(true);

		final RepeatingView subdomainList = new RepeatingView("subdomainrepeat");
		subdomainList.setOutputMarkupId(true);
		listContainer.add(subdomainList);

		form.add(new AjaxButton("ajax-button", form) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(AjaxRequestTarget target, Form<?> form) {
				log.debug("Signin page: " + SiteConfiguration.getSigninUrl());
				final ClientResource clientResource = new ClientResource(
						SiteConfiguration.getSigninUrl());
				final UserHubResource userResource = clientResource
						.wrap(UserHubResource.class);

				try {
					String emailString = email.getModelObject();

					final String[] response = userResource
							.getSubdomainsOfUser(emailString);

					if (response.length == 1) {
						String redirectUrl = "";
						if (Application.get().usesDevelopmentConfig()) {
							redirectUrl = SiteConfiguration.getAppUrl();
						} else {
							redirectUrl = response[0];
						}
						this.getRequestCycle()
								.scheduleRequestHandlerAfterCurrent(
										new RedirectRequestHandler(redirectUrl));
					} else {

						subdomainList.removeAll();
						for (String subdomainString : response) {
							final AbstractItem newItem = new AbstractItem(
									subdomainList.newChildId());

							Label subdomain = new Label("subdomain",
									subdomainString + ".mycollab.com");
							newItem.add(subdomain);

							ExternalLink gotosubdomainBtn = new ExternalLink(
									"gotosubdomain", "https://"
											+ subdomainString + ".mycollab.com");
							newItem.add(gotosubdomainBtn);

							subdomainList.add(newItem);
						}

						target.add(listContainer);
					}

				} catch (Exception e) {
					log.error("Error when retrieve sub domain of user", e);
				}
			}
		});

		add(form);
		form.add(email);
		form.add(listContainer);

		add(new Label("pagetitle", "Sign In"));
	}
}
