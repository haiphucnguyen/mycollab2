package com.esofthead.mycollab.module.crm.ui.components;

import javax.annotation.PostConstruct;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryContainer;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryDefinition;
import com.esofthead.mycollab.vaadin.data.MyBatisQueryFactory;
import com.esofthead.mycollab.vaadin.ui.BeanTable;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CampaignSelectionWindow extends Window {
	private static final long serialVersionUID = 1L;

	private CampaignSearchCriteria searchCriteria;

	private BeanTable<SimpleCampaign> tableItem;

	private FieldSelection fieldSelection;

	public CampaignSelectionWindow(FieldSelection fieldSelection) {
		super("Campaign Name Lookup");

		this.fieldSelection = fieldSelection;
	}

	@PostConstruct
	private void init() {
	}

	public void show() {
		searchCriteria = new CampaignSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);

		layout.addComponent(createSearchPanel());
		layout.addComponent(createCampaignList());
		this.setContent(layout);
	}

	private ComponentContainer createSearchPanel() {
		GridFormLayoutHelper layout = new GridFormLayoutHelper(3, 2);
		
		return layout.getLayout();
	}

	private com.vaadin.ui.Component createCampaignList() {
		tableItem = new BeanTable<SimpleCampaign>();
		tableItem.addStyleName("striped");
		tableItem.setWidth("100%");
		tableItem.setHeight("200px");

		MyBatisQueryContainer<SimpleCampaign> container = new MyBatisQueryContainer<SimpleCampaign>(
				new MyBatisQueryDefinition<CampaignSearchCriteria>(
						AppContext.getSpringBean(CampaignService.class), false,
						5), new MyBatisQueryFactory<CampaignSearchCriteria>(
						searchCriteria));

		container.addContainerProperty("campaignname", String.class, "", true,
				true);
		tableItem.setColumnWidth("campaignname", 250);

		container.addContainerProperty("type", String.class, "", true, true);
		tableItem.setColumnWidth("type", 150);

		container.addContainerProperty("status", String.class, "", true, true);
		tableItem.setColumnWidth("status", 150);

		container.addContainerProperty("startdate", String.class, "", true,
				true);
		tableItem.setColumnWidth("startdate", 120);

		container.addContainerProperty("enddate", String.class, "", true, true);
		tableItem.setColumnWidth("enddate", 120);

		tableItem.setContainerDataSource(container);

		tableItem.addGeneratedColumn("campaignname", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			public com.vaadin.ui.Component generateCell(final Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleCampaign campaign = ((BeanTable<SimpleCampaign>) source)
						.getBeanByIndex(itemId);
				Button b = new Button(campaign.getCampaignname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								final SimpleCampaign campaign = ((BeanTable<SimpleCampaign>) source)
										.getBeanByIndex(itemId);
								fieldSelection.fireValueChange(campaign);
								CampaignSelectionWindow.this.getParent()
										.removeWindow(
												CampaignSelectionWindow.this);
							}
						});
				b.setStyleName("link");
				return b;
			}
		});

		tableItem.setColumnHeaders(new String[] { "Campaign", "Type", "Status",
				"Start Date", "End Date" });

		return tableItem;
	}
}
