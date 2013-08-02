package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Button;

public class ButtonLink extends Button {

    private static final long serialVersionUID = 1L;

    public ButtonLink(String caption) {
        super(caption);
        this.setStyleName("link");
        this.addStyleName("wordWrap");
    }

    public ButtonLink(String caption, ClickListener listener) {
        super(caption, listener);
        this.setStyleName("link");
        this.addStyleName("wordWrap");
    }
}
