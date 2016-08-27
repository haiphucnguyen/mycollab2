package com.mycollab.pro.module.project.view.time;

import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Invoice;
import com.mycollab.module.project.domain.SimpleInvoice;
import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.CurrencyComboBoxField;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.DoubleField;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

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
    protected Field<?> onCreateField(Object propertyId) {
        if (Invoice.Field.currentid.equalTo(propertyId)) {
            return new CurrencyComboBoxField();
        } else if (Invoice.Field.status.equalTo(propertyId)) {
            return new InvoiceStatusComboBox();
        } else if (Invoice.Field.assignuser.equalTo(propertyId)) {
            return new ProjectMemberSelectionField();
        } else if (Invoice.Field.noid.equalTo(propertyId)) {
            TextField field = new TextField();
            field.setRequired(true);
            field.setRequiredError("Invoice No/ID must not be null");
            return field;
        } else if (Invoice.Field.issuedate.equalTo(propertyId)) {
            PopupDateFieldExt field = new PopupDateFieldExt();
            field.setRequired(true);
            field.setRequiredError("Issue date must not be null");
            return field;
        } else if (Invoice.Field.type.equalTo(propertyId)) {
            PricingTypeField field = new PricingTypeField();
            attachForm.getBean().setType(InvoiceI18nEnum.FIX_PRICE.name());
            field.setRequired(true);
            return field;
        } else if (Invoice.Field.description.equalTo(propertyId)) {
            return new TextArea();
        } else if (Invoice.Field.amount.equalTo(propertyId)) {
            DoubleField field = new DoubleField();
            field.setRequired(true);
            field.setRequiredError("Amount can not be null");
            return field;
        } else if (Invoice.Field.id.equalTo(propertyId)) {
            Invoice beanItem = attachForm.getBean();
            if (beanItem.getId() != null) {
                String attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(AppContext.getAccountId(),
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
}
