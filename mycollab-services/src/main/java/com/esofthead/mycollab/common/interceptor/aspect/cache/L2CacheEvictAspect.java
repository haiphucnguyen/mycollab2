package com.esofthead.mycollab.common.interceptor.aspect.cache;

import java.lang.annotation.Annotation;
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
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.utils.BeanUtility;

@Aspect
@Component
@Configurable
public class L2CacheEvictAspect {

	private Logger log = LoggerFactory.getLogger(L2CacheEvictAspect.class);

	@AfterReturning("execution(public * com.esofthead.mycollab..service..*.*(..))")
	public void cacheEvict(JoinPoint pjp) throws Throwable {

		Advised advised = (Advised) pjp.getThis();
		Class cls = advised.getTargetSource().getTargetClass();

		if (CacheServiceIgnoreList.isInBlackList(CacheUtils
				.getEnclosingServiceInterface(cls))) {
			return;
		}

		final Signature signature = pjp.getStaticPart().getSignature();
		CacheEvict cacheable = null;
		if (signature instanceof MethodSignature) {
			final MethodSignature ms = (MethodSignature) signature;
			Method method = ms.getMethod();

			cacheable = method.getAnnotation(CacheEvict.class);
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

								if (arg instanceof Integer) {
									groupId = (Integer) arg;
								} else {
									try {
										groupId = (Integer) PropertyUtils
												.getProperty(arg, "saccountid");
									} catch (Exception e) {
										log.error(
												"Can not define cache key of class {}, method {} with argument {}",
												new String[] {
														cls.getName(),
														method.getName(),
														BeanUtility
																.printBeanObj(arg) });
										return;
									}
								}

								String prefixKey = CacheUtils
										.getEnclosingServiceInterfaceName(cls);
								CacheUtils.cleanCache(groupId, prefixKey);

								if (cacheable.serviceMap() != null
										&& cacheable.serviceMap().length > 0) {
									for (Class prefKey : cacheable.serviceMap()) {
										CacheUtils.cleanCache(groupId,
												prefKey.getName());
									}
								}
							}
						}
					}
				} else {
					log.error(
							"Can not define the cache key of class {}, method {}",
							cls.getName(), method.getName());
				}
			}
		}
	}
}
