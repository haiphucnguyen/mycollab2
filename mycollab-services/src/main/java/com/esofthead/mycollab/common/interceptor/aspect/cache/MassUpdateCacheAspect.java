package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.infinispan.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.core.cache.CacheEvict;

@Aspect
@Component
@Configurable
public class MassUpdateCacheAspect {
	private static Logger log = LoggerFactory
			.getLogger(MassUpdateCacheAspect.class);

	@AfterReturning("execution(public * com.esofthead.mycollab..service..*.massUpdateWithSession(..)) && args(bean, primaryKeys, accountId)")
	public void cacheEvictSave(JoinPoint pjp, Object bean, List primaryKeys,
			Integer accountId) {
		Advised advised = (Advised) pjp.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();

		final Signature signature = pjp.getStaticPart().getSignature();
		CacheEvict cacheable = null;
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			Method method = ms.getMethod();
			cacheable = method.getAnnotation(CacheEvict.class);
			if (cacheable != null) {
				try {
					BasicCache<String, Object> cache = LocalCacheManager
							.getCache(accountId.toString());
					Set<String> keys = cache.keySet();
					if (keys != null && keys.size() > 0) {
						String keyPrefix = String.format("%s-%d-",
								cls.getName(), accountId);

						String[] keyArr = keys.toArray(new String[0]);
						for (int i = 0; i < keyArr.length; i++) {
							if (keyArr[i].startsWith(keyPrefix)) {
								log.debug("Remove cache key {}", keyArr[i]);
								cache.removeAsync(keyArr[i]);
							}
						}

					}
				} catch (Exception e) {
					log.error("Error when clean cache", e);
				}
			}
		}
	}
}
