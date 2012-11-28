package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.ui.components.OpportunitySearchPanel;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public class OpportunityListViewImpl extends AbstractView implements
		OpportunityListView {
	private static final long serialVersionUID = 1L;

	private final OpportunitySearchPanel opportunitySeachPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity> tableItem;

	private final VerticalLayout accountListLayout;

	private PopupButtonControl tableActionControls;

	private final Label selectedItemsNumberLabel = new Label();

	public OpportunityListViewImpl() {
		this.setSpacing(true);

		opportunitySeachPanel = new OpportunitySearchPanel();
		this.addComponent(opportunitySeachPanel);

		accountListLayout = new VerticalLayout();
		accountListLayout.setSpacing(true);
		this.addComponent(accountListLayout);

		generateDisplayTable();
	}

	@SuppressWarnings("serial")
	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity>(
				AppContext.getSpringBean(OpportunityService.class),
				SimpleOpportunity.class, new String[] { "selected",
						"opportunityname", "accountName", "salesstage",
						"amount", "expectedcloseddate", "assignUserFullName",
						"createdtime" }, new String[] { "", "Name",
						"Account Name", "Sales Stage", "Amount", "Close",
						"User", "Date Created" });

		tableItem.addGeneratedColumn("selected", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						SimpleOpportunity opportunity = tableItem
								.getBeanByIndex(itemId);
						tableItem.fireSelectItemEvent(opportunity);
					}
				});

				@SuppressWarnings("unchecked")
				SimpleOpportunity opportunity = ((PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity>) source)
						.getBeanByIndex(itemId);
				opportunity.setExtraData(cb);
				return cb;
			}
		});

		tableItem.addGeneratedColumn("opportunityname", new ColumnGenerator() {

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleOpportunity opportunity = ((PagedBeanTable2<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity>) source)
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(opportunity.getOpportunityname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new OpportunityEvent.GotoRead(this,
												opportunity));
							}
						});
				return b;
			}
		});

		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("opportunityname", 1.0f);

		tableItem
				.setColumnWidth("accountName", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("salesstage", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("amount", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("expectedcloseddate",
				UIConstants.TABLE_DATE_WIDTH);
		tableItem.setColumnWidth("assignUserFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("createdtime", UIConstants.TABLE_DATE_WIDTH);

		accountListLayout.addComponent(constructTableActionControls());
		accountListLayout.addComponent(tableItem);
		accountListLayout.addComponent(tableItem.createControls());
	}

	@Override
	public HasSearchHandlers<OpportunitySearchCriteria> getSearchHandlers() {
		return opportunitySeachPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton(tableItem);
		layout.addComponent(selectOptionButton);

		tableActionControls = new PopupButtonControl("delete", "Delete");
		tableActionControls.addOptionItem("mail", "Mail");
		tableActionControls.addOptionItem("export", "Export");

		layout.addComponent(tableActionControls);
		layout.addComponent(selectedItemsNumberLabel);
		layout.setComponentAlignment(selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layout;
	}

	@Override
	public void enableActionControls(int numOfSelectedItems) {
		tableActionControls.setEnabled(true);
		selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setEnabled(false);
		selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleOpportunity> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<OpportunityService, OpportunitySearchCriteria, SimpleOpportunity> getPagedBeanTable() {
		return tableItem;
	}
}
