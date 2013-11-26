package com.esofthead.mycollab.pages;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.rmi.server.UID;

import javax.imageio.ImageIO;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.DynamicImageResource;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.ErrorReportingUtils;
import com.esofthead.mycollab.SiteConfiguration;
import com.esofthead.mycollab.WicketApplication;
import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.rest.client.RemoteServiceProxy;
import com.esofthead.mycollab.rest.server.domain.ContactForm;
import com.esofthead.mycollab.rest.server.resource.ContactResource;

public class ContactUsPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ContactUsPage.class);

	private String challengeId = null;

	public ContactUsPage(final PageParameters parameters) {
		super(parameters);

		final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
		feedbackPanel.setOutputMarkupId(true);

		final RequiredTextField<String> email = new RequiredTextField<String>(
				"emailfield", new Model<String>());
		email.setLabel(new ResourceModel("label.email"));
		email.add(EmailAddressValidator.getInstance());

		final RequiredTextField<String> name = new RequiredTextField<String>(
				"namefield", new Model<String>());
		name.setLabel(new ResourceModel("label.name"));

		final RequiredTextField<String> company = new RequiredTextField<String>(
				"companyfield", new Model<String>());
		company.setLabel(new ResourceModel("label.company"));

		final HiddenField<String> role = new HiddenField<String>("rolefield",
				new Model<String>());
		role.setRequired(true);
		role.setLabel(new ResourceModel("label.role"));

		final HiddenField<String> industry = new HiddenField<String>(
				"industryfield", new Model<String>());
		industry.setRequired(true);
		industry.setLabel(new ResourceModel("label.industry"));

		final HiddenField<String> budget = new HiddenField<String>(
				"budgetfield", new Model<String>());
		budget.setRequired(true);
		budget.setLabel(new ResourceModel("label.budget"));

		final TextField<String> subject = new TextField<String>("subjectfield",
				new Model<String>());
		subject.setLabel(new ResourceModel("label.subject"));

		final TextArea<String> message = new TextArea<String>("messagefield",
				new Model<String>());
		message.setRequired(true);
		message.setLabel(new ResourceModel("label.message"));

		final RequiredTextField<String> captcha = (RequiredTextField<String>) new RequiredTextField<String>(
				"captchafield", new Model<String>())
				.add(new CaptchaValidator());
		captcha.setLabel(new ResourceModel("label.captcha"));

		final StatelessForm<Void> form = new StatelessForm<Void>("contact-form") {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit() {
				final ContactResource contactResource = RemoteServiceProxy
						.build(SiteConfiguration.getApiUrl(),
								ContactResource.class);

				try {
					final ContactForm dataform = new ContactForm();
					dataform.setName(name.getModelObject());
					dataform.setEmail(email.getModelObject());
					dataform.setCompany(company.getModelObject());
					dataform.setBudget(budget.getModelObject());
					dataform.setIndustry(industry.getModelObject());
					dataform.setRole(role.getModelObject());
					dataform.setSubject(subject.getModelObject());
					dataform.setMessage(message.getModelObject());

					ContactUsPage.log.debug("Submit form {}",
							SiteConfiguration.getApiUrl());
					final String response = contactResource.submit(dataform);
					ContactUsPage.log.debug("Response of site: {}", response);

				} catch (final Exception e) {
					error(e.getMessage());
					ErrorReportingUtils.reportError(e);
				}
			}
		};

		this.add(form);
		form.add(email);
		form.add(name);
		form.add(company);
		form.add(role);
		form.add(budget);
		form.add(industry);
		form.add(subject);
		form.add(message);
		final DynamicImageResource res = new DynamicImageResource() {

			private static final long serialVersionUID = 1L;

			@Override
			protected byte[] getImageData(final Attributes arg0) {
				challengeId = new UID().toString();
				final BufferedImage challenge = WicketApplication.captchaService
						.getImageChallengeForID(challengeId, Session.get()
								.getLocale());
				try {
					ByteArrayOutputStream outStream = new ByteArrayOutputStream();
					ImageIO.write(challenge, "png", outStream);
					return outStream.toByteArray();
				} catch (final Exception e) {
					throw new RuntimeException(e);
				}
			}
		};

		final Image captchaImage = new Image("captchaImage", res);
		captchaImage.setOutputMarkupId(true);
		form.add(captchaImage);
		form.add(captcha);

		this.add(feedbackPanel);
		this.add(new Label("pagetitle", "Contact Us"));
	}

	private class CaptchaValidator implements IValidator<String> {

		private static final long serialVersionUID = 1L;

		@Override
		public void validate(final IValidatable<String> validatable) {
			if (!WicketApplication.captchaService.validateResponseForID(
					challengeId, validatable.getValue())) {
				error("Wrong captcha");
			}
		}

	}

}
