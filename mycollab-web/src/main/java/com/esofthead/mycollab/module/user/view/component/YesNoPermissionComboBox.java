package com.esofthead.mycollab.module.user.view.component;

import com.esofthead.mycollab.module.user.BooleanPermissionFlag;
import com.esofthead.mycollab.vaadin.ui.KeyCaptionComboBox;

public class YesNoPermissionComboBox extends KeyCaptionComboBox {
	private static final long serialVersionUID = 1L;

	public YesNoPermissionComboBox() {
		super(false);

		this.addItem(BooleanPermissionFlag.TRUE, "Yes");
		this.addItem(BooleanPermissionFlag.FALSE, "No");
	}
}
