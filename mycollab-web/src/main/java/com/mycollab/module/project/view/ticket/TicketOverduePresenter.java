package com.mycollab.module.project.view.ticket;

import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.db.arguments.DateSearchField;
import com.mycollab.db.arguments.SearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.db.arguments.StringSearchField;
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.module.project.i18n.TicketI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.spring.annotation.SpringComponent;
import org.vaadin.spring.annotation.PrototypeScope;

import java.io.Serializable;
import java.util.List;

@SpringComponent
@PrototypeScope
public class TicketOverduePresenter implements Serializable {

    private TicketOverdueWidget view;

    private ProjectTicketSearchCriteria searchCriteria;

    void initView(TicketOverdueWidget view) {
        this.view = view;
    }

    void displayOverdueTickets(boolean meOnly) {
        if (searchCriteria != null) {
            if (meOnly) {
                searchCriteria.setAssignUser(StringSearchField.and(UserUIContext.getUsername()));
            } else {
                searchCriteria.setAssignUser(null);
            }

            view.getOverdueTicketList().setSearchCriteria(searchCriteria);
        }
    }

    public void showUnresolvedTickets(List<Integer> prjKeys) {
        searchCriteria = new ProjectTicketSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(prjKeys));
        searchCriteria.setOpenned(new SearchField());
        searchCriteria.setDueDate(new DateSearchField(DateTimeUtils.getCurrentDateWithoutMS().toLocalDate(),
                DateSearchField.LESS_THAN));
        updateSearchResult();
    }

    private void updateSearchResult() {
        view.getOverdueTicketList().setSearchCriteria(searchCriteria);
        view.setTitle(String.format("%s (%d)", UserUIContext.getMessage(TicketI18nEnum.VAL_OVERDUE_TICKETS), view.getOverdueTicketList().getTotalCount()));
    }
}
