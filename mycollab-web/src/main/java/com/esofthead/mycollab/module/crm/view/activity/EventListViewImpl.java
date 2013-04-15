package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.hene.splitbutton.PopupButtonControl;

import com.esofthead.mycollab.module.crm.domain.SimpleEvent;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.localization.CrmCommonI18nEnum;
import com.esofthead.mycollab.module.crm.localization.TaskI18nEnum;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPopupActionHandlers;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectionOptionHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.SelectionOptionButton;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.table.IPagedBeanTable;
import com.esofthead.mycollab.vaadin.ui.table.TableClickEvent;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class EventListViewImpl extends AbstractView implements EventListView {

	private static final long serialVersionUID = 1L;
	private final EventSearchPanel eventSearchPanel;
	private SelectionOptionButton selectOptionButton;
	private EventTableDisplay tableItem;
	private final VerticalLayout eventListLayout;
	private PopupButtonControl tableActionControls;
	private final Label selectedItemsNumberLabel = new Label();

	public EventListViewImpl() {
		this.setSpacing(true);

		eventSearchPanel = new EventSearchPanel();
		this.addComponent(eventSearchPanel);

		eventListLayout = new VerticalLayout();
		eventListLayout.setSpacing(true);
		this.addComponent(eventListLayout);

		generateDisplayTable();
	}

	@SuppressWarnings("serial")
	private void generateDisplayTable() {

		if (ScreenSize.hasSupport1024Pixels()) {
			tableItem = new EventTableDisplay(
					new String[] { "selected", "status", "eventType",
							"subject", "endDate" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_TYPE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_END_DATE_HEADER) });
		} else if (ScreenSize.hasSupport1280Pixels()) {
			tableItem = new EventTableDisplay(
					new String[] { "selected", "status", "eventType",
							"subject", "startDate", "endDate" },
					new String[] {
							"",
							LocalizationHelper
									.getMessage(CrmCommonI18nEnum.TABLE_STATUS_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_TYPE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_SUBJECT_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_START_DATE_HEADER),
							LocalizationHelper
									.getMessage(TaskI18nEnum.TABLE_END_DATE_HEADER) });
		}

		tableItem
				.addTableListener(new ApplicationEventListener<TableClickEvent>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TableClickEvent.class;
					}

					@Override
					public void handle(TableClickEvent event) {
						SimpleEvent simpleEvent = (SimpleEvent) event.getData();
						if ("Task".equals(simpleEvent.getEventType())) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskRead(this,
											simpleEvent.getId()));
						} else if ("Meeting".equals(simpleEvent.getEventType())) {
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

		eventListLayout.addComponent(constructTableActionControls());
		eventListLayout.addComponent(tableItem);
	}

	@Override
	public HasSearchHandlers<EventSearchCriteria> getSearchHandlers() {
		return eventSearchPanel;
	}

	private ComponentContainer constructTableActionControls() {
		HorizontalLayout layout = new HorizontalLayout();
		layout.setSpacing(true);

		selectOptionButton = new SelectionOptionButton(tableItem);
		layout.addComponent(selectOptionButton);

		tableActionControls = new PopupButtonControl("delete",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_DELETE));
		tableActionControls.addOptionItem("mail",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_MAIL));
		tableActionControls.addOptionItem("export",
				LocalizationHelper.getMessage(CrmCommonI18nEnum.BUTTON_EXPORT));
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
		selectedItemsNumberLabel.setValue(LocalizationHelper
				.getMessage(CrmCommonI18nEnum.TABLE_SELECTED_ITEM_TITLE,
						numOfSelectedItems));
	}

	@Override
	public void disableActionControls() {
		tableActionControls.setVisible(false);
		selectedItemsNumberLabel.setValue("");
		selectOptionButton.setSelectedChecbox(false);
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
	public HasSelectableItemHandlers<SimpleEvent> getSelectableItemHandlers() {
		return tableItem;
	}

	@Override
	public IPagedBeanTable<EventSearchCriteria, SimpleEvent> getPagedBeanTable() {
		return tableItem;
	}
}
