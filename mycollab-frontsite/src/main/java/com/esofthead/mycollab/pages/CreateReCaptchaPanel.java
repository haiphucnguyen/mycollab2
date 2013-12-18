package com.esofthead.mycollab.pages;

import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.WebRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Displays recaptcha widget. It is configured using a pair of public/private
 * keys which can be registered at the following location:
 * 
 * https://www.google.com/recaptcha/admin/create <br>
 * More details about recaptcha API:
 * http://code.google.com/apis/recaptcha/intro.html
 */
@SuppressWarnings("serial")
public class CreateReCaptchaPanel extends Panel {
	private static final Logger log = LoggerFactory
			.getLogger(CreateReCaptchaPanel.class);

	public CreateReCaptchaPanel(final String id) {
		super(id);

		add(new FormComponent<Void>("captcha", new Model()) {
			@Override
			public void onComponentTagBody(final MarkupStream markupStream,
					final ComponentTag openTag) {
				replaceComponentTagBody(markupStream, openTag,
						ReCaptchaUtil.createRecaptchaHtml());
			}

			@Override
			public void validate() {
				final WebRequest request = (WebRequest) RequestCycle.get()
						.getRequest();

				final String remoteAddr = request.getContainerRequest()
						.toString();

				final String challenge = request.getRequestParameters()
						.getParameterValue("recaptcha_challenge_field")
						.toString();
				final String uresponse = request.getRequestParameters()
						.getParameterValue("recaptcha_response_field")
						.toString();
				final ReCaptchaResponse reCaptchaResponse = ReCaptchaUtil
						.checkAnswer(remoteAddr, challenge, uresponse);

				if (!reCaptchaResponse.isValid()) {
					log.debug("wrong captcha");
					error("Invalid captcha!");
				}
			}
		});
	}
}
