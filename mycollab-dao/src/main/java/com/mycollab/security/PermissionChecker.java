package com.mycollab.security;

import com.mycollab.core.MyCollabException;

/**
 * Utility to check permission value
 *
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public class PermissionChecker {
    /**
     * @param flag
     * @return true if <code>flag</code> is boolean permission flag
     */
    public static boolean isBooleanPermission(Integer flag) {
        return (flag >> 7) == 1;
    }

    /**
     * @param flag
     * @return true of <code>flag</code> is access permission
     */
    public static boolean isAccessPermission(Integer flag) {
        return (flag >> 3) == 0;
    }

    /**
     * Check whether permission value <code>flag</code> implies permission value
     * <code>impliedVal</code>
     *
     * @param flag
     * @param impliedVal
     * @return
     */
    public static boolean isImplied(int flag, int impliedVal) {
        if (isBooleanPermission(flag)) {
            return flag == impliedVal;
        } else if (isAccessPermission(flag)) {
            if (impliedVal == AccessPermissionFlag.READ_ONLY) {
                return AccessPermissionFlag.canRead(flag);
            } else if (impliedVal == AccessPermissionFlag.READ_WRITE) {
                return AccessPermissionFlag.canWrite(flag);
            } else if (impliedVal == AccessPermissionFlag.ACCESS) {
                return AccessPermissionFlag.canAccess(flag);
            } else {
                return true;
            }
        } else {
            throw new MyCollabException("Do not support permission category except boolean and access permission, the check permission is " + flag);
        }
    }
}
