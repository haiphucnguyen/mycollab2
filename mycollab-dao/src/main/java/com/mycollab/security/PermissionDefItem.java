package com.mycollab.security;

/**
 * Permission item
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class PermissionDefItem {
    private String key;

    /**
     * Display name of permission
     */
    private Enum caption;

    /**
     * Permission flag of permission
     */
    private Class<? extends PermissionFlag> permissionCls;

    public PermissionDefItem(String key, Enum caption, Class<? extends PermissionFlag> permissionCls) {
        this.key = key;
        this.caption = caption;
        this.permissionCls = permissionCls;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Enum getCaption() {
        return caption;
    }

    public void setCaption(Enum caption) {
        this.caption = caption;
    }

    public Class<? extends PermissionFlag> getPermissionCls() {
        return permissionCls;
    }

    public void setPermissionCls(Class<? extends PermissionFlag> permissionCls) {
        this.permissionCls = permissionCls;
    }
}
