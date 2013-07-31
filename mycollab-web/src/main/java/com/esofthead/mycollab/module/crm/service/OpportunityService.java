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
package com.esofthead.mycollab.module.crm.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.GroupItem;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.OpportunityContact;
import com.esofthead.mycollab.module.crm.domain.OpportunityLead;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.domain.criteria.OpportunitySearchCriteria;

public interface OpportunityService extends
        IDefaultService<Integer, Opportunity, OpportunitySearchCriteria> {

    SimpleOpportunity findById(int opportunityId);
    
    List<GroupItem> getSalesStageSummary(OpportunitySearchCriteria criteria);
    
    List<GroupItem> getLeadSourcesSummary(OpportunitySearchCriteria criteria);
    
    void saveOpportunityContactRelationship(List<OpportunityContact> associateContacts);
    
    void removeOpportunityContactRelationship(OpportunityContact associateContact);
    
    void saveOpportunityLeadRelationship(List<OpportunityLead> associateLeads);
    
    void removeOpportunityLeadRelationship(OpportunityLead associateLead);

	void updateBySearchCriteria(Opportunity value,OpportunitySearchCriteria searchCriteria);
}
