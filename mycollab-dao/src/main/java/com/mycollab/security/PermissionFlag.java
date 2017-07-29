package com.mycollab.security;

import com.mycollab.common.i18n.SecurityI18nEnum;

import static com.mycollab.security.AccessPermissionFlag.*;
import static com.mycollab.security.BooleanPermissionFlag.FALSE;
import static com.mycollab.security.BooleanPermissionFlag.TRUE;

/**
 * Signal interface of Permission flag
 *
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public class PermissionFlag {
    public static SecurityI18nEnum toVal(Integer flag) {
        if (flag == null || flag == NO_ACCESS) {
            return SecurityI18nEnum.NO_ACCESS;
        } else if (flag == READ_ONLY) {
            return SecurityI18nEnum.READONLY;
        } else if (flag == READ_WRITE) {
            return SecurityI18nEnum.READ_WRITE;
        } else if (flag == ACCESS) {
            return SecurityI18nEnum.ACCESS;
        } else if (flag == TRUE) {
            return SecurityI18nEnum.YES;
        } else if (flag == FALSE) {
            return SecurityI18nEnum.NO;
        } else {
            return SecurityI18nEnum.UNDEFINE;
        }
    }
}
