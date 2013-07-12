package com.esofthead.mycollab.common.interceptor.service;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.cache.mybatis.InfinispanCache;

@Aspect
@Component
@Configurable
public class CacheManagerAspect {

	@AfterReturning("execution(public * com.esofthead.mycollab..service..*.*(..)) && @annotation(flushcache)")
	public void traceAfterUpdateActivity(FlushCache flushcache) {
		Class[] ids = flushcache.ids();
		for (Class id : ids) {
			InfinispanCache.clearCache(id.getName());
		}
	}
}
