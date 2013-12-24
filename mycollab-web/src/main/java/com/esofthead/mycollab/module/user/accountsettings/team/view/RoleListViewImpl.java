/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.esofthead.mycollab.module.user.accountsettings.team.view;

import java.util.Arrays;

import com.esofthead.mycollab.vaadin.ui.PopupButtonControl;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.domain.Role;
import com.esofthead.mycollab.module.user.domain.SimpleRole;
import com.esofthead.mycollab.module.user.domain.criteria.RoleSearchCriteria;
import com.esofthead.mycollab.module.user.events.RoleEvent;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.HasMassItemActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.MassItemActionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class RoleListViewImpl extends AbstractPageView implements RoleListView {

	private static final long serialVersionUID = 1L;
	private final RoleSearchPanel searchPanel;
	private SelectionOptionButton selectOptionButton;
	private RoleTableDisplay tableItem;
	private final VerticalLayout listLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public RoleListViewImpl() {
		
		this.setMargin(new MarginInfo(true, false, false, false));

		this.searchPanel = new RoleSearchPanel();
		this.addComponent(this.searchPanel);

		this.listLayout = new VerticalLayout();
		this.addComponent(this.listLayout);

		this.generateDisplayTable();
	}

	private void generateDisplayTable() {
		this.tableItem = new RoleTableDisplay(new TableViewField("",
				"selected", UIConstants.TABLE_CONTROL_WIDTH), Arrays.asList(
				new TableViewField("Name", "rolename",
						UIConstants.TABLE_EX_LABEL_WIDTH), new TableViewField(
						"Description", "description",
						UIConstants.TABLE_EX_LABEL_WIDTH)));

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final Role role = (Role) event.getData();
						if ("rolename".equals(event.getFieldName())) {
							EventBus.getInstance()
									.fireEvent(
											new RoleEvent.GotoRead(
													RoleListViewImpl.this, role
															.getId()));
						}
					}
				});

		this.listLayout.addComponent(this.constructTableActionControls());
		this.listLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<RoleSearchCriteria> getSearchHandlers() {
		return this.searchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		final CssLayout layoutWrapper = new CssLayout();
		layoutWrapper.setWidth("100%");
		final HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);
		layoutWrapper.addStyleName(UIConstants.TABLE_ACTION_CONTROLS);
		layoutWrapper.addComponent(layout);

		this.selectOptionButton = new SelectionOptionButton(this.tableItem);
		layout.addComponent(this.selectOptionButton);

		final Button deleteBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_DELETE));
		deleteBtn.setEnabled(AppContext
				.canAccess(RolePermissionCollections.ACCOUNT_ROLE));

		this.tableActionControls = new PopupButtonControl(
				MassItemActionHandler.DELETE_ACTION, deleteBtn);
		this.tableActionControls.addOptionItem(
				MassItemActionHandler.MAIL_ACTION,
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_MAIL));
		this.tableActionControls
				.addOptionItem(MassItemActionHandler.EXPORT_CSV_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_CSV));
		this.tableActionControls
				.addOptionItem(MassItemActionHandler.EXPORT_PDF_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_PDF));
		this.tableActionControls.addOptionItem(
				MassItemActionHandler.EXPORT_EXCEL_ACTION, LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_EXPORT_EXCEL));

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layoutWrapper;
	}

	@Override
	public void enableActionControls(final int numOfSelectedItems) {
		this.tableActionControls.setVisible(true);
		this.selectedItemsNumberLabel.setValue("Selected: "
				+ numOfSelectedItems);
	}

	@Override
	public void disableActionControls() {
		this.tableActionControls.setVisible(false);
		this.selectOptionButton.setSelectedChecbox(false);
		this.selectedItemsNumberLabel.setValue("");
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return this.selectOptionButton;
	}

	@Override
	public HasMassItemActionHandlers getPopupActionHandlers() {
		return this.tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleRole> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<RoleSearchCriteria, SimpleRole> getPagedBeanTable() {
		return this.tableItem;
	}
}
