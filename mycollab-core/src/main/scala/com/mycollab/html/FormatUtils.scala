package com.mycollab.html

import com.hp.gagawa.java.elements.{A, Img, Span, Text}

/**
 * @author MyCollab Ltd.
 * @since 4.6.0
 */
object FormatUtils {
  def newA(href: String, text: String): A = new A(href, new Text(text))

  def newImg(alt: String, src: String): Img = new Img(alt, src).setStyle("vertical-align: middle; margin-right: 3px;")

  def newLink(img: Img, link: A): Span = new Span().appendChild(img, DivLessFormatter.EMPTY_SPACE, link)

  def newLink(txt:Text, link: A):Span = new Span().appendChild(txt, DivLessFormatter.EMPTY_SPACE, link)
}
