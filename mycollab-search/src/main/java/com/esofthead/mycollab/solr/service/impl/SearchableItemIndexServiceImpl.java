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

	@Resource
	private SolrItemRepository repository;

	@Override
	public void addToIndex(SearchableItem item) {
		repository.save(item);
	}

	@Transactional
	@Override
	public void deleteFromIndex(Long index) {
		repository.delete(index.toString());

	}

}
