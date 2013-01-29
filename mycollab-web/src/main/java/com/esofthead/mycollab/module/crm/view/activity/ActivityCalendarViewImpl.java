package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.addon.calendar.event.CalendarEvent;
import com.vaadin.addon.calendar.ui.Calendar;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents;
import com.vaadin.addon.calendar.ui.CalendarComponentEvents.EventClick;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hene.popupbutton.PopupButton;

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
        
        actionBtnLayout.addComponent(new ButtonLink("Create Todo", listener));
        actionBtnLayout.addComponent(new ButtonLink("Create Call", listener));
        actionBtnLayout
                .addComponent(new ButtonLink("Create Meeting", listener));
        
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
                CalendarEvent calendarEvent = event.getCalendarEvent();
                getWindow().showNotification(
                        "Event clicked: " + calendarEvent.getCaption(),
                        calendarEvent.getDescription());
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
            } else if (caption.equals("Create Meeting")) {
                EventBus.getInstance().fireEvent(
                        new ActivityEvent.MeetingAdd(this, null));
            }
        }
    }
}
