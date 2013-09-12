package com.esofthead.mycollab.module.user.view.component;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.AccessPermissionFlag;
import com.esofthead.mycollab.module.user.BooleanPermissionFlag;
import com.esofthead.mycollab.module.user.PermissionFlag;
import com.esofthead.mycollab.vaadin.ui.KeyCaptionComboBox;

public class PermissionComboBoxFactory {
	public static KeyCaptionComboBox createPermissionSelection(
			Class<? extends PermissionFlag> flag) {
		if (AccessPermissionFlag.class.isAssignableFrom(flag)) {
			return new AccessPermissionComboBox();
		} else if (BooleanPermissionFlag.class.isAssignableFrom(flag)) {
			return new YesNoPermissionComboBox();
		} else {
			throw new MyCollabException("Do not support permission flag "
					+ flag);
		}
	}
}
