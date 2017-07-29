package com.mycollab.mobile.module.crm.ui;

import com.mycollab.module.crm.domain.*;
import com.mycollab.module.crm.service.*;
import com.mycollab.module.file.StorageUtils;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Resource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author MyCollab Ltd.
 * @since 4.1
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
            final String type = (String) PropertyUtils.getProperty(RelatedReadItemField.this.bean, "type");
            if (type == null || type.equals("")) {
                return new Label("");
            }

            final Integer typeId = (Integer) PropertyUtils.getProperty(bean, "typeid");
            if (typeId == null) {
                return new Label("");
            }

            Resource relatedLink = null;
            String relateItemName = null;

            if ("Account".equals(type)) {
                AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                final SimpleAccount account = accountService.findById(typeId, MyCollabUI.getAccountId());
                if (account != null) {
                    relateItemName = account.getAccountname();
                    relatedLink = new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/crm/account.png"));
                }
            } else if ("Campaign".equals(type)) {
                CampaignService campaignService = AppContextUtil.getSpringBean(CampaignService.class);
                final SimpleCampaign campaign = campaignService.findById(typeId, MyCollabUI.getAccountId());
                if (campaign != null) {
                    relateItemName = campaign.getCampaignname();
                    relatedLink = new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/crm/campaign.png"));
                }
            } else if ("Contact".equals(type)) {
                ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
                final SimpleContact contact = contactService.findById(typeId, MyCollabUI.getAccountId());
                if (contact != null) {
                    relateItemName = contact.getContactName();
                    relatedLink = new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/crm/contact.png"));

                }
            } else if ("Lead".equals(type)) {
                LeadService leadService = AppContextUtil.getSpringBean(LeadService.class);
                final SimpleLead lead = leadService.findById(typeId, MyCollabUI.getAccountId());
                if (lead != null) {
                    relateItemName = lead.getLeadName();
                    relatedLink = new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/crm/lead.png"));
                }
            } else if ("Opportunity".equals(type)) {
                OpportunityService opportunityService = AppContextUtil.getSpringBean(OpportunityService.class);
                final SimpleOpportunity opportunity = opportunityService.findById(typeId, MyCollabUI.getAccountId());
                if (opportunity != null) {
                    relateItemName = opportunity.getOpportunityname();
                    relatedLink = new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/crm/opportunity.png"));

                }
            } else if ("Case".equals(type)) {
                CaseService caseService = AppContextUtil.getSpringBean(CaseService.class);
                final SimpleCase cases = caseService.findById(typeId, MyCollabUI.getAccountId());
                if (cases != null) {
                    relateItemName = cases.getSubject();
                    relatedLink = new ExternalResource(StorageUtils.generateAssetRelativeLink("icons/16/crm/case.png"));
                }
            }

            Button related = new Button(relateItemName);
            if (relatedLink != null)
                related.setIcon(relatedLink);

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
