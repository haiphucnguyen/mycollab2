package com.mycollab.billing.fastspring.domain

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
@JsonCreator
class Customer(@BeanProperty @JsonProperty("firstName") val firstName: String,
               @BeanProperty @JsonProperty("lastName") val lastName: String,
               @BeanProperty @JsonProperty("email") val email: String,
               @BeanProperty @JsonProperty("phoneNumber") val phoneNumber: String) {
  
}
