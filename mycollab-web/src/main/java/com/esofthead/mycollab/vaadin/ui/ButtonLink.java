package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Button;

public class ButtonLink extends Button {

    private static final long serialVersionUID = 1L;

    public ButtonLink(String caption, Boolean wordWrap) {
        super(caption);
        this.setStyleName("link");
        if (wordWrap)
            this.addStyleName("wordWrap");
    }

    public ButtonLink(String caption) {
        this(caption, true);
    }

    public ButtonLink(String caption, ClickListener listener, Boolean wordWrap) {
        super(caption, listener);
        this.setStyleName("link");
        if (wordWrap)
            this.addStyleName("wordWrap");
    }

    public ButtonLink(String caption, ClickListener listener) {
        this(caption, listener, true);
    }
}
