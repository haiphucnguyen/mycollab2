package com.mycollab.premium.mobile.module.crm.view.contact;

import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.crm.view.contact.ContactAddView;
import com.mycollab.mobile.module.crm.view.contact.ContactDefaultDynaFormLayoutFactory;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleContact;
import com.mycollab.module.crm.i18n.ContactI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
@ViewComponent
public class ContactAddViewImpl extends AbstractEditItemComp<SimpleContact> implements ContactAddView {
    private static final long serialVersionUID = 1560876283170769148L;

    @Override
    protected String initFormTitle() {
        return (beanItem.getContactName() != null ? beanItem.getContactName() : UserUIContext.getMessage(ContactI18nEnum.NEW));
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.CONTACT, ContactDefaultDynaFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleContact> initBeanFormFieldFactory() {
        return new ContactEditFormFieldFactory<>(this.editForm);
    }

}
