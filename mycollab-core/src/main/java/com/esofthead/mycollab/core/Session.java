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
package com.esofthead.mycollab.core;

import java.io.Serializable;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public final class Session implements Serializable {
    private static final long serialVersionUID = 1L;

    public static String LAST_ACCESSED_TIME = "LAST_ACCESSED_TIME";

    private String username;
    
    private String sessionid;

    private Map<String, Object> attributeMap = new Hashtable<String, Object>();

    public Session(String username) {
        this.username = username;
        
        this.sessionid = username + new GregorianCalendar().getTimeInMillis();
    }

    public String getSessionid() {
		return sessionid;
	}
    
	public Set<String> getAttributeKeys() {
        return attributeMap.keySet();
    }

    public Collection<Object> attributeValues() {
        return attributeMap.values();
    }

    public synchronized Object getAttribute(String key) {
        return attributeMap.get(key);
    }

    public synchronized void removeAttribute(String key) {
        if (attributeMap.get(key) != null) {
            attributeMap.remove(key);
        }
    }

    public synchronized void setAttribute(String key, Object value) {
        if (attributeMap.get(key) != null) {
            attributeMap.remove(key);
        }
        attributeMap.put(key, value);
    }

    public String getRemoteUser() {
        return this.username;
    }
}

