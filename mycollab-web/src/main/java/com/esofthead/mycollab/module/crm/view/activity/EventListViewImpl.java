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
package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Arrays;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.TaskI18nEnum;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.events.TablePopupActionHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.AbstractPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.vaadin.ui.table.TableViewField;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class EventListViewImpl extends AbstractPageView implements EventListView {

	private static final long serialVersionUID = 1L;
	private final EventSearchPanel eventSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private EventTableDisplay tableItem;
	private final VerticalLayout eventListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public EventListViewImpl() {

		this.setMargin(true);
		this.eventSearchPanel = new EventSearchPanel();
		this.addComponent(this.eventSearchPanel);

		this.eventListLayout = new VerticalLayout();
		this.addComponent(this.eventListLayout);

		this.generateDisplayTable();
	}

	@SuppressWarnings("serial")
	private void generateDisplayTable() {

		this.tableItem = new EventTableDisplay(new TableViewField("",
				"selected", UIConstants.TABLE_CONTROL_WIDTH), Arrays.asList(
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
						"subject", UIConstants.TABLE_EX_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
						"status", UIConstants.TABLE_S_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_TYPE_HEADER),
						"eventType", UIConstants.TABLE_S_LABEL_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
						"startDate", UIConstants.TABLE_DATE_TIME_WIDTH),
				new TableViewField(LocalizationHelper
						.getMessage(TaskI18nEnum.TABLE_END_DATE_HEADER),
						"endDate", UIConstants.TABLE_DATE_TIME_WIDTH)));

		this.tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(final TableClickEvent event) {
						final SimpleEvent simpleEvent = (SimpleEvent) event
								.getData();
						if ("Task".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskRead(this,
											simpleEvent.getId()));
						} else if ("Event".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.MeetingRead(this,
											simpleEvent.getId()));
						} else if ("Call".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallRead(this,
											simpleEvent.getId()));
						}
					}
				});

		this.eventListLayout.addComponent(this.constructTableActionControls());
		this.eventListLayout.addComponent(this.tableItem);
	}

	@Override
	public HasSearchHandlers<EventSearchCriteria> getSearchHandlers() {
		return this.eventSearchPanel;
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
		boolean isDeleteEnable = AppContext
				.canAccess(RolePermissionCollections.CRM_CALL)
				|| AppContext.canAccess(RolePermissionCollections.CRM_MEETING)
				|| AppContext.canAccess(RolePermissionCollections.CRM_TASK);
		deleteBtn.setEnabled(isDeleteEnable);

		this.tableActionControls = new PopupButtonControl(
				TablePopupActionHandler.DELETE_ACTION, deleteBtn);
		this.tableActionControls.addOptionItem(TablePopupActionHandler.MAIL_ACTION,
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_MAIL));
		this.tableActionControls
				.addOptionItem(TablePopupActionHandler.EXPORT_CSV_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_CSV));
		this.tableActionControls
				.addOptionItem(TablePopupActionHandler.EXPORT_PDF_ACTION,
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_EXPORT_PDF));
		this.tableActionControls.addOptionItem(
				TablePopupActionHandler.EXPORT_EXCEL_ACTION, LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_EXPORT_EXCEL));
		this.tableActionControls.setVisible(false);

		layout.addComponent(this.tableActionControls);
		layout.addComponent(this.selectedItemsNumberLabel);
		layout.setComponentAlignment(this.selectedItemsNumberLabel,
				Alignment.MIDDLE_CENTER);
		return layoutWrapper;
	}

	@Override
	public void enableActionControls(final int numOfSelectedItems) {
		this.tableActionControls.setVisible(true);
		this.selectedItemsNumberLabel.setValue(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TABLE_SELECTED_ITEM_TITLE,
						numOfSelectedItems));
	}

	@Override
	public void disableActionControls() {
		this.tableActionControls.setVisible(false);
		this.selectedItemsNumberLabel.setValue("");
		this.selectOptionButton.setSelectedChecbox(false);
	}

	@Override
	public HasSelectionOptionHandlers getOptionSelectionHandlers() {
		return this.selectOptionButton;
	}

	@Override
	public HasPopupActionHandlers getPopupActionHandlers() {
		return this.tableActionControls;
	}

	@Override
	public HasSelectableItemHandlers<SimpleEvent> getSelectableItemHandlers() {
		return this.tableItem;
	}

	@Override
	public AbstractPagedBeanTable<EventSearchCriteria, SimpleEvent> getPagedBeanTable() {
		return this.tableItem;
	}
}
