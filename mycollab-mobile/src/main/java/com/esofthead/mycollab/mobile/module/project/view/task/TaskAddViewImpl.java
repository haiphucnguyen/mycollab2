package com.esofthead.mycollab.mobile.module.project.view.task;

import com.esofthead.mycollab.mobile.ui.AbstractEditItemComp;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 */

@ViewComponent
public class TaskAddViewImpl extends AbstractEditItemComp<SimpleTask> implements
		TaskAddView {

	private static final long serialVersionUID = 6835605062072536907L;

	@Override
	protected String initFormTitle() {
		return (beanItem.getId() == null) ? AppContext
				.getMessage(TaskI18nEnum.FORM_NEW_TASK_TITLE) : beanItem
				.getTaskname();
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new TaskFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<SimpleTask> initBeanFormFieldFactory() {
		// TODO Auto-generated method stub
		return null;
	}

}
