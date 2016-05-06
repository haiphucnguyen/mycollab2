package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.module.project.domain.Invoice;
import com.esofthead.mycollab.module.project.i18n.InvoiceI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceDefaultFormLayoutFactory {

    public static DynaForm getForm() {
        DynaForm defaultForm = new DynaForm();
        DynaSection mainSection = new DynaSectionBuilder().layoutType(
                DynaSection.LayoutType.TWO_COLUMN).build();

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.noid).displayName(AppContext
                .getMessage(InvoiceI18nEnum.FORM_NOID_FIELD)).contextHelp(AppContext.getMessage(InvoiceI18nEnum.FORM_NOID_FIELD_HELP))
                .fieldIndex(0).mandatory(true).required(true).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.issuedate).displayName(AppContext
                .getMessage(InvoiceI18nEnum.FORM_ISSUE_DATE_FIELD)).fieldIndex(1).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.currentid).displayName(AppContext
                .getMessage(InvoiceI18nEnum.FORM_CURRENCY_FIELD)).fieldIndex(2).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.assignuser).displayName(AppContext
                .getMessage(GenericI18Enum.FORM_ASSIGNEE)).fieldIndex(3).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.status)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_STATUS))
                .contextHelp(AppContext.getMessage(InvoiceI18nEnum.FORM_STATUS_HELP)).fieldIndex(4).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.contactuserfullname).displayName(AppContext
                .getMessage(InvoiceI18nEnum.FORM_CONTACT_PERSON)).fieldIndex(5).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.type).displayName(AppContext
                .getMessage(InvoiceI18nEnum.FORM_TYPE)).contextHelp(AppContext.getMessage(InvoiceI18nEnum.FORM_TYPE_HELP))
                .fieldIndex(6).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.amount).displayName(AppContext
                .getMessage(InvoiceI18nEnum.FORM_AMOUNT)).fieldIndex(7).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.note).displayName(AppContext
                .getMessage(InvoiceI18nEnum.FORM_NOTE)).fieldIndex(8).colSpan(true).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.description).displayName(AppContext
                .getMessage(GenericI18Enum.FORM_DESCRIPTION)).fieldIndex(9).colSpan(true).build());

        mainSection.addField(new TextDynaFieldBuilder().fieldName(Invoice.Field.id).displayName(AppContext.getMessage
                (GenericI18Enum.FORM_ATTACHMENTS)).fieldIndex(10).colSpan(true).build());

        defaultForm.addSection(mainSection);
        return defaultForm;
    }
}
