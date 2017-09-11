package com.mycollab.mobile.module.crm.view.activity;

import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.module.crm.events.ActivityEvent;
import com.mycollab.mobile.ui.DefaultPagedBeanList;
import com.mycollab.mobile.ui.MobileUIConstants;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleActivity;
import com.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.mycollab.module.crm.service.EventService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.ui.IBeanList;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;

import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd
 * @since 1.0
 */
public class ActivityListDisplay extends DefaultPagedBeanList<EventService, ActivitySearchCriteria, SimpleActivity> {
    private static final long serialVersionUID = -2050012123292483060L;

    public ActivityListDisplay() {
        super(AppContextUtil.getSpringBean(EventService.class), new ActivityRowDisplayHandler());
    }

    static public class ActivityRowDisplayHandler implements IBeanList.RowDisplayHandler<SimpleActivity> {

        @Override
        public Component generateRow(IBeanList<SimpleActivity> host, final SimpleActivity simpleEvent, int rowIndex) {
            Button b = new Button(simpleEvent.getSubject(), clickEvent -> {
                if (simpleEvent.getEventType().equals(CrmTypeConstants.INSTANCE.getTASK())) {
                    EventBusFactory.getInstance().post(new ActivityEvent.TaskRead(this, simpleEvent.getId()));
                } else if (simpleEvent.getEventType().equals(CrmTypeConstants.INSTANCE.getCALL())) {
                    EventBusFactory.getInstance().post(new ActivityEvent.CallRead(this, simpleEvent.getId()));
                } else if (simpleEvent.getEventType().equals(CrmTypeConstants.INSTANCE.getMEETING())) {
                    EventBusFactory.getInstance().post(new ActivityEvent.MeetingRead(this, simpleEvent.getId()));
                }
            });

            if ("Held".equals(simpleEvent.getStatus())) {
                b.addStyleName(MobileUIConstants.LINK_COMPLETED);
            } else {
                if (simpleEvent.getEndDate() != null && (simpleEvent.getEndDate().before(new GregorianCalendar().getTime()))) {
                    b.addStyleName(MobileUIConstants.LINK_OVERDUE);
                }
            }
            b.setWidth("100%");
            return b;
        }
    }
}
