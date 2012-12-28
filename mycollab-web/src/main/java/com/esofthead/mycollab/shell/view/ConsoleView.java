package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Label;

@ViewComponent
public class ConsoleView extends AbstractView {

    private static final long serialVersionUID = 1L;

    public ConsoleView() {
        this.addComponent(new Label("Console View"));
    }
}
