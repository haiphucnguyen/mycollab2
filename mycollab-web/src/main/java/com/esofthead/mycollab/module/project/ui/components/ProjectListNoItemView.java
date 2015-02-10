package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public abstract class ProjectListNoItemView extends AbstractPageView {
    public ProjectListNoItemView() {
        MVerticalLayout layout = new MVerticalLayout();
        layout.addStyleName("case-noitem");
        layout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        Label image = new Label(viewIcon().getHtml(), ContentMode.HTML);
        image.setSizeUndefined();
        layout.with(image).withAlign(image, Alignment.TOP_CENTER);

        Label title = new Label(viewTitle());
        title.addStyleName("h2");
        title.setSizeUndefined();
        layout.with(title).withAlign(title, Alignment.TOP_CENTER);

        Label body = new Label(viewHint());
        body.setWidthUndefined();
        layout.addComponent(body);

        Button createBugBtn = new Button(
                actionMessage(), actionListener());
        createBugBtn.setEnabled(hasPermission());

        MHorizontalLayout links = new MHorizontalLayout();

        links.addComponent(createBugBtn);
        createBugBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

        layout.addComponent(links);
        this.addComponent(layout);
        this.setComponentAlignment(layout, Alignment.TOP_CENTER);
    }

    abstract protected FontAwesome viewIcon();

    abstract protected String viewTitle();

    abstract protected String viewHint();

    abstract protected String actionMessage();

    abstract protected Button.ClickListener actionListener();

    abstract protected boolean hasPermission();
}
