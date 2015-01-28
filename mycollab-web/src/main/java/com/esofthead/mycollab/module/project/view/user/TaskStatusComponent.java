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
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class TaskStatusComponent extends MVerticalLayout {
    private static final long serialVersionUID = 1L;


    private TaskStatusPagedList taskComponents;
    private ProjectGenericTaskSearchCriteria searchCriteria;

    public TaskStatusComponent() {
        withSpacing(false).withMargin(false);
        this.addStyleName("myprojectlist");

        MHorizontalLayout header = new MHorizontalLayout().withSpacing(false).withMargin(new MarginInfo(false, true,
                false, true)).withHeight("40px");
        header.addStyleName("header");

        Label title = new Label("Overdue Tasks");
        final CheckBox myItemsOnly = new CheckBox("My Items");
        myItemsOnly.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                boolean selectMyItemsOnly = myItemsOnly.getValue();
                if (selectMyItemsOnly) {
                    searchCriteria.setAssignUser(new StringSearchField(AppContext.getUsername()));
                } else {
                    searchCriteria.setAssignUser(null);
                }
                taskComponents.setSearchCriteria(searchCriteria);
            }
        });

        header.with(title, myItemsOnly).withAlign(title, Alignment.MIDDLE_LEFT).withAlign(myItemsOnly, Alignment
                .MIDDLE_RIGHT).expand(title);

        taskComponents = new TaskStatusPagedList();

        this.with(header, taskComponents);
    }

    public void showProjectTasksByStatus(List<Integer> prjKeys) {
        searchCriteria = new ProjectGenericTaskSearchCriteria();
        searchCriteria.setProjectIds(new SetSearchField<>(prjKeys.toArray(new Integer[prjKeys.size()])));
        searchCriteria.setIsOpenned(new SearchField());
        taskComponents.setSearchCriteria(searchCriteria);
    }

    private static class TaskStatusPagedList extends DefaultBeanPagedList<ProjectGenericTaskService,
            ProjectGenericTaskSearchCriteria, ProjectGenericTask> {

        public TaskStatusPagedList() {
            super(ApplicationContextUtil.getSpringBean(ProjectGenericTaskService.class), new
                    GenericTaskRowDisplayHandler(), 10);
        }
    }

    private static class GenericTaskRowDisplayHandler implements AbstractBeanPagedList.RowDisplayHandler<ProjectGenericTask> {
        @Override
        public Component generateRow(ProjectGenericTask task, int rowIndex) {
            Label lbl = new Label(task.getName());
            return lbl;
        }
    }
}