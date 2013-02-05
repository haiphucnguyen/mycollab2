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
package com.esofthead.mycollab.module.crm.service.ibatis;

import com.esofthead.mycollab.common.interceptor.service.Auditable;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.crm.dao.CampaignAccountMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignMapper;
import com.esofthead.mycollab.module.crm.dao.CampaignMapperExt;
import com.esofthead.mycollab.module.crm.domain.Campaign;
import com.esofthead.mycollab.module.crm.domain.CampaignAccount;
import com.esofthead.mycollab.module.crm.domain.CampaignAccountExample;
import com.esofthead.mycollab.module.crm.domain.SimpleCampaign;
import com.esofthead.mycollab.module.crm.domain.criteria.CampaignSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CampaignService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = "Crm", type = "Campaign", nameField = "campaignname")
@Auditable(module = "Crm", type = "Campaign")
public class CampaignServiceImpl extends DefaultService<Integer, Campaign, CampaignSearchCriteria> implements
        CampaignService {
    
    @Autowired
    private CampaignMapper campaignMapper;
    @Autowired
    private CampaignMapperExt campaignMapperExt;
    @Autowired
    private CampaignAccountMapper campaignAccountMapper;
    
    @Override
    public ICrudGenericDAO<Integer, Campaign> getCrudMapper() {
        return campaignMapper;
    }
    
    @Override
    public ISearchableDAO<CampaignSearchCriteria> getSearchMapper() {
        return campaignMapperExt;
    }
    
    @Override
    public SimpleCampaign findCampaignById(int campaignId) {
        return campaignMapperExt.findCampaignById(campaignId);
    }
    
    @Override
    public void saveCampaignAccountRelationship(List<CampaignAccount> associateAccounts) {
        for (CampaignAccount associateAccount : associateAccounts) {
            CampaignAccountExample ex = new CampaignAccountExample();
            ex.createCriteria().andAccountidEqualTo(associateAccount.getAccountid()).andCampaignidEqualTo(associateAccount.getCampaignid());
            if (campaignAccountMapper.countByExample(ex) == 0) {
                campaignAccountMapper.insert(associateAccount);
            }
        }
    }
    
    @Override
    public void removeCampaignAccountRelationship(CampaignAccount associateAccount) {
        CampaignAccountExample ex = new CampaignAccountExample();
        ex.createCriteria().andAccountidEqualTo(associateAccount.getAccountid()).andCampaignidEqualTo(associateAccount.getCampaignid());
        campaignAccountMapper.deleteByExample(ex);
    }
}
