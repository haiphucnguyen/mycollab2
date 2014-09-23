package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.infinispan.commons.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.utils.BeanUtility;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Aspect
@Component
@Configurable
public class L2CacheAspect {
	private Logger log = LoggerFactory.getLogger(L2CacheAspect.class);

	@Around("execution(public * com.esofthead.mycollab..service..*.*(..))")
	public Object cacheGet(ProceedingJoinPoint pjp) throws Throwable {

		Advised advised = (Advised) pjp.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method method = ms.getMethod();
		if (method.getAnnotation(Cacheable.class) == null) {
			return pjp.proceed();
		}

		if (CacheUtils.isInBlackList(CacheUtils
				.getEnclosingServiceInterface(cls))) {
			return pjp.proceed();
		}

		Object[] args = pjp.getArgs();
		if (args != null && args.length > 0) {
			Annotation[][] parameterAnnotations = method
					.getParameterAnnotations();
			for (int i = 0; i < parameterAnnotations.length; i++) {
				Annotation[] annos = parameterAnnotations[i];
				for (Annotation paramAnno : annos) {
					if (paramAnno instanceof CacheKey) {
						Object arg = args[i];
						Integer groupId = null;
						try {
							if (arg instanceof Integer) {
								groupId = (Integer) arg;
							} else if (arg instanceof SearchCriteria
									&& (((SearchCriteria) arg).getSaccountid() != null)) {
								groupId = (Integer) ((SearchCriteria) arg)
										.getSaccountid().getValue();
							} else if (arg instanceof SearchRequest) {
								SearchCriteria criteria = ((SearchRequest) arg)
										.getSearchCriteria();
								if (criteria instanceof SearchCriteria
										&& (((SearchCriteria) criteria)
												.getSaccountid() != null)) {
									groupId = (Integer) ((SearchCriteria) criteria)
											.getSaccountid().getValue();
								} else {
									return pjp.proceed();
								}
							} else {
								log.error(
										"Cache key must be one of types [Integer, GroupableSearchCriteria, SearchRequest], now it has type {}",
										arg);
								return pjp.proceed();
							}
						} catch (Exception e) {
							log.error("Error when retrieve cache key with "
									+ BeanUtility.printBeanObj(arg)
									+ " in service class " + cls.getName()
									+ "." + method.getName(), e);
							return pjp.proceed();
						}

						String key = String
								.format("%s-%s-%s", CacheUtils
										.getEnclosingServiceInterfaceName(cls),
										method.getName(), CacheUtils
												.constructParamsKey(args));
						BasicCache<String, Object> cache = LocalCacheManager
								.getCache(groupId.toString());
						Object returnVal = cache.get(key);
						if (returnVal == null) {
							returnVal = pjp.proceed();
							try {
								if (returnVal == null) {
									return returnVal;
								}
								cache.put(key, returnVal);
								log.debug(
										"There is no exist value of key {}, query from database then put it to cache",
										key);
							} catch (Exception e) {
								log.error("Error while put to cache", e);
							}
							return returnVal;
						} else {
							log.debug(
									"There is exist value of key {}, no need to query from database",
									key);
							return returnVal;
						}
					}
				}
			}

			log.error(
					"Can not cache class {}, method {} because we can not detect cache key with args {}",
					new Object[] { cls.getName(), method.getName(),
							BeanUtility.printBeanObj(args) });
			return pjp.proceed();
		} else {
			return pjp.proceed();
		}

	}
}
