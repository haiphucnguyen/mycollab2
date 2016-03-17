package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.module.project.domain.Invoice;
import com.esofthead.mycollab.module.project.domain.SimpleInvoice;
import com.esofthead.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionBox;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.CurrencyComboBoxField;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.DoubleField;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleInvoice> {
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
            return new ProjectMemberSelectionBox(true);
        } else if (Invoice.Field.noid.equalTo(propertyId)) {
            TextField field = new TextField();
            field.setRequired(true);
            field.setRequiredError("Invoice No/ID must not be null");
            return field;
        } else if (Invoice.Field.issuedate.equalTo(propertyId)) {
            DateField field = new DateField();
            field.setRequired(true);
            field.setRequiredError("Issue date must not be null");
            return field;
        } else if (Invoice.Field.type.equalTo(propertyId)) {
            PricingTypeField field = new PricingTypeField();
            attachForm.getBean().setType(InvoiceI18nEnum.FIX_PRICE.name());
            field.setRequired(true);
            return field;
        } else if (Invoice.Field.amount.equalTo(propertyId)) {
            return new DoubleField();
        }
        return null;
    }
}
