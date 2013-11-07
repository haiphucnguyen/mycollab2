package com.esofthead.mycollab.module.crm.view.activity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.vaadin.addon.customfield.CustomField;
import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
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
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickHandler;
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
	private Button monthViewBtn;

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
		monthViewBtn = new Button("Monthly View", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				calendarComponent.switchToMonthView();
				monthViewBtn.addStyleName("selected-style");
			}
		});

		groupViewBtn.addButton(monthViewBtn);
		Button weekViewBtn = new Button("Weekly View",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						java.util.Calendar cal = java.util.Calendar
								.getInstance();
						int week = cal.get(java.util.Calendar.WEEK_OF_YEAR);

						WeekClickHandler handler = (WeekClickHandler) calendarComponent
								.getHandler(WeekClick.EVENT_ID);
						handler.weekClick(new WeekClick(calendarComponent,
								week, calendarComponent.getCalendar().get(
										GregorianCalendar.YEAR)));
					}
				});
		groupViewBtn.addButton(weekViewBtn);
		Button dailyViewBtn = new Button("Daily View",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						DateClickHandler handler = (DateClickHandler) calendarComponent
								.getHandler(DateClickEvent.EVENT_ID);
						handler.dateClick(new DateClickEvent(calendarComponent,
								java.util.Calendar.getInstance().getTime()));
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

		HorizontalLayout noteWapper = new HorizontalLayout();
		noteWapper.setHeight("30px");
		Label noteLbl = new Label("Note:");
		noteWapper.addComponent(noteLbl);
		noteWapper.setComponentAlignment(noteLbl, Alignment.MIDDLE_CENTER);
		noteInfoLayout.addComponent(noteWapper);

		HorizontalLayout completeWapper = new HorizontalLayout();
		completeWapper.setWidth("100px");
		completeWapper.setHeight("30px");
		completeWapper.addStyleName("eventLblcompleted");
		Label completeLabel = new Label("Completed");
		completeWapper.addComponent(completeLabel);
		completeWapper.setComponentAlignment(completeLabel,
				Alignment.MIDDLE_CENTER);
		noteInfoLayout.addComponent(completeWapper);

		HorizontalLayout overdueWapper = new HorizontalLayout();
		overdueWapper.setWidth("100px");
		overdueWapper.setHeight("30px");
		overdueWapper.addStyleName("eventLbloverdue");
		Label overdueLabel = new Label("Overdue");
		overdueWapper.addComponent(overdueLabel);
		overdueWapper.setComponentAlignment(overdueLabel,
				Alignment.MIDDLE_CENTER);
		noteInfoLayout.addComponent(overdueWapper);

		HorizontalLayout futureWapper = new HorizontalLayout();
		futureWapper.setWidth("100px");
		futureWapper.setHeight("30px");
		futureWapper.addStyleName("eventLblfuture");
		Label futureLabel = new Label("Future");
		futureWapper.addComponent(futureLabel);
		futureWapper
				.setComponentAlignment(futureLabel, Alignment.MIDDLE_CENTER);
		noteInfoLayout.addComponent(futureWapper);

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
		private EditForm editForm;
		private Meeting meeting;
		private EventDatePicker startDatePicker;
		private EventDatePicker endDatePicker;

		public EventQuickCreateWindow(Date startDate, Date endDate) {
			super("Quick Create Event");
			this.startDatePicker = new EventDatePicker(startDate, meeting, true);
			this.endDatePicker = new EventDatePicker(endDate, meeting, false);
			this.center();
			this.setWidth("1000px");

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
									meeting.setStartdate(startDatePicker
											.getValue());
									meeting.setEnddate(endDatePicker.getValue());
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
						return startDatePicker;
					} else if (propertyId.equals("enddate")) {
						return endDatePicker;
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

	public static class EventDatePicker extends CustomField {
		private static final long serialVersionUID = 1L;

		private PopupDateField popupDateField;
		private HourPickerComboBox hourPickerComboBox;
		private MinusPickerComboBox minusPickerComboBox;
		private ValueComboBox timeFormatComboBox;
		public static final long ONE_MINUTE_IN_MILLIS = 60000;

		public EventDatePicker(final Meeting meeting, final boolean isStartDate) {
			this(null, meeting, isStartDate);
		}

		public EventDatePicker(Date date, final Meeting meeting,
				final boolean isStartDate) {
			HorizontalLayout layout = new HorizontalLayout();
			layout.setMargin(true);
			layout.setSpacing(true);
			long min = 0, hrs = 0;
			String timeFormat = "AM";
			if (date != null) {
				java.util.Calendar cal = java.util.Calendar.getInstance();
				cal.setTime(date);
				min = cal.get(java.util.Calendar.MINUTE);
				hrs = cal.get(java.util.Calendar.HOUR);
				timeFormat = (cal.get(java.util.Calendar.AM_PM) == 0) ? "AM"
						: "PM";
			}
			popupDateField = new PopupDateField(null, date);
			popupDateField.setResolution(PopupDateField.RESOLUTION_DAY);
			popupDateField.setWidth("130px");
			popupDateField.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					if (isStartDate) {
						meeting.setStartdate(EventDatePicker.this.getValue());
					} else {
						meeting.setEnddate(EventDatePicker.this.getValue());
					}
				}
			});
			layout.addComponent(popupDateField);

			hourPickerComboBox = new HourPickerComboBox();
			hourPickerComboBox.setValue((hrs < 10) ? "0" + hrs : "" + hrs);
			hourPickerComboBox.setWidth("50px");
			hourPickerComboBox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					if (isStartDate) {
						meeting.setStartdate(EventDatePicker.this.getValue());
					} else {
						meeting.setEnddate(EventDatePicker.this.getValue());
					}
				}
			});
			layout.addComponent(hourPickerComboBox);
			minusPickerComboBox = new MinusPickerComboBox();
			minusPickerComboBox.setWidth("50px");
			minusPickerComboBox.setValue((min < 10) ? "0" + min : "" + min);
			minusPickerComboBox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					if (isStartDate) {
						meeting.setStartdate(EventDatePicker.this.getValue());
					} else {
						meeting.setEnddate(EventDatePicker.this.getValue());
					}
				}
			});
			layout.addComponent(minusPickerComboBox);

			timeFormatComboBox = new ValueComboBox();
			timeFormatComboBox.setWidth("50px");
			timeFormatComboBox.setCaption(null);
			timeFormatComboBox.loadData(new String[] { "AM", "PM" });
			timeFormatComboBox.setNullSelectionAllowed(false);
			timeFormatComboBox.setImmediate(true);
			timeFormatComboBox.setValue(timeFormat);
			timeFormatComboBox.addListener(new ValueChangeListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void valueChange(
						com.vaadin.data.Property.ValueChangeEvent event) {
					if (isStartDate) {
						meeting.setStartdate(EventDatePicker.this.getValue());
					} else {
						meeting.setEnddate(EventDatePicker.this.getValue());
					}
				}
			});
			layout.addComponent(timeFormatComboBox);
			this.setCompositionRoot(layout);
		}

		private long calculateMiniSecond(Integer hour, Integer minus,
				String timeFormat) {
			long allMinus = 0;
			if (timeFormat.equals("AM")) {
				allMinus = (((hour == 12) ? 0 : hour) * 60 + minus)
						* ONE_MINUTE_IN_MILLIS;
			} else if (timeFormat.equals("PM")) {
				allMinus = (((hour == 12) ? 12 : hour + 12) * 60 + minus)
						* ONE_MINUTE_IN_MILLIS;
			}
			return allMinus;
		}

		@Override
		public Class<Date> getType() {
			return Date.class;
		}

		@Override
		public Date getValue() {
			if (popupDateField.getValue() == null) {
				return null;
			}
			Date baseDate = DateTimeUtils.convertDate((Date) popupDateField
					.getValue());
			Integer hour = Integer.parseInt((String) hourPickerComboBox
					.getValue());
			Integer minus = Integer.parseInt((String) minusPickerComboBox
					.getValue());
			String timeFormat = (String) timeFormatComboBox.getValue();
			long milliseconds = calculateMiniSecond(hour, minus, timeFormat);
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTimeInMillis(baseDate.getTime() + milliseconds);
			return cal.getTime();
		}

		private class HourPickerComboBox extends ValueComboBox {
			private static final long serialVersionUID = 1L;
			private final String[] HOURDATA = new String[] { "12", "01", "02",
					"03", "04", "05", "06", "07", "08", "09", "10", "11" };

			public HourPickerComboBox() {
				super();
				setCaption(null);
				this.loadData(HOURDATA);
			}
		}

		private class MinusPickerComboBox extends ValueComboBox {
			private static final long serialVersionUID = 1L;
			private String[] MINUSDATA = new String[] {};

			public MinusPickerComboBox() {
				super();
				setCaption(null);
				List<String> lst = new ArrayList<String>();
				for (int i = 0; i <= 60; i++) {
					String str = (i < 10) ? "0" + i : "" + i;
					lst.add(str);
				}
				this.loadData(lst.toArray(MINUSDATA));
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

		public MonthViewCalendar() {
			super(new ActivityEventProvider());
			this.setTimeFormat(TimeFormat.Format12H);
			this.setSizeFull();
			this.setImmediate(true);
			this.setLocale(Locale.US);

			Date today = new Date();
			calendar.setTime(today);
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
					if (crmEvent.getStart().compareTo(event.getNewStart()) != 0) {
						SimpleMeeting simpleMeeting = crmEvent.getSource();

						Date newStartDate = event.getNewStart();
						long rangeOfStartEnd = crmEvent.getEnd().getTime()
								- crmEvent.getStart().getTime();
						long newEndDateTime;
						if (crmEvent.getStart().compareTo(newStartDate) > 0) {
							newEndDateTime = newStartDate.getTime()
									+ rangeOfStartEnd;
						} else {
							newEndDateTime = newStartDate.getTime()
									- rangeOfStartEnd;
						}
						java.util.Calendar calendar = java.util.Calendar
								.getInstance();
						calendar.setTimeInMillis(newEndDateTime);

						simpleMeeting.setStartdate(newStartDate);
						simpleMeeting.setEnddate(calendar.getTime());

						MeetingService service = ApplicationContextUtil
								.getSpringBean(MeetingService.class);
						service.updateWithSession(simpleMeeting,
								AppContext.getUsername());
						ActivityCalendarViewImpl.this.getWindow()
								.showNotification(
										"Event: \""
												+ simpleMeeting.getSubject()
												+ "\" has been updated!");
						EventBus.getInstance().fireEvent(
								new ActivityEvent.GotoCalendar(this, null));
					}
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
