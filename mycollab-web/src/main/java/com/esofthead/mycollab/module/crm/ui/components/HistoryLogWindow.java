/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.vaadin.ui.HistoryLogComponent;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class HistoryLogWindow extends Window {

	private final HistoryLogComponent historyLogComponent;

	public HistoryLogWindow(final String module, final String type,
			final int typeid) {
		super("Change Log");
		this.setWidth("700px");

		this.historyLogComponent = new HistoryLogComponent(module, type, typeid);
		this.addComponent(this.historyLogComponent);
		this.center();
	}

	public void generateFieldDisplayHandler(final String fieldname,
			final String displayName) {
		this.historyLogComponent.generateFieldDisplayHandler(fieldname,
				displayName);
	}

	public void generateFieldDisplayHandler(final String fieldname,
			final String displayName,
			final HistoryLogComponent.HistoryFieldFormat format) {
		this.historyLogComponent.generateFieldDisplayHandler(fieldname,
				displayName, format);
	}

	public void generateFieldDisplayHandler(final String fieldname,
			final String displayName, final String formatName) {
		this.historyLogComponent.generateFieldDisplayHandler(fieldname,
				displayName, formatName);
	}
}
