package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class StartDateOrderComponent extends BugGroupOrderComponent {
    private Map<DateTime, GroupComponent> startDateAvailables = new TreeMap<>();
    private GroupComponent unspecifiedTasks;

    @Override
    public void insertBugs(List<SimpleBug> bugs) {
        for (SimpleBug bug : bugs) {
            if (bug.getCreatedtime() != null) {
                Date startDate = bug.getCreatedtime();
                DateTime jodaTime = new DateTime(startDate, DateTimeZone.UTC);
                DateTime monDay = jodaTime.dayOfWeek().withMinimumValue();
                if (startDateAvailables.containsKey(monDay)) {
                    GroupComponent groupComponent = startDateAvailables.get(monDay);
                    groupComponent.insertTask(bug);
                } else {
                    GroupComponent groupComponent = new GroupComponent(monDay);
                    startDateAvailables.put(monDay, groupComponent);
                    addComponent(groupComponent);
                    groupComponent.insertTask(bug);
                }
            } else {
                if (unspecifiedTasks == null) {
                    unspecifiedTasks = new GroupComponent();
                    addComponent(unspecifiedTasks, 0);
                }
                unspecifiedTasks.insertTask(bug);
            }
        }
    }

    private static class GroupComponent extends VerticalLayout {
        private CssLayout wrapBody;
        private Label headerLbl;

        GroupComponent(DateTime startDate) {
            initComponent();
            DateTime maxValue = startDate.dayOfWeek().withMaximumValue();
            DateTimeFormatter fomatter = DateTimeFormat.forPattern("E, dd MMM yyyy");
            String monDayStr = fomatter.print(startDate);
            String sundayStr = fomatter.print(maxValue);
            headerLbl.setValue(String.format("%s - %s", monDayStr, sundayStr));
        }

        GroupComponent() {
            initComponent();
            headerLbl.setValue("Unscheduled");
        }

        private void initComponent() {
            this.setMargin(new MarginInfo(true, false, true, false));
            wrapBody = new CssLayout();
            wrapBody.setStyleName("tasklist");
            headerLbl = new Label();
            headerLbl.addStyleName("h2");
            this.addComponent(headerLbl);
            this.addComponent(wrapBody);
        }

        void insertTask(SimpleBug bug) {
            wrapBody.addComponent(new BugRowRenderer(bug));
        }
    }
}