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
import com.esofthead.mycollab.module.crm.dao.AccountContactMapper;
import com.esofthead.mycollab.module.crm.dao.ContactMapper;
import com.esofthead.mycollab.module.crm.dao.ContactMapperExt;
import com.esofthead.mycollab.module.crm.dao.ContactOpportunityMapper;
import com.esofthead.mycollab.module.crm.domain.AccountContact;
import com.esofthead.mycollab.module.crm.domain.AccountContactExample;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunity;
import com.esofthead.mycollab.module.crm.domain.ContactOpportunityExample;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import java.util.GregorianCalendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = "Crm", type = "Contact", nameField = "lastname")
@Auditable(module = "Crm", type = "Contact")
public class ContactServiceImpl extends DefaultService<Integer, SimpleContact, ContactSearchCriteria> implements
        ContactService {
    
    @Autowired
    private ContactMapper contactMapper;
    @Autowired
    private ContactMapperExt contactMapperExt;
    @Autowired
    private AccountContactMapper accountContactMapper;
    @Autowired
    private ContactOpportunityMapper contactOpportunityMapper;
    
    @Override
    public ICrudGenericDAO<Integer, Contact> getCrudMapper() {
        return contactMapper;
    }
    
    @Override
    public ISearchableDAO<ContactSearchCriteria> getSearchMapper() {
        return contactMapperExt;
    }
    
    @Override
    public int saveWithSession(SimpleContact record, String username) {
        int contactId = super.saveWithSession(record, username);
        
        if (record.getAccountId() != null) {
            AccountContact accountContactRel = new AccountContact();
            accountContactRel.setAccountid(record.getAccountId());
            accountContactRel.setContactid(contactId);
            accountContactRel.setCreatedtime(new GregorianCalendar().getTime());
            accountContactMapper.insert(accountContactRel);
        }
        return contactId;
    }
    
    @Override
    public int updateWithSession(SimpleContact record, String username) {
        return super.updateWithSession(record, username);
    }
    
    @Override
    public SimpleContact findContactById(int contactId) {
        return contactMapperExt.findContactById(contactId);
    }
    
    @Override
    public SimpleContact findByPrimaryKey(Integer primaryKey) {
        return findContactById(primaryKey);
    }
    
    @Override
    public void postUpdate(SimpleContact oldValue, SimpleContact newValue) {
        if (oldValue.getAccountId() != newValue.getAccountId()) {
            if (oldValue.getAccountId() == null) {
                AccountContact accountContactRel = new AccountContact();
                accountContactRel.setAccountid(newValue.getAccountId());
                accountContactRel.setContactid(newValue.getId());
                accountContactRel.setCreatedtime(new GregorianCalendar().getTime());
                accountContactMapper.insert(accountContactRel);
            } else {
                AccountContactExample ex = new AccountContactExample();
                ex.createCriteria().andAccountidEqualTo(oldValue.getAccountId());
                ex.createCriteria().andContactidEqualTo(oldValue.getId());
                accountContactMapper.deleteByExample(ex);
                
                
                if (newValue.getAccountId() != null) {
                    AccountContact accountContactRel = new AccountContact();
                    accountContactRel.setAccountid(newValue.getAccountId());
                    accountContactRel.setContactid(newValue.getId());
                    accountContactRel.setCreatedtime(new GregorianCalendar().getTime());
                    accountContactMapper.insert(accountContactRel);
                }
            }
        }
    }
    
    @Override
    public void removeContactOpportunityRelationship(ContactOpportunity associateOpportunity) {
        ContactOpportunityExample ex = new ContactOpportunityExample();
        ex.createCriteria().andContactidEqualTo(associateOpportunity.getContactid()).andOpportunityidEqualTo(associateOpportunity.getOpportunityid());
        contactOpportunityMapper.deleteByExample(ex);
    }
    
    @Override
    public void saveContactOpportunityRelationship(List<ContactOpportunity> associateOpportunities) {
        for (ContactOpportunity assoOpportunity : associateOpportunities) {
            ContactOpportunityExample ex = new ContactOpportunityExample();
            ex.createCriteria().andContactidEqualTo(assoOpportunity.getContactid()).andOpportunityidEqualTo(assoOpportunity.getOpportunityid());
            if (contactOpportunityMapper.countByExample(ex) == 0) {
                contactOpportunityMapper.insert(assoOpportunity);
            }
        }
    }
}
