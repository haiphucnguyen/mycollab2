package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.module.project.domain.Invoice;
import com.esofthead.mycollab.module.project.domain.SimpleInvoice;
import com.esofthead.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.field.DateViewField;
import com.esofthead.mycollab.vaadin.web.ui.field.DefaultViewField;
import com.esofthead.mycollab.vaadin.web.ui.field.I18nFormViewField;
import com.vaadin.ui.Field;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleInvoice> {
    public InvoiceReadFormFieldFactory(GenericBeanForm<SimpleInvoice> form) {
        super(form);
    }

    @Override
    protected Field<?> onCreateField(Object propertyId) {
        SimpleInvoice invoice = attachForm.getBean();
        if (Invoice.Field.currentid.equalTo(propertyId)) {
            if (invoice.getCurrency() != null) {
                return new DefaultViewField(invoice.getCurrency().getShortname());
            } else {
                return new DefaultViewField("");
            }
        } else if (Invoice.Field.issuedate.equalTo(propertyId)) {
            return new DateViewField(invoice.getIssuedate());
        } else if (Invoice.Field.status.equalTo(propertyId)) {
            return new I18nFormViewField(invoice.getStatus(), OptionI18nEnum.InvoiceStatus.class);
        } else if (Invoice.Field.type.equalTo(propertyId)) {
            return new I18nFormViewField(invoice.getType(), InvoiceI18nEnum.class);
        } else if (Invoice.Field.assignuser.equalTo(propertyId)) {
            return new ProjectUserFormLinkField(invoice.getAssignuser(), invoice.getAssignUserAvatarId(), invoice.getAssignUserFullName());
        }
        return null;
    }
}
