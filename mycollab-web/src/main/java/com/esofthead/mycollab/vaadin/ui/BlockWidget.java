package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 4.4.0
 */
public class BlockWidget extends CssLayout {
    private static final long serialVersionUID = -8503014399083980294L;

    private Label titleLbl;
    private CssLayout bodyLayout;

    public BlockWidget(String title) {
        titleLbl = new Label();
        super.addComponent(titleLbl);

        bodyLayout = new CssLayout();
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
