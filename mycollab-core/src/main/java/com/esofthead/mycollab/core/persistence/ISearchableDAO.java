/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.core.persistence;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ISearchableDAO<S extends SearchCriteria> {

	int getTotalCount(@Param("searchCriteria") S criteria);

	List findPagableListByCriteria(@Param("searchCriteria") S criteria,
			RowBounds rowBounds);

	Integer getNextItemKey(@Param("searchCriteria") S criteria);

	Integer getPreviousItemKey(@Param("searchCriteria") S criteria);

	void removeByCriteria(@Param("searchCriteria") S criteria);
}
