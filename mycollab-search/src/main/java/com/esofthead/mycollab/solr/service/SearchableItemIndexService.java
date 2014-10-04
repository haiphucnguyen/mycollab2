package com.esofthead.mycollab.solr.service;

import com.esofthead.mycollab.solr.domain.SearchableItem;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 *
 */
public interface SearchableItemIndexService {
	public void addToIndex(SearchableItem item);

	public void deleteFromIndex(Long index);
}
