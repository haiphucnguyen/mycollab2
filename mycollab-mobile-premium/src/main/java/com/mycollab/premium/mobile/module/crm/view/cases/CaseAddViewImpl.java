package com.mycollab.premium.mobile.module.crm.view.cases;

import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.crm.view.cases.CaseAddView;
import com.mycollab.mobile.module.crm.view.cases.CaseDefaultFormLayoutFactory;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleCase;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewComponent
public class CaseAddViewImpl extends AbstractEditItemComp<SimpleCase> implements CaseAddView {
    private static final long serialVersionUID = 4331832126821188011L;

    @Override
    protected String initFormTitle() {
        return beanItem.getSubject() != null ? beanItem.getSubject() : "Add Case";
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.INSTANCE.getCASE(), CaseDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleCase> initBeanFormFieldFactory() {
        return new CaseEditFormFieldFactory<>(this.editForm);
    }
}