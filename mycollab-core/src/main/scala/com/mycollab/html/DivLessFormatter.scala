package com.mycollab.html

import com.hp.gagawa.java.elements.{Div, Text}

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
class DivLessFormatter extends Div {

    override def write: String = {
        val b = new StringBuilder
        if ((this.children != null) && (this.children.size > 0)) {
            import scala.collection.JavaConverters._
            for (child <- this.children.asScala.toList) {
                b.append(child.write)
            }
        }
        b.toString
    }
}

object DivLessFormatter {
    val EMPTY_SPACE = new Text("&nbsp;")
}
