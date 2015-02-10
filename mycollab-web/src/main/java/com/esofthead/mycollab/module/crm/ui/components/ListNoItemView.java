package com.esofthead.mycollab.module.crm.ui.components;

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
public abstract class ListNoItemView extends AbstractPageView {
    public ListNoItemView() {
        MVerticalLayout layout = new MVerticalLayout().withWidth("800px");
        layout.addStyleName("case-noitem");
        layout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        Label image = new Label(titleIcon().getHtml(), ContentMode.HTML);
        image.setSizeUndefined();
        layout.with(image).withAlign(image, Alignment.TOP_CENTER);

        Label title = new Label(titleMessage());
        title.addStyleName("h2");
        title.setWidthUndefined();
        layout.addComponent(title);

        Label hintLabel = new Label(hintMessage());
        hintLabel.setWidthUndefined();
        layout.addComponent(hintLabel);

        Button btCreateContact = new Button(actionMessage(), actionListener());

        MHorizontalLayout links = new MHorizontalLayout();

        links.addComponent(btCreateContact);
        btCreateContact.addStyleName(UIConstants.THEME_GREEN_LINK);

		/*
         * Label or = new Label("Or"); or.setStyleName("h2");
		 * links.addComponent(or);
		 *
		 * Button btImportContact = new Button("Import Cases", new
		 * Button.ClickListener() { private static final long serialVersionUID =
		 * 1L;
		 *
		 * @Override public void buttonClick(ClickEvent arg0) {
		 * UI.getCurrent().addWindow(new CaseImportWindow()); } });
		 *
		 * btImportContact.addStyleName(UIConstants.THEME_GRAY_LINK);
		 *
		 *
		 * links.addComponent(btImportContact);
		 */

        layout.addComponent(links);
        this.with(layout).withAlign(layout, Alignment.TOP_CENTER);
    }

    abstract protected FontAwesome titleIcon();

    abstract protected String titleMessage();

    abstract protected String hintMessage();

    abstract protected String actionMessage();

    abstract protected Button.ClickListener actionListener();
}
