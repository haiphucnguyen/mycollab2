package com.esofthead.mycollab.pages;

import javax.servlet.http.HttpSession;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.StatelessLink;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.protocol.http.servlet.ServletWebRequest;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.brickred.socialauth.SocialAuthConfig;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.ErrorReportingUtils;
import com.esofthead.mycollab.SiteConfiguration;
import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.TimezoneMapper;
import com.esofthead.mycollab.core.utils.TimezoneMapper.TimezoneExt;
import com.esofthead.mycollab.rest.client.RemoteServiceProxy;
import com.esofthead.mycollab.rest.server.domain.SignupForm;
import com.esofthead.mycollab.rest.server.resource.AccountResource;
import com.esofthead.mycollab.uicomponents.OAuthServiceSignupPanel;

public class SignUpPage extends BasePage {

    private static final long serialVersionUID = 1L;

    private static final String GOOGLE_PLUS_LINK = "signupGoogle";

    private static final String FACEBOOK_LINK = "signupFacebook";

    private static final String TWITTER_LINK = "signupTwitter";

    private static final String LINKEDIN_LINK = "signupLinkedIn";

    private static final String YAHOO_LINK = "signupYahoo";

    private static Logger log = LoggerFactory.getLogger(SignUpPage.class);

    public String selected = "10";

    private int planId = 1;

    private HttpSession session;

    private String authEmail = null;

