/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class ProjectTaskListComboBox extends ComboBox {

    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    public ProjectTaskListComboBox() {
        super();
        this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);

        TaskListSearchCriteria criteria = new TaskListSearchCriteria();
        SimpleProject project = (SimpleProject) AppContext.getVariable(ProjectContants.PROJECT_NAME);
        criteria.setProjectId(new NumberSearchField(SearchField.AND,
                project.getId()));

        ProjectTaskListService taskListService = AppContext.getSpringBean(ProjectTaskListService.class);
        List<SimpleTaskList> taskLists = taskListService
                .findPagableListByCriteria(new SearchRequest<TaskListSearchCriteria>(
                criteria, 0, Integer.MAX_VALUE));

        BeanContainer<String, SimpleTaskList> beanItem = new BeanContainer<String, SimpleTaskList>(
                SimpleTaskList.class);
        beanItem.setBeanIdProperty("id");

        for (SimpleTaskList taskList : taskLists) {
            beanItem.addBean(taskList);
        }

        this.setContainerDataSource(beanItem);
        this.setItemCaptionPropertyId("name");
    }
    
}
