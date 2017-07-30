package com.mycollab.pro.module.project.ui.components;

import com.mycollab.common.TableViewField;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class TimeTrackingDateOrderComponent extends AbstractTimeTrackingDisplayComp {
    private static final long serialVersionUID = 1L;
    private DateTimeFormatter formatter = DateTimeFormat.forPattern(AppUI.getLongDateFormat()).withLocale
            (UserUIContext.getUserLocale());

    public TimeTrackingDateOrderComponent(List<TableViewField> fields, TableClickListener tableClickListener) {
        super(fields, tableClickListener);
        this.setWidth("100%");
    }

    @Override
    protected void displayGroupItems(List<SimpleItemTimeLogging> timeLoggingEntries) {
        if (timeLoggingEntries.size() > 0) {
            ELabel label = ELabel.h3(formatter.print(timeLoggingEntries.get(0).getLogforday().getTime()));
            addComponent(label);
            addComponent(new TimeLoggingBockLayout(visibleFields, tableClickListener, timeLoggingEntries));
        }
    }

    @Override
    String getGroupCriteria(SimpleItemTimeLogging timeEntry) {
        return DateTimeUtils.formatDate(timeEntry.getLogforday(), "yyyy/MM/dd", UserUIContext.getUserLocale());
    }
}
