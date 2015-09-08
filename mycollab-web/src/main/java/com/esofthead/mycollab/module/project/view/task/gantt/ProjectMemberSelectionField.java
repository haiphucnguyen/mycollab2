package com.esofthead.mycollab.module.project.view.task.gantt;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.vaadin.ui.ComboBox;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
class ProjectMemberSelectionField extends ComboBox {
    ProjectMemberSelectionField() {
        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
        this.setNullSelectionAllowed(true);

        ProjectMemberSearchCriteria criteria = new ProjectMemberSearchCriteria();
        criteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
//        criteria.setStatus(new StringSearchField(ProjectMemberStatusConstants.ACTIVE));

        ProjectMemberService userService = ApplicationContextUtil.getSpringBean(ProjectMemberService.class);
        List<SimpleProjectMember> memberList = userService.findPagableListByCriteria(new SearchRequest<>(criteria, 0, Integer.MAX_VALUE));
        loadUserList(memberList);
    }

    private void loadUserList(List<SimpleProjectMember> memberList) {
        for (SimpleProjectMember member : memberList) {
            this.addItem(member.getUsername());
            this.setItemCaption(member.getUsername(), StringUtils.trim(member.getDisplayName(), 25, true));
            this.setItemIcon(member.getUsername(), UserAvatarControlFactory.createAvatarResource(member.getMemberAvatarId(), 16));
        }
    }
}
