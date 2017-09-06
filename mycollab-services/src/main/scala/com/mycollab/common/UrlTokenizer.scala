package com.mycollab.common

import scala.beans.BeanProperty
/**
  * @author MyCollab Ltd
  * @since 5.3.5        
  */
class UrlTokenizer(var internalVal: String, @BeanProperty var remainValue: String, @BeanProperty var query: String) {
  
  @throws[InvalidTokenException]
  def getInt: Integer = {
    if (hasMoreTokens)
      getNextToken.toInt
    else throw new InvalidTokenException("Invalid token " + internalVal)
  }
  
  @throws[InvalidTokenException]
  def getString: String = if (hasMoreTokens) getNextToken
  else throw new InvalidTokenException("Invalid token " + internalVal)
  
  def hasMoreTokens: Boolean = !(remainValue == "")
  
  private def getNextToken: String = {
    val index: Int = remainValue.indexOf("/")
    if (index < 0) {
      val result: String = remainValue + ""
      remainValue = ""
      result
    }
    else {
      val result: String = remainValue.substring(0, index)
      remainValue = remainValue.substring(index + 1)
      result
    }
  }
}

object UrlTokenizer {
  def apply(url: String): UrlTokenizer = {
    var internalVal = if (url.startsWith("/")) url.substring(1) else url
    val queryIndex: Int = internalVal.indexOf("?")
    var query, remainStrVal = ""
    if (queryIndex != -1) {
      query = internalVal.substring(queryIndex + 1)
      internalVal = internalVal.substring(0, queryIndex)
    }
    internalVal = UrlEncodeDecoder.decode(internalVal)
    remainStrVal = internalVal
    new UrlTokenizer(internalVal, remainStrVal, query)
  }
}
