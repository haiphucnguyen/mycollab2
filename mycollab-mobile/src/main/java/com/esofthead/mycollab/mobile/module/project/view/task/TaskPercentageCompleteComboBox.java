package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.mobile.ui.ValueComboBox;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 */
public class TaskPercentageCompleteComboBox extends ValueComboBox {
	private static final long serialVersionUID = 1L;

	public TaskPercentageCompleteComboBox() {
		super(false, 0d, 10d, 20d, 30d, 40d, 50d, 60d, 70d, 80d, 90d, 100d);
	}
}
