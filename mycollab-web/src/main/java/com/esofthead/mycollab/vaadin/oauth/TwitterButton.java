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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.Token;
import org.scribe.utils.OAuthEncoder;

import com.google.gson.annotations.SerializedName;

public class TwitterButton extends OAuthButton {

	/**
	 * Creates a "Log in with Twitter" button that will use the given API
	 * key/secret to authenticate the user with Twitter, and then call the given
	 * callback with {@link User} details.
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
	public TwitterButton(String apiKey, String apiSecret,
			OAuthListener authListener) {
		super("Log in with Twitter", apiKey, apiSecret, authListener);
	}

	/**
	 * Creates a button with the given caption that will use the given API
	 * key/secret to authenticate the user with Twitter, and then call the given
	 * callback with {@link User} details.
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
	public TwitterButton(String caption, String apiKey, String apiSecret,
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
		return MyTwitterApi.class;
	}

	@Override
	protected String getVerifierName() {
		return "oauth_verifier";
	}

	@Override
	protected String getJsonDataUrl() {
		String response = accessToken.getRawResponse();
		String id = extract(response, USER_REGEX);
		return "https://api.twitter.com/1/users/show.json?user_id=" + id;
	}

	@Override
	protected Class<? extends User> getUserClass() {
		return TwitterUser.class;
	}

	private static final Pattern USER_REGEX = Pattern
			.compile("user_id=([^&]+)");

	private String extract(String response, Pattern p) {
		Matcher matcher = p.matcher(response);
		if (matcher.find() && matcher.groupCount() >= 1) {
			return OAuthEncoder.decode(matcher.group(1));
		} else {
			throw new OAuthException(
					"Response body is incorrect. Can't extract token and secret from this: '"
							+ response + "'", null);
		}
	}

	public static class MyTwitterApi extends TwitterApi {

		private static final String AUTHENTICATE_URL = "https://api.twitter.com/oauth/authenticate?oauth_token=%s";

		@Override
		public String getAuthorizationUrl(Token requestToken) {
			return String.format(AUTHENTICATE_URL, requestToken.getToken());
		}

	}

	public static class TwitterUser implements User {

		@SerializedName("screen_name")
		private String screenName;
		private String id;
		private String name;
		private String token;
		private String tokenSecret;

		public String getToken() {
			return token;
		}

		public String getTokenSecret() {
			return tokenSecret;
		}

		public String getName() {
			return name;
		}

		public String getScreenName() {
			return screenName;
		}

		public String getPictureUrl() {
			return "https://api.twitter.com/1/users/profile_image?screen_name="
					+ screenName;
		}

		public String getId() {
			return id;
		}

		public String getPublicProfileUrl() {
			return "https://twitter.com/" + screenName;
		}

		public String getService() {
			return "twitter";
		}

		public String getEmail() {
			return null;
		}

	}
}
