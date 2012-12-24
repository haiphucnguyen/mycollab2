/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.crm.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.crm.Constants;
import com.esofthead.mycollab.module.crm.dao.ContactMapper;
import com.esofthead.mycollab.module.crm.dao.ContactMapperExt;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.TaskExample;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

@Service
@Transactional
public class ContactServiceImpl extends
		DefaultService<Integer, Contact, ContactSearchCriteria> implements
		ContactService {

	@Autowired
	private TaskMapper taskMapper;

	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private ContactMapper contactMapper;

	@Autowired
	private ContactMapperExt contactMapperExt;

	@Override
	public ICrudGenericDAO<Integer, Contact> getCrudMapper() {
		return contactMapper;
	}

	@Override
	public ISearchableDAO<ContactSearchCriteria> getSearchMapper() {
		return contactMapperExt;
	}

	public int remove(Integer primaryKey) {
		int result = super.remove(primaryKey);
		TaskExample ex = new TaskExample();
		ex.createCriteria().andTypeEqualTo(Constants.CONTACT)
				.andTypeidEqualTo(primaryKey);
		taskMapper.deleteByExample(ex);
		return result;
	}

	@Override
	public int updateWithSession(Contact record, String username) {
		Contact oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-contact-" + record.getId();
		auditLogService.saveAuditLog(username, refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, username);
	}

	public SimpleContact findContactById(int contactId) {
		return contactMapperExt.findContactById(contactId);
	}

}
