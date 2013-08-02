package com.vaadin.terminal.gwt.client.ui;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.vaadin.data.Property.ConversionException;
import com.vaadin.data.Property.ReadOnlyException;

public class VNumericField extends VTextField {
	public VNumericField() {
		super();
		addKeyPressHandler(new KeyPressHandler() {
			@Override
			public void onKeyPress(KeyPressEvent e) {
				if (e.getCharCode() == KeyCodes.KEY_END
						|| e.getCharCode() == KeyCodes.KEY_ENTER
						|| e.getCharCode() == KeyCodes.KEY_ESCAPE
						|| e.getCharCode() == KeyCodes.KEY_HOME
						|| e.getCharCode() == KeyCodes.KEY_LEFT
						|| e.getCharCode() == KeyCodes.KEY_PAGEDOWN
						|| e.getCharCode() == KeyCodes.KEY_PAGEUP
						|| e.getCharCode() == KeyCodes.KEY_RIGHT
						|| e.getCharCode() == KeyCodes.KEY_TAB
						|| e.isAnyModifierKeyDown() || e.getCharCode() == KeyCodes.KEY_UP)
					return;
				if (!Character.isDigit(e.getCharCode()))
					e.preventDefault();
			}
		});

		addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
			}
		});
		
		addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
			}
			
			
		});
	}

	@SuppressWarnings("unused")
	private String getValueToOperate(String value) {
		return value.length() > 1 ? value.substring(value.length() - 2) : value
				.substring(value.length() - 1);
	}

	@SuppressWarnings("unused")
	private String getLiteralValue(String value) {
		return value.length() > 1 ? value.substring(0, value.length() - 2)
				: value.substring(0, value.length() - 1);
	}
}
