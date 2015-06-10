package com.esofthead.mycollab.common

import scala.beans.BeanProperty

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class TableViewField(@BeanProperty var descKey: Enum[_], @BeanProperty var field:String,
                     @BeanProperty var defaultWidth:Integer) extends Serializable {

  def this(descKey: Enum[_], field:String) = this(descKey, field, -1)
}
