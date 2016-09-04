/**
 * This file is part of mycollab-caching.
 *
 * mycollab-caching is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-caching is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-caching.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.pro.cache.aspect;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.core.utils.ClassUtils;
import com.mycollab.db.persistence.service.IService;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class CacheUtils {

    static Class<?> getEnclosingServiceInterface(Class<?> serviceClass) {
        return ClassUtils.getInterfaceInstanceOf(serviceClass, IService.class);
    }

    static String getEnclosingServiceInterfaceName(Class<?> serviceClass) {
        return getEnclosingServiceInterface(serviceClass).getName();
    }

    static boolean isInBlackList(Class<?> cls) {
        return (cls != null) && (cls.getAnnotation(IgnoreCacheClass.class) != null);
    }
}
