package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.persistence.service.ICrudService;

@Aspect
@Component
@Configurable
public class SaveItemCacheEvictAspect {
	private Logger log = LoggerFactory
			.getLogger(SaveItemCacheEvictAspect.class);

	@AfterReturning("execution(public * com.esofthead.mycollab..service..*.saveWithSession(..)) && args(bean, username)")
	public void cacheEvictSave(JoinPoint pjp, Object bean, String username) {
		Advised advised = (Advised) pjp.getThis();
		Class<ICrudService> cls = (Class<ICrudService>) advised
				.getTargetSource().getTargetClass();

		final Signature signature = pjp.getStaticPart().getSignature();
		CacheEvict cacheable = null;
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			Method method = ms.getMethod();
			cacheable = method.getAnnotation(CacheEvict.class);
			if (cacheable != null) {
				try {
					Integer accountId = (Integer) PropertyUtils.getProperty(
							bean, "saccountid");
					if (accountId != null) {
						CacheUtils.cleanCache(cls, accountId);
					}
				} catch (Exception e) {
					log.error("Error when clean cache", e);
				}
			}
		}
	}
}
