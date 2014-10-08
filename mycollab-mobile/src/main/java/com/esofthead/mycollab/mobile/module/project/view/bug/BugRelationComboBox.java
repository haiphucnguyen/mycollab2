package com.esofthead.mycollab.mobile.module.project.view.bug;

import com.esofthead.mycollab.vaadin.ui.BugRelationConstants;
import com.vaadin.ui.ListSelect;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
public class BugRelationComboBox extends ListSelect {

	private static final long serialVersionUID = 1L;

	public BugRelationComboBox() {
		this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
		this.addItem(BugRelationConstants.PARENT);
		this.addItem(BugRelationConstants.CHILD);
		this.addItem(BugRelationConstants.RELATED);
		this.addItem(BugRelationConstants.DUPLICATED);
		this.addItem(BugRelationConstants.BEFORE);
		this.addItem(BugRelationConstants.AFTER);

		this.setNullSelectionAllowed(false);
		this.select(BugRelationConstants.PARENT);
	}

}
