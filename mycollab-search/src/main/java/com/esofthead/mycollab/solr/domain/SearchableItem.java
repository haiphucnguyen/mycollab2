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
package com.esofthead.mycollab.solr.domain;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.3.2
 *
 */
public class SearchableItem {

	@Id
	@Field
	private String id;

	@Field
	private Integer sAccountId;

	@Field
	private String type;

	@Field
	private String typeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getsAccountId() {
		return sAccountId;
	}

	public void setsAccountId(Integer sAccountId) {
		this.sAccountId = sAccountId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
}
