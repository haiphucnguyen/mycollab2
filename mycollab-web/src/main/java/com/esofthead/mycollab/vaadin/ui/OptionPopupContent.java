package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
public class OptionPopupContent extends MVerticalLayout {
    public OptionPopupContent() {
        withSpacing(false).withMargin(false).withStyleName(UIConstants.OPTION_POPUP_CONTENT).withWidth("150px");
    }

    @Override
    public OptionPopupContent withWidth(String width) {
        return (OptionPopupContent) super.withWidth(width);
    }

    public void addOption(Button btn) {
        CssLayout wrap = new CssLayout();
        btn.setStyleName("action");
        wrap.addStyleName("action-wrap");
        wrap.addComponent(btn);
        addComponent(wrap);
    }
}
