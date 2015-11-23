package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.vaadin.ui.SafeHtmlLabel;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

import static com.esofthead.mycollab.core.utils.StringUtils.isBlank;

/**
 * @author MyCollab Ltd.
 * @since 4.5.3
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class RichTextViewField extends CustomField {
    private static final long serialVersionUID = 1L;

    private String value;

    public RichTextViewField(String value) {
        this.value = value;
    }

    @Override
    public Class<?> getType() {
        return String.class;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    protected Component initContent() {
        if (isBlank(value)) {
            Label lbl = new Label("&nbsp;");
            lbl.setContentMode(ContentMode.HTML);
            lbl.setWidth("100%");
            return lbl;
        } else {
            SafeHtmlLabel link = new SafeHtmlLabel(value);
            link.setWidth("100%");
            return link;
        }
    }
}
