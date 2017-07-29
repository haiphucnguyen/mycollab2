package com.mycollab.schedule.email

import com.hp.gagawa.java.elements.{Td, Tr, Img}

import scala.collection.mutable._

/**
  * @author MyCollab Ltd
  * @since 5.1.0
  */
class MailStyles {
  private val styles = Map("footer_background" -> "#3A3A3A", "font" -> "14px Arial, 'Times New Roman', sans-serif",
    "small_font" -> "12px Arial, 'Times New Roman', sans-serif",
    "background" -> "#f5faff", "link_color" -> "#006DAC", "border_color" -> "#e5e5e5", "meta_color" -> "#999",
    "action_color" -> "#24a2e3")

  def get(name: String): String = {
    val option: Option[String] = styles.get(name)
    option.get
  }

  def cell(width: String): String = {
    return "width: " + width + "; padding: 10px; vertical-align: top;"
  }
}

object MailStyles {
  private val _instance: MailStyles = new MailStyles()

  def instance(): MailStyles = _instance
}
