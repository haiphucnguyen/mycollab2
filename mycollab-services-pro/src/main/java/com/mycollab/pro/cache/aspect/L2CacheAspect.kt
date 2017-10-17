package com.mycollab.pro.cache.aspect

import com.mycollab.cache.service.CacheService
import com.mycollab.core.cache.CacheKey
import com.mycollab.core.cache.Cacheable
import com.mycollab.core.utils.ArrayUtils
import com.mycollab.core.utils.BeanUtility
import com.mycollab.core.utils.JsonDeSerializer
import com.mycollab.db.arguments.BasicSearchRequest
import com.mycollab.db.arguments.SearchCriteria
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.aop.framework.Advised
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Aspect
@Component
@Configurable
class L2CacheAspect(private val cacheService: CacheService) {

    @Around("execution(public * com.mycollab..service..*.*(..))")
    @Throws(Throwable::class)
    fun cacheGet(pjp: ProceedingJoinPoint): Any? {
        val advised = pjp.`this` as Advised
        val cls = advised.targetSource.targetClass
        val ms = pjp.signature as MethodSignature
        val method = ms.method
        if (method.getAnnotation(Cacheable::class.java) == null) {
            return pjp.proceed()
        }

        if (CacheUtils.isInBlackList(CacheUtils.getEnclosingServiceInterface(cls))) {
            return pjp.proceed()
        }

        val args = pjp.args
        if (ArrayUtils.isNotEmpty(args)) {
            val parameterAnnotations = method.parameterAnnotations
            for (i in parameterAnnotations.indices) {
                val annotations = parameterAnnotations[i]
                for (paramAnnotation in annotations) {
                    if (paramAnnotation is CacheKey) {
                        val arg = args[i]
                        val groupId: Int?
                        try {
                            if (arg is Int) {
                                groupId = arg
                            } else if (arg is SearchCriteria && arg.saccountid != null) {
                                groupId = arg.saccountid!!.value
                            } else if (arg is BasicSearchRequest<*>) {
                                val criteria = arg.searchCriteria
                                if (criteria.saccountid != null) {
                                    groupId = criteria.saccountid!!.value
                                } else {
                                    return pjp.proceed()
                                }
                            } else {
                                LOG.error("Cache key must be one of types [Integer, GroupableSearchCriteria, SearchRequest], now it has type $arg. Check class.method ${cls.name} ${method.name}")
                                return pjp.proceed()
                            }
                        } catch (e: Exception) {
                            LOG.error("Error when retrieve cache key with ${BeanUtility.printBeanObj(arg)} in service class ${cls.name}.${method.name}", e)
                            return pjp.proceed()
                        }

                        val key = "${CacheUtils.getEnclosingServiceInterfaceName(cls)}-${method.name}-${JsonDeSerializer.toJson(args)}"
                        var returnVal = cacheService.getValue(groupId!!.toString(), key)
                        if (returnVal == null) {
                            returnVal = pjp.proceed()
                            try {
                                if (returnVal == null) {
                                    return null
                                }
                                cacheService.putValue(groupId.toString(), key, returnVal)
                                LOG.debug("There is no exist value of key $key, query from database then put it to cache")
                            } catch (e: Exception) {
                                LOG.error("Error while put to cache", e)
                            }

                            return returnVal
                        } else {
                            LOG.debug("There is exist value of key $key, no need to query from database")
                            return returnVal
                        }
                    }
                }
            }

            LOG.error("Can not cache class ${cls.name}, method ${method.name} because we can not detect cache key with args ${BeanUtility.printBeanObj(args)}")
            return pjp.proceed()
        } else {
            return pjp.proceed()
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(L2CacheAspect::class.java)
    }
}
