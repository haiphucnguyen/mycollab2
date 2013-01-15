/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user;

/**
 *
 * @author haiphucnguyen
 */
public class PermissionFlag {

    public static final int NO_ACCESS = 0;
    public static final int READ_ONLY = 1;
    public static final int READ_WRITE = 2;
    public static final int ACCESS = 4;

    public static boolean canRead(int flag) {
        return ((flag & READ_ONLY) == READ_ONLY) || ((flag & READ_WRITE) == READ_WRITE) || ((flag & ACCESS) == ACCESS);
    }

    public static boolean canWrite(int flag) {
        return ((flag & READ_WRITE) == READ_WRITE) || ((flag & ACCESS) == ACCESS);
    }

    public static boolean canAccess(int flag) {
        return ((flag & ACCESS) == ACCESS);
    }

    public static String toString(int flag) {
        if (flag == NO_ACCESS) {
            return "No Access";
        } else if (flag == READ_ONLY) {
            return "Read Only";
        } else if (flag == READ_WRITE) {
            return "Read & Write";
        } else if (flag == ACCESS) {
            return "Access";
        } else {
            return "";
        }
    }
}
