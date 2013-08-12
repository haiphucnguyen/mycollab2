package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.infinispan.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.GroupableSearchCriteria;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.utils.BeanUtility;

@Aspect
@Component
@Configurable
public class GetListCacheAspect {
	private Logger log = LoggerFactory.getLogger(GetListCacheAspect.class);

	@Around("execution(public * com.esofthead.mycollab..service..*.findPagableListByCriteria(..)) && args(searchRequest)")
	public Object cacheFindList(ProceedingJoinPoint pjp,
			SearchRequest searchRequest) {
		Advised advised = (Advised) pjp.getThis();
		Class cls = advised.getTargetSource().getTargetClass();

		final Signature signature = pjp.getStaticPart().getSignature();
		Cacheable cacheable = null;
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			Method method = ms.getMethod();
			cacheable = method.getAnnotation(Cacheable.class);
		}

		try {

			if (cacheable != null) {
				try {
					SearchCriteria searchCriteria = searchRequest
							.getSearchCriteria();
					if (searchCriteria != null
							&& searchCriteria instanceof GroupableSearchCriteria) {
						NumberSearchField accountIdField = ((GroupableSearchCriteria) searchCriteria)
								.getSaccountid();
						if (accountIdField != null) {
							Integer accountId = (Integer) accountIdField
									.getValue();
							String key = "%s-%d-%s-%s[%d-%d]";
							key = String.format(key, CacheUtils
									.getEnclosingServiceInterface(cls),
									accountId, BeanUtility
											.printBeanObj(searchRequest
													.getSearchCriteria()),
									"findPagableListByCriteria", searchRequest
											.getCurrentPage(), searchRequest
											.getNumberOfItems());
							BasicCache<String, Object> cache = LocalCacheManager
									.getCache(accountId.toString());
							Object val = cache.get(key);
							if (val != null) {
								log.debug(
										"There is exist value of key {}, no need to query from database",
										key);
								return val;
							} else {
								log.debug(
										"There is no exist value of key {}, query from database then put it to cache",
										key);
								Object returnVal = pjp.proceed();
								cache.put(key, returnVal);
								return returnVal;
							}
						}
					}

					Object returnVal = pjp.proceed();
					return returnVal;
				} catch (Exception e) {
					log.error("Error while trying to cache data", e);
				}
			}

			Object returnVal = pjp.proceed();
			return returnVal;

		} catch (Throwable e) {
			if (e instanceof MyCollabException) {
				throw (MyCollabException) e;
			} else {
				throw new MyCollabException(e);
			}
		}
	}
}
