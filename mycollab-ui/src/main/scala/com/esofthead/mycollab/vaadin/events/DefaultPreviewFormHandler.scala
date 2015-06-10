package com.esofthead.mycollab.vaadin.events

import com.esofthead.mycollab.core.MyCollabException

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class DefaultPreviewFormHandler[T] extends PreviewFormHandler[T] {
    override  def gotoNext(data: T) {}

    override  def gotoPrevious(data: T) {}

    override  def onEdit(data: T) {}

    override  def onDelete(data: T) {}

    override  def onClone(data: T) {}

    override  def onCancel {}

    override  def onAssign(data: T) {}

    override def onAdd(data: T) {}

    override  def onExtraAction(action: String, data: T) : Unit = throw new MyCollabException("Must be override by " +
        "sub class")
}
