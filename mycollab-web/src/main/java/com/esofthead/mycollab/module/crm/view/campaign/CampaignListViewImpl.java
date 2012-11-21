package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.ui.components.CampaignSearchPanel;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryContainer;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryDefinition;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryFactory;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class CampaignListViewImpl extends AbstractView implements
		CampaignListView {

	private static final long serialVersionUID = 1L;

	private BeanTable<SimpleCampaign> tableItem;

	private CampaignSearchCriteria searchCriteria;

	private VerticalLayout campaignListLayout;
	
	public CampaignListViewImpl() {
		this.setSpacing(true);

		CampaignSearchPanel campaignSearchPanel = AppContext
				.getSpringBean(CampaignSearchPanel.class);
		this.addComponent(campaignSearchPanel);

		campaignListLayout = new VerticalLayout();
		this.addComponent(campaignListLayout);
	}

	private void init() {
//		eventBus.addListener(new ApplicationEventListener<CampaignEvent.Search>() {
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return CampaignEvent.Search.class;
//			}
//
//			@Override
//			public void handle(CampaignEvent.Search event) {
//				searchCriteria = (CampaignSearchCriteria) event.getData();
//				CampaignListViewImpl.this.doSearch(searchCriteria);
//			}
//		});
	}

	@Override
	public void doDefaultSearch() {
		searchCriteria = new CampaignSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		doSearch(searchCriteria);
	}

	@Override
	public void doSearch(CampaignSearchCriteria searchCriteria) {
		tableItem = new BeanTable<SimpleCampaign>();
		tableItem.addStyleName("striped");

		MyBatisQueryContainer<SimpleCampaign> container = new MyBatisQueryContainer<SimpleCampaign>(
				new MyBatisQueryDefinition<CampaignSearchCriteria>(
						AppContext.getSpringBean(CampaignService.class), false,
						5), new MyBatisQueryFactory<CampaignSearchCriteria>(
						searchCriteria));

		container.addContainerProperty("campaignname", String.class, "", true,
				true);
		container.addContainerProperty("status", String.class, "", true, true);
		container.addContainerProperty("type", String.class, "", true, true);
		container.addContainerProperty("expectedrevenue", Long.class, "", true,
				true);

		container.addContainerProperty("enddate", String.class, "", true, true);

		container.addContainerProperty("assignUserFullName", String.class, "",
				true, true);
		container.addContainerProperty("action", Object.class, "", true, false);

		tableItem.setContainerDataSource(container);
		tableItem.setColumnHeaders(new String[] { "Campaign", "Status", "Type",
				"Expected Revenue", "End Date", "Assign User", "Action" });

		campaignListLayout.removeAllComponents();
		campaignListLayout.addComponent(tableItem);
//		campaignListLayout.addComponent(tableItem.createControls());
	}

}
