package com.mycollab.module.project;

import com.mycollab.module.project.domain.SimpleProjectMember;
import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.security.PermissionMap;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class ProjectPermissionChecker {
    private static ProjectMemberService getMemberService() {
        return AppContextUtil.getSpringBean(ProjectMemberService.class);
    }

    public static boolean canWrite(Integer prjId, String permissionItem) {
        SimpleProjectMember member = getMemberService().findMemberByUsername(UserUIContext.getUsername(), prjId, MyCollabUI.getAccountId());
        if (member != null) {
            if (member.isProjectOwner()) {
                return true;
            } else {
                PermissionMap permissionMap = member.getPermissionMaps();
                return (permissionMap != null) && permissionMap.canWrite(permissionItem);
            }
        } else {
            return false;
        }
    }
}
