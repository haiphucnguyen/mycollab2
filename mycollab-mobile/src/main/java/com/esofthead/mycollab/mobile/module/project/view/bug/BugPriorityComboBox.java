package com.esofthead.mycollab.mobile.module.project.view.bug;

import java.util.Arrays;

import com.esofthead.mycollab.mobile.ui.I18nValueComboBox;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugPriority;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class BugPriorityComboBox extends I18nValueComboBox {

	private static final long serialVersionUID = 1L;

	public BugPriorityComboBox() {
		super();
		this.setNullSelectionAllowed(false);
		this.setCaption(null);
		this.loadData(Arrays.asList(OptionI18nEnum.bug_priorities));
		this.setItemIcon(BugPriority.Blocker.name(), MyCollabResource
				.newResource(ProjectResources.B_PRIORITY_BLOCKER_IMG_12));
		this.setItemIcon(BugPriority.Critical.name(), MyCollabResource
				.newResource(ProjectResources.B_PRIORITY_CRITICAL_IMG_12));
		this.setItemIcon(BugPriority.Major.name(), MyCollabResource
				.newResource(ProjectResources.B_PRIORITY_MAJOR_IMG_12));
		this.setItemIcon(BugPriority.Minor.name(), MyCollabResource
				.newResource(ProjectResources.B_PRIORITY_MINOR_IMG_12));
		this.setItemIcon(BugPriority.Trivial.name(), MyCollabResource
				.newResource(ProjectResources.B_PRIORITY_TRIVIAL_IMG_12));

	}
}
