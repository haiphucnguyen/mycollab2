package com.mycollab.vaadin.web.ui;

import com.vaadin.ui.Button;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class CommonUIFactory {
    public static Button createButtonTooltip(String caption, String description) {
        Button btn = new Button(caption);
        btn.setDescription(description);
        return btn;
    }

    public static Button createButtonTooltip(String caption, String description, Button.ClickListener listener) {
        Button btn = new Button(caption);
        btn.setDescription(description);
        btn.addClickListener(listener);
        return btn;
    }
}
