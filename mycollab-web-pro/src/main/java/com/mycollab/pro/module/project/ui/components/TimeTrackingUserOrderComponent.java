package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.TableViewField;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.mycollab.vaadin.web.ui.table.IPagedTable.TableClickListener;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class TimeTrackingUserOrderComponent extends AbstractTimeTrackingDisplayComp {
    private static final long serialVersionUID = 1L;

    public TimeTrackingUserOrderComponent(List<TableViewField> fields, TableClickListener tableClickListener) {
        super(fields, tableClickListener);
    }

    @Override
    protected void displayGroupItems(List<SimpleItemTimeLogging> timeLoggingEntries) {
        if (timeLoggingEntries.size() > 0) {
            SimpleItemTimeLogging timeEntry = timeLoggingEntries.get(0);
            addComponent(new ProjectUserLink(timeEntry.getProjectid(), timeEntry.getLoguser(),
                    timeEntry.getLogUserAvatarId(), timeEntry.getLogUserFullName()));
            addComponent(new TimeLoggingBockLayout(visibleFields, tableClickListener, timeLoggingEntries));
        }
    }

    @Override
    String getGroupCriteria(SimpleItemTimeLogging timeEntry) {
        return timeEntry.getLoguser();
    }
}
