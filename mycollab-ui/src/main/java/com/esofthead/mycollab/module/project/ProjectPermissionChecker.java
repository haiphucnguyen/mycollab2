package com.esofthead.mycollab.module.project;

import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.security.PermissionMap;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class ProjectPermissionChecker {
    private static ProjectMemberService getMemberService() {
        return ApplicationContextUtil.getSpringBean(ProjectMemberService.class);
    }

    public static boolean canWrite(Integer prjId, String permissionItem) {
        SimpleProjectMember member = getMemberService().findMemberByUsername(AppContext.getUsername(), prjId, AppContext.getAccountId());
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
