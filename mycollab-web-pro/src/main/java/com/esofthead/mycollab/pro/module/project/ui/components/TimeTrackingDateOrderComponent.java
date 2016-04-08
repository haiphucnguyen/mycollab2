package com.esofthead.mycollab.pro.module.project.ui.components;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;
import org.apache.commons.lang3.time.FastDateFormat;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class TimeTrackingDateOrderComponent extends AbstractTimeTrackingDisplayComp {
    private static final long serialVersionUID = 1L;

    private static final FastDateFormat DATE_FORMAT = FastDateFormat.getInstance("EEEE, dd MMMM yyyy");

    public TimeTrackingDateOrderComponent(List<TableViewField> fields, TableClickListener tableClickListener) {
        super(fields, tableClickListener);
        this.setWidth("100%");
    }

    @Override
    protected void displayGroupItems(List<SimpleItemTimeLogging> timeLoggingEntries) {
        if (timeLoggingEntries.size() > 0) {
            ELabel label = ELabel.h3(DATE_FORMAT.format(timeLoggingEntries.get(0).getLogforday()));
            addComponent(label);
            addComponent(new TimeLoggingBockLayout(visibleFields, tableClickListener, timeLoggingEntries));
        }
    }

    @Override
    String getGroupCriteria(SimpleItemTimeLogging timeEntry) {
        return DateTimeUtils.formatDate(timeEntry.getLogforday(), "yyyy/MM/dd");
    }
}
