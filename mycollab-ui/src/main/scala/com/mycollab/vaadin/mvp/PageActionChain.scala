package com.mycollab.vaadin.mvp

import scala.collection.mutable

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class PageActionChain {
  val chains: mutable.Buffer[ScreenData[_]] = mutable.Buffer()

  def this(pageActionArr: Array[ScreenData[_]]) = {
    this()
    chains.appendAll(pageActionArr)
  }

  def this(param: ScreenData[_]) = this(Array[ScreenData[_]](param))

  def this(param1: ScreenData[_], param2: ScreenData[_]) = this(Array[ScreenData[_]](param1, param2))

  def this(param1: ScreenData[_], param2: ScreenData[_], param3: ScreenData[_]) = this(Array[ScreenData[_]](param1, param2, param3))

  def add(pageAction: ScreenData[_]): PageActionChain = {
    chains += pageAction
    this
  }

  def pop: ScreenData[_] = {
    if (chains.size > 0) {
      val pageAction = chains(0)
      chains.remove(0)
      pageAction
    }
    else null
  }

  def peek: ScreenData[_] = chains(0)

  def hasNext: Boolean = chains.length > 0
}
