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
package com.esofthead.mycollab.module.project.service.ibatis;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria;
import com.esofthead.mycollab.common.interceptor.service.Traceable;
import com.esofthead.mycollab.core.MessageDispatcher;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.ISearchableDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultService;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.dao.PermissionMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMapper;
import com.esofthead.mycollab.module.project.dao.ProjectMapperExt;
import com.esofthead.mycollab.module.project.domain.PermissionExample;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectActivityStream;
import com.esofthead.mycollab.module.project.domain.ProjectExample;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectService;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Traceable(module = ModuleNameConstants.PRJ, nameField = "name", type = ProjectContants.PROJECT, extraFieldName = "id")
public class ProjectServiceImpl extends DefaultService<Integer, Project, ProjectSearchCriteria> implements
        ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectMapperExt projectMapperExt;

    @Override
    public ICrudGenericDAO<Integer, Project> getCrudMapper() {
        return projectMapper;
    }

    @Override
    public ISearchableDAO<ProjectSearchCriteria> getSearchMapper() {
        return projectMapperExt;
    }

    @Override
    public int saveWithSession(Project record, String username) {

        int projectid = super.saveWithSession(record, username);

        return projectid;
    }

    @Override
    public List<ProjectActivityStream> getProjectActivityStreams(SearchRequest<ActivityStreamSearchCriteria> searchRequest) {
        return projectMapperExt.getProjectActivityStreams(searchRequest.getSearchCriteria(),
                new RowBounds((searchRequest.getCurrentPage() - 1)
                * searchRequest.getNumberOfItems(), searchRequest.getNumberOfItems()));
    }

    @Override
    public int getTotalActivityStream(ActivityStreamSearchCriteria searchCriteria) {
        return projectMapperExt.getTotalActivityStream(searchCriteria);
    }

    @Override
    public SimpleProject findProjectById(int projectId) {
        return projectMapperExt.findProjectById(projectId);
    }
}
