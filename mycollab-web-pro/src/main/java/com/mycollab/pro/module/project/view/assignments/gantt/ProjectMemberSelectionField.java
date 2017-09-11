package com.mycollab.pro.module.project.view.assignments.gantt;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.ui.UserAvatarControlFactory;
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
        criteria.addOrderField(new SearchCriteria.OrderField("memberFullName", SearchCriteria.Companion.getASC()));
//        criteria.setStatus(StringSearchField.and(ProjectMemberStatusConstants.ACTIVE));

        ProjectMemberService userService = AppContextUtil.getSpringBean(ProjectMemberService.class);
        List<SimpleProjectMember> memberList = userService.findPageableListByCriteria(new BasicSearchRequest<>(criteria, 0, Integer.MAX_VALUE));
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
