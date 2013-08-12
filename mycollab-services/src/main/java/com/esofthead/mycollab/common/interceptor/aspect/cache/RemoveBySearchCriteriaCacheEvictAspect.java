package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;

@Aspect
@Component
@Configurable
public class RemoveBySearchCriteriaCacheEvictAspect {

	@AfterReturning("execution(public * com.esofthead.mycollab..service..*.removeByCriteria(..)) && args(searchCriteria, accountId)")
	public void cacheEvictSave(JoinPoint pjp, SearchCriteria searchCriteria,
			Integer accountId) {
		Advised advised = (Advised) pjp.getThis();
		Class<ISearchableService> cls = (Class<ISearchableService>) advised
				.getTargetSource().getTargetClass();

		final Signature signature = pjp.getStaticPart().getSignature();
		CacheEvict cacheable = null;
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			Method method = ms.getMethod();
			cacheable = method.getAnnotation(CacheEvict.class);
			if (cacheable != null) {
				CacheUtils.cleanCache(cls, accountId);
			}
		}
	}
}
