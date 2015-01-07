/**
 * This file is part of mycollab-scheduler.
 *
 * mycollab-scheduler is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-scheduler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-scheduler.  If not, see <http://www.gnu.org/licenses/>.
 */
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
