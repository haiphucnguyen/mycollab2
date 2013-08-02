/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.Resource;
import com.vaadin.ui.ComboBox;

/**
 * 
 * @author haiphucnguyen
 */
public class BugSeverityComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	public BugSeverityComboBox() {
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
		IndexedContainer ic = new IndexedContainer();
		ic.addItem(BugSeverityConstants.CRITICAL);
		ic.addItem(BugSeverityConstants.MAJOR);
		ic.addItem(BugSeverityConstants.MINOR);
		ic.addItem(BugSeverityConstants.TRIVIAL);

		this.setContainerDataSource(ic);

		this.setItemIcon(BugSeverityConstants.CRITICAL,
				MyCollabResource.newResource(BugSeverityConstants.CRITICAL_IMG));
		this.setItemIcon(BugSeverityConstants.MAJOR,
				MyCollabResource.newResource(BugSeverityConstants.MAJOR_IMG));
		this.setItemIcon(BugSeverityConstants.MINOR,
				MyCollabResource.newResource(BugSeverityConstants.MINOR_IMG));
		this.setItemIcon(BugSeverityConstants.TRIVIAL,
				MyCollabResource.newResource(BugSeverityConstants.TRIVIAL_IMG));
		this.setNullSelectionAllowed(false);
	}

	public static Resource getIconResourceBySeverity(String severity) {
		Resource iconseverity = MyCollabResource
				.newResource(BugSeverityConstants.MINOR_IMG);
		if (BugSeverityConstants.CRITICAL.equals(severity)) {
			iconseverity = MyCollabResource
					.newResource(BugSeverityConstants.CRITICAL_IMG);
		} else if (BugSeverityConstants.MAJOR.equals(severity)) {
			iconseverity = MyCollabResource
					.newResource(BugSeverityConstants.MAJOR_IMG);
		} else if (BugSeverityConstants.MINOR.equals(severity)) {
			iconseverity = MyCollabResource
					.newResource(BugSeverityConstants.MINOR_IMG);
		} else if (BugSeverityConstants.TRIVIAL.equals(severity)) {
			iconseverity = MyCollabResource
					.newResource(BugSeverityConstants.TRIVIAL_IMG);
		}
		return iconseverity;
	}
}
