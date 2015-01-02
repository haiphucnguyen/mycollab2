package com.esofthead.mycollab.schedule.email.format

import com.hp.gagawa.java.elements.{Span, Img, Text, A}

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
object TagBuilder {
  def newA(href: String, text: String): A = {
    val link: A = new A(href, new Text(text))
    link.setStyle("text-decoration: none; color: rgb(0, 109, 172);")
    return link
  }

  def newImg(alt: String, src: String): Img = {
    val img: Img = new Img(alt, src)
    img.setStyle("vertical-align: middle; margin-right: 3px;")
    return img
  }

  def newLink(img: Img, link: A): Span = {
    val span: Span = new Span
    span.appendChild(img, link)
    return span
  }
}
