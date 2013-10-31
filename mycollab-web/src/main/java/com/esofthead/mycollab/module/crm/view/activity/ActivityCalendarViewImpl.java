package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.view.activity.ActivityEventProvider.CrmEvent;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.BackwardEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.BackwardHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventMoveHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventResize;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventResizeHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.MoveEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.WeekClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.WeekClickHandler;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@ViewComponent
public class ActivityCalendarViewImpl extends AbstractView implements
		ActivityCalendarView {

	private static final long serialVersionUID = 1L;
	private final PopupButton calendarActionBtn;

	public ActivityCalendarViewImpl() {
		super();

		this.setStyleName("activityCalendar");

		MenuActionListener listener = new MenuActionListener();

		calendarActionBtn = new PopupButton("Create");

		VerticalLayout actionBtnLayout = new VerticalLayout();
		actionBtnLayout.setMargin(true);
		actionBtnLayout.setSpacing(true);
		actionBtnLayout.setWidth("200px");

		ButtonLink todoBtn = new ButtonLink("Create Todo", listener);
		actionBtnLayout.addComponent(todoBtn);
		todoBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_TASK));

		Button callBtn = new ButtonLink("Create Call", listener);
		actionBtnLayout.addComponent(callBtn);
		callBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CALL));

		ButtonLink meetingBtn = new ButtonLink("Create Event", listener);
		actionBtnLayout.addComponent(meetingBtn);
		meetingBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_MEETING));

		calendarActionBtn.addComponent(actionBtnLayout);

		HorizontalLayout actionPanel = new HorizontalLayout();
		actionPanel.setWidth("100%");
		actionPanel.setStyleName("actionPanel");
		actionPanel.addComponent(calendarActionBtn);

		this.addComponent(actionPanel);

		Calendar calendar = new Calendar(new ActivityEventProvider());
		calendar.setHandler(new CalendarComponentEvents.EventClickHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void eventClick(EventClick event) {
				CrmEvent calendarEvent = (CrmEvent) event.getCalendarEvent();
				SimpleMeeting source = calendarEvent.getSource();
				EventBus.getInstance().fireEvent(
						new ActivityEvent.MeetingRead(
								ActivityCalendarViewImpl.this, source.getId()));
			}
		});
		calendar.setHandler(new DateClickHandler() {

			@Override
			public void dateClick(DateClickEvent event) {
				ActivityCalendarViewImpl.this.getWindow().showNotification(
						"date click");
			}
		});

		calendar.setHandler(new WeekClickHandler() {

			@Override
			public void weekClick(WeekClick event) {
				ActivityCalendarViewImpl.this.getWindow().showNotification(
						"weeken evetn");

			}
		});

		calendar.setHandler(new EventMoveHandler() {

			@Override
			public void eventMove(MoveEvent event) {
				ActivityCalendarViewImpl.this.getWindow().showNotification(
						"Event move");
			}
		});

		calendar.setHandler(new EventResizeHandler() {

			@Override
			public void eventResize(EventResize event) {
				ActivityCalendarViewImpl.this.getWindow().showNotification(
						"Event resize");
			}
		});

		calendar.setHandler(new BackwardHandler() {

			@Override
			public void backward(BackwardEvent event) {
				ActivityCalendarViewImpl.this.getWindow().showNotification(
						"backwardHandler");
			}
		});

		calendar.setHandler(new RangeSelectHandler() {

			@Override
			public void rangeSelect(RangeSelectEvent event) {
				ActivityCalendarViewImpl.this.getWindow().showNotification(
						"ragechoose");
			}
		});

		this.addComponent(calendar);
		this.setComponentAlignment(calendar, Alignment.MIDDLE_CENTER);
	}

	private class MenuActionListener implements Button.ClickListener {

		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			calendarActionBtn.setPopupVisible(false);
			String caption = event.getButton().getCaption();
			if (caption.equals("Create Todo")) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.TaskAdd(this, null));
			} else if (caption.equals("Create Call")) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.CallAdd(this, null));
			} else if (caption.equals("Create Event")) {
				EventBus.getInstance().fireEvent(
						new ActivityEvent.MeetingAdd(this, null));
			}
		}
	}
}
