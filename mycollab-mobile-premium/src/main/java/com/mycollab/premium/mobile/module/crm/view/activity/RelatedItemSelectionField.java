package com.mycollab.premium.mobile.module.crm.view.activity;

import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.*;
import com.mycollab.module.crm.service.*;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.ui.FieldSelection;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * @author MyCollab Ltd.
 * @since 4.2
 */
public class RelatedItemSelectionField extends CustomField<Integer> implements FieldSelection {
    private static final long serialVersionUID = -3572873867793792681L;

    private static final Logger LOG = LoggerFactory.getLogger(RelatedItemSelectionField.class);

    protected NavigationButton navButton = new NavigationButton();
    protected Object bean;

    public RelatedItemSelectionField(Object bean) {
        this.bean = bean;
    }

    @Override
    public void setPropertyDataSource(Property newDataSource) {
        Object value = newDataSource.getValue();
        if (value instanceof Integer) {
            setValue((Integer) value);
            super.setPropertyDataSource(newDataSource);
        } else {
            super.setPropertyDataSource(newDataSource);
        }
    }

    @Override
    public void setValue(Integer typeid) {
        try {
            String type = (String) PropertyUtils.getProperty(bean, "type");
            if (type != null && typeid != null) {
                if ("Account".equals(type)) {
                    AccountService accountService = AppContextUtil.getSpringBean(AccountService.class);
                    SimpleAccount account = accountService.findById(typeid, AppUI.getAccountId());
                    if (account != null) {
                        navButton.setCaption(account.getAccountname());
                    }
                } else if ("Campaign".equals(type)) {
                    CampaignService campaignService = AppContextUtil.getSpringBean(CampaignService.class);
                    SimpleCampaign campaign = campaignService.findById(typeid, AppUI.getAccountId());
                    if (campaign != null) {
                        navButton.setCaption(campaign.getCampaignname());
                    }
                } else if ("Contact".equals(type)) {
                    ContactService contactService = AppContextUtil.getSpringBean(ContactService.class);
                    SimpleContact contact = contactService.findById(typeid, AppUI.getAccountId());
                    if (contact != null) {
                        navButton.setCaption(contact.getContactName());
                    }
                } else if ("Lead".equals(type)) {
                    LeadService leadService = AppContextUtil.getSpringBean(LeadService.class);
                    SimpleLead lead = leadService.findById(typeid, AppUI.getAccountId());
                    if (lead != null) {
                        navButton.setCaption(lead.getLeadName());
                    }
                } else if ("Opportunity".equals(type)) {
                    OpportunityService opportunityService = AppContextUtil.getSpringBean(OpportunityService.class);
                    SimpleOpportunity opportunity = opportunityService.findById(typeid, AppUI.getAccountId());
                    if (opportunity != null) {
                        navButton.setCaption(opportunity.getOpportunityname());
                    }
                } else if ("Case".equals(type)) {
                    CaseService caseService = AppContextUtil.getSpringBean(CaseService.class);
                    SimpleCase cases = caseService.findById(typeid, AppUI.getAccountId());
                    if (cases != null) {
                        navButton.setCaption(cases.getSubject());
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Error when set type", e);
        }
    }

    @Override
    public void fireValueChange(Object data) {
        try {
            Object dataId = PropertyUtils.getProperty(data, "id");
            if (dataId == null) {
                PropertyUtils.setProperty(bean, "type", null);
                return;
            }

            setInternalValue((Integer) dataId);

            if (data instanceof SimpleAccount) {
                PropertyUtils.setProperty(bean, "type", CrmTypeConstants.INSTANCE.getACCOUNT());
                navButton.setCaption(((SimpleAccount) data).getAccountname());
            } else if (data instanceof SimpleCampaign) {
                PropertyUtils.setProperty(bean, "type", CrmTypeConstants.INSTANCE.getCAMPAIGN());
                navButton.setCaption(((SimpleCampaign) data).getCampaignname());
            } else if (data instanceof SimpleContact) {
                PropertyUtils.setProperty(bean, "type", CrmTypeConstants.INSTANCE.getCONTACT());
                navButton.setCaption(((SimpleContact) data).getContactName());
            } else if (data instanceof SimpleLead) {
                PropertyUtils.setProperty(bean, "type", CrmTypeConstants.INSTANCE.getLEAD());
                navButton.setCaption(((SimpleLead) data).getLeadName());
            } else if (data instanceof SimpleOpportunity) {
                PropertyUtils.setProperty(bean, "type", CrmTypeConstants.INSTANCE.getOPPORTUNITY());
                navButton.setCaption(((SimpleOpportunity) data).getOpportunityname());
            } else if (data instanceof SimpleCase) {
                PropertyUtils.setProperty(bean, "type", CrmTypeConstants.INSTANCE.getCASE());
                navButton.setCaption(((SimpleCase) data).getSubject());
            }
        } catch (Exception e) {
            LOG.error("Error when fire value", e);
        }
    }

    @Override
    protected Component initContent() {
        final RelatedItemSelectionView targetView = new RelatedItemSelectionView(this);
        navButton.setWidth("100%");
        navButton.setTargetView(targetView);
        navButton.addClickListener(navigationButtonClickEvent -> {
            try {
                targetView.selectTab((String) PropertyUtils.getProperty(bean, "type"));
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LOG.error("Error when select tab", e);
            }
        });
        return navButton;
    }

    @Override
    public Class<? extends Integer> getType() {
        return Integer.class;
    }

}
