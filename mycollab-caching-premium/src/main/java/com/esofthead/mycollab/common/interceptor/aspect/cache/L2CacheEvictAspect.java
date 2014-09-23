package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.core.cache.CacheArgs;
import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
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
public class L2CacheEvictAspect {

	private Logger log = LoggerFactory.getLogger(L2CacheEvictAspect.class);

	@AfterReturning("execution(public * com.esofthead.mycollab..service..*.*(..))")
	public void cacheEvict(JoinPoint pjp) throws Throwable {

		Advised advised = (Advised) pjp.getThis();
		Class<?> cls = advised.getTargetSource().getTargetClass();

		if (CacheUtils.isInBlackList(CacheUtils
				.getEnclosingServiceInterface(cls))) {
			return;
		}

		MethodSignature ms = (MethodSignature) pjp.getSignature();
		Method method = ms.getMethod();

		if (method.getAnnotation(CacheEvict.class) == null) {
			return;
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

						if (arg instanceof Integer) {
							groupId = (Integer) arg;
						} else {
							try {
								groupId = (Integer) PropertyUtils.getProperty(
										arg, "saccountid");
							} catch (Exception e) {
								log.error(
										"Can not define cache key of class {}, method {} with argument {}",
										new Object[] { cls.getName(),
												method.getName(),
												BeanUtility.printBeanObj(arg) });
								return;
							}
						}

						String prefixKey = CacheUtils
								.getEnclosingServiceInterfaceName(cls);

						if (groupId != null) {
							CacheUtils.cleanCache(groupId, prefixKey);

							try {
								method = cls.getDeclaredMethod(
										method.getName(),
										method.getParameterTypes());
							} catch (Exception e) {

							}

							CacheArgs cacheable = method
									.getAnnotation(CacheArgs.class);
							log.debug("Method: " + method + "---" + cacheable);
							if (cacheable != null) {
								if (cacheable.values() != null
										&& cacheable.values().length > 0) {
									for (Class prefKey : cacheable.values()) {
										CacheUtils.cleanCache(groupId,
												prefKey.getName());
									}
								}
							}

						}
					}
				}
			}
		} else {
			log.error("Can not define the cache key of class {}, method {}",
					cls.getName(), method.getName());
		}
	}
}
