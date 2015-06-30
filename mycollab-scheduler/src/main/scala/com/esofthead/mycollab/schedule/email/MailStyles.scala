package com.esofthead.mycollab.schedule.email

import scala.collection.mutable._
/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class MailStyles {
    private val styles:Map[String, String] = Map();
    styles.put("link_footer", "color: rgb(201, 201, 201); text-decoration: none;")

    def style(name: String): String = {
        val option:Option[String] = styles.get(name)
        option.get
    }
}

object MailStyles {
    private val _instance:MailStyles = new MailStyles()

    def instance(): MailStyles = _instance
}
