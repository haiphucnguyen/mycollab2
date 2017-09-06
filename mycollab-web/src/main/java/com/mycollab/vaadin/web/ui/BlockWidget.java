package com.mycollab.vaadin.web.ui;

import com.mycollab.vaadin.ui.ELabel;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public class BlockWidget extends MVerticalLayout {
    private static final long serialVersionUID = -8503014399083980294L;

    private ELabel titleLbl;
    private CssLayout bodyLayout;

    public BlockWidget(String title) {
        titleLbl = ELabel.h2("");
        super.addComponent(titleLbl);

        bodyLayout = new CssLayout();
        bodyLayout.setWidth("100%");
        super.addComponent(bodyLayout);
        this.setTitle(title);
    }

    public void setTitle(String title) {
        this.titleLbl.setValue(title);
    }

    public void addToBody(Component component) {
        bodyLayout.addComponent(component);
    }

    @Override
    public void addComponent(Component c) {
        this.addToBody(c);
    }

    @Override
    public void addComponentAsFirst(Component c) {
        bodyLayout.addComponentAsFirst(c);
    }

    @Override
    public void addComponent(Component c, int index) {
        bodyLayout.addComponent(c, index);
    }

    @Override
    public void removeComponent(Component c) {
        bodyLayout.removeComponent(c);
    }

    @Override
    public void replaceComponent(Component oldComponent, Component newComponent) {
        bodyLayout.replaceComponent(oldComponent, newComponent);
    }

    @Override
    public int getComponentIndex(Component component) {
        return bodyLayout.getComponentIndex(component);
    }

    @Override
    public Component getComponent(int index) throws IndexOutOfBoundsException {
        return bodyLayout.getComponent(index);
    }

    @Override
    public void addComponents(Component... components) {
        bodyLayout.addComponents(components);
    }

    @Override
    public void removeAllComponents() {
        bodyLayout.removeAllComponents();
    }
}
