package com.esofthead.mycollab.cache;

import java.io.Writer;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
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
				getEnclosingServiceInterface(serviceClass), accountId);
	}

	public static String getEnclosingServiceInterface(Class serviceClass) {
		Class<?> cls = ClassUtils.getInterfaceInstanceOf(serviceClass,
				ICrudService.class);
		if (cls == null) {
			cls = ClassUtils.getInterfaceInstanceOf(serviceClass,
					ISearchableService.class);
		}

		if (cls == null) {
			cls = ClassUtils.getInterfaceInstanceOf(serviceClass,
					IDefaultService.class);
		}

		if (cls == null) {
			throw new MyCollabException(
					"Can not get enclosing interface service");
		}

		return cls.getName();
	}

	public static void cleanCache(Integer accountId, String prefixKey) {
		LocalCacheManager.removeCacheItems(accountId.toString(), prefixKey);
	}
}
