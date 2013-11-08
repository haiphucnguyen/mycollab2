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

		setDefaultSelectionByIndex(this.buttons.indexOf(defaultChoice));
	}

	public void setDefaultSelectionByIndex(int buttonIndex) {
		this.defaultSelection = buttonIndex;
		this.requestRepaint();
	}

	private void setDefaultButtonCss() {
		if (this.defaultSelection == -1)
			return;
		
		this.removeButtonsCss("selected");
		this.buttons.get(defaultSelection).addStyleName("selected");

	}
}
