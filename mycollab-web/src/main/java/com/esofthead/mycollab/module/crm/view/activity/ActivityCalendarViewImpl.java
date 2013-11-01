package com.esofthead.mycollab.module.crm.view.activity;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedEditItemField;
import com.esofthead.mycollab.module.crm.view.activity.ActivityEventProvider.CrmEvent;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.addon.calendar.event.BasicEvent;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.event.CalendarEventProvider;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventMoveHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventResize;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventResizeHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.MoveEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectHandler;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@ViewComponent
public class ActivityCalendarViewImpl extends AbstractView implements
		ActivityCalendarView {

	private static final long serialVersionUID = 1L;
	private final PopupButton calendarActionBtn;
	private MonthViewCalendar monthViewcalendar;
	private WeekViewCalendar weekViewCalendar;
	private ButtonLink switchViewCalendarBtn;

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
		actionPanel.setSpacing(true);
		actionPanel.setStyleName("actionPanel");
		actionPanel.addComponent(calendarActionBtn);
		actionPanel.setComponentAlignment(calendarActionBtn,
				Alignment.MIDDLE_LEFT);
		this.addComponent(actionPanel);

		switchViewCalendarBtn = new ButtonLink("Monthly View",
				new ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (switchViewCalendarBtn.getCaption().equals(
								"Weekly View")) {
							switchViewCalendarBtn.setCaption("Monthly View");
							ActivityCalendarViewImpl.this
									.removeComponent(monthViewcalendar);
							if (weekViewCalendar == null) {
								weekViewCalendar = new WeekViewCalendar();
							}
							ActivityCalendarViewImpl.this.addComponent(
									weekViewCalendar, 1);
							ActivityCalendarViewImpl.this
									.setComponentAlignment(weekViewCalendar,
											Alignment.MIDDLE_CENTER);
						} else {
							switchViewCalendarBtn.setCaption("Weekly View");
							ActivityCalendarViewImpl.this
									.removeComponent(weekViewCalendar);
							if (monthViewcalendar == null) {
								monthViewcalendar = new MonthViewCalendar();
							}
							ActivityCalendarViewImpl.this.addComponent(
									monthViewcalendar, 1);
							ActivityCalendarViewImpl.this
									.setComponentAlignment(monthViewcalendar,
											Alignment.MIDDLE_CENTER);
						}
					}
				});
		actionPanel.addComponent(switchViewCalendarBtn);
		actionPanel.setComponentAlignment(switchViewCalendarBtn,
				Alignment.MIDDLE_LEFT);

		weekViewCalendar = new WeekViewCalendar();
		this.addComponent(weekViewCalendar);
		this.setComponentAlignment(weekViewCalendar, Alignment.MIDDLE_CENTER);

		HorizontalLayout spacing = new HorizontalLayout();
		spacing.setHeight("30px");
		this.addComponent(spacing);

		HorizontalLayout noteInfoLayout = new HorizontalLayout();
		noteInfoLayout.setSpacing(true);
		noteInfoLayout.addComponent(new Label("Note:"));
		Label completeLabel = new Label("Completed");
		completeLabel.setWidth("100px");
		completeLabel.setHeight("20px");
		completeLabel.addStyleName("eventLblcompleted");
		noteInfoLayout.addComponent(completeLabel);

		Label overdueLabel = new Label("Overdue");
		overdueLabel.setWidth("100px");
		overdueLabel.setHeight("20px");
		overdueLabel.addStyleName("eventLbloverdue");
		noteInfoLayout.addComponent(overdueLabel);

		Label futureLabel = new Label("Future");
		futureLabel.setWidth("100px");
		futureLabel.setHeight("20px");
		futureLabel.addStyleName("eventLblfuture");
		noteInfoLayout.addComponent(futureLabel);

		this.addComponent(noteInfoLayout);
		this.setComponentAlignment(noteInfoLayout, Alignment.MIDDLE_CENTER);
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

	public class EventQuickCreateWindow extends Window {
		private static final long serialVersionUID = 1L;
		private Date startDate;
		private Date endDate;
		private EditForm editForm;
		private Meeting meeting;

		public EventQuickCreateWindow(Date startDate, Date endDate) {
			super("Quick Create Event");
			this.startDate = startDate;
			this.endDate = endDate;
			this.center();
			this.setWidth("950px");

			this.meeting = new Meeting();
			this.meeting.setSaccountid(AppContext.getAccountId());
			editForm = new EditForm();
			editForm.setItemDataSource(new BeanItem<Meeting>(meeting));
			this.addComponent(editForm);
		}

		private class EditForm extends AdvancedEditBeanForm<Meeting> {

			private static final long serialVersionUID = 1L;

			@Override
			public void setItemDataSource(Item newDataSource,
					Collection<?> propertyIds) {
				this.setFormLayoutFactory(new FormLayoutFactory());
				this.setFormFieldFactory(new EditFormFieldFactory());
				super.setItemDataSource(newDataSource, propertyIds);
			}

			private class FormLayoutFactory extends MeetingFormLayoutFactory {

				private static final long serialVersionUID = 1L;

				public FormLayoutFactory() {
					super((meeting.getId() == null) ? "Create Event" : meeting
							.getSubject());
				}

				private Layout createButtonControls() {
					final HorizontalLayout controlPanel = new HorizontalLayout();
					controlPanel.setWidth("100%");

					final HorizontalLayout layout = new HorizontalLayout();
					layout.setMargin(true);
					layout.setSpacing(true);
					layout.setStyleName("addNewControl");
					Button saveBtn = new Button("Save",
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									MeetingService meetingService = ApplicationContextUtil
											.getSpringBean(MeetingService.class);
									meetingService.saveWithSession(meeting,
											AppContext.getUsername());
									EventQuickCreateWindow.this.close();
									EventBus.getInstance().fireEvent(
											new ActivityEvent.GotoCalendar(
													this, null));
								}
							});
					saveBtn.setIcon(MyCollabResource
							.newResource("icons/16/save.png"));
					saveBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
					layout.addComponent(saveBtn);
					layout.setComponentAlignment(saveBtn,
							Alignment.MIDDLE_CENTER);
					Button cancelBtn = new Button("Cancel",
							new ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(ClickEvent event) {
									EventQuickCreateWindow.this.close();
								}
							});
					cancelBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
					cancelBtn.setIcon(MyCollabResource
							.newResource("icons/16/cancel.png"));
					layout.addComponent(cancelBtn);
					layout.setComponentAlignment(cancelBtn,
							Alignment.MIDDLE_CENTER);
					controlPanel.addComponent(layout);
					controlPanel.setComponentAlignment(layout,
							Alignment.MIDDLE_CENTER);
					return controlPanel;
				}

				@Override
				protected Layout createTopPanel() {
					return createButtonControls();
				}

				@Override
				protected Layout createBottomPanel() {
					return null;
				}
			}

			private class EditFormFieldFactory extends
					DefaultEditFormFieldFactory {

				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						com.vaadin.ui.Component uiContext) {
					if (propertyId.equals("subject")) {
						TextField tf = new TextField();
						tf.setNullRepresentation("");
						tf.setRequired(true);
						tf.setRequiredError("Subject must not be null");
						return tf;
					} else if (propertyId.equals("status")) {
						return new MeetingStatusComboBox();
					} else if (propertyId.equals("startdate")) {
						return new PopupDateField("", startDate);
					} else if (propertyId.equals("enddate")) {
						return new PopupDateField("", endDate);
					} else if (propertyId.equals("description")) {
						TextArea descArea = new TextArea();
						descArea.setNullRepresentation("");
						return descArea;
					} else if (propertyId.equals("type")) {
						RelatedEditItemField field = new RelatedEditItemField(
								new String[] { CrmTypeConstants.ACCOUNT,
										CrmTypeConstants.CAMPAIGN,
										CrmTypeConstants.CONTACT,
										CrmTypeConstants.LEAD,
										CrmTypeConstants.OPPORTUNITY,
										CrmTypeConstants.CASE }, meeting);
						field.setType(meeting.getType());
						return field;
					} else if (propertyId.equals("isrecurrence")) {
					}
					return null;
				}
			}

			private class MeetingStatusComboBox extends ValueComboBox {

				private static final long serialVersionUID = 1L;

				public MeetingStatusComboBox() {
					super();
					setCaption(null);
					this.loadData(new String[] { "Planned", "Held", "Not Held" });
				}
			}
		}
	}

	public class WeekViewCalendar extends Calendar {
		private static final long serialVersionUID = 1L;

		public WeekViewCalendar() {
			super(new ActivityEventProvider());
			this.setHandler(new CalendarComponentEvents.EventClickHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void eventClick(EventClick event) {
					CrmEvent calendarEvent = (CrmEvent) event
							.getCalendarEvent();
					SimpleMeeting source = calendarEvent.getSource();
					EventBus.getInstance().fireEvent(
							new ActivityEvent.MeetingRead(
									ActivityCalendarViewImpl.this, source
											.getId()));
				}
			});

			this.setHandler(new DateClickHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void dateClick(DateClickEvent event) {
					// do nothing
				}
			});

			this.setHandler(new RangeSelectHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void rangeSelect(RangeSelectEvent event) {
					ActivityCalendarViewImpl.this.getWindow().addWindow(
							new EventQuickCreateWindow(event.getStart(), event
									.getEnd()));
				}
			});

			this.setHandler(new EventResizeHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void eventResize(EventResize event) {
					CrmEvent crmEvent = (CrmEvent) event.getCalendarEvent();
					SimpleMeeting simpleMeeting = crmEvent.getSource();
					simpleMeeting.setStartdate(event.getNewStartTime());
					simpleMeeting.setEnddate(event.getNewEndTime());
					MeetingService service = ApplicationContextUtil
							.getSpringBean(MeetingService.class);
					service.updateWithSession(simpleMeeting,
							AppContext.getUsername());
					ActivityCalendarViewImpl.this.getWindow().showNotification(
							"Event: \"" + simpleMeeting.getSubject()
									+ "\" has been updated!");
				}
			});

			this.setHandler(new EventMoveHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void eventMove(MoveEvent event) {
					CrmEvent crmEvent = (CrmEvent) event.getCalendarEvent();
					SimpleMeeting simpleMeeting = crmEvent.getSource();
					simpleMeeting.setStartdate(event.getNewStart());

					MeetingService service = ApplicationContextUtil
							.getSpringBean(MeetingService.class);
					service.updateWithSession(simpleMeeting,
							AppContext.getUsername());
					ActivityCalendarViewImpl.this.getWindow().showNotification(
							"Event: \"" + simpleMeeting.getSubject()
									+ "\" has been updated!");
				}
			});
		}
	}

	public class MonthViewCalendar extends Calendar {
		private static final long serialVersionUID = 1L;

		GregorianCalendar calendar = new GregorianCalendar();

		private Date currentMonthsFirstDate = null;

		private Label label = new Label("");

		public MonthViewCalendar() {
			super(new ActivityEventProvider());
			this.setStyleName("calendartest");
			this.setTimeFormat(TimeFormat.Format24H);

			Date today = new Date();
			calendar.setTime(today);
			calendar.get(GregorianCalendar.MONTH);

			DateFormatSymbols s = new DateFormatSymbols();
			String month = s.getShortMonths()[calendar
					.get(GregorianCalendar.MONTH)];
			label.setValue(month + " " + calendar.get(GregorianCalendar.YEAR));
			int rollAmount = calendar.get(GregorianCalendar.DAY_OF_MONTH) - 1;
			calendar.add(GregorianCalendar.DAY_OF_MONTH, -rollAmount);
			currentMonthsFirstDate = calendar.getTime();
			this.setStartDate(currentMonthsFirstDate);
			calendar.add(GregorianCalendar.MONTH, 1);
			calendar.add(GregorianCalendar.DATE, -1);
			this.setEndDate(calendar.getTime());

			this.setHandler(new DateClickHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void dateClick(DateClickEvent event) {
					// do nothing
				}
			});
			this.setHandler(new EventClickHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void eventClick(EventClick event) {
					CrmEvent calendarEvent = (CrmEvent) event
							.getCalendarEvent();
					SimpleMeeting source = calendarEvent.getSource();
					EventBus.getInstance().fireEvent(
							new ActivityEvent.MeetingRead(
									ActivityCalendarViewImpl.this, source
											.getId()));
				}
			});

			this.setHandler(new EventMoveHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void eventMove(MoveEvent event) {
					CrmEvent crmEvent = (CrmEvent) event.getCalendarEvent();
					SimpleMeeting simpleMeeting = crmEvent.getSource();
					simpleMeeting.setStartdate(event.getNewStart());

					MeetingService service = ApplicationContextUtil
							.getSpringBean(MeetingService.class);
					service.updateWithSession(simpleMeeting,
							AppContext.getUsername());
					ActivityCalendarViewImpl.this.getWindow().showNotification(
							"Event: \"" + simpleMeeting.getSubject()
									+ "\" has been updated!");
					EventBus.getInstance().fireEvent(
							new ActivityEvent.GotoCalendar(this, null));
				}
			});
		}
	}

	public class CalendarMonthEventProvider implements CalendarEventProvider {

		private static final long serialVersionUID = -5436777475398410597L;

		GregorianCalendar calendar = new GregorianCalendar();

		public CalendarMonthEventProvider() {
		}

		public List<CalendarEvent> getEvents(Date fromStartDate, Date toEndDate) {
			return getEventsOverlappingForMonthlyTest(fromStartDate, toEndDate);
		}

		private List<CalendarEvent> getEventsOverlappingForMonthlyTest(
				Date fromStartDate, Date toEndDate) {
			calendar.setTime(fromStartDate);
			calendar.add(GregorianCalendar.DATE, 5);

			List<CalendarEvent> e = new ArrayList<CalendarEvent>();

			CalendarTestEvent event = getNewEvent("Phase1", fromStartDate,
					calendar.getTime());
			event.setDescription("asdgasdgj asdfg adfga fsdgafdsgasdga asdgadfsg");
			event.setStyleName("color1");
			e.add(event);

			calendar.add(GregorianCalendar.DATE, 3);
			Date d = calendar.getTime();
			calendar.add(GregorianCalendar.DATE, 3);
			Date d2 = calendar.getTime();
			event = getNewEvent("Phase2", d, d2);
			event.setStyleName("color2");
			e.add(event);

			calendar.add(GregorianCalendar.DATE, 1);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.DATE, 10);
			d2 = calendar.getTime();
			event = getNewEvent("Phase3", d, d2);
			event.setStyleName("color3");
			e.add(event);
			calendar.add(GregorianCalendar.DATE, -1);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.DATE, 3);
			d2 = calendar.getTime();
			event = getNewEvent("Phase4", d, d2);
			event.setStyleName("color4");
			e.add(event);

			calendar.add(GregorianCalendar.DATE, -1);
			calendar.add(GregorianCalendar.HOUR, -6);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.HOUR, 1);
			d2 = calendar.getTime();
			event = getNewEvent("Session 1", d, d2);
			e.add(event);

			calendar.add(GregorianCalendar.HOUR, 1);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.HOUR, 1);
			d2 = calendar.getTime();
			event = getNewEvent("Session 2", d, d2);
			event.setStyleName("color4");
			e.add(event);

			calendar.add(GregorianCalendar.MINUTE, 30);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.MINUTE, 30);
			d2 = calendar.getTime();
			event = getNewEvent("Session 3", d, d2);
			e.add(event);

			calendar.add(GregorianCalendar.MINUTE, 30);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.MINUTE, 30);
			d2 = calendar.getTime();
			event = getNewEvent("Session 4", d, d2);
			e.add(event);

			calendar.add(GregorianCalendar.MINUTE, 30);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.MINUTE, 30);
			d2 = calendar.getTime();
			event = getNewEvent("Session 5", d, d2);
			e.add(event);

			calendar.add(GregorianCalendar.MINUTE, 30);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.MINUTE, 30);
			d2 = calendar.getTime();
			event = getNewEvent("Session 6", d, d2);
			e.add(event);

			calendar.add(GregorianCalendar.MINUTE, 30);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.MINUTE, 30);
			d2 = calendar.getTime();
			event = getNewEvent("Session 7", d, d2);
			e.add(event);

			calendar.add(GregorianCalendar.HOUR, 1);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.HOUR, 1);
			d2 = calendar.getTime();
			event = getNewEvent("Session 8", d, d2);

			calendar.add(GregorianCalendar.HOUR, 1);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.HOUR, 1);
			d2 = calendar.getTime();
			event = getNewEvent("Session 9", d, d2);
			e.add(event);

			calendar.add(GregorianCalendar.HOUR, 1);
			d = calendar.getTime();
			calendar.add(GregorianCalendar.HOUR, 1);
			d2 = calendar.getTime();
			event = getNewEvent("Session 10", d, d2);
			e.add(event);
			e.add(event);
			return e;
		}

		private CalendarTestEvent getNewEvent(String caption, Date start,
				Date end) {
			CalendarTestEvent event = new CalendarTestEvent();
			event.setCaption(caption);
			event.setStart(start);
			event.setEnd(end);

			return event;
		}

		public class CalendarTestEvent extends BasicEvent {

			private static final long serialVersionUID = 2820133201983036866L;
			private String where;
			private Object data;

			public String getWhere() {
				return where;
			}

			public void setWhere(String where) {
				this.where = where;
				fireEventChange();
			}

			public Object getData() {
				return data;
			}

			public void setData(Object data) {
				this.data = data;
				fireEventChange();
			}
		}
	}
}
