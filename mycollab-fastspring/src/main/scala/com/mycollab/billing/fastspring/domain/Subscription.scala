package com.mycollab.billing.fastspring.domain

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.joda.time.DateTime

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
@JsonCreator
@JsonDeserialize(using = classOf[SubscriptionDeserializer])
class Subscription(@BeanProperty @JsonProperty("status") val status: String,
                   @BeanProperty @JsonProperty("statusChanged") val statusChanged: DateTime,
                   @BeanProperty @JsonProperty("cancelable") val cancelable: Boolean,
                   @BeanProperty @JsonProperty("reference") val reference: String,
                   @BeanProperty @JsonProperty("test") val test: Boolean,
                   @BeanProperty @JsonProperty("referrer") val referrer: String,
                   @BeanProperty @JsonProperty("nextPeriodDate") val nextPeriodDate: DateTime,
                   @BeanProperty @JsonProperty("customer") val customer: Customer,
                   @BeanProperty @JsonProperty("customerUrl") val customerUrl: String,
                   @BeanProperty @JsonProperty("productName") val productName: String,
                   @BeanProperty @JsonProperty("quantity") val quantity: Int) {
}
