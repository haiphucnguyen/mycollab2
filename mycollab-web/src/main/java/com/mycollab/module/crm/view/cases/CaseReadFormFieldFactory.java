package com.mycollab.module.crm.view.cases;

import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.domain.CaseWithBLOBs;
import com.mycollab.module.crm.domain.SimpleCase;
import com.mycollab.module.crm.i18n.OptionI18nEnum.*;
import com.mycollab.module.crm.ui.CrmAssetsManager;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.ui.field.EmailViewField;
import com.mycollab.vaadin.ui.field.I18nFormViewField;
import com.mycollab.vaadin.ui.field.RichTextViewField;
import com.mycollab.vaadin.web.ui.field.LinkViewField;
import com.mycollab.vaadin.web.ui.field.UserLinkViewField;
import com.vaadin.ui.Field;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
class CaseReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleCase> {
    private static final long serialVersionUID = 1L;

    public CaseReadFormFieldFactory(GenericBeanForm<SimpleCase> form) {
        super(form);
    }

    @Override
    protected Field<?> onCreateField(Object propertyId) {
        final SimpleCase cases = attachForm.getBean();
        if (propertyId.equals("accountid")) {
            return new LinkViewField(cases.getAccountName(),
                    CrmLinkBuilder.generateAccountPreviewLinkFull(cases.getAccountid()),
                    CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getACCOUNT()));
        } else if (propertyId.equals("email")) {
            return new EmailViewField(cases.getEmail());
        } else if (propertyId.equals("assignuser")) {
            return new UserLinkViewField(cases.getAssignuser(), cases.getAssignUserAvatarId(), cases.getAssignUserFullName());
        } else if (propertyId.equals("description")) {
            return new RichTextViewField(cases.getDescription());
        } else if (CaseWithBLOBs.Field.resolution.equalTo(propertyId)) {
            return new RichTextViewField(cases.getResolution());
        } else if (CaseWithBLOBs.Field.origin.equalTo(propertyId)) {
            return new I18nFormViewField(cases.getOrigin(), CaseOrigin.class);
        } else if (CaseWithBLOBs.Field.priority.equalTo(propertyId)) {
            return new I18nFormViewField(cases.getPriority(), CasePriority.class);
        } else if (CaseWithBLOBs.Field.status.equalTo(propertyId)) {
            return new I18nFormViewField(cases.getStatus(), CaseStatus.class);
        } else if (CaseWithBLOBs.Field.reason.equalTo(propertyId)) {
            return new I18nFormViewField(cases.getReason(), CaseReason.class);
        } else if (CaseWithBLOBs.Field.type.equalTo(propertyId)) {
            return new I18nFormViewField(cases.getType(), CaseType.class).withStyleName(UIConstants.FIELD_NOTE);
        }
        return null;
    }
}
