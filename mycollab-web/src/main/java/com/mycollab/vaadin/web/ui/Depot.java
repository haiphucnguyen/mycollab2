package com.mycollab.vaadin.web.ui;

import com.vaadin.ui.*;
import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class Depot extends DDVerticalLayout {
    private static final long serialVersionUID = 1L;

    private boolean isOpened = true;
    protected MHorizontalLayout header;
    protected Label headerLbl;
    protected MHorizontalLayout headerContent;
    protected ComponentContainer bodyContent;

    public Depot(String title, ComponentContainer content) {
        this.addStyleName("depotComp");
        header = new MHorizontalLayout().withHeight("40px").withStyleName("depotHeader");
        bodyContent = content;
        bodyContent.setWidth("100%");
        headerContent = new MHorizontalLayout().withFullHeight().withWidthUndefined().withVisible(false);
        this.addComponent(header);

        headerLbl = new Label(title);
        final MHorizontalLayout headerLeft = new MHorizontalLayout(headerLbl).withStyleName("depot-title")
                .withAlign(headerLbl, Alignment.MIDDLE_LEFT).withFullWidth();
        headerLeft.addLayoutClickListener(layoutClickEvent -> {
            isOpened = !isOpened;
            if (isOpened) {
                bodyContent.setVisible(true);
                removeStyleName("collapsed");
                header.removeStyleName("border-bottom");
            } else {
                bodyContent.setVisible(false);
                addStyleName("collapsed");
                header.addStyleName("border-bottom");
            }
        });
        header.with(headerLeft, headerContent).withAlign(headerLeft, Alignment.MIDDLE_LEFT).withAlign(headerContent,
                Alignment.MIDDLE_RIGHT).expand(headerLeft);

        bodyContent.addStyleName("depotContent");
        this.addComponent(bodyContent);
    }

    public void addHeaderElement(final Component component) {
        if (component != null) {
            headerContent.with(component).withAlign(component, Alignment.MIDDLE_RIGHT);
            headerContent.setVisible(true);
        }
    }

    public void setContentBorder(final boolean hasBorder) {
        if (hasBorder) {
            bodyContent.addStyleName("bordered");
        } else {
            bodyContent.removeStyleName("bordered");
        }
    }

    public void setTitle(final String title) {
        headerLbl.setValue(title);
    }

    public ComponentContainer getContent() {
        return bodyContent;
    }
}
