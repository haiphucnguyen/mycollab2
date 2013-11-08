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
package com.esofthead.mycollab.cache;

import java.io.Writer;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;
import com.thoughtworks.xstream.io.json.JsonWriter;
import com.thoughtworks.xstream.io.json.JsonWriter.Format;

public class CacheUtils {
	private static final XStream jsonXStream = new XStream(
			new JsonHierarchicalStreamDriver() {
				public HierarchicalStreamWriter createWriter(Writer writer) {

					return new JsonWriter(writer, JsonWriter.DROP_ROOT_MODE,
							new Format(new char[0], new char[0],
									Format.SPACE_AFTER_LABEL
											| Format.COMPACT_EMPTY_ELEMENT));
				}
			});

	public static String constructParamsKey(Object[] args) {
		return jsonXStream.toXML(args);
	}

	public static String getCachePrefix(Class serviceClass, Integer accountId) {
		return String.format("%s-%d",
				getEnclosingServiceInterfaceName(serviceClass), accountId);
	}

	public static Class getEnclosingServiceInterface(Class serviceClass) {
		Class<?> cls = ClassUtils.getInterfaceInstanceOf(serviceClass,
				IService.class);
		if (cls == null) {
			throw new MyCollabException(
					"Can not get enclosing interface service of class "
							+ serviceClass.getName());
		}

		return cls;
	}

	public static String getEnclosingServiceInterfaceName(Class serviceClass) {
		return getEnclosingServiceInterface(serviceClass).getName();
	}

	public static void cleanCache(Integer accountId, String prefixKey) {
		LocalCacheManager.removeCacheItems(accountId.toString(), prefixKey);
	}

	public static void cleanCaches(Integer accountId, Class... classes) {
		for (Class prefKey : classes) {
			cleanCache(accountId, prefKey.getName());
		}
	}
}
