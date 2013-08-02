/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.tracker.dao;

import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.module.tracker.domain.SimpleVersion;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;

/**
 *
 * @author haiphucnguyen
 */
public interface VersionMapperExt extends ISearchableDAO<VersionSearchCriteria> {

    SimpleVersion findVersionById(int versionId);
    
}
