package com.mycollab.vaadin.mvp.service

import com.mycollab.vaadin.mvp.IPresenter
import com.mycollab.vaadin.mvp.PageView
import com.mycollab.vaadin.mvp.ViewComponent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.core.type.filter.AnnotationTypeFilter
import org.springframework.core.type.filter.AssignableTypeFilter
import org.springframework.stereotype.Component
import org.springframework.util.ClassUtils

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Component
open class ComponentScannerService : InitializingBean {
    companion object {
        val LOG: Logger = LoggerFactory.getLogger(ComponentScannerService::class.java)
    }

    var viewClasses: Set<Class<*>> = mutableSetOf()
    var presenterClasses: Set<Class<IPresenter<out PageView>>> = mutableSetOf()

    var cacheViewClasses: Map<Class<*>, Class<*>> = mutableMapOf()
    var cachePresenterClasses: Map<Class<*>, Class<*>> = mutableMapOf()

    override fun afterPropertiesSet() {
        val provider = ClassPathScanningCandidateComponentProvider(false)
        provider.addIncludeFilter(AnnotationTypeFilter(ViewComponent::class.java))
        provider.addIncludeFilter(AssignableTypeFilter(IPresenter::class.java))
        LOG.info("Started resolving view and presenter classes")
        val candidateComponents = provider.findCandidateComponents("com.mycollab.**.view")
        candidateComponents.forEach {
            val cls = ClassUtils.resolveClassName(it.beanClassName, ClassUtils.getDefaultClassLoader())
            if (cls.getAnnotation(ViewComponent::class.java) != null) viewClasses += cls
            else if (IPresenter::class.java.isAssignableFrom(cls)) presenterClasses += cls as Class<IPresenter<out PageView>>
        }

        LOG.info("Resolved view and presenter classes")
    }

    fun getViewImplCls(viewClass: Class<*>): Class<*>? {
        val aClass = cacheViewClasses[viewClass]
        return when (aClass) {
            null -> {
                viewClasses.forEach {
                    if (viewClass.isAssignableFrom(it)) {
                        cacheViewClasses += (viewClass to it)
                        return it
                    }
                }
                null
            }
            else -> aClass
        }
    }

    fun getPresenterImplCls(presenterClass: Class<*>): Class<*>? {
        val aClass = cachePresenterClasses[presenterClass]
        return when (aClass) {
            null -> {
                presenterClasses.forEach {
                    if (presenterClass.isAssignableFrom(it) && !it.isInterface) {
                        cachePresenterClasses += (presenterClass to it)
                        return it
                    }
                }
                return null
            }
            else -> aClass
        }
    }
}