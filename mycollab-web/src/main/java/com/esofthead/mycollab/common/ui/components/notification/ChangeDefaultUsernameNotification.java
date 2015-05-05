package com.esofthead.mycollab.common.ui.components.notification;

import com.esofthead.mycollab.common.ui.components.AbstractNotification;
import com.esofthead.mycollab.module.user.AccountLinkGenerator;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 5.0.6
 */
public class ChangeDefaultUsernameNotification  extends AbstractNotification {

    public ChangeDefaultUsernameNotification() {
        super(AbstractNotification.WARNING);
    }

    @Override
    public Component renderContent() {
        Span spanEl = new Span();
        spanEl.appendText("You use the default username is admin@mycollab.com. You should change it at ");

        A link = new A(AccountLinkGenerator.generateFullProfileLink(AppContext.getSiteUrl()));
        link.appendText("here");
        spanEl.appendChild(link);
        return new Label(FontAwesome.EXCLAMATION.getHtml() + " " + spanEl.write(), ContentMode.HTML);
    }

}
