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
@Repository
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
