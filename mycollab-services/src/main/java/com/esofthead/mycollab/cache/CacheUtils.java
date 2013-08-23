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
}
