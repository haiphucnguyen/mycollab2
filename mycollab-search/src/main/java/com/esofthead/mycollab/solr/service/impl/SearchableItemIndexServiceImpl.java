/**
 * This file is part of mycollab-search.
 *
 * mycollab-search is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-search is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-search.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.solr.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esofthead.mycollab.solr.domain.SearchableItem;
import com.esofthead.mycollab.solr.service.SearchableItemIndexService;
import com.esofthead.mycollab.solr.service.SolrItemRepository;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
@Service
public class SearchableItemIndexServiceImpl implements
		SearchableItemIndexService {

	@Resource(name = "solrRepository")
	private SolrItemRepository solrRepository;

	@Override
	public void addToIndex(SearchableItem item) {
		// solrRepository.save(item);
	}

	@Transactional
	@Override
	public void deleteFromIndex(Long index) {
		// solrRepository.delete(index.toString());

	}

}
