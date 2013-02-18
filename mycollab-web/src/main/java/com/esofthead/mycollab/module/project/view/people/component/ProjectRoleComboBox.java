package com.esofthead.mycollab.module.project.view.people.component;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ComboBox;

public class ProjectRoleComboBox extends ComboBox {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public ProjectRoleComboBox() {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);

		ProjectRoleSearchCriteria criteria = new ProjectRoleSearchCriteria();
		SimpleProject project = (SimpleProject) AppContext
				.getVariable(ProjectContants.PROJECT_NAME);
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setProjectId(new NumberSearchField(project.getId()));

		ProjectRoleService roleService = AppContext
				.getSpringBean(ProjectRoleService.class);
		List<SimpleProjectRole> roleList = roleService
				.findPagableListByCriteria(new SearchRequest<ProjectRoleSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));

		BeanContainer<String, SimpleProjectRole> beanItem = new BeanContainer<String, SimpleProjectRole>(
				SimpleProjectRole.class);
		beanItem.setBeanIdProperty("id");

		for (SimpleProjectRole role : roleList) {
			beanItem.addBean(role);
		}

		this.setNullSelectionAllowed(false);
		this.setContainerDataSource(beanItem);
		this.setItemCaptionPropertyId("rolename");
		if (roleList.size() > 0) {
			this.setValue(this.getContainerPropertyIds().iterator().next());
		}
	}

}
