package com.esofthead.mycollab.module.project.view.task;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;
import com.vaadin.server.GenericFontIcon;

/**
 * Created by baohan on 7/7/15.
 */
public enum TaskPriorityResource implements FontIcon {
    URGENT(FontAwesome.ADJUST.getCodepoint()), HIGH(FontAwesome.ADJUST.getCodepoint()),
    MEDIUM(FontAwesome.ADJUST.getCodepoint()), LOW(FontAwesome.ADJUST.getCodepoint()),
    NONE(FontAwesome.ADJUST.getCodepoint());;

    private int codepoint;

    private TaskPriorityResource(int codepoint) {
        this.codepoint = codepoint;
    }

    public String getMIMEType() {
        throw new UnsupportedOperationException(FontIcon.class.getSimpleName() + " should not be used where a MIME type is needed.");
    }

    public String getFontFamily() {
        return "FontAwesome";
    }

    public int getCodepoint() {
        return this.codepoint;
    }

    public String getHtml() {
        return GenericFontIcon.getHtml("FontAwesome", this.codepoint);
    }
}
