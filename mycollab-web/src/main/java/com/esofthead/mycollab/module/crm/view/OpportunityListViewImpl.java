package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.ui.components.OpportunitySearchPanel;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryContainer;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryDefinition;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryFactory;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;


public class OpportunityListViewImpl extends AbstractView implements
		OpportunityListView {
	private static final long serialVersionUID = 1L;

	private BeanTable<SimpleOpportunity> tableItem;

	private OpportunitySearchCriteria searchCriteria;

	private VerticalLayout opportunityListLayout;
	
	public OpportunityListViewImpl() {
		this.setSpacing(true);

		OpportunitySearchPanel opportunitySearchPanel = AppContext
				.getSpringBean(OpportunitySearchPanel.class);
		this.addComponent(opportunitySearchPanel);

		opportunityListLayout = new VerticalLayout();
		this.addComponent(opportunityListLayout);
	}

	private void init() {
//		eventBus.addListener(new ApplicationEventListener<OpportunityEvent.Search>() {
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return OpportunityEvent.Search.class;
//			}
//
//			@Override
//			public void handle(OpportunityEvent.Search event) {
//				searchCriteria = (OpportunitySearchCriteria) event.getData();
//				OpportunityListViewImpl.this.doSearch(searchCriteria);
//			}
//		});
	}

	@Override
	public void doDefaultSearch() {
		searchCriteria = new OpportunitySearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		doSearch(searchCriteria);
	}

	@Override
	public void doSearch(OpportunitySearchCriteria searchCriteria) {
		tableItem = new BeanTable<SimpleOpportunity>();
		tableItem.addStyleName("striped");

		MyBatisQueryContainer<SimpleOpportunity> container = new MyBatisQueryContainer<SimpleOpportunity>(
				new MyBatisQueryDefinition<OpportunitySearchCriteria>(
						AppContext.getSpringBean(OpportunityService.class), false,
						5), new MyBatisQueryFactory<OpportunitySearchCriteria>(
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

		opportunityListLayout.removeAllComponents();
		opportunityListLayout.addComponent(tableItem);
//		opportunityListLayout.addComponent(tableItem.createControls());
	}
}
