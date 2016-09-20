package com.mycollab.module.project.view.assignments;

import com.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.mycollab.vaadin.web.ui.DefaultGenericSearchPanel;
import com.mycollab.vaadin.web.ui.GenericSearchPanel;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class AssignmentSearchPanel extends DefaultGenericSearchPanel<ProjectGenericTaskSearchCriteria> {
    @Override
    protected SearchLayout<ProjectGenericTaskSearchCriteria> createBasicSearchLayout() {
        return null;
    }

    @Override
    protected SearchLayout<ProjectGenericTaskSearchCriteria> createAdvancedSearchLayout() {
        return null;
    }

    @Override
    protected ComponentContainer buildSearchTitle() {
        return null;
    }
}
