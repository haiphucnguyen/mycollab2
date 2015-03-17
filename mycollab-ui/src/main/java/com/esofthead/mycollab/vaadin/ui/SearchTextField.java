package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextField;
import org.vaadin.maddon.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
public class SearchTextField extends MHorizontalLayout {
    private FontIconLabel icon;
    private TextField innerField;

    public SearchTextField() {
        this.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        icon = new FontIconLabel(FontAwesome.SEARCH);
        innerField = new TextField();
        innerField.setInputPrompt("Search");
        innerField.setWidth("180px");
        this.with(icon, innerField).withStyleName("searchfield");
        innerField.addShortcutListener(new ShortcutListener("searchtext", ShortcutAction.KeyCode.ENTER, null) {
            @Override
            public void handleAction(Object o, Object o1) {

            }
        });
    }

    public void setInputPrompt(String value) {
        innerField.setInputPrompt(value);
    }
}
