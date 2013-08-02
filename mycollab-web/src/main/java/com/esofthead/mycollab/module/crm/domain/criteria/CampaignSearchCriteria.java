/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateTimeSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class CampaignSearchCriteria extends SearchCriteria {

    private StringSearchField campaignName;
    private StringSearchField assignUserName;
    private StringSearchField assignUser;
    private NumberSearchField leadId;
    private NumberSearchField saccountid;
    private SetSearchField<String> statuses;
    private SetSearchField<String> types;
    private SetSearchField<String> assignUsers;
    private DateTimeSearchField startDate;
    private DateTimeSearchField endDate;
    private RangeDateTimeSearchField startDateRange;
    private RangeDateTimeSearchField endDateRange;
    private NumberSearchField id;

    public StringSearchField getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(StringSearchField campaignName) {
        this.campaignName = campaignName;
    }

    public StringSearchField getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(StringSearchField assignUser) {
        this.assignUser = assignUser;
    }

    public NumberSearchField getLeadId() {
        return leadId;
    }

    public void setLeadId(NumberSearchField leadId) {
        this.leadId = leadId;
    }

    public NumberSearchField getSaccountid() {
        return saccountid;
    }

    public void setSaccountid(NumberSearchField saccountid) {
        this.saccountid = saccountid;
    }

    public SetSearchField<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(SetSearchField<String> statuses) {
        this.statuses = statuses;
    }

    public SetSearchField<String> getTypes() {
        return types;
    }

    public void setTypes(SetSearchField<String> types) {
        this.types = types;
    }

    public SetSearchField<String> getAssignUsers() {
        return assignUsers;
    }

    public void setAssignUsers(SetSearchField<String> assignUsers) {
        this.assignUsers = assignUsers;
    }

    public StringSearchField getAssignUserName() {
        return assignUserName;
    }

    public void setAssignUserName(StringSearchField assignUserName) {
        this.assignUserName = assignUserName;
    }

    public DateTimeSearchField getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTimeSearchField startDate) {
        this.startDate = startDate;
    }

    public DateTimeSearchField getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTimeSearchField endDate) {
        this.endDate = endDate;
    }

    public RangeDateTimeSearchField getStartDateRange() {
        return startDateRange;
    }

    public void setStartDateRange(RangeDateTimeSearchField startDateRange) {
        this.startDateRange = startDateRange;
    }

    public RangeDateTimeSearchField getEndDateRange() {
        return endDateRange;
    }

    public void setEndDateRange(RangeDateTimeSearchField endDateRange) {
        this.endDateRange = endDateRange;
    }

    public void setId(NumberSearchField id) {
        this.id = id;
    }

    public NumberSearchField getId() {
        return id;
    }
}
