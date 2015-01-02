package com.esofthead.mycollab.schedule.email

import com.esofthead.mycollab.schedule.email.format.{DefaultFieldFormat, FieldFormat}

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
class ItemFieldMapper {
  private val fieldNameMap: Map[String, FieldFormat] = Map[String, FieldFormat]()

  def put(fieldName: Enum[_], displayName: Enum[_]) {
    fieldNameMap.+(fieldName.name() -> new DefaultFieldFormat(fieldName.name, displayName))
  }

  def put(fieldName: Enum[_], displayName: Enum[_], isColSpan: Boolean) {
    fieldNameMap.+(fieldName.name() -> new DefaultFieldFormat(fieldName.name, displayName, isColSpan))
  }

  def put(fieldName: Enum[_], format: FieldFormat) {
    fieldNameMap.+(fieldName.name -> format)
  }

  def hasField(fieldName: String): Boolean = fieldNameMap contains fieldName

  def getFieldLabel(fieldName: String): FieldFormat = fieldNameMap(fieldName)

  def keySet: Set[String] = fieldNameMap.keySet
}
