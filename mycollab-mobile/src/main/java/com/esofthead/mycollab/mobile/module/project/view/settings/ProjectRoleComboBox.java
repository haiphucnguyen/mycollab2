package com.esofthead.mycollab.mobile.module.project.view.settings;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.mobile.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.mobile.ui.ValueComboBox;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectRoleSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectRoleService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.data.util.BeanContainer;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class ProjectRoleComboBox extends ValueComboBox {

	private static final long serialVersionUID = 1L;

	private List<SimpleProjectRole> roleList;

	@SuppressWarnings("unchecked")
	public ProjectRoleComboBox() {
		super();
		this.setImmediate(true);
		this.setItemCaptionMode(ItemCaptionMode.PROPERTY);

		ProjectRoleSearchCriteria criteria = new ProjectRoleSearchCriteria();
		criteria.setSaccountid(new NumberSearchField(SearchField.AND,
				AppContext.getAccountId()));
		criteria.setProjectId(new NumberSearchField(CurrentProjectVariables
				.getProjectId()));

		ProjectRoleService roleService = ApplicationContextUtil
				.getSpringBean(ProjectRoleService.class);
		roleList = roleService
				.findPagableListByCriteria(new SearchRequest<ProjectRoleSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));

		BeanContainer<String, SimpleProjectRole> beanItem = new BeanContainer<String, SimpleProjectRole>(
				SimpleProjectRole.class);
		beanItem.setBeanIdProperty("id");

		for (SimpleProjectRole role : roleList) {
			beanItem.addBean(role);
		}

		SimpleProjectRole ownerRole = new SimpleProjectRole();
		ownerRole.setId(-1);
		ownerRole.setRolename("Project Owner");
		beanItem.addBean(ownerRole);

		this.setNullSelectionAllowed(false);
		this.setContainerDataSource(beanItem);
		this.setItemCaptionPropertyId("rolename");
		if (roleList.size() > 0) {
			SimpleProjectRole role = roleList.get(0);
			this.setValue(role.getId());
		} else {
			this.setValue(-1);
		}
	}

}
