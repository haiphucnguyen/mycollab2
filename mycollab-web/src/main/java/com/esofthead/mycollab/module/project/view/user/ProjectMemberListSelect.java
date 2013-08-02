package com.esofthead.mycollab.module.project.view.user;

import java.util.List;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.ui.ListSelect;

public @SuppressWarnings("serial")
class ProjectMemberListSelect extends ListSelect {

    public ProjectMemberListSelect() {
        super("username");
        this.setItemCaptionMode(ITEM_CAPTION_MODE_PROPERTY);
        this.setMultiSelect(true);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void attach() {
        super.attach();
        this.removeAllItems();
        ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
        criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));

        ProjectMemberService userService = AppContext.getSpringBean(ProjectMemberService.class);
        List<SimpleProjectMember> userList = userService
                .findPagableListByCriteria(new SearchRequest<ProjectMemberSearchCriteria>(
                criteria, 0, Integer.MAX_VALUE));

        BeanContainer<String, SimpleProjectMember> beanItem = new BeanContainer<String, SimpleProjectMember>(
        		SimpleProjectMember.class);
        beanItem.setBeanIdProperty("username");

        for (SimpleProjectMember user : userList) {
            beanItem.addBean(user);
        }

        this.setContainerDataSource(beanItem);
        this.setItemCaptionPropertyId("memberFullName");
        this.setRows(4);
    }
}
