package com.esofthead.mycollab.module.crm.view.activity;

import java.util.Collection;
import java.util.Date;

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
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickEvent;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.DateClickHandler;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
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
			private static final long serialVersionUID = 1L;

			@Override
			public void dateClick(DateClickEvent event) {
				// do nothing
			}
		});

		calendar.setHandler(new RangeSelectHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public void rangeSelect(RangeSelectEvent event) {
				ActivityCalendarViewImpl.this.getWindow().addWindow(
						new EventQuickCreateWindow(event.getStart(), event
								.getEnd()));
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
}
