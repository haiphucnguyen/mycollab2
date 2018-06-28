/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.crm.service.impl

import com.mycollab.db.persistence.ISearchableDAO
import com.mycollab.db.persistence.service.DefaultSearchService
import com.mycollab.module.crm.dao.ContactOpportunityMapperExt
import com.mycollab.module.crm.domain.criteria.ContactSearchCriteria
import com.mycollab.module.crm.service.ContactOpportunityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@Service
class ContactOpportunityServiceImpl : DefaultSearchService<ContactSearchCriteria>(), ContactOpportunityService {

    @Autowired
    private val contactOpportunityMapperExt: ContactOpportunityMapperExt? = null

    override val searchMapper: ISearchableDAO<ContactSearchCriteria>
        get() = contactOpportunityMapperExt as ISearchableDAO<ContactSearchCriteria>

}
