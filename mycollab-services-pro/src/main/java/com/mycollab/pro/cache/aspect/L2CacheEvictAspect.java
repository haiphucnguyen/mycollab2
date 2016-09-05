package com.mycollab.pro.cache.aspect;

import com.mycollab.cache.service.CacheService;
import com.mycollab.core.cache.CacheArgs;
import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.utils.BeanUtility;
import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Aspect
@Component
@Configurable
public class L2CacheEvictAspect {
    private static final Logger LOG = LoggerFactory.getLogger(L2CacheEvictAspect.class);

    @Autowired
    private CacheService cacheService;

    @AfterReturning("execution(public * com.mycollab..service..*.*(..))")
    public void cacheEvict(JoinPoint pjp) throws Throwable {
        Advised advised = (Advised) pjp.getThis();
        Class<?> cls = advised.getTargetSource().getTargetClass();

        if (CacheUtils.isInBlackList(CacheUtils.getEnclosingServiceInterface(cls))) {
            return;
        }

        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();

        if (method.getAnnotation(CacheEvict.class) == null) {
            return;
        }

        Object[] args = pjp.getArgs();
        if (args != null && args.length > 0) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Annotation[] annotations = parameterAnnotations[i];
                for (Annotation paramAnnotation : annotations) {
                    if (paramAnnotation instanceof CacheKey) {
                        Object arg = args[i];
                        Integer groupId;

                        if (arg instanceof Integer) {
                            groupId = (Integer) arg;
                        } else {
                            try {
                                groupId = (Integer) PropertyUtils.getProperty(arg, "saccountid");
                            } catch (Exception e) {
                                LOG.error("Can not define cache key of class {}, method {} with argument {}",
                                        cls.getName(), method.getName(), BeanUtility.printBeanObj(arg));
                                return;
                            }
                        }

                        String prefixKey = CacheUtils.getEnclosingServiceInterfaceName(cls);

                        if (groupId != null) {
                            cacheService.removeCacheItem(groupId.toString(), prefixKey);

                            try {
                                method = cls.getDeclaredMethod(method.getName(), method.getParameterTypes());
                            } catch (Exception e) {

                            }

                            CacheArgs cacheable = method.getAnnotation(CacheArgs.class);
                            if (cacheable != null) {
                                if (cacheable.values().length > 0) {
                                    for (Class prefKey : cacheable.values()) {
                                        cacheService.removeCacheItem(groupId.toString(), prefKey.getName());
                                    }
                                }
                            }

                        }
                    }
                }
            }
        } else {
            LOG.error("Can not define the cache key of class {}, method {}", cls.getName(), method.getName());
        }
    }
}
