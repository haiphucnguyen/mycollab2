package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import org.apache.commons.lang3.StringUtils;

/**
 * @author MyCollab Ltd.
 * @since 4.5.3
 */
public class UrlLinkViewField extends CustomField<String> {
    private static final long serialVersionUID = 1L;

    private String url;

    public UrlLinkViewField(String url) {
        this.url = url;
    }

    @Override
    public Class<String> getType() {
        return String.class;
    }

    @Override
    protected Component initContent() {
        if (StringUtils.isBlank(url)) {
            Label lbl = new Label("&nbsp;");
            lbl.setContentMode(ContentMode.HTML);
            return lbl;
        } else {
            final Link link = new Link(url, new ExternalResource(url));
            link.setTargetName("_blank");
            return link;
        }
    }
}
