package com.esofthead.mycollab.vaadin.ui;

import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class ReadViewLayout extends CssLayout {
    private static final long serialVersionUID = 1L;

    private final HorizontalLayout header;
    private final Embedded iconEmbed;
    private final Label titleLbl;
    private final DetachedTabs viewTab;
    private final CssLayout body;

    public ReadViewLayout(final Resource icon) {
        this(icon, true);
    }

    public ReadViewLayout(final Resource icon, final Boolean hasHeader) {
        this.setSizeFull();
        this.setStyleName("readview-layout");
        if (hasHeader) {
            this.header = new HorizontalLayout();
            this.header.setWidth("100%");
            this.header.setMargin(true, true, false, true);
            this.header.setStyleName("readview-layout-header");
            this.addComponent(this.header);

            final HorizontalLayout headerLeft = new HorizontalLayout();
            headerLeft.setWidth("100%");
            headerLeft.addStyleName("readview-header-left");
            this.iconEmbed = new Embedded();
            headerLeft.addComponent(this.iconEmbed);

            this.setTitleIcon(icon);

            headerLeft.setComponentAlignment(this.iconEmbed,
                    Alignment.MIDDLE_LEFT);

            this.titleLbl = new Label();
            this.titleLbl.setStyleName("h1");
            this.titleLbl.setWidth("100%");
            headerLeft.addComponent(this.titleLbl);
            headerLeft.setExpandRatio(this.titleLbl, 1.0f);
            headerLeft.setComponentAlignment(this.titleLbl,
                    Alignment.MIDDLE_LEFT);
            this.header.addComponent(headerLeft);
            this.header
                    .setComponentAlignment(headerLeft, Alignment.MIDDLE_LEFT);
            this.header.setExpandRatio(headerLeft, 1.0f);

            this.body = new CssLayout();
            this.body.setStyleName("readview-layout-body");
            this.body.setSizeFull();
            this.addComponent(this.body);

            this.viewTab = new DetachedTabs.Horizontal(this.body);
            this.viewTab.setSizeUndefined();
            this.header.addComponent(this.viewTab);
            this.header.setComponentAlignment(this.viewTab,
                    Alignment.BOTTOM_CENTER);
        } else {
            this.header = null;
            this.titleLbl = null;
            this.iconEmbed = null;
            this.viewTab = null;

            this.body = new CssLayout();
            this.body.setStyleName("readview-layout-body");
            this.body.setSizeFull();
            this.addComponent(this.body);
        }

    }

    public void addControlButtons(final Component controlsBtn) {
        if (this.header != null) {
            this.header.addComponent(controlsBtn);
            this.header.setComponentAlignment(controlsBtn,
                    Alignment.MIDDLE_CENTER);
        }
    }

    public void addTab(final Component content, final String button) {
        if (this.viewTab != null) {
            this.viewTab.addTab(content, new Button(button));
        }
    }

    public void addBody(final Component content) {
        this.body.addComponent(content);
    }

    public void addTabChangedListener(
            final DetachedTabs.TabChangedListener listener) {
        if (this.viewTab != null) {
            this.viewTab.addTabChangedListener(listener);
        }
    }

    public void selectTab(final String viewName) {
        if (this.viewTab != null) {
            this.viewTab.selectTab(viewName);
        }
    }

    public void setTitle(final String title) {
        if (this.titleLbl != null) {
            this.titleLbl.setValue(title);
        }
    }

    public void setTitleIcon(final Resource iconResource) {
        if (this.iconEmbed != null && iconResource != null) {
            this.iconEmbed.setSource(iconResource);
        }
    }
}
