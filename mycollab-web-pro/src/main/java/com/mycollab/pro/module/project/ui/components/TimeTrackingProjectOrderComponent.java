package com.mycollab.pro.module.project.ui.components;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.mycollab.common.TableViewField;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.table.IPagedTable.TableClickListener;

import java.util.List;
import java.util.Set;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class TimeTrackingProjectOrderComponent extends AbstractTimeTrackingDisplayComp {
    private static final long serialVersionUID = 1L;

    public TimeTrackingProjectOrderComponent(Set<TableViewField> fields, TableClickListener tableClickListener) {
        super(fields, tableClickListener);
        this.setWidth("100%");
    }

    @Override
    protected void displayGroupItems(List<SimpleItemTimeLogging> timeLoggingEntries) {
        if (timeLoggingEntries.size() > 0) {
            SimpleItemTimeLogging firstItem = timeLoggingEntries.get(0);

            Div projectDiv = new Div().appendText(ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT).getHtml() + " ").
                    appendChild(new A(ProjectLinkGenerator.generateProjectLink(firstItem.getProjectid())).appendText(firstItem.getProjectName()));
            ELabel link = ELabel.h3(projectDiv.write());
            addComponent(link);
            addComponent(new TimeLoggingBockLayout(visibleFields, tableClickListener, timeLoggingEntries));
        }
    }

    @Override
    String getGroupCriteria(SimpleItemTimeLogging timeEntry) {
        return timeEntry.getProjectShortName();
    }
}
