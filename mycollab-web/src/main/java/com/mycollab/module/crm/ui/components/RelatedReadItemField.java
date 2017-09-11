package com.mycollab.module.crm.ui.components;

import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.ui.CrmAssetsManager;
import com.mycollab.module.crm.domain.*;
import com.mycollab.module.crm.service.*;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.web.ui.LabelLink;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class RelatedReadItemField extends CustomField {
    private static final long serialVersionUID = 1L;

    private Object bean;

    public RelatedReadItemField(Object bean) {
        this.bean = bean;
    }

    @Override
    protected Component initContent() {
        try {
            final Integer typeId = (Integer) PropertyUtils.getProperty(RelatedReadItemField.this.bean, "typeid");
            if (typeId == null) {
                return new Label("");
            }

            final String type = (String) PropertyUtils.getProperty(bean, "type");
            if (type == null || type.equals("")) {
                return new Label("");
            }

            FontAwesome relatedLink = null;
            String relateItemName = null;

            if (CrmTypeConstants.INSTANCE.getACCOUNT().equals(type)) {
                AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                final SimpleAccount account = accountService.findById(typeId, AppUI.getAccountId());
                if (account != null) {
                    relateItemName = account.getAccountname();
                    relatedLink = CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getACCOUNT());
                }
            } else if (CrmTypeConstants.INSTANCE.getCAMPAIGN().equals(type)) {
                CampaignService campaignService = AppContextUtil.getSpringBean(CampaignService.class);
                final SimpleCampaign campaign = campaignService.findById(typeId, AppUI.getAccountId());
                if (campaign != null) {
                    relateItemName = campaign.getCampaignname();
                    relatedLink = CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getCAMPAIGN());

                }
            } else if (CrmTypeConstants.INSTANCE.getCONTACT().equals(type)) {
                ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
                final SimpleContact contact = contactService.findById(typeId, AppUI.getAccountId());
                if (contact != null) {
                    relateItemName = contact.getContactName();
                    relatedLink = CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getCONTACT());

                }
            } else if (CrmTypeConstants.INSTANCE.getLEAD().equals(type)) {
                LeadService leadService = AppContextUtil.getSpringBean(LeadService.class);
                final SimpleLead lead = leadService.findById(typeId, AppUI.getAccountId());
                if (lead != null) {
                    relateItemName = lead.getLeadName();
                    relatedLink = CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getLEAD());

                }
            } else if (CrmTypeConstants.INSTANCE.getOPPORTUNITY().equals(type)) {
                OpportunityService opportunityService = AppContextUtil.getSpringBean(OpportunityService.class);
                final SimpleOpportunity opportunity = opportunityService.findById(typeId, AppUI.getAccountId());
                if (opportunity != null) {
                    relateItemName = opportunity.getOpportunityname();
                    relatedLink = CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getOPPORTUNITY());

                }
            } else if (CrmTypeConstants.INSTANCE.getCASE().equals(type)) {
                CaseService caseService = AppContextUtil.getSpringBean(CaseService.class);
                final SimpleCase cases = caseService.findById(typeId, AppUI.getAccountId());
                if (cases != null) {
                    relateItemName = cases.getSubject();
                    relatedLink = CrmAssetsManager.getAsset(CrmTypeConstants.INSTANCE.getCASE());
                }
            }

            LabelLink related = new LabelLink(relateItemName, CrmLinkBuilder.INSTANCE.generateActivityPreviewLinkFull(type, typeId));
            if (relatedLink != null)
                related.setIconLink(relatedLink);

            if (relatedLink != null) {
                return related;
            } else {
                return new Label("");
            }

        } catch (Exception e) {
            return new Label("");
        }
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }
}