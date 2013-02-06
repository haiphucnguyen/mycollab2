/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.contact;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunitySimpleSearchPanel;
import com.esofthead.mycollab.module.crm.view.opportunity.OpportunityTableDisplay;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Button;

/**
 *
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class ContactOpportunitySelectionWindow extends RelatedItemSelectionWindow<SimpleOpportunity, OpportunitySearchCriteria> {

    public ContactOpportunitySelectionWindow(ContactOpportunityListComp associateOpportunityList) {
        super("Select Opportunities", associateOpportunityList);
        
        this.setWidth("900px");
    }

    @Override
    protected void initUI() {
        tableItem = new OpportunityTableDisplay(new String[]{"selected",
                    "opportunityname", "salesstage", "expectedcloseddate"}, new String[]{"", "Name", "Stage",
                    "Expected Closed Date"});
        
        Button selectBtn = new Button("Select", new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
        
        OpportunitySimpleSearchPanel opportunitySimpleSearchPanel = new OpportunitySimpleSearchPanel();
        opportunitySimpleSearchPanel.addSearchHandler(new SearchHandler<OpportunitySearchCriteria>(){

			@Override
			public void onSearch(OpportunitySearchCriteria criteria) {
				tableItem.setSearchCriteria(criteria);
			}
        	
        });
        
        this.bodyContent.addComponent(opportunitySimpleSearchPanel);
        this.bodyContent.addComponent(selectBtn);
        this.bodyContent.addComponent(tableItem);
    }
    
}
