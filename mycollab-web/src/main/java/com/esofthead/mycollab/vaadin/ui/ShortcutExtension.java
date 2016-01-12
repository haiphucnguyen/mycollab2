package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.event.FieldEvents;
import com.vaadin.event.ShortcutListener;
import com.vaadin.ui.TextField;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
public class ShortcutExtension {
    public static TextField installShortcutAction(final TextField textField, final ShortcutListener listener) {
        textField.addFocusListener(new FieldEvents.FocusListener() {
            @Override
            public void focus(FieldEvents.FocusEvent focusEvent) {
                textField.addShortcutListener(listener);
            }
        });

        textField.addBlurListener(new FieldEvents.BlurListener() {
            @Override
            public void blur(FieldEvents.BlurEvent blurEvent) {
                textField.removeShortcutListener(listener);
            }
        });
        return textField;
    }
}
