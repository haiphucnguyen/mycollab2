/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user.view.component;

import com.esofthead.mycollab.security.AccessPermissionFlag;
import com.esofthead.mycollab.vaadin.ui.KeyCaptionComboBox;

/**
 * 
 * @author haiphucnguyen
 */
public class AccessPermissionComboBox extends KeyCaptionComboBox {
	private static final long serialVersionUID = 1L;

	public AccessPermissionComboBox() {
		super(false);

		this.addItem(AccessPermissionFlag.NO_ACCESS, "No Access");
		this.addItem(AccessPermissionFlag.READ_ONLY, "Read Only");
		this.addItem(AccessPermissionFlag.READ_WRITE, "Read & Write");
		this.addItem(AccessPermissionFlag.ACCESS, "Access");

		this.setValue(AccessPermissionFlag.READ_ONLY);
	}
}
