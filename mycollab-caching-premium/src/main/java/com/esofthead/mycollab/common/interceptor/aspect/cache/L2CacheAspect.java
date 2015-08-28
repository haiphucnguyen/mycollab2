package com.esofthead.mycollab.common.interceptor.aspect.cache;

import com.esofthead.mycollab.cache.CacheUtils;
import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.cache.Cacheable;
import com.esofthead.mycollab.core.utils.ArrayUtils;
import com.esofthead.mycollab.core.utils.BeanUtility;
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

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Aspect
@Component
@Configurable
public class L2CacheAspect {
    private static final Logger LOG = LoggerFactory.getLogger(L2CacheAspect.class);

    @Around("execution(public * com.esofthead.mycollab..service..*.*(..))")
    public Object cacheGet(ProceedingJoinPoint pjp) throws Throwable {
        Advised advised = (Advised) pjp.getThis();
        Class<?> cls = advised.getTargetSource().getTargetClass();
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        Method method = ms.getMethod();
        if (method.getAnnotation(Cacheable.class) == null) {
            return pjp.proceed();
        }

        if (CacheUtils.isInBlackList(CacheUtils.getEnclosingServiceInterface(cls))) {
            return pjp.proceed();
        }

        Object[] args = pjp.getArgs();
        if (ArrayUtils.isNotEmpty(args)) {
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++) {
                Annotation[] annos = parameterAnnotations[i];
                for (Annotation paramAnno : annos) {
                    if (paramAnno instanceof CacheKey) {
                        Object arg = args[i];
                        Integer groupId;
                        try {
                            if (arg instanceof Integer) {
                                groupId = (Integer) arg;
                            } else if (arg instanceof SearchCriteria && (((SearchCriteria) arg).getSaccountid() != null)) {
                                groupId = (Integer) ((SearchCriteria) arg).getSaccountid().getValue();
                            } else if (arg instanceof SearchRequest) {
                                SearchCriteria criteria = ((SearchRequest) arg).getSearchCriteria();
                                if (criteria instanceof SearchCriteria && (criteria.getSaccountid() != null)) {
                                    groupId = (Integer) criteria.getSaccountid().getValue();
                                } else {
                                    return pjp.proceed();
                                }
                            } else {
                                LOG.error("Cache key must be one of types [Integer, GroupableSearchCriteria, SearchRequest], now it has type {}. Check class.method {} {}",
                                        arg, cls.getName(), method.getName());
                                return pjp.proceed();
                            }
                        } catch (Exception e) {
                            LOG.error(String.format("Error when retrieve cache key with %s in service class %s.%s",
                                    BeanUtility.printBeanObj(arg), cls.getName(), method.getName()), e);
                            return pjp.proceed();
                        }

                        String key = String.format("%s-%s-%s", CacheUtils.getEnclosingServiceInterfaceName(cls),
                                method.getName(), CacheUtils.constructParamsKey(args));
                        BasicCache<String, Object> cache = LocalCacheManager.getCache(groupId.toString());
                        Object returnVal = cache.get(key);
                        if (returnVal == null) {
                            returnVal = pjp.proceed();
                            try {
                                if (returnVal == null) {
                                    return returnVal;
                                }
                                cache.put(key, returnVal);
                                LOG.debug("There is no exist value of key {}, query from database then put it to cache", key);
                            } catch (Exception e) {
                                LOG.error("Error while put to cache", e);
                            }
                            return returnVal;
                        } else {
                            LOG.debug("There is exist value of key {}, no need to query from database", key);
                            return returnVal;
                        }
                    }
                }
            }

            LOG.error("Can not cache class {}, method {} because we can not detect cache key with args {}",
                    cls.getName(), method.getName(), BeanUtility.printBeanObj(args));
            return pjp.proceed();
        } else {
            return pjp.proceed();
        }

    }
}
