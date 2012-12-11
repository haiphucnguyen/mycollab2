package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
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
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.vaadin.ui.VerticalLayout;

public class LeadListViewImpl extends AbstractView implements LeadListView {
	private static final long serialVersionUID = 1L;

	private final LeadSearchPanel leadSearchPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead> tableItem;

	private final VerticalLayout accountListLayout;

	private PopupButtonControl tableActionControls;

	private final Label selectedItemsNumberLabel = new Label();

	public LeadListViewImpl() {
		this.setSpacing(true);

		leadSearchPanel = new LeadSearchPanel();
		this.addComponent(leadSearchPanel);

		accountListLayout = new VerticalLayout();
		accountListLayout.setSpacing(true);
		this.addComponent(accountListLayout);

		generateDisplayTable();
	}

	@SuppressWarnings("serial")
	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<LeadService, LeadSearchCriteria, SimpleLead>(
				AppContext.getSpringBean(LeadService.class), SimpleLead.class,
				new String[] { "selected", "leadName", "status", "accountname",
						"officephone", "email", "assignUserFullName" },
				new String[] { "", "Name", "Status", "Account Name",
						"Office Phone", "Email", "Assign User" });

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
						SimpleLead lead = tableItem.getBeanByIndex(itemId);
						tableItem.fireSelectItemEvent(lead);

					}
				});

				SimpleLead lead = tableItem.getBeanByIndex(itemId);
				lead.setExtraData(cb);
				return cb;
			}
		});

		tableItem.addGeneratedColumn("leadName", new ColumnGenerator() {

			@Override
			public Object generateCell(Table source, Object itemId,
					Object columnId) {
				final SimpleLead lead = tableItem.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(lead.getLeadName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new LeadEvent.GotoRead(this, lead
												.getId()));
							}
						});
				return b;
			}
		});

		tableItem.addGeneratedColumn("email", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleLead lead = tableItem.getBeanByIndex(itemId);
				Link l = new Link();
				l.setResource(new ExternalResource("mailto:" + lead.getEmail()));
				l.setCaption(lead.getEmail());
				return l;

			}
		});

		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("leadName", 1.0f);

		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("status", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem
				.setColumnWidth("accountname", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem
				.setColumnWidth("officephone", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		tableItem.setColumnWidth("assignuser", UIConstants.TABLE_X_LABEL_WIDTH);

		accountListLayout.addComponent(constructTableActionControls());
		accountListLayout.addComponent(tableItem);
	}

	@Override
	public HasSearchHandlers<LeadSearchCriteria> getSearchHandlers() {
		return leadSearchPanel;
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
	public HasSelectableItemHandlers<SimpleLead> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<LeadService, LeadSearchCriteria, SimpleLead> getPagedBeanTable() {
		return tableItem;
	}
}
