package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.db.query.SearchQueryInfo;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.vaadin.ui.SavedFilterComboBox;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class TaskSavedFilterComboBox extends SavedFilterComboBox {
    public TaskSavedFilterComboBox() {
        super(ProjectTypeConstants.TASK);

        SearchQueryInfo allTasksQuery = new SearchQueryInfo("All Tasks");

        this.addSharedSearchQueryInfo(new SearchQueryInfo("All Tasks"));
        this.addSharedSearchQueryInfo(new SearchQueryInfo("All Open Tasks"));
        this.addSharedSearchQueryInfo(new SearchQueryInfo("My Tasks"));
        this.addSharedSearchQueryInfo(new SearchQueryInfo("New This Week"));
        this.addSharedSearchQueryInfo(new SearchQueryInfo("New Last Week"));
    }


}
