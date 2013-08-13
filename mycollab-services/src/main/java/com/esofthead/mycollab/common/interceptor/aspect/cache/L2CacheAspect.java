package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

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
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.utils.BeanUtility;

@Aspect
@Component
@Configurable
public class L2CacheAspect {
	private Logger log = LoggerFactory.getLogger(L2CacheAspect.class);

	static List<Class> blacklistCls = Arrays
			.asList(new Class[] { RelayEmailNotificationService.class });

	@Around("execution(public * com.esofthead.mycollab..service..*.*(..))")
	public Object cacheGet(ProceedingJoinPoint pjp) throws Throwable {

		Advised advised = (Advised) pjp.getThis();
		Class cls = advised.getTargetSource().getTargetClass();
		if (blacklistCls.contains(cls)) {
			return pjp.proceed();
		}

		final Signature signature = pjp.getStaticPart().getSignature();
		Cacheable cacheable = null;
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			Method method = ms.getMethod();
			cacheable = method.getAnnotation(Cacheable.class);

			if (cacheable != null) {
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
									} else if (arg instanceof SearchCriteria) {
										groupId = (Integer) ((SearchCriteria) arg)
												.getSaccountid().getValue();
									} else if (arg instanceof SearchRequest) {
										SearchCriteria criteria = ((SearchRequest) arg)
												.getSearchCriteria();
										if (criteria instanceof SearchCriteria) {
											groupId = (Integer) ((SearchCriteria) criteria)
													.getSaccountid().getValue();
										} else {
											return pjp.proceed();
										}
									} else {
										log.error(
												"Cache key must be one of types [Integer, GroupableSearchCriteria, SearchRequest], now it has type {}",
												arg.getClass().getName());
										return pjp.proceed();
									}
								} catch (Exception e) {
									log.error("Error when retrieve cache key ",
											e);
									return pjp.proceed();
								}

								String key = String
										.format("%s-%s-%s",
												CacheUtils
														.getEnclosingServiceInterface(cls),
												method.getName(),
												CacheUtils
														.constructParamsKey(args));
								BasicCache<String, Object> cache = LocalCacheManager
										.getCache(groupId.toString());
								Object returnVal = cache.get(key);
								if (returnVal == null) {
									returnVal = pjp.proceed();
									try {
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
							new String[] { cls.getName(), method.getName(),
									BeanUtility.printBeanObj(args) });
					return pjp.proceed();
				} else {
					return pjp.proceed();
				}
			} else {
				return pjp.proceed();
			}
		} else {
			return pjp.proceed();
		}
	}
}
