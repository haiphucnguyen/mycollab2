package com.esofthead.mycollab.cache;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.service.ICrudService;
import com.esofthead.mycollab.core.persistence.service.IDefaultService;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.core.utils.ClassUtils;

public class CacheUtils {
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

	public static void cleanCache(Class serviceClass, Integer accountId) {
		String prefixKey = getCachePrefix(serviceClass, accountId);
		LocalCacheManager.removeCacheItems(accountId.toString(), prefixKey);
	}
}
