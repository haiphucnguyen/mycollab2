/**
 * Engroup - Enterprise Groupware Platform
 * Copyright (C) 2007-2009 eSoftHead Company <engroup@esofthead.com>
 * http://www.esofthead.com
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.esofthead.mycollab.module.file_old.domain;

import java.util.ArrayList;


public class Folder extends Content {
    private java.util.Collection<Content> children = new ArrayList<Content>();

    public java.util.Collection<Content> getChildren() {
        return children;
    }

    public void setChildren(java.util.Collection<Content> children) {
        this.children = children;
    }

    public void addChild(Content node) {
        if (children == null) {
            children = new ArrayList<Content>();
        }
        children.add(node);
    }
    
    public java.util.Collection<IdentityPermission> getInheritPermissions() {
        ArrayList<IdentityPermission> result = new ArrayList<IdentityPermission>();

        java.util.Collection<IdentityPermission> permissions = getPermissions();
        for (IdentityPermission permission : permissions) {
            if (permission.isInherit()) {
                result.add(permission);
            }
        }

        return result;
    }

    public String getName() {
        String filePath = getPath();
        int lastIndex = filePath.lastIndexOf("/");
        if (lastIndex >= 0) {
            return filePath.substring(lastIndex + 1, filePath.length());
        }
        return "";
    }
}
