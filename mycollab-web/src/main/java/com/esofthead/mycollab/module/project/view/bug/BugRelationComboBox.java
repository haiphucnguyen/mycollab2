/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.bug;

import com.vaadin.ui.ComboBox;

/**
 * 
 * @author haiphucnguyen
 */
public class BugRelationComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	public BugRelationComboBox() {
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);
		this.addItem(BugRelationConstants.PARENT);
		this.addItem(BugRelationConstants.CHILD);
		this.addItem(BugRelationConstants.RELATED);
		this.addItem(BugRelationConstants.DUPLICATED);
		this.addItem(BugRelationConstants.SUBTASK);
		this.addItem(BugRelationConstants.BEFORE);
		this.addItem(BugRelationConstants.AFTER);
	}

}
