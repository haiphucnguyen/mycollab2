/**
 * This file is part of mycollab-api-impl.
 *
 * mycollab-api-impl is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-api-impl is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-api-impl.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.rest.server.resource.impl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.mail.IContentGenerator;
import com.esofthead.mycollab.module.mail.MailUtils;
import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.esofthead.mycollab.rest.server.domain.ContactForm;
import com.esofthead.mycollab.rest.server.resource.ContactResource;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class ContactResourceImpl implements ContactResource {
	private static Logger log = LoggerFactory
			.getLogger(ContactResourceImpl.class);
	private static final String contactUsTemplate = "contactUs.mt";

	@Autowired
	private IContentGenerator contentGenerator;

	@Override
	public Response submit(@Form final ContactForm entity) {
		log.debug("Start handling form request");
		log.debug("Name: " + entity.getName());
		log.debug("Email: " + entity.getEmail());
		log.debug("Company: " + entity.getCompany());
		log.debug("Role: " + entity.getRole());
		log.debug("Industry: " + entity.getIndustry());
		log.debug("Budget: " + entity.getBudget());
		log.debug("Subject: " + entity.getSubject());
		log.debug("Message: " + entity.getMessage());

		// -----------------------------------------------------------

		contentGenerator.putVariable("name", entity.getName());
		contentGenerator.putVariable("email", entity.getEmail());
		contentGenerator.putVariable("company", entity.getCompany());
		contentGenerator.putVariable("role", entity.getRole());
		contentGenerator.putVariable("industry", entity.getIndustry());
		contentGenerator.putVariable("budget", entity.getBudget());
		contentGenerator.putVariable("subject", entity.getSubject());
		contentGenerator.putVariable("message", entity.getMessage());
		MailRelayService mailRelayService = ApplicationContextUtil
				.getSpringBean(MailRelayService.class);
		mailRelayService
				.saveRelayEmail(
						new String[] { "Sir" },
						new String[] { "hainguyen@esofthead.com" },
						contentGenerator
								.generateSubjectContent("New guy wanna contact you!"),
						contentGenerator.generateBodyContent(MailUtils
								.templatePath(contactUsTemplate,
										SiteConfiguration.getDefaultLocale())));
		Response response = Response.status(200).entity("OK")
				.type(MediaType.TEXT_PLAIN_TYPE).build();
		response.getHeaders().add("Access-Control-Allow-Origin", "*");
		return response;
	}
}
