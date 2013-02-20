package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Button;

public class CommonUIFactory {
	public static Button createButtonTooltip(String caption, String description) {
		Button btn = new Button(caption);
		btn.setDescription(description);
		return btn;
	}

	public static Button createButtonTooltip(String caption,
			String description, Button.ClickListener listener) {
		Button btn = new Button(caption);
		btn.setDescription(description);
		btn.addListener(listener);
		return btn;
	}
}
