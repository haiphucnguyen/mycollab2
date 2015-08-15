package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
public class PopupBeanField extends PopupView {
    public PopupBeanField(final String valueAsHtml) {
        super(new Content() {
            @Override
            public String getMinimizedValueAsHTML() {
                return valueAsHtml;
            }

            @Override
            public Component getPopupComponent() {
                return new Label("The feature is not presented for this edition");
            }
        });
        this.setStyleName("block-popupedit");
    }
}