    public SignUpPage(final PageParameters parameters) {
        super(parameters);

        session = ((ServletWebRequest) RequestCycle.get().getRequest())
                .getContainerRequest().getSession();

        if (!parameters.get("planId").isNull()) {
            planId = parameters.get("planId").toInt();
        }

        final RequiredTextField<String> email = new RequiredTextField<String>(
                "emailfield", new Model<String>(authEmail));
        email.setLabel(new ResourceModel("label.email"));
        email.add(EmailAddressValidator.getInstance());
        if (session.getAttribute("authEmail") != null) {
            authEmail = session.getAttribute("authEmail").toString();
            session.removeAttribute("authEmail");
            email.add(AttributeAppender.append("readonly", new Model<String>(
                    "true")));
            email.setDefaultModel(new Model<String>(authEmail));
        }

        final CheckBox receiveupdate = new CheckBox("receiveupdatefield",
                new Model<Boolean>());

        final RequiredTextField<String> subdomain = new RequiredTextField<String>(
                "subdomainfield", new Model<String>());
        subdomain.setLabel(new ResourceModel("label.subdomain"));

        final PasswordTextField password = new PasswordTextField(
                "passwordfield", new Model<String>());
        password.setLabel(new ResourceModel("label.password"));

        final PasswordTextField cpassword = new PasswordTextField(
                "cpasswordfield", new Model<String>());
        cpassword.setLabel(new ResourceModel("label.cpassword"));

        final HiddenField<String> timezone = new HiddenField<String>(
                "timezonefield", new Model<String>());

        final StatelessForm<Void> form = new StatelessForm<Void>("signupform") {

            private static final long serialVersionUID = 1L;

            @Override
            protected void onSubmit() {
                AccountResource userResource = RemoteServiceProxy.build(
                        SiteConfiguration.getApiUrl(), AccountResource.class);

                try {

                    final SignupForm form = new SignupForm();
                    form.setSubdomain(subdomain.getModelObject());
                    form.setPlanId(Integer.parseInt(planId + ""));
                    if (authEmail != null) {
                        form.setEmail(authEmail);
                    } else {
                        form.setEmail(email.getModelObject());
                    }

                    form.setPassword(password.getModelObject());
                    form.setTimezoneId(timezone.getModelObject());

                    log.debug("Submit form {}", SiteConfiguration.getApiUrl());
                    final String response = userResource.signup(form);
                    log.debug("Response of site: {}", response);

                    this.getRequestCycle().scheduleRequestHandlerAfterCurrent(
                            new RedirectRequestHandler(response));
                } catch (BadRequestException e) {
                    Response response = e.getResponse();
                    String mycollabEx = response.readEntity(String.class);
                    if (mycollabEx != null) {
                        this.error(mycollabEx);
                    } else {
                        this.error(e.getMessage());
                    }
                } catch (Exception e) {
                    this.error(e.getMessage());
                }
            }
        };

        this.add(form);
        this.add(new FeedbackPanel("feedback"));
        form.add(subdomain);
        form.add(email);
        form.add(password);
        form.add(cpassword);
        form.add(timezone);
        form.add(receiveupdate);

        final RepeatingView timezoneAreaRepeat = new RepeatingView("arearepeat");
        form.add(timezoneAreaRepeat);

        for (final String timezoneArea : TimezoneMapper.AREAS) {
            final AbstractItem areaItem = new AbstractItem(
                    timezoneAreaRepeat.newChildId());
            timezoneAreaRepeat.add(areaItem);

            areaItem.add(new Label("one_area", timezoneArea));

            final RepeatingView timezoneRepeat = new RepeatingView(
                    "timezonerepeat");
            areaItem.add(timezoneRepeat);

            for (final TimezoneExt oneTimezone : TimezoneMapper.timeMap
                    .values()) {
                if (oneTimezone.getArea().equals(timezoneArea)) {
                    final AbstractItem timezoneItem = new AbstractItem(
                            timezoneRepeat.newChildId());
                    timezoneRepeat.add(timezoneItem);
                    timezoneItem.add(new Label("one_timezone", oneTimezone
                            .getDisplayName()));
                    timezoneItem.add(AttributeModifier.replace("data-tag",
                            new AbstractReadOnlyModel<String>() {
                                private static final long serialVersionUID = 1L;

                                @Override
                                public String getObject() {
                                    return oneTimezone.getId();
                                }
                            }));
                }
            }
        }

        String serviceId = "";
        try {
            if (session.getAttribute("authManager") != null) {
                SocialAuthManager manager = (SocialAuthManager) session
                        .getAttribute("authManager");
                serviceId = manager.getCurrentAuthProvider().getProviderId();
                session.removeAttribute("authManager");
            }
        } catch (Exception e) {
            ErrorReportingUtils.reportError(e);
        }

        final String currentService = serviceId;

        this.add(new OAuthServiceSignupPanel("oauth-service-panel",
                currentService));

        StatelessLink<Void> googleLink = new StatelessLink<Void>("signupGoogle") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                doAuthenticate(GOOGLE_PLUS_LINK);
            }
        };
        googleLink.add(AttributeModifier.replace("class",
                new AbstractReadOnlyModel<String>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getObject() {
                        if (currentService.equals("")
                                || currentService.equals(Constants.GOOGLE_PLUS)) {
                            if (currentService.equals(Constants.GOOGLE_PLUS))
                                return "service-selected";
                            return "service-enabled";
                        }
                        return "service-disabled";
                    }
                }));
        if (!currentService.equals("")
                && !currentService.equals(Constants.GOOGLE_PLUS))
            googleLink.setEnabled(false);
        this.add(googleLink);

        StatelessLink<Void> facebookLink = new StatelessLink<Void>(
                "signupFacebook") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                doAuthenticate(FACEBOOK_LINK);
            }

        };
        facebookLink.add(AttributeModifier.replace("class",
                new AbstractReadOnlyModel<String>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getObject() {
                        if (currentService.equals("")
                                || currentService.equals(Constants.FACEBOOK)) {
                            if (currentService.equals(Constants.FACEBOOK))
                                return "service-selected";
                            return "service-enabled";
                        }
                        return "service-disabled";
                    }
                }));
        if (!currentService.equals("")
                && !currentService.equals(Constants.FACEBOOK))
            facebookLink.setEnabled(false);
        this.add(facebookLink);

        StatelessLink<Void> linkedInLink = new StatelessLink<Void>(
                "signupLinkedIn") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                doAuthenticate(LINKEDIN_LINK);
            }
        };
        linkedInLink.add(AttributeModifier.replace("class",
                new AbstractReadOnlyModel<String>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getObject() {
                        if (currentService.equals("")
                                || currentService.equals(Constants.LINKEDIN)) {
                            if (currentService.equals(Constants.LINKEDIN))
                                return "service-selected";
                            return "service-enabled";
                        }
                        return "service-disabled";
                    }
                }));
        if (!currentService.equals("")
                && !currentService.equals(Constants.LINKEDIN))
            linkedInLink.setEnabled(false);
        this.add(linkedInLink);

        StatelessLink<Void> yahooLink = new StatelessLink<Void>("signupYahoo") {
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick() {
                doAuthenticate(YAHOO_LINK);
            }
        };
        yahooLink.add(AttributeModifier.replace("class",
                new AbstractReadOnlyModel<String>() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public String getObject() {
                        if (currentService.equals("")
                                || currentService.equals(Constants.YAHOO)) {
                            if (currentService.equals(Constants.YAHOO))
                                return "service-selected";
                            return "service-enabled";
                        }
                        return "service-disabled";
                    }
                }));
        if (!currentService.equals("")
                && !currentService.equals(Constants.YAHOO))
            yahooLink.setEnabled(false);
        this.add(yahooLink);

        Label separatorText = new Label("separator-text", "Or");
        if (currentService != "") {
            separatorText.add(AttributeModifier.replace("class",
                    new AbstractReadOnlyModel<String>() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public String getObject() {
                            return "hidding";
                        }
                    }));
        }
        this.add(separatorText);

        BookmarkablePageLink<Void> backToNormal = new BookmarkablePageLink<Void>(
                "getback-to-normal", SignUpPage.class,
                new PageParameters().add("planId", String.valueOf(planId)));
        form.add(backToNormal);
        if (currentService == "") {
            backToNormal.setVisible(false);
        }

        this.add(new Label("pagetitle", "Sign Up"));
    }

    private void doAuthenticate(String authId) {
        try {
            // Create an instance of SocialAuthConfgi object
            SocialAuthConfig config = SocialAuthConfig.getDefault();

            // load configuration. By default load the configuration
            // from
            // oauth_consumer.properties.
            // You can also pass input stream, properties object or
            // properties file name.
            config.load();

            // Create an instance of SocialAuthManager and set config
            SocialAuthManager manager = new SocialAuthManager();
            manager.setSocialAuthConfig(config);

            // URL of YOUR application which will be called after
            // authentication
            String successUrl = "";
            String providerId = "";

            if (GOOGLE_PLUS_LINK.equals(authId)) {
                successUrl = SiteConfiguration.getSiteUrl()
                        + "oauth2/externalCallbackCommand";
                providerId = "googleplus";
            } else if (FACEBOOK_LINK.equals(authId)) {
                successUrl = SiteConfiguration.getSiteUrl()
                        + "oauth2/externalCallbackCommand";
                providerId = "facebook";
            } else if (TWITTER_LINK.equals(authId)) {
                successUrl = SiteConfiguration.getSiteUrl()
                        + "oauth2/externalCallbackCommand";
                providerId = "twitter";
            } else if (LINKEDIN_LINK.equals(authId)) {
                successUrl = SiteConfiguration.getSiteUrl()
                        + "oauth2/externalCallbackCommand";
                providerId = "linkedin";
            } else if (YAHOO_LINK.equals(authId)) {
                successUrl = SiteConfiguration.getSiteUrl()
                        + "oauth2/externalCallbackCommand";
                providerId = "yahoo";
            } else {
                throw new MyCollabException(
                        "Do not support authentication with external service with id "
                                + authId);
            }

            // get Provider URL to which you should redirect for
            // authentication.
            // id can have values "facebook", "twitter", "yahoo" etc. or
            // the
            // OpenID URL
            String url = manager.getAuthenticationUrl(providerId, successUrl);

            log.debug("Redirect url {}", url);

            // Store in session
            session = ((ServletWebRequest) RequestCycle.get().getRequest())
                    .getContainerRequest().getSession();
            session.setAttribute("authManager", manager);
            session.setAttribute("planId", planId);
            getRequestCycle().scheduleRequestHandlerAfterCurrent(
                    new RedirectRequestHandler(url));
        } catch (Exception e) {
            throw new MyCollabException(e);
        }
    }

}
