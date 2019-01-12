package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.TableViewField;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.table.IPagedTable.TableClickListener;
import org.apache.commons.collections4.CollectionUtils;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class TimeTrackingDateOrderComponent extends AbstractTimeTrackingDisplayComp {
    private static final long serialVersionUID = 1L;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(AppUI.getLongDateFormat()).withLocale
            (UserUIContext.getUserLocale());

    public TimeTrackingDateOrderComponent(List<TableViewField> fields, TableClickListener tableClickListener) {
        super(fields, tableClickListener);
    }

    @Override
    protected void displayGroupItems(List<SimpleItemTimeLogging> timeLoggingEntries) {
        if (CollectionUtils.isNotEmpty(timeLoggingEntries)) {
            ELabel label = ELabel.h3(formatter.format(timeLoggingEntries.get(0).getLogforday()));
            with(label, new TimeLoggingBockLayout(visibleFields, tableClickListener, timeLoggingEntries));
        }
    }

    @Override
    String getGroupCriteria(SimpleItemTimeLogging timeEntry) {
        return DateTimeUtils.formatDate(timeEntry.getLogforday(), "yyyy/MM/dd", UserUIContext.getUserLocale());
    }
}
