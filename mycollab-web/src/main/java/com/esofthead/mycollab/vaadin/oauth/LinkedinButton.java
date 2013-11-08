/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.oauth;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.LinkedInApi;

public class LinkedinButton extends OAuthButton {

	/**
	 * Creates a "Log in with LinkedIn" button that will use the given API
	 * key/secret to authenticate the user with LinkedIn, and then call the
	 * given callback with {@link User} details.
	 * 
	 * @param apiKey
	 *            API key from the service providing OAuth
	 * @param apiSecret
	 *            API secret from the service providing OAuth
	 * @param authListener
	 *            called once the user has been authenticated
	 */
	public LinkedinButton(String apiKey, String apiSecret,
			OAuthListener authListener) {
		super("Log in with LinkedIn", apiKey, apiSecret, authListener);
	}

	/**
	 * Creates a button with the given caption that will use the given API
	 * key/secret to authenticate the user with LinkedIn, and then call the
	 * given callback with {@link User} details.
	 * 
	 * @param caption
	 *            button caption
	 * @param apiKey
	 *            API key from the service providing OAuth
	 * @param apiSecret
	 *            API secret from the service providing OAuth
	 * @param authListener
	 *            called once the user has been authenticated
	 */
	public LinkedinButton(String caption, String apiKey, String apiSecret,
			OAuthListener authListener) {
		super(caption, apiKey, apiSecret, authListener);
	}

	@Override
	protected String getAuthUrl() {
		requestToken = getService().getRequestToken();
		return getService().getAuthorizationUrl(requestToken);
	}

	@Override
	protected Class<? extends Api> getApi() {
		return LinkedInApi.class;
	}

	@Override
	protected String getVerifierName() {
		return "oauth_verifier";
	}

	@Override
	protected String getJsonDataUrl() {
		return "http://api.linkedin.com/v1/people/~:(id,first-name,last-name,picture-url,public-profile-url)?format=json";
	}

	@Override
	protected Class<? extends User> getUserClass() {
		return LinkedinUser.class;
	}

	public static class LinkedinUser implements User {

		private String firstName;
		private String lastName;
		private String pictureUrl;
		private String id;
		private String publicProfileUrl;
		private String token;
		private String tokenSecret;

		public String getToken() {
			return token;
		}

		public String getTokenSecret() {
			return tokenSecret;
		}

		public String getName() {
			return firstName + " " + lastName;
		}

		public String getScreenName() {
			return publicProfileUrl
					.substring(publicProfileUrl.lastIndexOf("/") + 1);
		}

		public String getPictureUrl() {
			return pictureUrl;
		}

		public String getId() {
			return id;
		}

		public String getPublicProfileUrl() {
			return publicProfileUrl;
		}

		public String getService() {
			return "linkedin";
		}

		public String getEmail() {
			return null;
		}

	}

}
