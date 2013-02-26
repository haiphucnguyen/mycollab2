/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.core.persistence.service;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import java.util.List;

import net.bull.javamelody.MonitoredWithSpring;

/**
 * Engroup serivice supports pagable/search data.
 *
 * @param <S>
 */
public interface ISearchableService<S extends SearchCriteria> extends IService {

    /**
     * Get the total available items base on search criteria.
     *
     * @param criteria
     * @return
     */
	@MonitoredWithSpring
    int getTotalCount(S criteria);

    /**
     *
     * @param criteria
     * @param skipNum
     * @param maxResult
     * @return
     */
    @MonitoredWithSpring
    List findPagableListByCriteria(SearchRequest<S> searchRequest);

    void removeByCriteria(S criteria);
    
    Integer getNextItemKey(S criteria);
    
    Integer getPreviousItemKey(S criteria);
}
