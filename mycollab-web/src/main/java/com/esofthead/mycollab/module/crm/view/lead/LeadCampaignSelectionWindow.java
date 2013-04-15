/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.ui.components.RelatedItemSelectionWindow;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignSimpleSearchPanel;
import com.esofthead.mycollab.module.crm.view.campaign.CampaignTableDisplay;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Button;

/**
 * 
 * @author haiphucnguyen
 */
@SuppressWarnings("serial")
public class LeadCampaignSelectionWindow extends
		RelatedItemSelectionWindow<SimpleCampaign, CampaignSearchCriteria> {

	public LeadCampaignSelectionWindow(LeadCampaignListComp associateLeadList) {
		super("Select Campaigns", associateLeadList);

		this.setWidth("900px");
	}

	@Override
	protected void initUI() {
		tableItem = new CampaignTableDisplay(
				new String[] { "selected", "campaignname", "status", "type",
						"enddate" },
				new String[] {
						"",
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_NAME_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_TYPE_HEADER),
						LocalizationHelper
								.getMessage(CrmCommonI18nEnum.TABLE_END_DATE_HEADER) });

		Button selectBtn = new Button("Select", new Button.ClickListener() {

			@Override
			public void buttonClick(Button.ClickEvent event) {
				close();
			}
		});
		selectBtn.setStyleName(UIConstants.THEME_BLUE_LINK);

		CampaignSimpleSearchPanel campaignSimpleSearchPanel = new CampaignSimpleSearchPanel();
		campaignSimpleSearchPanel
				.addSearchHandler(new SearchHandler<CampaignSearchCriteria>() {

					@Override
					public void onSearch(CampaignSearchCriteria criteria) {
						tableItem.setSearchCriteria(criteria);
					}

				});

		this.bodyContent.addComponent(campaignSimpleSearchPanel);
		this.bodyContent.addComponent(selectBtn);
		this.bodyContent.addComponent(tableItem);
	}

}
