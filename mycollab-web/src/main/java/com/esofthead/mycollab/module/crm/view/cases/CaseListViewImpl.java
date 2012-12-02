package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.domain.criteria.CaseSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.PagedBeanTable2;
import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class CaseListViewImpl extends AbstractView implements CaseListView {
	private static final long serialVersionUID = 1L;

	private final CaseSearchPanel searchPanel;

	private SelectionOptionButton selectOptionButton;

	private PagedBeanTable2<CaseService, CaseSearchCriteria, SimpleCase> tableItem;

	private final VerticalLayout listLayout;

	private PopupButtonControl tableActionControls;

	private final Label selectedItemsNumberLabel = new Label();

	public CaseListViewImpl() {
		this.setSpacing(true);

		searchPanel = new CaseSearchPanel();
		this.addComponent(searchPanel);

		listLayout = new VerticalLayout();
		listLayout.setSpacing(true);
		this.addComponent(listLayout);

		generateDisplayTable();
	}

	private void generateDisplayTable() {
		tableItem = new PagedBeanTable2<CaseService, CaseSearchCriteria, SimpleCase>(
				AppContext.getSpringBean(CaseService.class),
				SimpleCase.class, new String[] { "selected", "accountname",
						"city", "billingCountry", "phoneoffice", "email",
						"assignUserFullName", "createdtime" }, new String[] {
						"", "Name", "City", "Billing Country", "Phone Office",
						"Email Address", "Assign User", "Created Time" });

		tableItem.setColumnExpandRatio("accountname", 1);
		tableItem.setColumnWidth("selected", UIConstants.TABLE_CONTROL_WIDTH);
		tableItem.setColumnWidth("city", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("billingCountry",
				UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem
				.setColumnWidth("phoneoffice", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("email", UIConstants.TABLE_EMAIL_WIDTH);
		tableItem.setColumnWidth("assignuser", UIConstants.TABLE_X_LABEL_WIDTH);
		tableItem.setColumnWidth("createdtime", UIConstants.TABLE_DATE_WIDTH);

		tableItem.setWidth("100%");

		listLayout.addComponent(constructTableActionControls());
		listLayout.addComponent(tableItem);
		listLayout.addComponent(tableItem.createControls());
	}

	@Override
	public HasSearchHandlers<CaseSearchCriteria> getSearchHandlers() {
		return searchPanel;
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
	public HasSelectableItemHandlers<SimpleCase> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<CaseService, CaseSearchCriteria, SimpleCase> getPagedBeanTable() {
		return tableItem;
	}

}
