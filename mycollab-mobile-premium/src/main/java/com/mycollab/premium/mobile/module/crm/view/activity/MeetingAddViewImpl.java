package com.mycollab.premium.mobile.module.crm.view.activity;

import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.crm.view.activity.MeetingAddView;
import com.mycollab.mobile.module.crm.view.activity.MeetingDefaultFormLayoutFactory;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.MeetingWithBLOBs;
import com.mycollab.module.crm.i18n.MeetingI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewComponent
public class MeetingAddViewImpl extends AbstractEditItemComp<MeetingWithBLOBs> implements MeetingAddView {
    private static final long serialVersionUID = 2739924176536721587L;

    @Override
    protected String initFormTitle() {
        return beanItem.getSubject() != null ? beanItem.getSubject() : UserUIContext.getMessage(MeetingI18nEnum.NEW);
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.INSTANCE.getMEETING(), MeetingDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<MeetingWithBLOBs> initBeanFormFieldFactory() {
        return new MeetingEditFormFieldFactory(editForm);
    }

}
