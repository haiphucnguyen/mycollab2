/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.service.ibatis;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.project.dao.ProjectCustomizeViewMapper;
import com.esofthead.mycollab.module.project.domain.ProjectCustomizeView;
import com.esofthead.mycollab.module.project.service.ProjectCustomizeViewService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.4.0
 *
 */
public class ProjectCustomizeViewServiceImpl extends
		DefaultCrudService<Integer, ProjectCustomizeView> implements
		ProjectCustomizeViewService {

	@Autowired
	private ProjectCustomizeViewMapper projectCustomizeMapper;

	@Override
	public ICrudGenericDAO<Integer, ProjectCustomizeView> getCrudMapper() {
		return projectCustomizeMapper;
	}

}
