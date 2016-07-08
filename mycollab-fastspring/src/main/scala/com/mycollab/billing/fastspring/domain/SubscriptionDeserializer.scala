package com.mycollab.billing.fastspring.domain

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.{DeserializationContext, JsonDeserializer, JsonNode}
import org.joda.time.format.DateTimeFormat

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */

class SubscriptionDeserializer extends JsonDeserializer[Subscription] {
  val datetimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  val dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'Z'")

  override def deserialize(jsonParser: JsonParser, deserializationContext: DeserializationContext): Subscription = {
    val node:JsonNode = jsonParser.getCodec().readTree(jsonParser)
    val status = node.get("status").asText()
    val statusChanged = datetimeFormatter.parseDateTime(node.get("statusChanged").asText())
    val cancelable = node.get("cancelable").asBoolean()
    val test = node.get("test").asBoolean()
    val reference = node.get("reference").asText()
    val referrer = node.get("referrer").asText()
    val nextPeriodDate = dateFormatter.parseDateTime(node.get("nextPeriodDate").asText())
    val customerUrl = node.get("customerUrl").asText()
    val productName = node.get("productName").asText()
    val quantity = node.get("quantity").asInt()

    val firstName = node.get("customer").get("firstName").asText()
    val lastName = node.get("customer").get("lastName").asText()
    val email = node.get("customer").get("email").asText()
    val phoneNumber = node.get("customer").get("phoneNumber").asText()
    new Subscription(status, statusChanged, cancelable, reference, test, referrer, nextPeriodDate,
      new Customer(firstName, lastName, email, phoneNumber), customerUrl, productName, quantity)
  }
}
