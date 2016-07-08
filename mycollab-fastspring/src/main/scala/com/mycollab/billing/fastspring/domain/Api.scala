package com.mycollab.billing.fastspring.domain

import java.io.{BufferedReader, InputStreamReader}
import java.nio.charset.Charset

import org.apache.commons.codec.binary.Base64
import org.apache.commons.io.FileUtils
import org.apache.http.{HttpHeaders, HttpResponse}
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClientBuilder

/**
  * @author MyCollab Ltd
  * @since 5.3.5
  */
class Api(val username: String, val password: String) {
  def subscription(id: String): Subscription = {
    val client = HttpClientBuilder.create.build
    val request = new HttpGet("https://api.fastspring.com/company/mycollab/subscription/" + id)
    val auth = username + ":" + password
    val encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("ISO-8859-1")))
    val authHeader = "Basic " + new String(encodedAuth)
    request.setHeader(HttpHeaders.AUTHORIZATION, authHeader)
    val response = client.execute(request)
    val rd = new BufferedReader(new InputStreamReader(response.getEntity.getContent))

    null
  }
}
