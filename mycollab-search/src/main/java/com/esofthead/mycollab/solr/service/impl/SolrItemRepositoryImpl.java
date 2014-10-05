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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.esofthead.mycollab.solr.domain.SearchableItem;
import com.esofthead.mycollab.solr.service.SolrItemRepository;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
@Repository("solrRepository")
public class SolrItemRepositoryImpl implements SolrItemRepository {

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterable<SearchableItem> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<SearchableItem> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SearchableItem> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends SearchableItem> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SearchableItem findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<SearchableItem> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<SearchableItem> findAll(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(SearchableItem entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends SearchableItem> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
