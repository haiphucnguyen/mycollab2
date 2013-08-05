package com.esofthead.mycollab.vaadin.oauth;

import org.scribe.builder.api.Api;

public class DropboxButton extends OAuthButton {
	private static final long serialVersionUID = 1L;

	public DropboxButton(String apiKey, String apiSecret,
			OAuthListener authListener) {
		super("Add Dropbox", apiKey, apiSecret, authListener);
	}

	@Override
	protected String getAuthUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<? extends Api> getApi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getVerifierName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getJsonDataUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<? extends User> getUserClass() {
		return DropboxUser.class;
	}

	public static class DropboxUser implements User {
		private String id;
		private String token;
		private String tokenSecret;

		@Override
		public String getService() {
			return "Dropbox";
		}

		@Override
		public String getName() {
			return "";
		}

		@Override
		public String getScreenName() {
			return "";
		}

		@Override
		public String getPictureUrl() {
			return "";
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getPublicProfileUrl() {
			return "";
		}

		@Override
		public String getEmail() {
			return "";
		}

		@Override
		public String getToken() {
			return token;
		}

		@Override
		public String getTokenSecret() {
			return tokenSecret;
		}

	}

}
