package com.esofthead.mycollab.uicomponents;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.brickred.socialauth.util.Constants;

public class OAuthServiceSignupPanel extends Panel {
    private static final long serialVersionUID = 1L;

    private Label _instructionText;

    public OAuthServiceSignupPanel(String id, String serviceId) {
        super(id);

        _instructionText = new Label(
                "instruction-text",
                "To finish linking your Google account with your MyCollab account, please enter your MyCollab password and your desired subdomain below.");

        this.add(_instructionText);

        if (serviceId.equals(Constants.FACEBOOK)) {
            _instructionText
                    .setDefaultModel(new Model<String>(
                            "To finish linking your Facebook account with your MyCollab account, please enter your MyCollab password and your desired subdomain below."));
        } else if (serviceId.equals(Constants.YAHOO)) {
            _instructionText
                    .setDefaultModel(new Model<String>(
                            "To finish linking your Yahoo account with your MyCollab account, please enter your MyCollab password and your desired subdomain below."));
        } else if (serviceId.equals(Constants.LINKEDIN)) {
            _instructionText
                    .setDefaultModel(new Model<String>(
                            "To finish linking your LinkedIn account with your MyCollab account, please enter your MyCollab password and your desired subdomain below."));
        } else if (!serviceId.equals(Constants.GOOGLE_PLUS)) {
            this.setVisible(false);
        }
    }

}
