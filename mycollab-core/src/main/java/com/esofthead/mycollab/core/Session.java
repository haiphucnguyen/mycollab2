/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
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

