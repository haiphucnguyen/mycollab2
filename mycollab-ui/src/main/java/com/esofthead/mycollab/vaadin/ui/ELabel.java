package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

import java.util.Date;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
public class ELabel extends Label {
    public ELabel() {
        super();
    }

    public ELabel(String content) {
        super(content);
    }

    public ELabel(String content, ContentMode mode) {
        super(content, mode);
    }

    public ELabel withDescription(String description) {
        this.setDescription(description);
        return this;
    }

    public ELabel withStyleName(String styleName) {
        this.setStyleName(styleName);
        return this;
    }

    public ELabel prettyDate(Date date) {
        this.setValue(AppContext.formatPrettyTime(date));
        this.setDescription(AppContext.formatDate(date));
        return this;
    }

    public ELabel prettyDateTime(Date date) {
        this.setValue(AppContext.formatPrettyTime(date));
        this.setDescription(AppContext.formatDateTime(date));
        return this;
    }
}
