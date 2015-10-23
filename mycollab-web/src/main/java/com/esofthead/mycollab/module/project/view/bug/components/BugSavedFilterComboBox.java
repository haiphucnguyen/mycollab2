package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.db.query.PropertyListParam;
import com.esofthead.mycollab.core.db.query.SearchFieldInfo;
import com.esofthead.mycollab.core.db.query.SearchQueryInfo;
import com.esofthead.mycollab.core.db.query.VariableInjecter;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.esofthead.mycollab.module.project.query.CurrentProjectIdInjecter;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.SavedFilterComboBox;

import java.util.Arrays;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public class BugSavedFilterComboBox extends SavedFilterComboBox {
    public BugSavedFilterComboBox() {
        super(ProjectTypeConstants.BUG);

        SearchQueryInfo allBugsQuery = new SearchQueryInfo("All Bugs", SearchFieldInfo.inCollection
                (BugSearchCriteria.p_projectIds, new CurrentProjectIdInjecter()));

        SearchQueryInfo allOpenBugsQuery = new SearchQueryInfo("All Open Bugs", new SearchFieldInfo(SearchField.AND,
                new PropertyListParam("bug-status", BugI18nEnum.FORM_STATUS, "m_tracker_bug", "status"), PropertyListParam.BELONG_TO, new
                VariableInjecter() {
                    @Override
                    public Object eval() {
                        return Arrays.asList(BugStatus.InProgress.name(), BugStatus.Open.name(), BugStatus.ReOpened.name());
                    }
                }));

        SearchQueryInfo myBugsQuery = new SearchQueryInfo("My Bugs", SearchFieldInfo.inCollection
                (BugSearchCriteria.p_assignee, new VariableInjecter() {
                    @Override
                    public Object eval() {
                        return Arrays.asList(AppContext.getUsername());
                    }
                }));

        SearchQueryInfo newBugsThisWeekQuery = new SearchQueryInfo("New This Week", SearchFieldInfo.inDateRange
                (BugSearchCriteria.p_createddate, VariableInjecter.THIS_WEEK));

        SearchQueryInfo updateBugsThisWeekQuery = new SearchQueryInfo("Update This Week", SearchFieldInfo.inDateRange
                (BugSearchCriteria.p_lastupdatedtime, VariableInjecter.THIS_WEEK));

        SearchQueryInfo newBugsLastWeekQuery = new SearchQueryInfo("New Last Week", SearchFieldInfo.inDateRange
                (BugSearchCriteria.p_createddate, VariableInjecter.LAST_WEEK));

        SearchQueryInfo updateBugsLastWeekQuery = new SearchQueryInfo("Update Last Week", SearchFieldInfo.inDateRange
                (BugSearchCriteria.p_lastupdatedtime, VariableInjecter.LAST_WEEK));

        this.addSharedSearchQueryInfo(allBugsQuery);
        this.addSharedSearchQueryInfo(allOpenBugsQuery);
        this.addSharedSearchQueryInfo(myBugsQuery);
        this.addSharedSearchQueryInfo(newBugsThisWeekQuery);
        this.addSharedSearchQueryInfo(updateBugsThisWeekQuery);
        this.addSharedSearchQueryInfo(newBugsLastWeekQuery);
        this.addSharedSearchQueryInfo(updateBugsLastWeekQuery);
    }
}
