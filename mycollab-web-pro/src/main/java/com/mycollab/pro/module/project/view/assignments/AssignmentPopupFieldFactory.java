package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.module.project.domain.ProjectGenericTask;
import com.mycollab.vaadin.mvp.CacheableComponent;
import com.vaadin.ui.AbstractComponent;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
public interface AssignmentPopupFieldFactory extends CacheableComponent {

    AbstractComponent createStartDatePopupField(ProjectGenericTask assignment);

    AbstractComponent createEndDatePopupField(ProjectGenericTask assignment);
}
