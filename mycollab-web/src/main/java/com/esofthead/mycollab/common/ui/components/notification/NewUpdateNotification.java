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

import java.util.Properties;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class NewUpdateNotification extends AbstractNotification {
    private Properties props;

    public NewUpdateNotification(Properties props) {
        super(AbstractNotification.WARNING);

        this.props = props;
    }

    @Override
    public Component renderContent() {
        Span spanEl = new Span();
        spanEl.appendText("There is the new MyCollab version " + props.getProperty("version") + " . For the " +
                "enhancements and security purpose, you should upgrade to the latest version at ");

        A link = new A(props.getProperty("downloadLink"), "_blank");
        link.appendText("here");
        spanEl.appendChild(link);
        return new Label(FontAwesome.EXCLAMATION.getHtml() + " " + spanEl.write(), ContentMode.HTML);
    }
}
