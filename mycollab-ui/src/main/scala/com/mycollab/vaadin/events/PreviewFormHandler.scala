package com.mycollab.vaadin.events

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
trait PreviewFormHandler[T] {
  /**
    *
    * @param data
    */
  def gotoNext(data: T)

  /**
    * @param data
    */
  def gotoPrevious(data: T)

  /**
    * @param data
    */
  def onAssign(data: T)

  /**
    * @param data
    */
  def onEdit(data: T)

  /**
    *
    * @param data
    */
  def onAdd(data: T)

  /**
    *
    * @param data
    */
  def onDelete(data: T)

  def onPrint(source: Object, data: T)

  /**
    * @param data
    */
  def onClone(data: T)

  /**
    *
    */
  def onCancel()

  /**
    * @param action
    * @param data
    */
  def onExtraAction(action: String, data: T): Unit
}
