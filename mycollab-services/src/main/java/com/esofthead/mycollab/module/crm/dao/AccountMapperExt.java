/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.dao;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.core.persistence.IMassUpdateDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.domain.criteria.AccountSearchCriteria;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public interface AccountMapperExt extends
		ISearchableDAO<AccountSearchCriteria>,
		IMassUpdateDAO<Account, AccountSearchCriteria> {

	SimpleAccount findById(int accountId);

	SimpleAccount findAccountAssoWithConvertedLead(@Param("leadId") int leadId);
}
