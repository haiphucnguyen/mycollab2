package com.esofthead.mycollab.pages;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.https.RequireHttps;
import org.apache.wicket.request.http.handler.RedirectRequestHandler;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.SiteConfiguration;
import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.rest.client.RemoteServiceProxy;
import com.esofthead.mycollab.rest.server.resource.UserResource;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RequireHttps
public class SignInPage extends BasePage {

    private static final long serialVersionUID = 1L;

    private static Logger log = LoggerFactory.getLogger(SignInPage.class);

    final MarkupContainer listContainer;
    final RepeatingView subdomainList;
    final Label helpText;
    final FeedbackPanel feedbackPanel;
    final TextField<String> email;
    final StatelessForm<Void> form;
    final AjaxButton submitBtn;

    public SignInPage(final PageParameters parameters) {
        super(parameters);

        email = new TextField<String>("emailfield", new Model<String>());
        email.setOutputMarkupId(true);

        form = new StatelessForm<Void>("signinform");
        form.setOutputMarkupId(true);

        listContainer = new WebMarkupContainer("listcontainer");
        listContainer.setOutputMarkupId(true);

        subdomainList = new RepeatingView("subdomainrepeat");
        subdomainList.setOutputMarkupId(true);

        feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);

        helpText = new Label("helptext", "");
        helpText.setOutputMarkupId(true);

        submitBtn = new AjaxButton("ajax-button", form) {

            private static final long serialVersionUID = 1L;

            @Override
            public void onSubmit(AjaxRequestTarget target, Form<?> form) {
                log.info("Signin page: " + SiteConfiguration.getApiUrl());
                UserResource userResource = RemoteServiceProxy.build(
                        SiteConfiguration.getApiUrl(), UserResource.class);

                try {
                    SignUserIn(userResource, target, null);
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
        submitBtn.setOutputMarkupId(true);

        if (!parameters.get("email").isNull()) {
            String emailValue = parameters.get("email").toString();
            email.setDefaultModel(new Model<String>(emailValue));
            UserResource userResource = RemoteServiceProxy.build(
                    SiteConfiguration.getApiUrl(), UserResource.class);
            SignUserIn(userResource, null, emailValue);
        }

        add(form);
        add(feedbackPanel);

        listContainer.add(helpText);
        listContainer.add(subdomainList);

        form.add(email);
        form.add(submitBtn);
        form.add(listContainer);

        add(new Label("pagetitle", "Sign In"));
    }

    protected void SignUserIn(UserResource userResource,
            AjaxRequestTarget target, String emailValue) {
        listContainer.removeAll();
        subdomainList.removeAll();
        helpText.setDefaultModel(new Model<String>(""));

        String emailString;
        if (emailValue != null)
            emailString = emailValue;
        else
            emailString = email.getModelObject();

        if (target != null)
            target.add(feedbackPanel);

        Response response = userResource.getSubdomainsOfUser(emailString);
        Gson gson = new GsonBuilder().create();
        String responseVal = response.readEntity(String.class);
        log.debug("Domains of user {} is {}", emailString, responseVal);
        String[] subdomains = gson.fromJson(responseVal, String[].class);

        if (subdomains == null || subdomains.length == 0) {
            this.error("Can not find subdomain of user " + emailString);
        } else if (subdomains.length == 1) {
            String redirectUrl = "";
            if (SiteConfiguration.getDeploymentMode() == DeploymentMode.LOCAL) {
                redirectUrl = SiteConfiguration.getAppUrl();
            } else {
                redirectUrl = String.format(SiteConfiguration.getAppUrl(),
                        subdomains[0]);
            }

            log.debug("Redirect user {} to subdomain {}", emailString,
                    redirectUrl);
            this.getRequestCycle().scheduleRequestHandlerAfterCurrent(
                    new RedirectRequestHandler(redirectUrl));
        } else {
            for (String subdomainString : subdomains) {
                log.debug("List subdomain {} to user {}", subdomainString,
                        emailString);
                final AbstractItem newItem = new AbstractItem(
                        subdomainList.newChildId());

                Label subdomain = new Label("subdomain", subdomainString
                        + ".mycollab.com");
                newItem.add(subdomain);

                ExternalLink gotosubdomainBtn = new ExternalLink(
                        "gotosubdomain", "https://" + subdomainString
                                + ".mycollab.com");
                newItem.add(gotosubdomainBtn);

                subdomainList.add(newItem);
            }
            helpText.setDefaultModel(new Model<String>(
                    "<div class='helptext'>Here're subdomains that you're working on, please click <b>GO</b> button next to the subdomain to sign in to that subdomain.</div>"));
            helpText.setEscapeModelStrings(false);
        }
        if (target != null) {
            listContainer.add(helpText);
            listContainer.add(subdomainList);
            target.add(listContainer);
        }
        // replace(form);
    }
}
