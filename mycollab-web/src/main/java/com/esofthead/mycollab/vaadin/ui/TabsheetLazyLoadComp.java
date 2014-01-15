package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Component;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class TabsheetLazyLoadComp extends TabsheetDecor {
	private static final long serialVersionUID = 1L;

	public TabsheetLazyLoadComp() {
		this.addSelectedTabChangeListener(new SelectedTabChangeListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void selectedTabChange(SelectedTabChangeEvent event) {
				Component comp = event.getTabSheet().getSelectedTab();
				System.out.println("Comp: " + comp);
			}
		});
	}
}
