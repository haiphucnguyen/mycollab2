package com.esofthead.mycollab.mobile.module.project.view.task;

import java.util.Arrays;

import com.esofthead.mycollab.mobile.ui.I18nValueComboBox;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.TaskPriority;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 */
public class TaskPriorityComboBox extends I18nValueComboBox {

	private static final long serialVersionUID = 5484692572022056722L;

	public TaskPriorityComboBox() {
		this.setNullSelectionAllowed(false);

		this.loadData(Arrays.asList(TaskPriority.Urgent, TaskPriority.High,
				TaskPriority.Medium, TaskPriority.Low, TaskPriority.None));
		this.setValue(this.getItemIds().iterator().next());
	}

}
