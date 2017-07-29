package com.mycollab.schedule.email

import com.mycollab.schedule.email.format.DefaultFieldFormat
import com.mycollab.schedule.email.format.{DefaultFieldFormat, FieldFormat}

import scala.collection.mutable._

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
class ItemFieldMapper {
    private val fieldNameMap: Map[String, FieldFormat] = LinkedHashMap()

    def put(fieldName: Enum[_], displayName: Enum[_]): Unit = {
        fieldNameMap.put(fieldName.name(), new DefaultFieldFormat(fieldName.name, displayName))
    }

    def put(fieldName: Enum[_], displayName: Enum[_], isColSpan: Boolean): Unit = {
        fieldNameMap.put(fieldName.name(), new DefaultFieldFormat(fieldName.name, displayName, isColSpan))
    }

    def put(fieldName: Enum[_], format: FieldFormat): Unit = {
        fieldNameMap.put(fieldName.name, format)
    }

    def keySet(): java.util.Set[String] = {
        import scala.collection.JavaConverters._
        fieldNameMap.keySet.asJava
    }

    def hasField(fieldName: String): Boolean = fieldNameMap.contains(fieldName)

    def getField(fieldName: String): FieldFormat = fieldNameMap(fieldName)
}
