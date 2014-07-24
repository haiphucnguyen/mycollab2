package com.esofthead.mycollab.billing.rest.client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.esofthead.mycollab.billing.domain.Subscription;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.2
 *
 */
public interface SubscriptionService {
	@GET
	@Path("company/{company}/subscription/{reference}")
	@Produces("application/xml")
	Subscription getSubscription(@PathParam("company") String company,
			@PathParam("reference") String referemce);
}
