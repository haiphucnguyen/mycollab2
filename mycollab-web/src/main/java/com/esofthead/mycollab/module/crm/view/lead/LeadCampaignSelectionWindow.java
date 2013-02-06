/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignTableDisplay;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;

/**
 *
 * @author haiphucnguyen
 */
public class LeadCampaignSelectionWindow extends RelatedItemSelectionWindow<SimpleCampaign, CampaignSearchCriteria> {

    public LeadCampaignSelectionWindow(LeadCampaignListComp associateLeadList) {
        super("Select Campaigns", associateLeadList);
        
        this.setWidth("900px");
    }

    @Override
    protected void initUI() {
        tableItem = new CampaignTableDisplay(new String[]{"selected",
                    "campaignname", "status", "type", "startdate"}, 
                new String[]{"", "Name", "Status", "Type", "Start Date"});
        
        Button selectBtn = new Button("Select", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        
        this.bodyContent.addComponent(selectBtn);
        this.bodyContent.addComponent(tableItem);
    }
    
}
