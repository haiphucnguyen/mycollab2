package com.esofthead.mycollab.solr.service;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.esofthead.mycollab.solr.domain.SearchableItem;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public interface SolrItemRepository extends
		SolrCrudRepository<SearchableItem, String> {

}
