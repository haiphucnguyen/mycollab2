package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ToggleButtonGroup extends ButtonGroup {
	private static final long serialVersionUID = 1L;

	public ToggleButtonGroup() {
		super();
		this.addStyleName("toggle-btn-group");
	}

	@Override
	public Button addButton(Button button) {
		Button newBtn = super.addButton(button);
		newBtn.addListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				Button currentBtn = event.getButton();
				ToggleButtonGroup.this.removeButtonsCss("selected");
				currentBtn.addStyleName("selected");
			}
		});
		return newBtn;
	}
}
