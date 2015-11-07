package com.esofthead.mycollab.module.project.view.task.components;

import com.esofthead.mycollab.core.utils.SortedArrayMap;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
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

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class CreatedDateOrderComponent  extends TaskGroupOrderComponent {
    private SortedArrayMap<DateTime, GroupComponent> createdDateAvailables = new SortedArrayMap<>();
    private GroupComponent unspecifiedTasks;

    @Override
    public void insertTasks(List<SimpleTask> tasks) {
        for (SimpleTask task : tasks) {
            if (task.getCreatedtime() != null) {
                Date createdDate = task.getCreatedtime();
                DateTime jodaTime = new DateTime(createdDate, DateTimeZone.UTC);
                DateTime monDay = jodaTime.dayOfWeek().withMinimumValue();
                if (createdDateAvailables.containsKey(monDay)) {
                    GroupComponent groupComponent = createdDateAvailables.get(monDay);
                    groupComponent.insertTask(task);
                } else {
                    GroupComponent groupComponent = new GroupComponent(monDay);
                    createdDateAvailables.put(monDay, groupComponent);
                    int index = createdDateAvailables.getKeyIndex(monDay);
                    if (index > -1) {
                        addComponent(groupComponent, index);
                    } else {
                        addComponent(groupComponent);
                    }

                    groupComponent.insertTask(task);
                }
            } else {
                if (unspecifiedTasks == null) {
                    unspecifiedTasks = new GroupComponent();
                    addComponent(unspecifiedTasks, 0);
                }
                unspecifiedTasks.insertTask(task);
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
            headerLbl.addStyleName(ValoTheme.LABEL_H3);
            this.addComponent(headerLbl);
            this.addComponent(wrapBody);
        }

        void insertTask(SimpleTask task) {
            wrapBody.addComponent(new TaskRowRenderer(task));
        }
    }
}
