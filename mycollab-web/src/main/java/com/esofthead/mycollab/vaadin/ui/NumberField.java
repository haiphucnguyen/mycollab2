package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.server.AbstractClientConnector;
import com.vaadin.server.AbstractExtension;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class NumberField extends AbstractExtension {
	private static final long serialVersionUID = 1L;

	public static void extend(TextField field) {
		new NumberField().extend((AbstractClientConnector) field);
	}
}
