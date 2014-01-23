package com.esofthead.mycollab.pages;

import com.esofthead.mycollab.ErrorReportingUtils;
import com.esofthead.mycollab.SiteConfiguration;
import com.esofthead.mycollab.rest.client.RemoteServiceProxy;
import com.esofthead.mycollab.rest.server.domain.ContactForm;
import com.esofthead.mycollab.rest.server.resource.ContactResource;
import com.esofthead.mycollab.uicomponents.ContactFormNotificationPanel;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.base.BasePage;

public class ContactUsPage extends BasePage {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(ContactUsPage.class);

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

		final Form<Void> form = new Form<Void>("contact-form");

		final ModalWindow modal = new ModalWindow("modal");
		modal.setResizable(false);
		modal.setInitialHeight(90);
		modal.setInitialWidth(400);
		modal.setHeightUnit("px");
		modal.setWidthUnit("px");
		modal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
			@Override
			public boolean onCloseButtonClicked(AjaxRequestTarget target) {
				return false;
			}
		});
		modal.setContent(new ContactFormNotificationPanel(modal.getContentId()));

		final AjaxButton submitLink = new AjaxButton("submit-link") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit(final AjaxRequestTarget target,
					final Form<?> form) {
				target.add(feedbackPanel);
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

					log.debug("Submit form {}", SiteConfiguration.getApiUrl());
					final String response = contactResource.submit(dataform);
					log.debug("Response of site: {}", response);
					modal.show(target);

				} catch (final Exception e) {
					error(e.getMessage());
					ErrorReportingUtils.reportError(e);
				}

			}

			@Override
			public void onError(final AjaxRequestTarget target,
					final Form<?> form) {
				target.add(feedbackPanel);
			}
		};

		this.add(form);
		this.add(modal);
		form.add(email);
		form.add(name);
		form.add(company);
		form.add(role);
		form.add(budget);
		form.add(industry);
		form.add(subject);
		form.add(message);
		form.add(submitLink);

		form.add(new CreateReCaptchaPanel("recaptcha"));

		this.add(feedbackPanel);
		this.add(new Behavior() {
			@Override
			public void renderHead(Component component, IHeaderResponse response) {
				response.render(new OnDomReadyHeaderItem(
						"Wicket.Window.unloadConfirmation = false;"));
			}
		});
	}

	@Override
	public IModel getPageTitle() {
		return new Model<String>(
				"Contact Us - Online Project Management, CRM, Document Management");
	}

	@Override
	public IModel getDescription() {
		return new Model<String>(
				"Contact us for inquiry, support about MyCollab services");
	}

	@Override
	public IModel getKeywords() {
		return new Model<String>(
				"project management tool, business tools, crm system, online collaboration, cloud office, documents management, online office");
	}

}
