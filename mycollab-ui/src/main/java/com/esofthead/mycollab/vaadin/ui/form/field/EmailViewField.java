package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Link;

/**
 * @author MyCollab Ltd.
 * @since 4.5.3
 */
public class EmailViewField extends CustomField<String> {
    private static final long serialVersionUID = 1L;

    private String email;

    public EmailViewField(String email) {
        this.email = email;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    protected Component initContent() {
        final Link emailLink = new Link(email, new ExternalResource("mailto:" + email));
        return emailLink;
    }
}
