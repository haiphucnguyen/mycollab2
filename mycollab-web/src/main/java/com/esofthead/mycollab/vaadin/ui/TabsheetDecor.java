package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.TabSheet;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class TabsheetDecor extends TabSheet {
	private static final long serialVersionUID = 1L;

	public Tab selectTab(final String viewName) {
		int compCount = this.getComponentCount();
		for (int i = 0; i < compCount; i++) {
			Tab tab = this.getTab(i);
			if (tab.getCaption().equals(viewName)) {
				this.setSelectedTab(tab);
				return tab;
			}
		}

		return null;
	}

	public Tab getSelectedTabInfo() {
		return this.getTab(this.getSelectedTab());
	}
}
