package com.mycollab.configuration

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.1.0
  */
class DatabaseConfiguration(@BeanProperty val driverClass:String, @BeanProperty val url:String,
                            @BeanProperty val user:String, @BeanProperty val password:String) {

}