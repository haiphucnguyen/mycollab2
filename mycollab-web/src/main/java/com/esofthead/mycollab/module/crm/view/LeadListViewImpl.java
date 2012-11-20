package com.esofthead.mycollab.module.crm.view;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.ui.components.LeadSearchPanel;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryContainer;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryDefinition;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryFactory;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class LeadListViewImpl extends AbstractView implements LeadListView {
	private static final long serialVersionUID = 1L;

	private BeanTable<SimpleLead> tableItem;

	private LeadSearchCriteria searchCriteria;

	private VerticalLayout leadListLayout;
	
	public LeadListViewImpl() {
		this.setSpacing(true);
		LeadSearchPanel leadSearchPanel = AppContext
				.getSpringBean(LeadSearchPanel.class);
		this.addComponent(leadSearchPanel);

		leadListLayout = new VerticalLayout();
		this.addComponent(leadListLayout);
	}


	private void init() {
//		eventBus.addListener(new ApplicationEventListener<LeadEvent.Search>() {
//
//			@Override
//			public Class<? extends ApplicationEvent> getEventType() {
//				return LeadEvent.Search.class;
//			}
//
//			@Override
//			public void handle(LeadEvent.Search event) {
//				searchCriteria = (LeadSearchCriteria) event.getData();
//				LeadListViewImpl.this.doSearch(searchCriteria);
//			}
//		});
	}

	@Override
	public void doDefaultSearch() {
		searchCriteria = new LeadSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		doSearch(searchCriteria);
	}

	@Override
	public void doSearch(LeadSearchCriteria searchCriteria) {
		tableItem = new BeanTable<SimpleLead>();
		tableItem.addStyleName("striped");

		MyBatisQueryContainer<SimpleLead> container = new MyBatisQueryContainer<SimpleLead>(
				new MyBatisQueryDefinition<LeadSearchCriteria>(
						AppContext.getSpringBean(LeadService.class), false, 5),
				new MyBatisQueryFactory<LeadSearchCriteria>(searchCriteria));

		container
				.addContainerProperty("leadName", String.class, "", true, true);
		container.addContainerProperty("status", String.class, "", true, true);
		container.addContainerProperty("accountname", String.class, "", true,
				true);
		container.addContainerProperty("officephone", Long.class, "", true,
				true);

		container.addContainerProperty("email", String.class, "", true, true);

		container.addContainerProperty("assignUserFullName", String.class, "",
				true, true);
		container.addContainerProperty("action", Object.class, "", true, false);

		tableItem.setContainerDataSource(container);
		tableItem.setColumnHeaders(new String[] { "Name", "Status",
				"Account Name", "Office Phone", "Email", "Assign User",
				"Action" });

		leadListLayout.removeAllComponents();
		leadListLayout.addComponent(tableItem);
		leadListLayout.addComponent(tableItem.createControls());
	}
}
