package com.mycollab.pro.module.project.view.finance;

import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Invoice;
import com.mycollab.module.project.domain.SimpleInvoice;
import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.CurrencyComboBoxField;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.data.HasValue;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextArea;
import org.vaadin.viritin.fields.DoubleField;
import org.vaadin.viritin.fields.MTextField;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleInvoice> {

    private AttachmentUploadField attachmentUploadField;

    InvoiceEditFormFieldFactory(GenericBeanForm<SimpleInvoice> form) {
        super(form);
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
        if (Invoice.Field.currentid.equalTo(propertyId)) {
            return new CurrencyComboBoxField();
        } else if (Invoice.Field.status.equalTo(propertyId)) {
            return new InvoiceStatusComboBox();
        } else if (Invoice.Field.assignuser.equalTo(propertyId)) {
            return new ProjectMemberSelectionField();
        } else if (Invoice.Field.noid.equalTo(propertyId)) {
            return new MTextField().withRequiredIndicatorVisible(true);
        } else if (Invoice.Field.issuedate.equalTo(propertyId)) {
            DateField field = new DateField();
            field.setRequiredIndicatorVisible(true);
            return field;
        } else if (Invoice.Field.type.equalTo(propertyId)) {
            PricingTypeField field = new PricingTypeField();
            attachForm.getBean().setType(InvoiceI18nEnum.FIX_PRICE.name());
            field.setRequiredIndicatorVisible(true);
            return field;
        } else if (Invoice.Field.description.equalTo(propertyId)) {
            return new TextArea();
        } else if (Invoice.Field.amount.equalTo(propertyId)) {
            return new DoubleField().withRequiredIndicatorVisible(true).withWidth(WebThemes.FORM_CONTROL_WIDTH);
        } else if (Invoice.Field.id.equalTo(propertyId)) {
            Invoice beanItem = attachForm.getBean();
            if (beanItem.getId() != null) {
                String attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(AppUI.getAccountId(),
                        CurrentProjectVariables.getProjectId(), ProjectTypeConstants.INVOICE, "" + beanItem.getId());
                attachmentUploadField = new AttachmentUploadField(attachmentPath);
            } else {
                attachmentUploadField = new AttachmentUploadField();
            }
            return attachmentUploadField;
        }
        return null;
    }

    public AttachmentUploadField getAttachmentUploadField() {
        return attachmentUploadField;
    }

    private static class PricingTypeField extends I18nValueComboBox<InvoiceI18nEnum> {
        PricingTypeField() {
            super(InvoiceI18nEnum.class, InvoiceI18nEnum.FIX_PRICE, InvoiceI18nEnum.TIME_MATERIAL);
            this.setWidth(WebThemes.FORM_CONTROL_WIDTH);
        }
    }
}
