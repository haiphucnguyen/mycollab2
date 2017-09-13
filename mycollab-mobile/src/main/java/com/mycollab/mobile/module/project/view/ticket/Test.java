package com.mycollab.mobile.module.project.view.ticket;

import com.mycollab.mobile.module.project.events.TicketEvent;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;

public class Test {
    public void test() {
        ProjectTicketSearchCriteria a = new ProjectTicketSearchCriteria();
        new TicketEvent.GotoDashboard(this, a);
    }
}
