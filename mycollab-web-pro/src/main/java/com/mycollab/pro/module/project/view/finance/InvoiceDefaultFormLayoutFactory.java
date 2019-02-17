package com.mycollab.pro.module.project.view.finance;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.form.view.LayoutType;
import com.mycollab.form.view.builder.DynaSectionBuilder;
import com.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.mycollab.form.view.builder.type.DynaForm;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.module.project.domain.Invoice;
import com.mycollab.module.project.i18n.InvoiceI18nEnum;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class InvoiceDefaultFormLayoutFactory {

    private static DynaSection mainSection() {
        DynaSection mainSection = new DynaSectionBuilder().layoutType(LayoutType.TWO_COLUMN).build();

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.noid).displayName(InvoiceI18nEnum.FORM_NOID_FIELD)
                .contextHelp(InvoiceI18nEnum.FORM_NOID_FIELD_HELP)
                .fieldIndex(0).mandatory(true).required(true).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.issuedate).displayName(InvoiceI18nEnum.FORM_ISSUE_DATE_FIELD)
                .fieldIndex(1).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.currentid).displayName(GenericI18Enum.FORM_CURRENCY)
                .fieldIndex(2).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.assignuser).displayName(GenericI18Enum.FORM_ASSIGNEE)
                .fieldIndex(3).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.status)
                .displayName(GenericI18Enum.FORM_STATUS)
                .contextHelp(InvoiceI18nEnum.FORM_STATUS_HELP).fieldIndex(4).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.contactuserfullname)
                .displayName(InvoiceI18nEnum.FORM_CONTACT_PERSON).fieldIndex(5).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.type).displayName(InvoiceI18nEnum.FORM_TYPE)
                .contextHelp(InvoiceI18nEnum.FORM_TYPE_HELP)
                .fieldIndex(6).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.amount).displayName(InvoiceI18nEnum.FORM_AMOUNT)
                .fieldIndex(7).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.note).displayName(InvoiceI18nEnum.FORM_NOTE)
                .fieldIndex(8).colSpan(true).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Invoice.Field.description).displayName(GenericI18Enum.FORM_DESCRIPTION)
                .fieldIndex(9).colSpan(true).build());

        return mainSection;
    }

    private static DynaSection attachmentSection() {
        DynaSection attachmentsSection = new DynaSectionBuilder().layoutType(LayoutType.ONE_COLUMN).header(GenericI18Enum.FORM_ATTACHMENTS).build();
        attachmentsSection.fields(new TextDynaFieldBuilder().fieldName("section-attachments").fieldIndex(0).build());
        return attachmentsSection;
    }

    public static DynaForm getForm() {
        return new DynaForm(mainSection(), attachmentSection());
    }
}
