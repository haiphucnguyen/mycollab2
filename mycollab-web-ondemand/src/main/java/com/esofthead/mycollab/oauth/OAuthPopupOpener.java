package com.esofthead.mycollab.oauth;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.ondemand.module.file.view.OAuthUI;
import com.vaadin.server.BrowserWindowOpener;
import org.scribe.builder.api.Api;

import java.util.LinkedList;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class OAuthPopupOpener extends BrowserWindowOpener {
    private LinkedList<OAuthListener> listeners = new LinkedList<>();

    private final OAuthData data;

    private OAuthListener dataListener;

    public OAuthPopupOpener(Class<? extends Api> apiClass, String key, String secret) {
        super(OAuthUI.class);
        this.data = new OAuthData(apiClass, key, secret);
        setCallbackToDefault();
    }

    public void addOAuthListener(OAuthListener listener) {
        listeners.add(listener);
    }

    public void removeOAuthListener(OAuthListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void attach() {
        super.attach();

        // Adding the session attribute.
        String attr = data.getSessionAttributeName();
        getSession().setAttribute(attr, data);
        setParameter("data", attr);

        dataListener = new OAuthListener() {
            @Override
            public void authSuccessful(String accessToken, String accessTokenSecret) {
                fireAuthSuccessful(accessToken, accessTokenSecret);
            }

            @Override
            public void authDenied(String reason) {
                fireAuthFailed(reason);
            }
        };
        data.addListener(dataListener);
    }

    @Override
    public void detach() {
        super.detach();

        data.removeListener(dataListener);

        // Deleting the session attribute.
        getSession().setAttribute(data.getSessionAttributeName(), null);
    }

    public void setCallback(String callback) {
        data.setCallback(callback);
    }

    public void setCallbackToDefault() {
        setCallback(getDefaultCallback());
    }

    public void setScope(String scope) {
        data.setScope(scope);
    }

    private static String getDefaultCallback() {
//        URI u = Page.getCurrent().getLocation();
//        return u.getScheme() + "://" + u.getAuthority() + u.getPath();
        return SiteConfiguration.getDropboxCallbackUrl();
    }

    private void fireAuthSuccessful(final String accessToken, final String accessTokenSecret) {
        // Coming from different thread than the usual Vaadin server visit.
        // That's why we have to call access (TODO: session or UI?, seems like UI is correct.)
        // Doing this here so our listeners don't need to.
        getUI().access(new Runnable() {
            @Override
            public void run() {
                for (final OAuthListener li : listeners) {
                    li.authSuccessful(accessToken, accessTokenSecret);
                }
            }
        });
    }

    private void fireAuthFailed(final String reason) {
        getUI().access(new Runnable() {
            @Override
            public void run() {
                for (final OAuthListener li : listeners) {
                    li.authDenied(reason);
                }

            }
        });
    }

    public void setCallbackInjecter(OAuthCallbackInjecter injecter) {
        data.setCallbackInjecter(injecter);
    }
}
