package com.mycollab.security;

import com.mycollab.common.i18n.SecurityI18nEnum;

/**
 * Boolean permission flag
 *
 * @author MyCollab Ltd
 * @version 1.0
 */
public class BooleanPermissionFlag extends PermissionFlag {
    public static final int TRUE = 128;
    public static final int FALSE = 129;

    /**
     * Check whether <code>flag</code> is true permission
     *
     * @param flag
     * @return
     */
    public static boolean beTrue(Integer flag) {
        return (flag == TRUE);
    }

    /**
     * Check whether <code>flag</code> is false permission
     *
     * @param flag
     * @return
     */
    public static boolean beFalse(Integer flag) {
        return (flag == FALSE);
    }

    public static SecurityI18nEnum toKey(Integer flag) {
        return (flag == TRUE) ? SecurityI18nEnum.YES : SecurityI18nEnum.NO;
    }
}
