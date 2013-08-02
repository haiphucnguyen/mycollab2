package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.terminal.Resource;
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

		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_BLOCKER,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_BLOCKER_IMG));
		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_CRITICAL,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_CRITICAL_IMG));
		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_MAJOR,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG));
		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_MINOR,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_MINOR_IMG));
		this.setItemIcon(
				BugPriorityStatusConstants.PRIORITY_TRIVIAL,
				MyCollabResource
						.newResource(BugPriorityStatusConstants.PRIORITY_TRIVIAL_IMG));
		this.setNullSelectionAllowed(false);
	}

	public static Resource getIconResourceByPriority(String priority) {
		Resource iconPriority = MyCollabResource
				.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG);
		if (BugPriorityStatusConstants.PRIORITY_BLOCKER.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_BLOCKER_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_CRITICAL
				.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_CRITICAL_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_MAJOR.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_MAJOR_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_MINOR.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_MINOR_IMG);
		} else if (BugPriorityStatusConstants.PRIORITY_TRIVIAL.equals(priority)) {
			iconPriority = MyCollabResource
					.newResource(BugPriorityStatusConstants.PRIORITY_TRIVIAL_IMG);
		}
		return iconPriority;
	}
}
