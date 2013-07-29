/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.BugResolutionConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;

/**
 * 
 * @author haiphucnguyen
 */
public class BugResolutionComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	private BugResolutionComboBox(boolean nullIsAllowable, String... values) {
		super(nullIsAllowable, values);
	}

	public static BugResolutionComboBox getInstanceForWontFixWindow() {
		return new BugResolutionComboBox(false, new String[] {
				BugResolutionConstants.CAN_NOT_REPRODUCE,
				BugResolutionConstants.DUPLICATE,
				BugResolutionConstants.INCOMPLETE,
				BugResolutionConstants.WON_FIX });
	}

	public static BugResolutionComboBox getInstanceForValidBugWindow() {
		return new BugResolutionComboBox(false, new String[] {
				BugResolutionConstants.NEWISSUE, BugResolutionConstants.REOPEN,
				BugResolutionConstants.WAITFORVERIFICATION });
	}

	public static BugResolutionComboBox getInstanceForResolvedBugWindow() {
		return new BugResolutionComboBox(false,
				new String[] { BugResolutionConstants.FIXED });
	}
}
