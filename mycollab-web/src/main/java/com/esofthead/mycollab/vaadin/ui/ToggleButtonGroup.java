package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.vaadin.terminal.PaintException;
import com.vaadin.terminal.PaintTarget;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class ToggleButtonGroup extends ButtonGroup {
	private static final long serialVersionUID = 1L;

	private int defaultSelection;

	public ToggleButtonGroup() {
		super();
		this.addStyleName("toggle-btn-group");
		this.defaultSelection = -1;
	}

	@Override
	public void paintContent(PaintTarget target) throws PaintException {
		super.paintContent(target);
		setDefaultButtonCss();
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

	public void setDefaultSelection(Button defaultChoice) {
		if (!this.buttons.contains(defaultChoice)) {
			throw new IllegalArgumentException(
					"Given old button does not exist in this group");
		}

		this.defaultSelection = this.buttons.indexOf(defaultChoice);
		this.requestRepaint();
	}
	
	public void setDefaultSelectionByIndex(int buttonIndex) {
		this.defaultSelection = buttonIndex;
		this.requestRepaint();
	}

	private void setDefaultButtonCss() {

		if (this.defaultSelection == -1)
			return;

		this.buttons.get(defaultSelection).addStyleName("selected");

	}
}
