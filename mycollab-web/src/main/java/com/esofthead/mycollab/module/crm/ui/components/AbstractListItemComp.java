package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ListView;
import com.esofthead.mycollab.vaadin.ui.DefaultGenericSearchPanel;
import com.esofthead.mycollab.vaadin.ui.DefaultMassItemActionHandlersContainer;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public abstract class AbstractListItemComp<S extends SearchCriteria, B> extends
		AbstractPageView implements ListView<S, B> {
	private static final long serialVersionUID = 1L;

	protected VerticalLayout contentLayout;
	protected DefaultGenericSearchPanel<S> searchPanel;
	protected AbstractPagedBeanTable<S, B> tableItem;

	protected Label selectedItemsNumberLabel = new Label();

	protected SelectionOptionButton selectOptionButton;
	protected DefaultMassItemActionHandlersContainer tableActionControls;
	protected HorizontalLayout extraControlsLayout;

	public AbstractListItemComp() {
		this.searchPanel = createSearchPanel();
		this.addComponent(this.searchPanel);

		this.contentLayout = new VerticalLayout();
		this.addComponent(this.contentLayout);

		this.tableItem = createBeanTable();
		buildLayout();
	}

	private void buildLayout() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layout.setWidth("100%");
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		this.selectOptionButton.setSizeUndefined();
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_DELETE));
		deleteBtn.setEnabled(AppContext
				.canAccess(RolePermissionCollections.CRM_ACCOUNT));

		this.tableActionControls = createActionControls();

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_LEFT);

		layout.setExpandRatio(this.selectedItemsNumberLabel, 1.0f);

		extraControlsLayout = new HorizontalLayout();
		extraControlsLayout.setSpacing(true);
		layout.addComponent(extraControlsLayout);
		layout.setComponentAlignment(this.extraControlsLayout,
				Alignment.MIDDLE_RIGHT);

		contentLayout.addComponent(layoutWrapper);
		contentLayout.addComponent(this.tableItem);

		buildExtraControls();
	}

	public void disableActionControls() {
		this.tableActionControls.setVisible(false);
		this.selectOptionButton.setSelectedChecbox(false);
		this.selectedItemsNumberLabel.setValue("");
	}

	public void enableActionControls(final int numOfSelectedItems) {
		this.tableActionControls.setVisible(true);
		this.selectedItemsNumberLabel.setValue(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TABLE_SELECTED_ITEM_TITLE,
						numOfSelectedItems));
	}

	public void addExtraComponent(Component component) {
		extraControlsLayout.addComponent(component);
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return this.selectOptionButton;
	}

	@Override
	public AbstractPagedBeanTable<S, B> getPagedBeanTable() {
		return this.tableItem;
	}

	@Override
	public HasMassItemActionHandlers getPopupActionHandlers() {
		return tableActionControls;
	}

	@Override
	public HasSearchHandlers<S> getSearchHandlers() {
		return this.searchPanel;
	}

	@Override
	public HasSelectableItemHandlers<B> getSelectableItemHandlers() {
		return this.tableItem;
	}

	abstract protected void buildExtraControls();

	abstract protected DefaultGenericSearchPanel<S> createSearchPanel();

	abstract protected AbstractPagedBeanTable<S, B> createBeanTable();

	abstract protected DefaultMassItemActionHandlersContainer createActionControls();
}
