package com.esofthead.mycollab.module.project.view.bug;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.ComboBox;

public class BugPriorityComboBox extends ComboBox {

    private static final long serialVersionUID = 1L;
    
    public static final String PRIORITY_BLOCKER_IMG = "icons/12/priority_urgent.png";
	public static final String PRIORITY_CRITICAL_IMG = "icons/12/priority_high.png";
	public static final String PRIORITY_MAJOR_IMG = "icons/12/priority_medium.png";
	public static final String PRIORITY_MINOR_IMG = "icons/12/priority_low.png";
	public static final String PRIORITY_TRIVIAL_IMG = "icons/12/priority_none.png";

	public static final String PRIORITY_BLOCKER= "Blocker";
	public static final String PRIORITY_CRITICAL = "Critical";
	public static final String PRIORITY_MAJOR = "Major";
	public static final String PRIORITY_MINOR = "Minor";
	public static final String PRIORITY_TRIVIAL = "Trivial";

    public BugPriorityComboBox() {
    	
    	this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

		IndexedContainer ic = new IndexedContainer();
		ic.addItem(PRIORITY_BLOCKER);
		ic.addItem(PRIORITY_CRITICAL);
		ic.addItem(PRIORITY_MAJOR);
		ic.addItem(PRIORITY_MINOR);
		ic.addItem(PRIORITY_TRIVIAL);

		this.setContainerDataSource(ic);

		this.setItemIcon(PRIORITY_BLOCKER, new ThemeResource(PRIORITY_BLOCKER_IMG));
		this.setItemIcon(PRIORITY_CRITICAL, new ThemeResource(PRIORITY_CRITICAL_IMG));
		this.setItemIcon(PRIORITY_MAJOR,
				new ThemeResource(PRIORITY_MAJOR_IMG));
		this.setItemIcon(PRIORITY_MINOR, new ThemeResource(PRIORITY_MINOR_IMG));
		this.setItemIcon(PRIORITY_TRIVIAL,
				new ThemeResource(PRIORITY_TRIVIAL_IMG));
		this.setNullSelectionAllowed(false);
		this.setSizeUndefined();
    }
}
