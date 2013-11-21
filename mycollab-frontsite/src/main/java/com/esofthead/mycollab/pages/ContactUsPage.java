package com.esofthead.mycollab.pages;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.rmi.server.UID;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

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

import com.esofthead.mycollab.SiteConfiguration;
import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.rest.client.RemoteServiceProxy;
import com.esofthead.mycollab.rest.server.domain.ContactForm;
import com.esofthead.mycollab.rest.server.resource.ContactResource;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ContactUsPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ContactUsPage.class);

	private static final ImageCaptchaService captchaService = new DefaultManageableImageCaptchaService();

	private String challengeId = null;

	public ContactUsPage(final PageParameters parameters) {
		super(parameters);

		final DynamicImageResource res = new DynamicImageResource() {

			private static final long serialVersionUID = 1L;

			@Override
			protected byte[] getImageData(final Attributes arg0) {
				final ByteArrayOutputStream os = new ByteArrayOutputStream();
				challengeId = new UID().toString();
				final BufferedImage challenge = ContactUsPage.captchaService
						.getImageChallengeForID(challengeId, Session.get()
								.getLocale());
				final JPEGImageEncoder encoder = JPEGCodec
						.createJPEGEncoder(os);
				try {
					encoder.encode(challenge);
					return os.toByteArray();
				} catch (final Exception e) {
					throw new RuntimeException(e);
				}
			}
		};

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

		final Image captchaImage = new Image("captchaImage", res);
		captchaImage.setOutputMarkupId(true);

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

				} catch (final BadRequestException e) {
					final Response response = e.getResponse();
					final String mycollabEx = response.readEntity(String.class);
					if (mycollabEx != null) {
						error(mycollabEx);
					} else {
						error(e.getMessage());
					}
				} catch (final Exception e) {
					error(e.getMessage());
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
		form.add(captchaImage);
		form.add(captcha);

		this.add(feedbackPanel);
		this.add(new Label("pagetitle", "Contact Us"));
	}

	private class CaptchaValidator implements IValidator<String> {

		private static final long serialVersionUID = 1L;

		@Override
		public void validate(final IValidatable<String> validatable) {
			if (!ContactUsPage.captchaService.validateResponseForID(
					challengeId, validatable.getValue())) {
				error("Wrong captcha");
			}
		}

	}

}
