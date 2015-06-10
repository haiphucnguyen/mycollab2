package com.esofthead.mycollab.vaadin.mvp

import com.esofthead.mycollab.core.MyCollabException
import com.esofthead.mycollab.core.utils.BeanUtility
import com.esofthead.mycollab.vaadin.ui.NotificationUtil
import org.slf4j.{Logger, LoggerFactory}

import scala.annotation.varargs
import scala.collection.mutable._

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
abstract class UrlResolver {
    private var subResolvers: Map[String, UrlResolver] = _
    protected var defaultUrlResolver: UrlResolver = _

    def addSubResolver(key: String, subResolver: UrlResolver) {
        if (subResolvers == null) {
            subResolvers = Map[String, UrlResolver]()
        }
        subResolvers += key -> subResolver
    }

    def getSubResolver(key:String): UrlResolver = subResolvers(key)

    @varargs def handle(params: String*): Unit = {
        try {
            if (params.length > 0) {
                val key: String = params(0)
                if (subResolvers == null) {
                    handlePage(params:_*)
                }
                else {
                    var urlResolver: UrlResolver = subResolvers(key)
                    if (urlResolver == null) {
                        if (defaultUrlResolver != null) {
                            urlResolver = defaultUrlResolver
                        }
                        else {
                            throw new MyCollabException(String.format("Can not register resolver key %s for Resolver: %s", key, this))
                        }
                    }

                    UrlResolver.LOG.debug("Handle url in resolver: " + urlResolver)
                    urlResolver.handle(params.tail:_*)
                }
            }
            else {
                handlePage()
            }
        }
        catch {
            case e: Exception => {
                UrlResolver.LOG.error("Error while navigation", e)
                defaultPageErrorHandler
                NotificationUtil.showRecordNotExistNotification
            }
        }
    }


    protected def defaultPageErrorHandler(): Unit

    /**
     * @param params
     */
    @varargs protected def handlePage(params: String*): Unit = {
        UrlResolver.LOG.debug(String.format("Handle page: %s with params: %s", this, BeanUtility.printBeanObj(params)));
    }
}

object UrlResolver {
    val LOG: Logger = LoggerFactory.getLogger(classOf[UrlResolver]);
}
