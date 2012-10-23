package com.esofthead.mycollab.module.file.validator;

import java.util.Collection;

import com.esofthead.mycollab.core.PlatformManager;
import com.esofthead.mycollab.core.Session;
import com.esofthead.mycollab.module.file.ContentException;
import com.esofthead.mycollab.module.file.domain.Content;
import com.esofthead.mycollab.module.file.domain.IdentityPermission;
import com.esofthead.mycollab.module.user.RoleConstants;
import com.esofthead.mycollab.module.user.domain.RoleCollection;
import com.esofthead.mycollab.module.user.domain.RolePermission;

public class DefaultAccessValidator implements AccessValidator {
    protected String userSessionId;

    protected Content content;

    protected Session session;

    public DefaultAccessValidator(String userSessionId, Content content) {
        this.content = content;
        this.session = PlatformManager.getInstance().getSession(userSessionId);
    }

    @Override
    public void validate() {
        if (content == null) {
            throw new ContentException(
                    "There is no permission content availabe");
        }
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    /**
     * 
     * @param userSessionId
     * @param roles
     * @param flags
     * @param permissions
     * @return
     */
    protected boolean hasRight(long[] flags) {
//        RoleCollection roles = session.getRoles();
//        Collection<IdentityPermission> permissions = content.getPermissions();
//
//        if (roles.impliesRole(RoleConstants.ADMINSITRATOR_ROLE)) {
//            return true;
//        }
//
//        for (IdentityPermission permission : permissions) {
//            if (permission instanceof UserPermission) {
//                if (((UserPermission) permission).getUsername().equals(
//                        session.getRemoteUser())
//                        && isImplyPermission(permission.getFlag(), flags)) {
//                    return true;
//                }
//            } else if (permission instanceof RolePermission) {
//                if (roles.impliesRole(((RolePermission) permission)
//                        .getRolename())
//                        && isImplyPermission(permission.getFlag(), flags)) {
//                    return true;
//                }
//            }
//        }
        return false;
    }

    private boolean isImplyPermission(long flag, long[] flags) {
        for (int i = 0; i < flags.length; i++) {
            if ((flag & flags[i]) == flags[i]) {
                return true;
            }
        }
        return false;
    }
}
