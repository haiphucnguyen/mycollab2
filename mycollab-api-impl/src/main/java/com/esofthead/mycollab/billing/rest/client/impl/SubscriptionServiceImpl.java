package com.esofthead.mycollab.billing.rest.client.impl;

import javax.ws.rs.core.Response;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.resteasy.client.jaxrs.engines.ApacheHttpClient4Engine;

import com.esofthead.mycollab.billing.domain.Subscription;
import com.esofthead.mycollab.billing.rest.client.SubscriptionService;
import com.esofthead.mycollab.rest.server.resource.UserResource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.2
 *
 */
public class SubscriptionServiceImpl implements SubscriptionService {

	@Override
	public Subscription getSubscription(String company, String reference) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		// Configure HttpClient to authenticate preemptively
		// by prepopulating the authentication data cache.

		HttpHost httpHost = new HttpHost("api.fastspring.com", 80, "https");
		// 1. Create AuthCache instance
		AuthCache authCache = new BasicAuthCache();

		// 2. Generate BASIC scheme object and add it to the local auth cache
		AuthScheme basicAuth = new BasicScheme();
		authCache.put(httpHost, basicAuth);

		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(new AuthScope(httpHost.getHostName(),
				httpHost.getPort()), new UsernamePasswordCredentials(
				"hainguyen@esofthead.com", "Hai79Linh80Han11"));

		// 3. Add AuthCache to the execution context
		HttpClientContext context = HttpClientContext.create();
		context.setAuthCache(authCache);
		context.setCredentialsProvider(credsProvider);

		// 4. Create client executor and proxy
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		ApacheHttpClient4Engine engine = new ApacheHttpClient4Engine(
				httpClient, context);
		ResteasyClient client = new ResteasyClientBuilder().httpEngine(engine)
				.build();

		ResteasyWebTarget target = client.target("https://api.mycollab.com/api");

		UserResource subscriptionService = target.proxy(UserResource.class);
		Response subscription = subscriptionService
				.getSubdomainsOfUser("hainguyen@esofthead.com");

		System.out.println("AAA : " + subscription.readEntity(String.class));
	}

}
