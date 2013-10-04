package com.esofthead.mycollab.rest.server.resource.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.rest.server.resource.UserResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class UserResourceImpl implements UserResource {

	private static Logger log = LoggerFactory.getLogger(UserResourceImpl.class);

	@Autowired
	private BillingService billingService;

	@Override
	public Response getSubdomainsOfUser(String username) {
		List<String> subdomains = this.billingService
				.getSubdomainsOfUser(username);
		String[] result;
		if (subdomains != null) {
			result = subdomains.toArray(new String[0]);
			log.debug("There are subdomains for user {} {}", username,
					BeanUtility.printBeanObj(result));

		} else {
			log.debug("There is no subdomain for user {}", username);
			result = new String[0];
		}

		Gson gson = new GsonBuilder().create(); // .serializeNulls()
		String json = gson.toJson(result);

		Response response = Response.status(200).entity(json).build();
		return response;
	}

}
