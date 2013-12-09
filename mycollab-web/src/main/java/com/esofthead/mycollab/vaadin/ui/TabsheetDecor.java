package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.TabSheet;

public class TabsheetDecor extends TabSheet {
	private static final long serialVersionUID = 1L;

	public void selectTab(final String viewName) {
		int compCount = this.getComponentCount();
		for (int i = 0; i < compCount; i++) {
			Tab tab = this.getTab(i);
			if (tab.getCaption().equals(viewName)) {
				this.setSelectedTab(tab);
			}
		}
	}
}
