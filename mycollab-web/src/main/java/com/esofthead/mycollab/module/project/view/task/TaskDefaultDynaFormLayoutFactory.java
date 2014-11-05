package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.form.view.builder.type.DynaForm;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.4
 *
 */
public class TaskDefaultDynaFormLayoutFactory {
	private static final DynaForm defaultForm;

	static {
		defaultForm = new DynaForm();
		
		
	}

	public static DynaForm getForm() {
		return defaultForm;
	}
}
