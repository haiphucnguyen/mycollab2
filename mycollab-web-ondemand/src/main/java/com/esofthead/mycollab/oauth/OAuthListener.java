package com.esofthead.mycollab.oauth;

/**
 * Called on OAuth success/failure.
 *
 * In the case that the user doesn't browse back to our application from
 * the authorization url, neither of the methods is called.
 */
public interface OAuthListener {
    /**
     * Called on successful OAuth.
     *
     * @param accessToken       the OAuth access token
     * @param accessTokenSecret the OAuth access token secret
     */
    void authSuccessful(String accessToken, String accessTokenSecret);

    /**
     * Called when the OAuth was denied.
     *
     * @param reason TODO: the reasons currently returned are not helpful.
     */
    void authDenied(String reason);
}
