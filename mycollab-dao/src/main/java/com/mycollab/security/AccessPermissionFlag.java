package com.mycollab.security;

/**
 * Access permission flag
 *
 * @author MyCollab Ltd
 * @since 1.0
 */
public class AccessPermissionFlag extends PermissionFlag {
    public static final int NO_ACCESS = 0;
    public static final int READ_ONLY = 1;
    public static final int READ_WRITE = 2;
    public static final int ACCESS = 4;

    /**
     * Check whether <code>flag</code> implies read permission
     *
     * @param flag
     * @return true of <code>flag</code> implies read permission
     */
    public static boolean canRead(Integer flag) {
        return ((flag & READ_ONLY) == READ_ONLY)
                || ((flag & READ_WRITE) == READ_WRITE)
                || ((flag & ACCESS) == ACCESS);
    }

    /**
     * Check whether <code>flag</code> implies write permission
     *
     * @param flag
     * @return true of <code>flag</code> implies write permission
     */
    public static boolean canWrite(int flag) {
        return ((flag & READ_WRITE) == READ_WRITE) || ((flag & ACCESS) == ACCESS);
    }

    /**
     * Check whether <code>flag</code> implies access permission
     *
     * @param flag
     * @return true of <code>flag</code> implies access permission
     */
    public static boolean canAccess(Integer flag) {
        return ((flag & ACCESS) == ACCESS);
    }
}
