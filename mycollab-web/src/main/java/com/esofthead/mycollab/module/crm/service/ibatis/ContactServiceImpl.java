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

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.esofthead.mycollab.core.PlatformManager;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultCrudService;
import com.esofthead.mycollab.module.crm.Constants;
import com.esofthead.mycollab.module.crm.dao.ContactMapperExt;
import com.esofthead.mycollab.module.crm.dao.TaskMapper;
import com.esofthead.mycollab.module.crm.domain.Contact;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.domain.TaskExample;
import com.esofthead.mycollab.module.crm.domain.criteria.ContactSearchCriteria;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.shared.audit.service.AuditLogService;

public class ContactServiceImpl extends DefaultCrudService<Contact, Integer>
		implements ContactService {

	private ContactMapperExt contactExtDAO;

	private TaskMapper taskDAO;

	private AuditLogService auditLogService;

	public void setAuditLogService(AuditLogService auditLogService) {
		this.auditLogService = auditLogService;
	}

	public void setTaskDAO(TaskMapper taskDAO) {
		this.taskDAO = taskDAO;
	}

	public void setContactExtDAO(ContactMapperExt contactExtDAO) {
		this.contactExtDAO = contactExtDAO;
	}

	public int remove(Integer primaryKey) {
		int result = super.remove(primaryKey);
		TaskExample ex = new TaskExample();
		ex.createCriteria().andTypeEqualTo(Constants.CONTACT)
				.andTypeidEqualTo(primaryKey);
		taskDAO.deleteByExample(ex);
		return result;
	}

	@Override
	public int updateWithSession(Contact record, String userSessionId) {
		Contact oldValue = this.findByPrimaryKey(record.getId());
		String refid = "crm-contact-" + record.getId();
		auditLogService.saveAuditLog(
				PlatformManager.getInstance().getSession(userSessionId)
						.getRemoteUser(), refid, (Object) oldValue,
				(Object) record);
		return super.updateWithSession(record, userSessionId);
	}

	public List<SimpleContact> findPagableListByCriteria(
			ContactSearchCriteria criteria, int skipNum, int maxResult) {
		return contactExtDAO.findPagableList(criteria, new RowBounds(skipNum,
				maxResult));
	}

	public int getTotalCount(ContactSearchCriteria criteria) {
		return contactExtDAO.getTotalCount(criteria);
	}

	public SimpleContact findContactById(int contactId) {
		return contactExtDAO.findContactById(contactId);
	}

}
