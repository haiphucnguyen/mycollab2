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

import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.vaadin.hene.popupbutton.PopupButton;
import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.MeetingWithBLOBs;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.ui.components.RelatedEditItemField;
import com.esofthead.mycollab.module.crm.view.activity.ActivityEventProvider.CrmEvent;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.StyleCalendarExp;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ValueComboBox;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.BackwardEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClickHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventMoveHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventResize;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventResizeHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.ForwardEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.MoveEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.RangeSelectHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.WeekClick;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.WeekClickHandler;
import com.vaadin.addon.calendar.ui.handler.BasicBackwardHandler;
import com.vaadin.addon.calendar.ui.handler.BasicDateClickHandler;
import com.vaadin.addon.calendar.ui.handler.BasicForwardHandler;
import com.vaadin.addon.calendar.ui.handler.BasicWeekClickHandler;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
@ViewComponent
public class ActivityCalendarViewImpl extends AbstractPageView implements
ActivityCalendarView {

	private static final long serialVersionUID = 1L;
	private final PopupButton calendarActionBtn;
	private MonthViewCalendar calendarComponent;
	private PopupButton toggleViewBtn;
	private Button monthViewBtn;
	private Button weekViewBtn;
	private Button dailyViewBtn;
	private Label dateHdr;
	private final StyleCalendarExp datePicker = new StyleCalendarExp();

	public ActivityCalendarViewImpl() {
		super();

		this.addStyleName("activityCalendar");

		HorizontalLayout contentWrapper = new HorizontalLayout();
		contentWrapper.setWidth("100%");
		this.addComponent(contentWrapper);

		/* Content cheat */
		VerticalLayout mainContent = new VerticalLayout();
		mainContent.setStyleName("readview-layout");
		mainContent.setWidth("100%");
		mainContent.setMargin(new MarginInfo(false, true, true, true));
		mainContent.setSpacing(true);
		contentWrapper.addComponent(mainContent);
		contentWrapper.setExpandRatio(mainContent, 1.0f);

		VerticalLayout rightColumn = new VerticalLayout();
		rightColumn.setWidth("250px");
		rightColumn.setSpacing(true);
		rightColumn.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		rightColumn.setMargin(new MarginInfo(true, false, true, false));
		contentWrapper.addComponent(rightColumn);

		MenuActionListener listener = new MenuActionListener();

		calendarActionBtn = new PopupButton("Create");
		calendarActionBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
		calendarActionBtn.addStyleName("esofthead");

		HorizontalLayout actionPanel = new HorizontalLayout();
		actionPanel.setWidth("100%");
		actionPanel.setSpacing(true);
		actionPanel.setMargin(new MarginInfo(true, false, true, false));
		actionPanel.setStyleName(UIConstants.HEADER_VIEW);
		actionPanel.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		actionPanel.addComponent(new Image(null, MyCollabResource.newResource("icons/22/crm/event.png")));

		Label headerText = new Label("Activity Calendar");
		headerText.setStyleName(UIConstants.HEADER_TEXT);
		headerText.setSizeUndefined();
		actionPanel.addComponent(headerText);
		actionPanel.setExpandRatio(headerText, 1.0f);

		mainContent.addComponent(actionPanel);

		this.dateHdr = new Label();
		this.dateHdr.setSizeUndefined();
		this.dateHdr.setStyleName("h2");
		mainContent.addComponent(this.dateHdr);
		mainContent.setComponentAlignment(this.dateHdr, Alignment.MIDDLE_CENTER);

		toggleViewBtn = new PopupButton("Monthly");
		toggleViewBtn.setWidth("200px");
		toggleViewBtn.addStyleName("calendar-view-switcher");
		VerticalLayout popupLayout = new VerticalLayout();
		popupLayout.setSpacing(true);
		popupLayout.setMargin(new MarginInfo(false, true, false, true));
		popupLayout.setWidth("190px");

		monthViewBtn = new Button("Monthly", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				toggleViewBtn.setPopupVisible(false);
				toggleViewBtn.setCaption(monthViewBtn.getCaption());
				calendarComponent.switchToMonthView(new Date(), true);	
				datePicker.selectDate(new Date());			
				monthViewBtn.addStyleName("selected-style");
				initLabelCaption();
			}
		});
		monthViewBtn.setStyleName("link");
		popupLayout.addComponent(monthViewBtn);

		weekViewBtn = new Button("Weekly", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				toggleViewBtn.setPopupVisible(false);
				toggleViewBtn.setCaption(weekViewBtn.getCaption());
				calendarComponent.switchToWeekView(new Date());
				datePicker.selectWeek(new Date());				
			}
		});
		weekViewBtn.setStyleName("link");
		popupLayout.addComponent(weekViewBtn);

		dailyViewBtn = new Button("Daily", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				toggleViewBtn.setPopupVisible(false);
				toggleViewBtn.setCaption(dailyViewBtn.getCaption());
				calendarComponent.switchToDateView(new Date());
				datePicker.selectDate(new Date());
			}
		});
		dailyViewBtn.setStyleName("link");
		popupLayout.addComponent(dailyViewBtn);

		toggleViewBtn.setContent(popupLayout);
		CssLayout toggleBtnWrap = new CssLayout();
		toggleBtnWrap.setStyleName("switcher-wrap");
		toggleBtnWrap.addComponent(toggleViewBtn);

		rightColumn.addComponent(toggleBtnWrap);
		rightColumn.setComponentAlignment(toggleBtnWrap, Alignment.MIDDLE_CENTER);

		rightColumn.addComponent(this.datePicker);
		initLabelCaption();
		addCalendarEvent();

		actionPanel.addComponent(calendarActionBtn);
		actionPanel.setComponentAlignment(calendarActionBtn,
				Alignment.MIDDLE_RIGHT);

		VerticalLayout actionBtnLayout = new VerticalLayout();
		actionBtnLayout.setMargin(true);
		actionBtnLayout.setSpacing(true);
		actionBtnLayout.setWidth("150px");

		ButtonLink todoBtn = new ButtonLink("Create Todo", listener);
		actionBtnLayout.addComponent(todoBtn);
		todoBtn.setIcon(MyCollabResource.newResource("icons/16/crm/task.png"));
		todoBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_TASK));

		Button callBtn = new ButtonLink("Create Call", listener);
		actionBtnLayout.addComponent(callBtn);
		callBtn.setIcon(MyCollabResource.newResource("icons/16/crm/call.png"));
		callBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_CALL));

		ButtonLink meetingBtn = new ButtonLink("Create Event", listener);
		actionBtnLayout.addComponent(meetingBtn);
		meetingBtn.setIcon(MyCollabResource
				.newResource("icons/16/crm/meeting.png"));
		meetingBtn.setEnabled(AppContext
				.canWrite(RolePermissionCollections.CRM_MEETING));

		calendarActionBtn.setContent(actionBtnLayout);

		ButtonGroup viewSwitcher = new ButtonGroup();

		Button calendarViewBtn = new Button("Calendar");
		calendarViewBtn.setStyleName("selected");
		calendarViewBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		viewSwitcher.addButton(calendarViewBtn);

		Button activityListBtn = new Button("Activities List", new Button.ClickListener() {
			private static final long serialVersionUID = 2156576556541398934L;

			@Override
			public void buttonClick(ClickEvent evt) {
				ActivitySearchCriteria criteria = new ActivitySearchCriteria();
				criteria.setSaccountid(new NumberSearchField(
						AppContext.getAccountId()));
				EventBus.getInstance().fireEvent(new ActivityEvent.GotoTodoList(this, null));
			}
		});
		activityListBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		viewSwitcher.addButton(activityListBtn);

		actionPanel.addComponent(viewSwitcher);
		actionPanel.setComponentAlignment(viewSwitcher, Alignment.MIDDLE_RIGHT);

		calendarComponent = new MonthViewCalendar();
		mainContent.addComponent(calendarComponent);
		mainContent.setExpandRatio(calendarComponent, 1);
		mainContent.setComponentAlignment(calendarComponent, Alignment.MIDDLE_CENTER);

		HorizontalLayout spacing = new HorizontalLayout();
		spacing.setHeight("30px");
		mainContent.addComponent(spacing);

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

		mainContent.addComponent(noteInfoLayout);
		mainContent.setComponentAlignment(noteInfoLayout, Alignment.MIDDLE_CENTER);
	}

	private void updateLabelCaption(Date date) {
		switch (calendarComponent.viewMode) {
		case MONTH:
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			DateFormatSymbols s = new DateFormatSymbols();
			String month = s.getMonths()[calendar.get(GregorianCalendar.MONTH)];
			dateHdr.setValue(month + " "
					+ calendar.get(GregorianCalendar.YEAR));
			break;
		case WEEK:
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(date);
			int week = cal.get(java.util.Calendar.WEEK_OF_YEAR);
			WeekClickHandler handler = (WeekClickHandler) calendarComponent
					.getHandler(WeekClick.EVENT_ID);
			handler.weekClick(new WeekClick(calendarComponent, week, cal
					.get(GregorianCalendar.YEAR)));

			cal.set(java.util.Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			String firstDateOfWeek = DateTimeUtils.formatDate(cal.getTime());
			cal.add(java.util.Calendar.DATE, 6);
			String endDateOfWeek = DateTimeUtils.formatDate(cal.getTime());
			dateHdr.setValue(firstDateOfWeek + " - " + endDateOfWeek);
			break;
		case DAY:
			dateHdr.setValue(AppContext.formatDate(date));
			break;
		}
	}

	private void addCalendarEvent() {
		this.datePicker.getStyleCalendar().addValueChangeListener(
				new ValueChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(final ValueChangeEvent event) {
						final Date selectedDate = (Date) event.getProperty()
								.getValue();
						if (calendarComponent.viewMode == ActivityCalendarViewImpl.Mode.WEEK) {
							datePicker.selectWeek(selectedDate);
						} else {
							datePicker.selectDate(selectedDate);
						}
						calendarComponent
						.switchCalendarByDatePicker(selectedDate);
						datePicker.setLabelTime(AppContext
								.formatDate(selectedDate));
						updateLabelCaption(selectedDate);
						//dateHdr.setPopupVisible(false);
					}
				});

		this.datePicker.getBtnShowNextYear().addClickListener(
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						datePicker.getStyleCalendar().showNextYear();
						datePicker.setLabelTime(AppContext
								.formatDate(datePicker.getStyleCalendar()
										.getShowingDate()));
					}
				});

		this.datePicker.getBtnShowNextMonth().addClickListener(
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						datePicker.getStyleCalendar().showNextMonth();
						datePicker.setLabelTime(AppContext
								.formatDate(datePicker.getStyleCalendar()
										.getShowingDate()));
					}
				});

		this.datePicker.getBtnShowPreviousMonth().addClickListener(
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						datePicker.getStyleCalendar().showPreviousMonth();
						datePicker.setLabelTime(AppContext
								.formatDate(datePicker.getStyleCalendar()
										.getShowingDate()));
					}
				});

		this.datePicker.getBtnShowPreviousYear().addClickListener(
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						datePicker.getStyleCalendar().showPreviousYear();
						datePicker.setLabelTime(AppContext
								.formatDate(datePicker.getStyleCalendar()
										.getShowingDate()));
					}
				});
	}

	private void initLabelCaption() {
		GregorianCalendar calendar = new GregorianCalendar();
		Date datenow = new Date();
		calendar.setTime(datenow);
		DateFormatSymbols s = new DateFormatSymbols();
		String month = s.getMonths()[calendar.get(GregorianCalendar.MONTH)];
		dateHdr.setValue(month + " "
				+ calendar.get(GregorianCalendar.YEAR));
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

	private class QuickCreateEventWindow extends Window {
		private static final long serialVersionUID = 1L;
		private EditForm editForm;
		private MeetingWithBLOBs meeting;

		public QuickCreateEventWindow(Date startDate, Date endDate) {
			super("Quick Create Event");
			this.center();
			this.setWidth("1220px");

			this.meeting = new MeetingWithBLOBs();
			this.meeting.setSaccountid(AppContext.getAccountId());
			this.meeting.setStartdate(startDate);
			this.meeting.setEnddate(endDate);

			VerticalLayout contentLayout = new VerticalLayout();
			this.setContent(contentLayout);
			editForm = new EditForm();
			editForm.setBean(meeting);
			contentLayout.addComponent(editForm);
		}

		private class EditForm extends AdvancedEditBeanForm<MeetingWithBLOBs> {

			private static final long serialVersionUID = 1L;

			@Override
			public void setBean(MeetingWithBLOBs newDataSource) {
				this.setFormLayoutFactory(new FormLayoutFactory());
				this.setBeanFormFieldFactory(new EditFormFieldFactory(
						EditForm.this));
				super.setBean(newDataSource);
			}

			private class FormLayoutFactory extends MeetingFormLayoutFactory {

				private static final long serialVersionUID = 1L;

				public FormLayoutFactory() {
					super(meeting.getId() == null ? "Create Event" : meeting
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
							if (EditForm.this.validateForm()) {
								MeetingService meetingService = ApplicationContextUtil
										.getSpringBean(MeetingService.class);
								meetingService.saveWithSession(meeting,
										AppContext.getUsername());
								QuickCreateEventWindow.this.close();
								EventBus.getInstance().fireEvent(
										new ActivityEvent.GotoCalendar(
												this, null));
							}

						}
					});
					saveBtn.setIcon(MyCollabResource
							.newResource("icons/16/save.png"));
					saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
					layout.addComponent(saveBtn);
					layout.setComponentAlignment(saveBtn,
							Alignment.MIDDLE_CENTER);
					Button cancelBtn = new Button("Cancel",
							new ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							QuickCreateEventWindow.this.close();
						}
					});
					cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
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
			AbstractBeanFieldGroupEditFieldFactory<MeetingWithBLOBs> {
				private static final long serialVersionUID = 1L;

				public EditFormFieldFactory(
						GenericBeanForm<MeetingWithBLOBs> form) {
					super(form);
				}

				@Override
				protected Field<?> onCreateField(Object propertyId) {
					if (propertyId.equals("subject")) {
						TextField tf = new TextField();
						tf.setNullRepresentation("");
						tf.setRequired(true);
						tf.setRequiredError("Subject must not be null");
						return tf;
					} else if (propertyId.equals("status")) {
						return new MeetingStatusComboBox();
					} else if (propertyId.equals("startdate")) {
						return new DateTimePickerField();
					} else if (propertyId.equals("enddate")) {
						return new DateTimePickerField();
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
						return null;
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

		public MonthViewCalendar() {
			super(new ActivityEventProvider());
			this.setTimeFormat(TimeFormat.Format12H);
			this.setSizeFull();
			this.setImmediate(true);
			this.setLocale(Locale.US);

			Date today = new Date();
			calendar.setTime(today);
			int rollAmount = calendar.get(GregorianCalendar.DAY_OF_MONTH) - 1;
			calendar.add(GregorianCalendar.DAY_OF_MONTH, -rollAmount);
			resetTime(false);
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
					viewMode = Mode.DAY;
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

			this.setHandler(new BasicForwardHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void forward(ForwardEvent event) {
					super.forward(event);
					switch (viewMode) {
					case WEEK:
						calendar.add(java.util.Calendar.DATE, 7);
						calendar.set(java.util.Calendar.DAY_OF_WEEK,
								calendar.getFirstDayOfWeek());
						String firstDateOfWeek = DateTimeUtils
								.formatDate(calendar.getTime());
						calendar.add(java.util.Calendar.DATE, 6);
						String endDateOfWeek = DateTimeUtils
								.formatDate(calendar.getTime());
						dateHdr.setValue(firstDateOfWeek + " - "
								+ endDateOfWeek);
						break;
					case DAY:
						calendar.add(java.util.Calendar.DATE, 1);
						dateHdr.setValue(DateTimeUtils
								.formatDate(calendar.getTime()));
						break;
					case MONTH:
						break;
					}
				}

			});

			this.setHandler(new BasicBackwardHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void backward(BackwardEvent event) {
					super.backward(event);
					switch (viewMode) {
					case WEEK:
						calendar.add(java.util.Calendar.DATE, -7);
						calendar.set(java.util.Calendar.DAY_OF_WEEK,
								calendar.getFirstDayOfWeek());
						String firstDateOfWeek = DateTimeUtils
								.formatDate(calendar.getTime());
						calendar.add(java.util.Calendar.DATE, 6);
						String endDateOfWeek = DateTimeUtils
								.formatDate(calendar.getTime());
						dateHdr.setValue(firstDateOfWeek + " - "
								+ endDateOfWeek);
						break;
					case DAY:
						calendar.add(java.util.Calendar.DATE, -1);
						dateHdr.setValue(DateTimeUtils
								.formatDate(calendar.getTime()));
						break;
					case MONTH:
						break;
					}
				}
			});

			this.setHandler(new RangeSelectHandler() {
				private static final long serialVersionUID = 1L;

				@Override
				public void rangeSelect(RangeSelectEvent event) {
					if (AppContext
							.canWrite(RolePermissionCollections.CRM_MEETING)) {
						UI.getCurrent().addWindow(
								new QuickCreateEventWindow(event.getStart(),
										event.getEnd()));
					}
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
					NotificationUtil.showNotification("Success", "Event: \""
							+ simpleMeeting.getSubject()
							+ "\" has been updated!", Type.HUMANIZED_MESSAGE);
					EventBus.getInstance().fireEvent(
							new ActivityEvent.GotoCalendar(this, null));
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
						NotificationUtil.showNotification("Success",
								"Event: \"" + simpleMeeting.getSubject()
								+ "\" has been updated!",
								Type.HUMANIZED_MESSAGE);
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
					viewMode = Mode.WEEK;
				}
			});
		}

		private void switchCalendarByDatePicker(Date date) {
			switch (viewMode) {
			case MONTH:
				switchToMonthView(date, false);
				break;
			case WEEK:
				switchToWeekView(date);
				break;
			case DAY:
				switchToDateView(date);
				break;
			}
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

		public Date getCurrentMonthsFirstDate() {
			return currentMonthsFirstDate;
		}

		public GregorianCalendar getCalendar() {
			return calendar;
		}

		private void switchToWeekView(Date date) {
			viewMode = Mode.WEEK;
			calendar.setTime(date);
			java.util.Calendar cal = java.util.Calendar.getInstance();
			cal.setTime(date);
			int week = cal.get(java.util.Calendar.WEEK_OF_YEAR);
			WeekClickHandler handler = (WeekClickHandler) calendarComponent
					.getHandler(WeekClick.EVENT_ID);
			handler.weekClick(new WeekClick(calendarComponent, week, cal
					.get(GregorianCalendar.YEAR)));

			cal.set(java.util.Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
			String firstDateOfWeek = DateTimeUtils.formatDate(cal.getTime());
			cal.add(java.util.Calendar.DATE, 6);
			String endDateOfWeek = DateTimeUtils.formatDate(cal.getTime());
			dateHdr.setValue(firstDateOfWeek + " - " + endDateOfWeek);
		}

		private void switchToDateView(Date date) {
			viewMode = Mode.DAY;
			calendar.setTime(date);
			DateClickHandler handler = (DateClickHandler) calendarComponent
					.getHandler(DateClickEvent.EVENT_ID);
			handler.dateClick(new DateClickEvent(calendarComponent, date));
			dateHdr.setValue(AppContext.formatDate(date));
		}

		private void switchToMonthView(Date date, boolean isViewCurrentMonth) {
			viewMode = Mode.MONTH;
			calendar = new GregorianCalendar();
			calendar.setTime(date);
			int rollAmount = calendar.get(GregorianCalendar.DAY_OF_MONTH) - 1;
			calendar.add(GregorianCalendar.DAY_OF_MONTH, -rollAmount);
			currentMonthsFirstDate = calendar.getTime();
			calendarComponent.setStartDate(currentMonthsFirstDate);

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

	@Override
	public void attach() {
		super.attach();

		if(this.getParent() instanceof CustomLayout) {
			this.getParent().addStyleName("preview-comp");
		}
	}
}
