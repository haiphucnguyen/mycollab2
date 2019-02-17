package com.mycollab.rest.server.resource

import org.apache.commons.codec.binary.Base64
import org.springframework.http.*
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
@RestController
@RequestMapping(value = ["/localization"])
class TranslationController {

    @RequestMapping(value = ["translations"], method = [(RequestMethod.GET)], produces = [(MediaType.APPLICATION_OCTET_STREAM_VALUE)])
    @ResponseBody
    fun retrieveAllTranslations(): ResponseEntity<*> {
        val plainCreds = "haiphucnguyen@gmail.com:=5EqGRN5Y=<%`tbX"
        val plainCredsBytes = plainCreds.toByteArray()
        val base64CredsBytes = Base64.encodeBase64(plainCredsBytes)
        val base64Creds = String(base64CredsBytes)

        val headers = HttpHeaders()
        headers.add("Authorization", "Basic $base64Creds")
        headers.accept = Arrays.asList(MediaType.APPLICATION_OCTET_STREAM)

        val request = HttpEntity<String>(headers)
        val template = RestTemplate()
        val response = template.exchange("https://api.getlocalization.com/mycollab/api/translations/zip/", HttpMethod.GET,
                request, ByteArray::class.java)
        return when {
            response.statusCode == HttpStatus.OK -> {
                val resultHeader = HttpHeaders()
                resultHeader.set("Content-Disposition", "attachment; filename=mycollab.zip")
                resultHeader.contentLength = response.body!!.size.toLong()
                ResponseEntity(response.body, resultHeader, HttpStatus.OK)
            }
            else -> ResponseEntity("Error", HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
