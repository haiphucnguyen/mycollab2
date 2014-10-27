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

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.rest.server.resource.UserResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class UserResourceImpl implements UserResource {

	private static final Logger LOG = LoggerFactory.getLogger(UserResourceImpl.class);

	@Autowired
	private BillingService billingService;

	@Override
	public Response getSubdomainsOfUser(String username) {
		List<String> subdomains = this.billingService
				.getSubdomainsOfUser(username);
		String[] result;
		if (subdomains != null) {
			result = subdomains.toArray(new String[0]);
			LOG.debug("There are subdomains for user {} {}", username,
					BeanUtility.printBeanObj(result));

		} else {
			LOG.debug("There is no subdomain for user {}", username);
			result = new String[0];
		}

		Gson gson = new GsonBuilder().create(); // .serializeNulls()
		String json = gson.toJson(result);

		Response response = Response.status(200).entity(json).build();
		response.getHeaders().add("Access-Control-Allow-Origin", "*");
		return response;
	}

}
