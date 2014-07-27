package com.esofthead.mycollab.billing.rest.client.impl;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.client.AuthCache;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

import com.esofthead.mycollab.billing.domain.Subscription;
import com.esofthead.mycollab.billing.rest.client.SubscriptionService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.2
 *
 */
public class SubscriptionServiceImpl implements SubscriptionService {

	@Override
	public Subscription getSubscription(String company, String referemce) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		// Configure HttpClient to authenticate preemptively
		// by prepopulating the authentication data cache.

		// 1. Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();

		// 2. Generate BASIC scheme object and add it to the local auth cache
		AuthScheme basicAuth = new BasicScheme();
		authCache.put(new HttpHost("https://api.fastspring.com"), basicAuth);

		// 3. Add AuthCache to the execution context
		HttpClientContext context = HttpClientContext.create();
		context.setAuthCache(authCache);

		// 4. Create client executor and proxy
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(
				httpClient, context);
		ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine)
				.build();

		ResteasyWebTarget target = client.target("https://api.fastspring.com");

		SubscriptionService subscriptionService = target
				.proxy(SubscriptionService.class);
		Subscription subscription = subscriptionService.getSubscription(
				"esofthead", "123");
		System.out.println("AAA");
	}

}
