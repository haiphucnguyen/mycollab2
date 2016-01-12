package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.TextField;
import org.vaadin.viritin.layouts.MHorizontalLayout;

import static com.esofthead.mycollab.core.utils.StringUtils.isNotBlank;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
public abstract class SearchTextField extends MHorizontalLayout {
    private ELabel icon;
    private TextField innerField;
    private ShortcutListener shortcutListener = new ShortcutListener("searchfield", ShortcutAction.KeyCode.ENTER, null) {
        @Override
        public void handleAction(Object sender, Object target) {
            String value = ((TextField) target).getValue();
            if (isNotBlank(value)) {
                doSearch(value);
            }
        }
    };

    public SearchTextField() {
        this.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        icon = new ELabel(FontAwesome.SEARCH);
        innerField = new TextField();
        innerField.setImmediate(true);
        innerField.setInputPrompt("Search");
        innerField.setWidth("180px");
        this.with(icon, innerField).withStyleName("searchfield");
        ShortcutExtension.installShortcutAction(innerField, shortcutListener);
    }

    abstract public void doSearch(String value);

    public void setInputPrompt(String value) {
        innerField.setInputPrompt(value);
    }
}
