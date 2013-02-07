/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.lead.LeadSimpleSearchPanel;
import com.esofthead.mycollab.module.crm.view.lead.LeadTableDisplay;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;

/**
 *
 * @author haiphucnguyen
 */
public class OpportunityLeadSelectionWindow extends RelatedItemSelectionWindow<SimpleLead, LeadSearchCriteria> {

    public OpportunityLeadSelectionWindow(OpportunityLeadListComp associateLeadList) {
        super("Select Leads", associateLeadList);
        
        this.setWidth("900px");
    }

    @Override
    protected void initUI() {
        tableItem = new LeadTableDisplay(new String[]{"selected",
                    "leadName", "status", "email", "officephone"}, 
                new String[]{"", "Name", "Status", "Email", "Phone"});
        
        Button selectBtn = new Button("Select", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        
        LeadSimpleSearchPanel leadSimpleSearchPanel = new LeadSimpleSearchPanel();
        leadSimpleSearchPanel.addSearchHandler(new SearchHandler<LeadSearchCriteria>(){

			@Override
			public void onSearch(LeadSearchCriteria criteria) {
				tableItem.setSearchCriteria(criteria);
			}
        	
        });
        
        this.bodyContent.addComponent(leadSimpleSearchPanel);
        this.bodyContent.addComponent(selectBtn);
        this.bodyContent.addComponent(tableItem);
    }
    
}
