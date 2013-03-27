package com.esofthead.mycollab.module.project.view.bug;

import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.ComboBox;

public class BugPriorityComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	public BugPriorityComboBox() {

		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

		IndexedContainer ic = new IndexedContainer();
		ic.addItem(BugPriorityStatusConstants.PRIORITY_BLOCKER);
		ic.addItem(BugPriorityStatusConstants.PRIORITY_CRITICAL);
		ic.addItem(BugPriorityStatusConstants.PRIORITY_MAJOR);
		ic.addItem(BugPriorityStatusConstants.PRIORITY_MINOR);
		ic.addItem(BugPriorityStatusConstants.PRIORITY_TRIVIAL);

		this.setContainerDataSource(ic);

		this.setItemIcon(BugPriorityStatusConstants.PRIORITY_BLOCKER,
				new ThemeResource(
						BugPriorityStatusConstants.PRIORITY_BLOCKER_IMG));
		this.setItemIcon(BugPriorityStatusConstants.PRIORITY_CRITICAL,
				new ThemeResource(
						BugPriorityStatusConstants.PRIORITY_CRITICAL_IMG));
		this.setItemIcon(BugPriorityStatusConstants.PRIORITY_MAJOR,
				new ThemeResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG));
		this.setItemIcon(BugPriorityStatusConstants.PRIORITY_MINOR,
				new ThemeResource(BugPriorityStatusConstants.PRIORITY_MINOR_IMG));
		this.setItemIcon(BugPriorityStatusConstants.PRIORITY_TRIVIAL,
				new ThemeResource(
						BugPriorityStatusConstants.PRIORITY_TRIVIAL_IMG));
		this.setNullSelectionAllowed(false);
	}

	public static ThemeResource getIconResourceByPriority(String priority) {
		ThemeResource iconPriority = new ThemeResource(
				BugPriorityStatusConstants.PRIORITY_MAJOR_IMG);
		if (BugPriorityStatusConstants.PRIORITY_BLOCKER.equals(priority)) {
			iconPriority = new ThemeResource(
					BugPriorityStatusConstants.PRIORITY_BLOCKER_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_CRITICAL.equals(priority)) {
			iconPriority = new ThemeResource(
					BugPriorityStatusConstants.PRIORITY_CRITICAL_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_MAJOR.equals(priority)) {
			iconPriority = new ThemeResource(
					BugPriorityStatusConstants.PRIORITY_MAJOR_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_MINOR.equals(priority)) {
			iconPriority = new ThemeResource(
					BugPriorityStatusConstants.PRIORITY_MINOR_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_TRIVIAL.equals(priority)) {
			iconPriority = new ThemeResource(
					BugPriorityStatusConstants.PRIORITY_TRIVIAL_IMG);
		}
		return iconPriority;
	}
}
