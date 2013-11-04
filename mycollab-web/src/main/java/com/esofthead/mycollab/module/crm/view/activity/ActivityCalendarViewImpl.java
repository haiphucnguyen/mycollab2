package com.esofthead.mycollab.module.crm.view.activity;

import java.text.DateFormatSymbols;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.peter.buttongroup.ButtonGroup;

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
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventMoveHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventResize;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventResizeHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.MoveEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.WeekClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.WeekClickHandler;
import com.vaadin.addon.calendar.ui.handler.BasicDateClickHandler;
import com.vaadin.addon.calendar.ui.handler.BasicWeekClickHandler;
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
	private MonthViewCalendar calendarComponent;
	private ButtonGroup groupViewBtn;

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

		groupViewBtn = new ButtonGroup();
		Button monthViewBtn = new Button("Monthly View",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						calendarComponent.switchToMonthView();
					}
				});
		groupViewBtn.addButton(monthViewBtn);
		Button weekViewBtn = new Button("Weekly View",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						WeekClickHandler handler = (WeekClickHandler) calendarComponent
								.getHandler(WeekClick.EVENT_ID);
						handler.weekClick(new WeekClick(calendarComponent,
								calendarComponent.getCalendar().get(
										GregorianCalendar.WEEK_OF_YEAR),
								calendarComponent.getCalendar().get(
										GregorianCalendar.YEAR)));
					}
				});
		groupViewBtn.addButton(weekViewBtn);
		Button dailyViewBtn = new Button("Daily View",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						// TODO Auto-generated method stub

					}
				});
		groupViewBtn.addButton(dailyViewBtn);

		actionPanel.addComponent(groupViewBtn);
		actionPanel.setComponentAlignment(groupViewBtn, Alignment.MIDDLE_RIGHT);

		calendarComponent = new MonthViewCalendar();
		this.addComponent(calendarComponent);
		this.setComponentAlignment(calendarComponent, Alignment.MIDDLE_CENTER);

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
			this.meeting.setStartdate(startDate);
			this.meeting.setEnddate(endDate);
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

	public enum Mode {
		MONTH, WEEK, DAY;
	}

	public class MonthViewCalendar extends Calendar {
		private static final long serialVersionUID = 1L;

		GregorianCalendar calendar = new GregorianCalendar();

		private Date currentMonthsFirstDate = null;

		private Mode viewMode = Mode.MONTH;
		private Label label = new Label("");

		public MonthViewCalendar() {
			super(new ActivityEventProvider());
			this.setTimeFormat(TimeFormat.Format24H);
			this.setSizeFull();
			this.setImmediate(true);

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

			this.setHandler(new BasicDateClickHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void dateClick(DateClickEvent event) {
					super.dateClick(event);
				}
			});
			this.setHandler(new EventClickHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void eventClick(EventClick event) {
					CrmEvent calendarEvent = (CrmEvent) event
							.getCalendarEvent();
					handleClickEvent(calendarEvent);
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

			this.setHandler(new BasicWeekClickHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void weekClick(WeekClick event) {
					super.weekClick(event);
					switchToWeekView();
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

		private void handleClickEvent(CalendarEvent event) {
			if (event == null) {
				return;
			}
			SimpleMeeting source = ((CrmEvent) event).getSource();
			EventBus.getInstance().fireEvent(
					new ActivityEvent.MeetingRead(
							ActivityCalendarViewImpl.this, source.getId()));
		}

		public GregorianCalendar getCalendar() {
			return calendar;
		}

		private void switchToWeekView() {
			viewMode = Mode.WEEK;
		}

		private void switchToMonthView() {
			viewMode = Mode.MONTH;
			calendar.setTime(currentMonthsFirstDate);
			this.setStartDate(currentMonthsFirstDate);

			calendar.add(GregorianCalendar.MONTH, 1);
			calendar.add(GregorianCalendar.DATE, -1);
			resetCalendarTime(true);
		}

		private void resetCalendarTime(boolean resetEndTime) {
			resetTime(resetEndTime);
			if (resetEndTime) {
				this.setEndDate(calendar.getTime());
			} else {
				this.setStartDate(calendar.getTime());
			}
		}

		private void resetTime(boolean max) {
			if (max) {
				calendar.set(GregorianCalendar.HOUR_OF_DAY,
						calendar.getMaximum(GregorianCalendar.HOUR_OF_DAY));
				calendar.set(GregorianCalendar.MINUTE,
						calendar.getMaximum(GregorianCalendar.MINUTE));
				calendar.set(GregorianCalendar.SECOND,
						calendar.getMaximum(GregorianCalendar.SECOND));
				calendar.set(GregorianCalendar.MILLISECOND,
						calendar.getMaximum(GregorianCalendar.MILLISECOND));
			} else {
				calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
				calendar.set(GregorianCalendar.MINUTE, 0);
				calendar.set(GregorianCalendar.SECOND, 0);
				calendar.set(GregorianCalendar.MILLISECOND, 0);
			}
		}
	}
}
