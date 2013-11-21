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

import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.rest.server.domain.ContactForm;
import com.esofthead.mycollab.rest.server.resource.ContactResource;

@Component
public class ContactResourceImpl implements ContactResource {
	private static Logger log = LoggerFactory
			.getLogger(ContactResourceImpl.class);

	@Override
	public String submit(@Form final ContactForm entity) {
		ContactResourceImpl.log.debug("Start handling form request");
		ContactResourceImpl.log.debug("Name: " + entity.getName());
		ContactResourceImpl.log.debug("Email: " + entity.getEmail());
		ContactResourceImpl.log.debug("Company: " + entity.getCompany());
		ContactResourceImpl.log.debug("Role: " + entity.getRole());
		ContactResourceImpl.log.debug("Industry: " + entity.getIndustry());
		ContactResourceImpl.log.debug("Budget: " + entity.getBudget());
		ContactResourceImpl.log.debug("Subject: " + entity.getSubject());
		ContactResourceImpl.log.debug("Message: " + entity.getMessage());

		return "OK";
	}

}
