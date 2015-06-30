package com.esofthead.mycollab.schedule.email

import scala.collection.mutable._

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class MailStyles {
    private val styles: Map[String, String] = Map();
    styles.put("footer_background", "#3A3A3A")
    styles.put("font", "13px Arial, 'Times New Roman', sans-serif")
    styles.put("background", "#FFFFFF")
    styles.put("link_color", "#006DAC")

    def get(name: String): String = {
        val option: Option[String] = styles.get(name)
        option.get
    }
}

object MailStyles {
    private val _instance: MailStyles = new MailStyles()

    def instance(): MailStyles = _instance
}
