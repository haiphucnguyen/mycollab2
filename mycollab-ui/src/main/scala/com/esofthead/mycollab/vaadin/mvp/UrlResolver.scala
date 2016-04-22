package com.esofthead.mycollab.vaadin.mvp

import com.esofthead.mycollab.core.MyCollabException
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

  def getSubResolver(key: String): UrlResolver = subResolvers(key)

  @varargs def handle(params: String*): Unit = {
    try {
      if (params.length > 0) {
        val key = params(0)
        if (subResolvers == null) {
          handlePage(params: _*)
        }
        else {
          val urlResolver = subResolvers.get(key)
          urlResolver match {
            case Some(value) => value.handle(params.tail: _*)
            case None => {
              if (defaultUrlResolver != null) {
                defaultUrlResolver.handle(params: _*)
              }
              else {
                throw new MyCollabException(String.format("Can not register resolver key %s for Resolver: %s", key, this))
              }
            }
          }
        }
      }
      else {
        handlePage()
      }
    }
    catch {
      case e: Exception => {
        UrlResolver.LOG.error("Error while navigation", e)
        defaultPageErrorHandler()
        NotificationUtil.showRecordNotExistNotification()
      }
    }
  }


  protected def defaultPageErrorHandler(): Unit

  /**
    * @param params
    */
  @varargs protected def handlePage(params: String*): Unit = {}
}

object UrlResolver {
  val LOG: Logger = LoggerFactory.getLogger(classOf[UrlResolver]);
}
