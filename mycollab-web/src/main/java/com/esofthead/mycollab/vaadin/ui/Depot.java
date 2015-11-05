/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.viritin.layouts.MCssLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class Depot extends VerticalLayout {
    private static final long serialVersionUID = 1L;

    private boolean isOpened = true;
    protected MHorizontalLayout header;
    protected Label headerLbl;
    protected AbstractOrderedLayout headerContent;
    protected ComponentContainer bodyContent;

    public Depot(String title, ComponentContainer component) {
        this(title, null, component);
    }

    public Depot(String title, AbstractOrderedLayout headerElement, ComponentContainer component) {
        this(new Label(title), headerElement, component);
    }

    public Depot(Label titleLbl, AbstractOrderedLayout headerElement, ComponentContainer component) {
        this.setStyleName("depotComp");
        this.setMargin(false);
        header = new MHorizontalLayout().withHeight("34px").withStyleName("depotHeader");
        bodyContent = component;
        if (headerElement != null) {
            headerContent = headerElement;
        } else {
            headerContent = new MHorizontalLayout().withMargin(true);
            headerContent.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
            headerContent.setVisible(false);
        }

        headerContent.setStyleName("header-elements");
        headerContent.setWidthUndefined();

        this.addComponent(header);

        headerLbl = titleLbl;
        headerLbl.setStyleName(ValoTheme.LABEL_H2);
        final MCssLayout headerLeft = new MCssLayout(headerLbl).withStyleName("depot-title");
        headerLeft.addLayoutClickListener(new LayoutClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void layoutClick(final LayoutClickEvent event) {
                isOpened = !isOpened;
                if (isOpened) {
                    bodyContent.setVisible(true);
                    removeStyleName("collapsed");
                } else {
                    bodyContent.setVisible(false);
                    addStyleName("collapsed");
                }
            }
        });
        header.with(headerLeft, headerContent).withAlign(headerLeft, Alignment.MIDDLE_LEFT).withAlign(headerContent,
                Alignment.MIDDLE_RIGHT).expand(headerLeft);

        final CustomComponent customComp = new CustomComponent(bodyContent);
        customComp.setWidth("100%");
        bodyContent.addStyleName("depotContent");

        this.addComponent(customComp);
        this.setComponentAlignment(customComp, Alignment.TOP_CENTER);
    }

    public void addHeaderElement(final Component component) {
        if (component != null) {
            headerContent.addComponent(component);
            headerContent.setComponentAlignment(component, Alignment.MIDDLE_RIGHT);
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
