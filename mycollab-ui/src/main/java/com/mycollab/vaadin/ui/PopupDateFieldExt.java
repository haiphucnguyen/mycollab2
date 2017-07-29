package com.mycollab.vaadin.ui;

import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.ui.PopupDateField;

import java.util.Date;

/**
 * @author MyCollab Ltd.
 * @since 4.5.4
 */
public class PopupDateFieldExt extends PopupDateField {
    private static final long serialVersionUID = 1L;

    public PopupDateFieldExt() {
        this(null);
    }

    public PopupDateFieldExt(Date value) {
        this(null, value);
    }

    public PopupDateFieldExt(String caption, Date value) {
        super(null, value);
        this.setTimeZone(UserUIContext.getUserTimeZone());
        this.setDateFormat(MyCollabUI.getDateFormat());
    }

    public PopupDateFieldExt withWidth(String width) {
        this.setWidth(width);
        return this;
    }
}
