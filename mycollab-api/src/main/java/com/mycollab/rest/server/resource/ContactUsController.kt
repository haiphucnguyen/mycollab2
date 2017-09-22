package com.mycollab.rest.server.resource

import com.hp.gagawa.java.elements.Div
import com.hp.gagawa.java.elements.Li
import com.hp.gagawa.java.elements.Ul
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.module.mail.service.ExtMailService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@Api(value = "contact-us", tags = arrayOf("Support"))
@RestController
@RequestMapping(value = "/contact-us")
class ContactUsController(private val extMailService: ExtMailService) {

    @ApiOperation(value = "Send the inquery request", response = String::class)
    @RequestMapping(value = "/submit", method = arrayOf(RequestMethod.POST), headers = arrayOf("Content-Type=application/x-www-form-urlencoded"))
    fun doContact(@RequestParam("name") name: String,
                  @RequestParam("email") email: String,
                  @RequestParam("company") company: String,
                  @RequestParam("role") role: String,
                  @RequestParam("industry") industry: String,
                  @RequestParam("budget") budget: String,
                  @RequestParam("subject") subject: String,
                  @RequestParam("message") message: String): String {
        val bodyContent = Div().appendChild(Ul().appendChild(
                Li().appendText(String.format("Name: %s", name)),
                Li().appendText(String.format("Email: %s", email)),
                Li().appendText(String.format("Company: %s", company)),
                Li().appendText(String.format("Role: %s", role)),
                Li().appendText(String.format("Industry: %s", industry)),
                Li().appendText(String.format("Budget: %s", budget)),
                Li().appendText(String.format("Subject: %s", subject)),
                Li().appendText(String.format("Message: %s", message))))
        extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), "MyCollab", listOf(MailRecipientField("hainguyen@mycollab.com", "Hai Nguyen")), "MyCollab inquiry", bodyContent.write())
        return "Ok"
    }
}