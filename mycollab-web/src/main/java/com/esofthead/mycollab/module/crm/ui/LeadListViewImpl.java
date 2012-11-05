package com.esofthead.mycollab.module.crm.ui;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.ui.components.LeadSearchPanel;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryContainer;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryDefinition;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryFactory;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEvent;
import com.esofthead.mycollab.vaadin.mvp.eventbus.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

@Component
@SuppressWarnings("serial")
public class LeadListViewImpl extends AbstractView implements LeadListView {
	private static final long serialVersionUID = 1L;

	private BeanTable<SimpleLead> tableItem;

	private LeadSearchCriteria searchCriteria;

	private VerticalLayout leadListLayout;

	
	@PostConstruct
	private void init() {
		eventBus.addListener(new ApplicationEventListener<LeadEvent.Search>() {

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return LeadEvent.Search.class;
			}

			@Override
			public void handle(LeadEvent.Search event) {
				searchCriteria = (LeadSearchCriteria) event.getData();
				LeadListViewImpl.this.doSearch(searchCriteria);
			}
		});
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

		leadListLayout.removeAllComponents();
		leadListLayout.addComponent(tableItem);
		leadListLayout.addComponent(tableItem.createControls());
	}

	@Override
	protected ComponentContainer initMainLayout() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		LeadSearchPanel leadSearchPanel = AppContext
				.getSpringBean(LeadSearchPanel.class);
		layout.addComponent(leadSearchPanel);

		leadListLayout = new VerticalLayout();
		layout.addComponent(leadListLayout);
		return layout;
	}
}
