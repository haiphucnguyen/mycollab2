package com.esofthead.mycollab.mobile.module.crm.view;

import com.vaadin.addon.touchkit.ui.NavigationManager;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class ActivityStreamViewManager extends NavigationManager {
	private static final long serialVersionUID = 1L;

	public ActivityStreamViewManager() {
		super(new ActivityStreamView());
	}
}
