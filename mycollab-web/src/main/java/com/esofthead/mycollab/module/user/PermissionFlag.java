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

    public static final int READ_ONLY = 1;
    public static final int READ_WRITE = 2;
    public static final int ADMIN = 4;

    public static boolean canRead(int flag) {
        return ((flag & READ_ONLY) == READ_ONLY) || ((flag & READ_WRITE) == READ_WRITE) || ((flag & ADMIN) == ADMIN);
    }
    
    public static boolean canWrite(int flag) {
       return ((flag & READ_WRITE) == READ_WRITE) || ((flag & ADMIN) == ADMIN); 
    }
    
    public static boolean canAccess(int flag) {
        return  ((flag & ADMIN) == ADMIN);
    }
}
