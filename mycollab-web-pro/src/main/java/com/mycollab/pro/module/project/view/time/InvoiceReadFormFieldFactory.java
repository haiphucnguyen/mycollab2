package com.mycollab.pro.module.project.view.time;

import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Invoice;
import com.mycollab.module.project.domain.SimpleInvoice;
import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum;
import com.mycollab.module.project.ui.form.ProjectFormAttachmentDisplayField;
import com.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.ui.field.CurrencyViewField;
import com.mycollab.vaadin.ui.field.DateViewField;
import com.mycollab.vaadin.ui.field.DefaultViewField;
import com.mycollab.vaadin.ui.field.I18nFormViewField;
import com.vaadin.ui.AbstractField;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleInvoice> {
    public InvoiceReadFormFieldFactory(GenericBeanForm<SimpleInvoice> form) {
        super(form);
    }

    @Override
    protected AbstractField<?> onCreateField(Object propertyId) {
        SimpleInvoice invoice = attachForm.getBean();
        if (Invoice.Field.currentid.equalTo(propertyId)) {
            return new CurrencyViewField(invoice.getCurrentid());
        } else if (Invoice.Field.issuedate.equalTo(propertyId)) {
            return new DateViewField(invoice.getIssuedate());
        } else if (Invoice.Field.status.equalTo(propertyId)) {
            return new I18nFormViewField(invoice.getStatus(), OptionI18nEnum.InvoiceStatus.class);
        } else if (Invoice.Field.type.equalTo(propertyId)) {
            return new I18nFormViewField(invoice.getType(), InvoiceI18nEnum.class);
        } else if (Invoice.Field.assignuser.equalTo(propertyId)) {
            return new ProjectUserFormLinkField(invoice.getProjectid(), invoice.getAssignuser(),
                    invoice.getAssignUserAvatarId(), invoice.getAssignUserFullName());
        } else if (Invoice.Field.amount.equalTo(propertyId)) {
            return new DefaultViewField(invoice.getAmount() + "").withStyleName(UIConstants.FIELD_NOTE);
        } else if (Invoice.Field.id.equalTo(propertyId)) {
            return new ProjectFormAttachmentDisplayField(invoice.getProjectid(), ProjectTypeConstants.INVOICE, invoice.getId());
        }
        return null;
    }
}
