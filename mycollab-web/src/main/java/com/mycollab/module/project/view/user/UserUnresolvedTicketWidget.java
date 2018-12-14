/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.view.user;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.service.ProjectTicketService;
import com.mycollab.module.project.view.ticket.TicketRowDisplayHandler;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.mycollab.vaadin.web.ui.Depot;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;

import javax.annotation.PostConstruct;

/**
 * @author MyCollab Ltd
 * @since 5.2.4
 */
@SpringComponent
@PrototypeScope
public class UserUnresolvedTicketWidget extends Depot {

    @Autowired
    private UserUnresolvedTicketPresenter userUnresolvedTicketPresenter;

    @Autowired
    private ProjectTicketService projectTicketService;

    private DefaultBeanPagedList<ProjectTicketService, ProjectTicketSearchCriteria, ProjectTicket> ticketList;

    public UserUnresolvedTicketWidget() {
        super("", new CssLayout());
        this.setWidth("100%");
    }

    @PostConstruct
    public void init() {
        userUnresolvedTicketPresenter.initView(this);

        final CheckBox myItemsSelection = new CheckBox(UserUIContext.getMessage(GenericI18Enum.OPT_MY_ITEMS));
        myItemsSelection.addValueChangeListener(valueChangeEvent -> {
            boolean isMyItemsOption = myItemsSelection.getValue();
            userUnresolvedTicketPresenter.showUnresolvedTickets(isMyItemsOption);
        });
        ticketList = new DefaultBeanPagedList<ProjectTicketService, ProjectTicketSearchCriteria, ProjectTicket>
                (projectTicketService, new TicketRowDisplayHandler(true), 10) {
            @Override
            protected String stringWhenEmptyList() {
                return UserUIContext.getMessage(ProjectI18nEnum.OPT_NO_TICKET);
            }
        };
        this.addHeaderElement(myItemsSelection);
        this.bodyContent.addComponent(ticketList);
    }

    public void displayUnresolvedAssignmentsThisWeek() {
        userUnresolvedTicketPresenter.displayUnresolvedAssignmentsThisWeek();
    }

    public void displayUnresolvedAssignmentsNextWeek() {
        userUnresolvedTicketPresenter.displayUnresolvedAssignmentsNextWeek();
    }

    DefaultBeanPagedList<ProjectTicketService, ProjectTicketSearchCriteria, ProjectTicket> getTicketList() {
        return ticketList;
    }
}
