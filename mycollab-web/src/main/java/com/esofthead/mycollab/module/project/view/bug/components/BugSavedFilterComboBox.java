/**
 * This file is part of mycollab-web.
 * <p/>
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
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
    public static final String ALL_BUGS = "ALL_BUGS";
    public static final String OPEN_BUGS = "OPEN_BUGS";
    public static final String MY_BUGS = "MY_BUGS";
    public static final String NEW_THIS_WEEK = "NEW_THIS_WEEK";
    public static final String UPDATE_THIS_WEEK = "UPDATE_THIS_WEEK";
    public static final String NEW_LAST_WEEK = "NEW_LAST_WEEK";
    public static final String UPDATE_LAST_WEEK = "UPDATE_LAST_WEEK";

    public BugSavedFilterComboBox() {
        super(ProjectTypeConstants.BUG);

        SearchQueryInfo allBugsQuery = new SearchQueryInfo(ALL_BUGS, "All Bugs", SearchFieldInfo.inCollection
                (BugSearchCriteria.p_projectIds, new CurrentProjectIdInjecter()));

        SearchQueryInfo allOpenBugsQuery = new SearchQueryInfo(OPEN_BUGS, "All Open Bugs", new SearchFieldInfo(SearchField.AND,
                new PropertyListParam("bug-status", BugI18nEnum.FORM_STATUS, "m_tracker_bug", "status"), PropertyListParam.BELONG_TO,
                new VariableInjecter() {
                    @Override
                    public Object eval() {
                        return Arrays.asList(BugStatus.InProgress.name(), BugStatus.Open.name(), BugStatus.ReOpened.name());
                    }
                }));

        SearchQueryInfo myBugsQuery = new SearchQueryInfo(MY_BUGS, "My Bugs", SearchFieldInfo.inCollection
                (BugSearchCriteria.p_assignee, new VariableInjecter() {
                    @Override
                    public Object eval() {
                        return Arrays.asList(AppContext.getUsername());
                    }
                }));

        SearchQueryInfo newBugsThisWeekQuery = new SearchQueryInfo(NEW_THIS_WEEK, "New This Week", SearchFieldInfo
                .inDateRange(BugSearchCriteria.p_createddate, VariableInjecter.THIS_WEEK));

        SearchQueryInfo updateBugsThisWeekQuery = new SearchQueryInfo(UPDATE_THIS_WEEK, "Update This Week",
                SearchFieldInfo.inDateRange(BugSearchCriteria.p_lastupdatedtime, VariableInjecter.THIS_WEEK));

        SearchQueryInfo newBugsLastWeekQuery = new SearchQueryInfo(NEW_LAST_WEEK, "New Last Week", SearchFieldInfo
                .inDateRange(BugSearchCriteria.p_createddate, VariableInjecter.LAST_WEEK));

        SearchQueryInfo updateBugsLastWeekQuery = new SearchQueryInfo(UPDATE_LAST_WEEK, "Update Last Week",
                SearchFieldInfo.inDateRange(BugSearchCriteria.p_lastupdatedtime, VariableInjecter.LAST_WEEK));

        this.addSharedSearchQueryInfo(allBugsQuery);
        this.addSharedSearchQueryInfo(allOpenBugsQuery);
        this.addSharedSearchQueryInfo(myBugsQuery);
        this.addSharedSearchQueryInfo(newBugsThisWeekQuery);
        this.addSharedSearchQueryInfo(updateBugsThisWeekQuery);
        this.addSharedSearchQueryInfo(newBugsLastWeekQuery);
        this.addSharedSearchQueryInfo(updateBugsLastWeekQuery);
    }

    public void setTotalCountNumber(int countNumber) {
        componentsText.setReadOnly(false);
        componentsText.setValue(selectedQueryName + " (" + countNumber + ")");
        componentsText.setReadOnly(true);
    }
}
