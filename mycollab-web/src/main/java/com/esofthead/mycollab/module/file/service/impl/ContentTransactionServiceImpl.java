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
package com.esofthead.mycollab.module.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.mybatis.DefaultService;
import com.esofthead.mycollab.module.file.dao.ContentTransactionMapper;
import com.esofthead.mycollab.module.file.dao.ContentTransactionMapperExt;
import com.esofthead.mycollab.module.file.domain.ContentTransaction;
import com.esofthead.mycollab.module.file.domain.criteria.ContentTransactionSearchCriteria;
import com.esofthead.mycollab.module.file.service.ContentTransactionService;

@Service
public class ContentTransactionServiceImpl extends
		DefaultService<Integer, ContentTransaction, ContentTransactionSearchCriteria>
		implements ContentTransactionService {

	@Autowired
	private ContentTransactionMapper contentTransactionMapper;
	
	@Autowired
	private ContentTransactionMapperExt contentTransactionMapperExt;

	@Override
	public ICrudGenericDAO<Integer, ContentTransaction> getCrudMapper() {
		return contentTransactionMapper;
	}

	@Override
	public ISearchableDAO<ContentTransactionSearchCriteria> getSearchMapper() {
		return contentTransactionMapperExt;
	}

	
}
