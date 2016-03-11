/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.pro.module.project.ui.components;

import com.esofthead.mycollab.common.TableViewField;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleItemTimeLogging;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.table.IPagedBeanTable.TableClickListener;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
public class TimeTrackingProjectOrderComponent extends AbstractTimeTrackingDisplayComp {
    private static final long serialVersionUID = 1L;

    public TimeTrackingProjectOrderComponent(List<TableViewField> fields, TableClickListener tableClickListener) {
        super(fields, tableClickListener);
        this.setWidth("100%");
    }

    @Override
    protected void displayGroupItems(List<SimpleItemTimeLogging> timeLoggingEntries) {
        if (timeLoggingEntries.size() > 0) {
            SimpleItemTimeLogging firstItem = timeLoggingEntries.get(0);

            Div projectDiv = new Div().appendText(ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT).getHtml
                    () + " ").appendChild(new A(ProjectLinkBuilder.generateProjectFullLink(firstItem.getProjectid())).appendText(firstItem.getProjectName()));
            ELabel link = new ELabel(projectDiv.write(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H3, ValoTheme.LABEL_NO_MARGIN);
            addComponent(link);
            addComponent(new TimeLoggingBockLayout(visibleFields, tableClickListener, timeLoggingEntries));
        }
    }

    @Override
    String getGroupCriteria(SimpleItemTimeLogging timeEntry) {
        return timeEntry.getProjectShortName();
    }
}
