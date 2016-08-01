package com.mycollab.vaadin.ui.registry

import com.mycollab.common.domain.{AuditChangeItem, SimpleActivityStream}
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.ui.formatter.FieldGroupFormatter
import org.apache.commons.collections.CollectionUtils
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
@Component
class AuditLogRegistry extends InitializingBean {
  private var auditPrinters: Map[String, FieldGroupFormatter] = Map[String, FieldGroupFormatter]()
  
  override def afterPropertiesSet(): Unit = {
    
  }
  
  def registerAuditLogHandler(typeVal: String, fieldGroupFormatter: FieldGroupFormatter) {
    auditPrinters += (typeVal -> fieldGroupFormatter)
  }
  
  def generatorDetailChangeOfActivity(activityStream: SimpleActivityStream): String = {
    if (activityStream.getAssoAuditLog != null) {
      val value = auditPrinters.get(activityStream.getType)
      value match {
        case Some(groupFormatter) =>
          val str = new StringBuilder("")
          var isAppended = false
          val changeItems: java.util.List[AuditChangeItem] = activityStream.getAssoAuditLog.getChangeItems
          if (CollectionUtils.isNotEmpty(changeItems)) {
            import scala.collection.JavaConversions._
            for (item <- changeItems) {
              val fieldName = item.getField
              val fieldDisplayHandler = groupFormatter.getFieldDisplayHandler(fieldName)
              if (fieldDisplayHandler != null) {
                isAppended = true
                str.append(fieldDisplayHandler.generateLogItem(item))
              }
            }
          }
          if (isAppended) {
            str.insert(0, "<p>").insert(0, "<ul>")
            str.append("</ul>").append("</p>")
          }
          return str.toString
        case None => return ""
      }
    }
    ""
  }
  
  def getFieldGroupFormatter(typeVal: String): FieldGroupFormatter = {
    val fieldGroupFormatter = auditPrinters.get(typeVal)
    fieldGroupFormatter match {
      case Some(field) => field
      case None => new FieldGroupFormatter
    }
  }
}

object AuditLogRegistry {
  def getFieldGroupFormatterOfType(typeVal: String): FieldGroupFormatter = {
    val auditLogRegistry = AppContextUtil.getSpringBean(classOf[AuditLogRegistry])
    auditLogRegistry.getFieldGroupFormatter(typeVal)
  }
}
