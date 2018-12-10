/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.project.ui.components;

import com.mycollab.core.MyCollabException;
import com.mycollab.core.SecureAccessException;
import com.mycollab.module.project.domain.Project;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.vaadin.data.Converter;
import com.vaadin.data.Result;
import com.vaadin.data.ValueContext;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ItemCaptionGenerator;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
public class UserProjectComboBox extends ComboBox implements Converter<SimpleProject, Integer> {

    private List<SimpleProject> projects;

    public UserProjectComboBox(String username) {
        ProjectService projectService = AppContextUtil.getSpringBean(ProjectService.class);
        projects = projectService.getProjectsUserInvolved(username, AppUI.getAccountId());
        setItems(projects);
        setItemCaptionGenerator((ItemCaptionGenerator<SimpleProject>) Project::getName);
    }

    public void setSelectedProjectId(Integer projectId) {
        SimpleProject selectedItem = projects.stream().filter(project -> project.getId() == projectId).findFirst().get();
        if (selectedItem != null) {
            setSelectedItem(selectedItem);
        } else {
            throw new MyCollabException("Not found");
        }
    }

    public Integer getDefaultSelectedProject() {
        if (projects.size() > 0) {
            SimpleProject project = projects.get(0);
            setSelectedItem(project);
            return project.getId();
        } else {
            throw new SecureAccessException();
        }
    }

    @Override
    public Result<Integer> convertToModel(SimpleProject simpleProject, ValueContext valueContext) {
        return Result.ok(simpleProject.getId());
    }

    @Override
    public SimpleProject convertToPresentation(Integer projectId, ValueContext valueContext) {
        return projects.stream().filter(project -> project.getId() == projectId).findFirst().get();
    }
}
