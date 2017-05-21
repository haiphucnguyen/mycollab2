package com.mycollab.premium.mobile.module.crm.view.activity;

import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.crm.view.activity.AssignmentAddView;
import com.mycollab.mobile.module.crm.view.activity.AssignmentDefaultFormLayoutFactory;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.CrmTask;
import com.mycollab.module.crm.i18n.TaskI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewComponent
public class AssignmentAddViewImpl extends AbstractEditItemComp<CrmTask> implements AssignmentAddView {
    private static final long serialVersionUID = 429332932454100255L;

    @Override
    protected String initFormTitle() {
        return beanItem.getSubject() != null ? beanItem.getSubject()
                : UserUIContext.getMessage(TaskI18nEnum.NEW);
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.TASK, AssignmentDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<CrmTask> initBeanFormFieldFactory() {
        return new AssignmentEditFormFieldFactory(this.editForm);
    }

}
