package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
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
 * @since 5.2.2
 */
public class CreatedDateOrderComponent extends BugGroupOrderComponent {
    private Map<DateTime, GroupComponent> createdDateAvailables = new TreeMap<>();
    private GroupComponent unspecifiedTasks;

    @Override
    public void insertBugs(List<SimpleBug> bugs) {
        for (SimpleBug bug : bugs) {
            if (bug.getCreatedtime() != null) {
                Date createdDate = bug.getCreatedtime();
                DateTime jodaTime = new DateTime(createdDate, DateTimeZone.UTC);
                DateTime monDay = jodaTime.dayOfWeek().withMinimumValue();
                if (createdDateAvailables.containsKey(monDay)) {
                    GroupComponent groupComponent = createdDateAvailables.get(monDay);
                    groupComponent.insertTask(bug);
                } else {
                    GroupComponent groupComponent = new GroupComponent(monDay);
                    createdDateAvailables.put(monDay, groupComponent);
                    addComponent(groupComponent);
                    groupComponent.insertTask(bug);
                }
            } else {
                if (unspecifiedTasks == null) {
                    unspecifiedTasks = new GroupComponent();
                    addComponent(unspecifiedTasks);
                }
                unspecifiedTasks.insertTask(bug);
            }
        }
    }

    private static class GroupComponent extends VerticalLayout {
        private CssLayout wrapBody;
        private Label headerLbl;
        private String durationLbl;
        private int numElements = 0;

        GroupComponent(DateTime startDate) {
            initComponent();
            DateTime maxValue = startDate.dayOfWeek().withMaximumValue();
            DateTimeFormatter formatter = DateTimeFormat.forPattern("E, dd MMM yyyy");
            String monDayStr = formatter.print(startDate);
            String sundayStr = formatter.print(maxValue);
            durationLbl = String.format("%s - %s", monDayStr, sundayStr);
            headerLbl.setValue(String.format("%s - %s", monDayStr, sundayStr));
            updateHeader();
        }

        GroupComponent() {
            initComponent();
            durationLbl = "Unscheduled";
            updateHeader();
        }

        private void updateHeader() {
            headerLbl.setValue(String.format("%s (%d)", durationLbl, numElements));
        }

        private void initComponent() {
            this.setMargin(new MarginInfo(true, false, true, false));
            wrapBody = new CssLayout();
            wrapBody.setStyleName("tasklist");
            headerLbl = new Label();
            headerLbl.addStyleName(ValoTheme.LABEL_H3);
            this.addComponent(headerLbl);
            this.addComponent(wrapBody);
        }

        void insertTask(SimpleBug bug) {
            wrapBody.addComponent(new BugRowRenderer(bug));
            numElements++;
            updateHeader();
        }
    }
}
