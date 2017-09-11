package com.mycollab.pro.module.project.view.time;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Invoice;
import com.mycollab.module.project.domain.SimpleInvoice;
import com.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.CurrencyComboBoxField;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.PopupDateFieldExt;
import com.mycollab.vaadin.web.ui.DoubleField;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import org.vaadin.viritin.fields.MTextField;

import java.util.Arrays;

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
            return new MTextField().withRequired(true)
                    .withRequiredError(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
                            UserUIContext.getMessage(InvoiceI18nEnum.FORM_NOID_FIELD)));
        } else if (Invoice.Field.issuedate.equalTo(propertyId)) {
            PopupDateFieldExt field = new PopupDateFieldExt();
            field.setRequired(true);
            field.setRequiredError(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
                    UserUIContext.getMessage(InvoiceI18nEnum.FORM_ISSUE_DATE_FIELD)));
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
            field.setRequiredError(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
                    UserUIContext.getMessage(InvoiceI18nEnum.FORM_AMOUNT)));
            return field;
        } else if (Invoice.Field.id.equalTo(propertyId)) {
            Invoice beanItem = attachForm.getBean();
            if (beanItem.getId() != null) {
                String attachmentPath = AttachmentUtils.INSTANCE.getProjectEntityAttachmentPath(AppUI.getAccountId(),
                        CurrentProjectVariables.getProjectId(), ProjectTypeConstants.INSTANCE.getINVOICE(), "" + beanItem.getId());
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

    private static class PricingTypeField extends I18nValueComboBox {
        PricingTypeField() {
            super();
            this.setNullSelectionAllowed(false);
            this.setCaption(null);
            this.loadData(Arrays.asList(InvoiceI18nEnum.FIX_PRICE, InvoiceI18nEnum.TIME_MATERIAL));
        }
    }
}
