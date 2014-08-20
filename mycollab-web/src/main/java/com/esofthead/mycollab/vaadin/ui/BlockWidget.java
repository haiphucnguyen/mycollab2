package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 */
public class BlockWidget extends CssLayout {

	private static final long serialVersionUID = -8503014399083980294L;

	private final Label title;

	private final CssLayout body;

	public BlockWidget() {
		super();
		this.setStyleName("block-widget");
		title = new Label();
		title.setStyleName("block-header");
		super.addComponent(title);

		body = new CssLayout();
		body.setWidth("100%");
		body.setStyleName("block-body");
		super.addComponent(body);
	}

	public BlockWidget(String title) {
		this();
		this.setTitle(title);
	}

	public void setTitle(String title) {
		this.title.setValue(title);
	}

	public void addToBody(Component component) {
		this.body.addComponent(component);
	}

	@Override
	public void addComponent(Component c) {
		this.addToBody(c);
	}

	@Override
	public void addComponentAsFirst(Component c) {
		this.body.addComponentAsFirst(c);
	}

	@Override
	public void addComponent(Component c, int index) {
		this.body.addComponent(c, index);
	}

	@Override
	public void removeComponent(Component c) {
		this.body.removeComponent(c);
	}

	@Override
	public void replaceComponent(Component oldComponent, Component newComponent) {
		this.body.replaceComponent(oldComponent, newComponent);
	}

	@Override
	public int getComponentIndex(Component component) {
		return this.body.getComponentIndex(component);
	}

	@Override
	public Component getComponent(int index) throws IndexOutOfBoundsException {
		return this.body.getComponent(index);
	}

	@Override
	public void addComponents(Component... components) {
		this.body.addComponents(components);
	}

	@Override
	public void removeAllComponents() {
		this.body.removeAllComponents();
	}

}
