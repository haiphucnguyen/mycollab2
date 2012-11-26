package com.esofthead.mycollab.module.crm.view.campaign;

import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.events.CampaignEvent;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import com.esofthead.mycollab.module.crm.ui.components.CampaignSearchPanel;
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

@SuppressWarnings("serial")
public class CampaignListViewImpl extends AbstractView implements
		CampaignListView {

	private CampaignSearchPanel campaignSearchPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable2<CampaignService, CampaignSearchCriteria, SimpleCampaign> tableItem;

	private VerticalLayout campainListLayout;

	private PopupButtonControl tableActionControls;

	private Label selectedItemsNumberLabel = new Label();

	public CampaignListViewImpl() {
		this.setSpacing(true);

		campaignSearchPanel = new CampaignSearchPanel();
		this.addComponent(campaignSearchPanel);

		campainListLayout = new VerticalLayout();
		campainListLayout.setSpacing(true);
		this.addComponent(campainListLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<CampaignService, CampaignSearchCriteria, SimpleCampaign>(
				AppContext.getSpringBean(CampaignService.class),
				SimpleCampaign.class, new String[] { "selected",
						"campaignname", "status", "type", "expectedrevenue",
						"enddate", "assignUserFullName" }, new String[] { "",
						"Campaign", "Status", "Type", "Expected Revenue",
						"End Date", "Assign User" });

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
						SimpleCampaign campaign = tableItem
								.getBeanByIndex(itemId);
						tableItem.fireSelectItemEvent(campaign);

					}
				});

				@SuppressWarnings("unchecked")
				SimpleCampaign campaign = ((PagedBeanTable2<CampaignService, CampaignSearchCriteria, SimpleCampaign>) source)
						.getBeanByIndex(itemId);
				campaign.setExtraData(cb);
				return cb;
			}
		});

		tableItem.addGeneratedColumn("campaignname", new ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				@SuppressWarnings("unchecked")
				final SimpleCampaign campaign = ((PagedBeanTable2<CampaignService, CampaignSearchCriteria, SimpleCampaign>) source)
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(campaign.getCampaignname(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new CampaignEvent.GotoRead(this,
												campaign));
							}
						});
				return b;

			}
		});

		tableItem.setWidth("100%");

		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("status", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("type", UIConstants.TABLE_M_LABEL_WIDTH);
		tableItem.setColumnWidth("expectedrevenue",
				UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("enddate", UIConstants.TABLE_DATE_WIDTH);
		tableItem.setColumnWidth("assignUserFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);

		campainListLayout.addComponent(constructTableActionControls());
		campainListLayout.addComponent(tableItem);
		campainListLayout.addComponent(tableItem.createControls());
	}

	@Override
	public HasSearchHandlers<CampaignSearchCriteria> getSearchHandlers() {
		return campaignSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton();
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
	public HasSelectableItemHandlers<SimpleCampaign> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<CampaignService, CampaignSearchCriteria, SimpleCampaign> getPagedBeanTable() {
		return tableItem;
	}

}
