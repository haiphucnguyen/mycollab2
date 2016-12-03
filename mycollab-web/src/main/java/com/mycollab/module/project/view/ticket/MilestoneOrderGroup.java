package com.mycollab.module.project.view.ticket;

import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.SortedArrayMap;
import com.mycollab.html.DivLessFormatter;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.vaadin.UserUIContext;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.4.6
 */
public class MilestoneOrderGroup extends TicketGroupOrderComponent {
    private SortedArrayMap<String, DefaultTicketGroupComponent> milestonesAvailable = new SortedArrayMap<>();
    private DefaultTicketGroupComponent unspecifiedTasks;

    @Override
    public void insertTickets(List<ProjectTicket> tickets) {
        for (ProjectTicket ticket : tickets) {
            String milestoneName = ticket.getMilestoneName();
            if (milestoneName != null) {
                if (milestonesAvailable.containsKey(milestoneName)) {
                    DefaultTicketGroupComponent groupComponent = milestonesAvailable.get(milestoneName);
                    groupComponent.insertTicket(ticket);
                } else {
                    Div milestoneDiv = new DivLessFormatter().appendChild(new Text(" " + ticket.getMilestoneName()));

                    DefaultTicketGroupComponent groupComponent = new DefaultTicketGroupComponent(milestoneDiv.write());
                    milestonesAvailable.put(milestoneName, groupComponent);
                    int index = milestonesAvailable.getKeyIndex(milestoneName);
                    if (index > -1) {
                        addComponent(groupComponent, index);
                    } else {
                        addComponent(groupComponent);
                    }

                    groupComponent.insertTicket(ticket);
                }
            } else {
                if (unspecifiedTasks == null) {
                    unspecifiedTasks = new DefaultTicketGroupComponent(UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED));
                    addComponent(unspecifiedTasks, 0);
                }
                unspecifiedTasks.insertTicket(ticket);
            }
        }
    }
}
