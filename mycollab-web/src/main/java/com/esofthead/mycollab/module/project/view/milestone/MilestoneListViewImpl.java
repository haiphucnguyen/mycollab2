/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.milestone;

import java.util.GregorianCalendar;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserLink;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.PagedBeanTable2;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class MilestoneListViewImpl extends AbstractView implements
		MilestoneListView {

	private static final long serialVersionUID = 1L;
	private final MilestoneSearchPanel riskSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private PagedBeanTable2<MilestoneService, MilestoneSearchCriteria, SimpleMilestone> tableItem;
	private final VerticalLayout riskListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public MilestoneListViewImpl() {
		this.setSpacing(true);
		this.setMargin(false, true, true, true);

		riskSearchPanel = new MilestoneSearchPanel();
		this.addComponent(riskSearchPanel);

		riskListLayout = new VerticalLayout();
		riskListLayout.setSpacing(true);
		this.addComponent(riskListLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<MilestoneService, MilestoneSearchCriteria, SimpleMilestone>(
				AppContext.getSpringBean(MilestoneService.class),
				SimpleMilestone.class, new String[] { "selected", "name",
						"iscompleted", "startdate", "enddate", "flag",
						"ownerFullName" }, new String[] { "", "Name", "Status",
						"Start Date", "End Date", "Flag", "Responsible User" });

		tableItem.addGeneratedColumn("selected", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public Object generateCell(final Table source, final Object itemId,
					Object columnId) {
				final CheckBox cb = new CheckBox("", false);
				cb.setImmediate(true);
				cb.addListener(new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(Button.ClickEvent event) {
						SimpleMilestone account = tableItem
								.getBeanByIndex(itemId);
						tableItem.fireSelectItemEvent(account);

					}
				});

				SimpleMilestone account = tableItem.getBeanByIndex(itemId);
				account.setExtraData(cb);
				return cb;
			}
		});

		tableItem.addGeneratedColumn("name", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					final Object itemId, Object columnId) {
				final SimpleMilestone milestone = tableItem
						.getBeanByIndex(itemId);
				ButtonLink b = new ButtonLink(milestone.getName(),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(Button.ClickEvent event) {
								EventBus.getInstance().fireEvent(
										new MilestoneEvent.GotoRead(this,
												milestone.getId()));
							}
						});
				b.addStyleName("medium-text");
				if (milestone.getEnddate() != null
						&& (milestone.getEnddate()
								.before(new GregorianCalendar().getTime()))) {
					b.addStyleName(UIConstants.LINK_OVERDUE);
				}
				return b;

			}
		});

		tableItem.addGeneratedColumn("ownerFullName",
				new Table.ColumnGenerator() {
					private static final long serialVersionUID = 1L;

					@Override
					public com.vaadin.ui.Component generateCell(Table source,
							final Object itemId, Object columnId) {
						final SimpleMilestone milestone = tableItem
								.getBeanByIndex(itemId);
						UserLink b = new UserLink(milestone.getOwner(),
								milestone.getOwnerFullName());
						return b;

					}
				});

		tableItem.addGeneratedColumn("startdate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleMilestone milestone = tableItem
						.getBeanByIndex(itemId);
				Label l = new Label();

				l.setValue(AppContext.formatDate(milestone.getStartdate()));
				return l;
			}
		});

		tableItem.addGeneratedColumn("enddate", new Table.ColumnGenerator() {
			private static final long serialVersionUID = 1L;

			@Override
			public com.vaadin.ui.Component generateCell(Table source,
					Object itemId, Object columnId) {
				final SimpleMilestone milestone = tableItem
						.getBeanByIndex(itemId);
				Label l = new Label();

				l.setValue(AppContext.formatDate(milestone.getEnddate()));
				return l;
			}
		});

		tableItem.setWidth("100%");

		tableItem.setColumnExpandRatio("accountname", 1);
		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("startdate", UIConstants.TABLE_DATE_WIDTH);
		tableItem.setColumnWidth("enddate", UIConstants.TABLE_DATE_WIDTH);
		tableItem
				.setColumnWidth("iscompleted", UIConstants.TABLE_S_LABEL_WIDTH);
		tableItem.setColumnWidth("flag", UIConstants.TABLE_S_LABEL_WIDTH);
		tableItem.setColumnWidth("ownerFullName",
				UIConstants.TABLE_X_LABEL_WIDTH);

		riskListLayout.addComponent(constructTableActionControls());
		riskListLayout.addComponent(tableItem);
	}

	@Override
	public HasSearchHandlers<MilestoneSearchCriteria> getSearchHandlers() {
		return riskSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton(tableItem);
		layout.addComponent(selectOptionButton);

		tableActionControls = new PopupButtonControl("delete", "Delete");
		tableActionControls.addOptionItem("mail", "Mail");
		tableActionControls.addOptionItem("export", "Export");
		tableActionControls.setVisible(false);

		layout.addComponent(tableActionControls);
		layout.addComponent(selectedItemsNumberLabel);
		layout.setComponentAlignment(selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layout;
	}

	@Override
	public void enableActionControls(int numOfSelectedItems) {
		tableActionControls.setVisible(true);
		selectedItemsNumberLabel.setValue("Selected: " + numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setVisible(false);
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
	public HasSelectableItemHandlers<SimpleMilestone> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<MilestoneSearchCriteria, SimpleMilestone> getPagedBeanTable() {
		return tableItem;
	}
}
