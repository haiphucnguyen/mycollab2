/**
 * Engroup - Enterprise Groupware Platform Copyright (C) 2007-2009 eSoftHead
 * Company <engroup@esofthead.com> http://www.esofthead.com
 *
 * Licensed under the GPL, Version 3.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.gnu.org/licenses/gpl.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.module.user.PermissionMap;
import java.util.Date;

public class SimpleUser extends User {

    private static final long serialVersionUID = 1L;
    
    public static final String ACTIVE_STATUS = "active";
    public static final String INACTION_STATUS = "inactive";
    public static final String PENDING_STATUS = "pending";
    
    public static final int ADMIN_VAL = 1;
    
    private Date lastAccessedTime;
    
    private PermissionMap permissionMaps;

    public Date getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(Date lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public String getDisplayName() {
        return getFirstname() + " " + getLastname();
    }

    public PermissionMap getPermissionMaps() {
        return permissionMaps;
    }

    public void setPermissionMaps(PermissionMap permissionMaps) {
        this.permissionMaps = permissionMaps;
    }
}
