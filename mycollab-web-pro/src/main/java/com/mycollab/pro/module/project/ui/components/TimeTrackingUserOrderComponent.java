package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.TableViewField;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.view.settings.component.ProjectUserLink;
import com.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class TimeTrackingUserOrderComponent extends AbstractTimeTrackingDisplayComp {
    private static final long serialVersionUID = 1L;

    public TimeTrackingUserOrderComponent(List<TableViewField> fields, TableClickListener tableClickListener) {
        super(fields, tableClickListener);
        this.setWidth("100%");
    }

    @Override
    protected void displayGroupItems(List<SimpleItemTimeLogging> timeLoggingEntries) {
        if (timeLoggingEntries.size() > 0) {
            SimpleItemTimeLogging firstItem = timeLoggingEntries.get(0);
            addComponent(new ProjectUserLink(firstItem.getLoguser(), firstItem.getLogUserAvatarId(), firstItem.getLogUserFullName()));
            addComponent(new TimeLoggingBockLayout(visibleFields, tableClickListener, timeLoggingEntries));
        }
    }

    @Override
    String getGroupCriteria(SimpleItemTimeLogging timeEntry) {
        return timeEntry.getLoguser();
    }
}
