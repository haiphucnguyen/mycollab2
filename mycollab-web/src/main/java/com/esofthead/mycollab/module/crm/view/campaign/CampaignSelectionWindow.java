package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.ui.DateSelectionField;
import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class CampaignSelectionWindow extends Window {

	private static final long serialVersionUID = 1L;
	private CampaignSearchCriteria searchCriteria;
	private CampaignTableDisplay tableItem;
	private FieldSelection fieldSelection;

	public CampaignSelectionWindow(FieldSelection fieldSelection) {
		super("Campaign Name Lookup");

		this.setWidth("800px");

		this.fieldSelection = fieldSelection;
	}

	public void show() {
		searchCriteria = new CampaignSearchCriteria();
		searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));

		VerticalLayout layout = new VerticalLayout();
		layout.setSpacing(true);
		layout.setMargin(true);

		createCampaignList();
		layout.addComponent(createSearchPanel());
		layout.addComponent(tableItem);
		this.setContent(layout);

		tableItem.setSearchCriteria(searchCriteria);
		center();
	}

	private TextField textValueField;
	private GridLayout layoutSearchPane;
	private DateSelectionField dateSearchField;

	private void addTextFieldSearch() {
		textValueField = new TextField();
		layoutSearchPane.addComponent(textValueField, 0, 0);
	}

	private void addDateFieldSearch() {
		dateSearchField = new DateSelectionField();
		dateSearchField.setDateFormat(AppContext.getDateFormat());
		layoutSearchPane.addComponent(dateSearchField, 0, 0);
	}

	private void removeComponents() {
		layoutSearchPane.removeComponent(0, 0);
		textValueField = null;
		dateSearchField = null;
	}

	@SuppressWarnings("serial")
	private ComponentContainer createSearchPanel() {
		layoutSearchPane = new GridLayout(3, 2);
		layoutSearchPane.setSpacing(true);

		final ValueComboBox group = new ValueComboBox(false, new String[] {
				"Campaign Name", "Start Date", "End Date" });
		group.select("Campaign Name");
		group.setImmediate(true);
		group.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				removeComponents();
				String searchType = (String) group.getValue();
				if (searchType.equals("Campaign Name")) {
					addTextFieldSearch();
				} else if (searchType.equals("Start Date")) {
					addDateFieldSearch();
				} else if (searchType.equals("End Date")) {
					addDateFieldSearch();
				}
			}
		});

		layoutSearchPane.addComponent(group, 1, 0);

		addTextFieldSearch();

		Button searchBtn = new Button("Search");
		searchBtn.addListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				searchCriteria = new CampaignSearchCriteria();
				searchCriteria.setSaccountid(new NumberSearchField(
						SearchField.AND, AppContext.getAccountId()));

				String searchType = (String) group.getValue();
				if (StringUtil.isNotNullOrEmpty(searchType)) {

					if (textValueField != null) {
						String strSearch = (String) textValueField.getValue();
						if (StringUtil.isNotNullOrEmpty(strSearch)) {

							if (searchType.equals("Campaign Name")) {
								searchCriteria
										.setCampaignName(new StringSearchField(
												SearchField.AND, strSearch));
							} else if (searchType.equals("Email")) {
							} else if (searchType.equals("Phone")) {
							}
						}
					}

					if (dateSearchField != null) {
						SearchField searchDate = dateSearchField.getValue();
						if (searchType.equals("Start Date")) {
							if (searchDate != null
									&& (searchDate instanceof DateSearchField)) {
								searchCriteria
										.setStartDate((DateSearchField) searchDate);
							} else if (searchDate != null
									&& (searchDate instanceof RangeDateSearchField)) {
								searchCriteria
										.setStartDateRange((RangeDateSearchField) searchDate);
							}
						} else if (searchType.equals("End Date")) {
							if (searchDate != null
									&& (searchDate instanceof DateSearchField)) {
								searchCriteria
										.setEndDate((DateSearchField) searchDate);
							} else if (searchDate != null
									&& (searchDate instanceof RangeDateSearchField)) {
								searchCriteria
										.setEndDateRange((RangeDateSearchField) searchDate);
							}
						}
					}
				}

				tableItem.setSearchCriteria(searchCriteria);
			}
		});
		layoutSearchPane.addComponent(searchBtn, 2, 0);
		return layoutSearchPane;
	}

	private void createCampaignList() {
		tableItem = new CampaignTableDisplay(new String[] { "campaignname",
				"type", "status", "startdate", "enddate" }, new String[] {
				"Campaign", "Type", "Status", "Start Date", "End Date" });
		tableItem.setWidth("100%");

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleCampaign campaign = (SimpleCampaign) event
								.getData();
						if ("campaignname".equals(event.getFieldName())) {
							fieldSelection.fireValueChange(campaign);
							CampaignSelectionWindow.this.getParent()
									.removeWindow(CampaignSelectionWindow.this);
						}

					}
				});
	}
}
