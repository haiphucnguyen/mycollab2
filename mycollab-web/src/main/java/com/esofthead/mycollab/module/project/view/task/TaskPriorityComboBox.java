package com.esofthead.mycollab.module.project.view.task;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.terminal.gwt.client.ui.Icon;
import com.vaadin.ui.ComboBox;

public class TaskPriorityComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	public static final String PRIORITY_HIGHT_IMG = "icons/12/priority_high.png";
	public static final String PRIORITY_LOW_IMG = "icons/12/priority_low.png";
	public static final String PRIORITY_MEDIUM_IMG = "icons/12/priority_medium.png";
	public static final String PRIORITY_NONE_IMG = "icons/12/priority_none.png";
	public static final String PRIORITY_URGENT_IMG = "icons/12/priority_urgent.png";

	public static final String PRIORITY_HIGHT = "High";
	public static final String PRIORITY_LOW = "Low";
	public static final String PRIORITY_MEDIUM = "Medium";
	public static final String PRIORITY_NONE = "None";
	public static final String PRIORITY_URGENT = "Urgent";

	public TaskPriorityComboBox() {
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

		IndexedContainer ic = new IndexedContainer();
		ic.addItem(PRIORITY_URGENT);
		ic.addItem(PRIORITY_HIGHT);
		ic.addItem(PRIORITY_MEDIUM);
		ic.addItem(PRIORITY_LOW);
		ic.addItem(PRIORITY_NONE);

		this.setContainerDataSource(ic);

		this.setItemIcon(PRIORITY_HIGHT, new ThemeResource(PRIORITY_HIGHT_IMG));
		this.setItemIcon(PRIORITY_LOW, new ThemeResource(PRIORITY_LOW_IMG));
		this.setItemIcon(PRIORITY_MEDIUM,
				new ThemeResource(PRIORITY_MEDIUM_IMG));
		this.setItemIcon(PRIORITY_NONE, new ThemeResource(PRIORITY_NONE_IMG));
		this.setItemIcon(PRIORITY_URGENT,
				new ThemeResource(PRIORITY_URGENT_IMG));
		
		this.setNullSelectionAllowed(false);
		this.setValue(this.getItemIds().iterator().next());
	}
}
