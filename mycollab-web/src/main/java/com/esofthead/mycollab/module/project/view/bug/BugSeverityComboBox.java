/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.ComboBox;

/**
 * 
 * @author haiphucnguyen
 */
public class BugSeverityComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	public static final String CRITICAL_IMG = "icons/12/priority_high.png";
	public static final String MAJOR_IMG = "icons/12/priority_medium.png";
	public static final String MINOR_IMG = "icons/12/priority_low.png";
	public static final String TRIVIAL_IMG = "icons/12/priority_none.png";

	public static final String CRITICAL = "Critical";
	public static final String MAJOR = "Major";
	public static final String MINOR = "Minor";
	public static final String TRIVIAL = "Trivial";

	public BugSeverityComboBox() {
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
		IndexedContainer ic = new IndexedContainer();
		ic.addItem(CRITICAL);
		ic.addItem(MAJOR);
		ic.addItem(MINOR);
		ic.addItem(TRIVIAL);

		this.setContainerDataSource(ic);

		this.setItemIcon(CRITICAL, new ThemeResource(CRITICAL_IMG));
		this.setItemIcon(MAJOR, new ThemeResource(MAJOR_IMG));
		this.setItemIcon(MINOR, new ThemeResource(MINOR_IMG));
		this.setItemIcon(TRIVIAL, new ThemeResource(TRIVIAL_IMG));
		this.setNullSelectionAllowed(false);
	}

	public static ThemeResource getIconResourceBySeverity(String severity) {
		ThemeResource iconseverity = new ThemeResource(MINOR_IMG);
		if (CRITICAL.equals(severity)) {
			iconseverity = new ThemeResource(CRITICAL_IMG);
		} else if (MAJOR.equals(severity)) {
			iconseverity = new ThemeResource(MAJOR_IMG);
		} else if (MINOR.equals(severity)) {
			iconseverity = new ThemeResource(MINOR_IMG);
		} else if (TRIVIAL.equals(severity)) {
			iconseverity = new ThemeResource(TRIVIAL_IMG);
		}
		return iconseverity;
	}
}
