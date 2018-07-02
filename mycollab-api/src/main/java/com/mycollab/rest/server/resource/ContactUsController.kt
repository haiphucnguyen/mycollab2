package com.mycollab.rest.server.resource

import com.hp.gagawa.java.elements.Div
import com.hp.gagawa.java.elements.Li
import com.hp.gagawa.java.elements.Ul
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.ApplicationConfiguration
import com.mycollab.module.mail.service.ExtMailService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@Api(value = "contact-us", tags = ["Support"])
@RestController
@RequestMapping(value = "/contact-us")
class ContactUsController(private val extMailService: ExtMailService,
                          private val applicationConfiguration: ApplicationConfiguration) {

    @ApiOperation(value = "Send the inquery request", response = String::class)
    @RequestMapping(value = "/submit", method = [(RequestMethod.POST)], headers = ["Content-Type=application/x-www-form-urlencoded"])
    @CrossOrigin(origins = ["https://*.mycollab.com", "https://mycollab.com"])
    fun doContact(@RequestParam("name") name: String,
                  @RequestParam("email") email: String,
                  @RequestParam("company") company: String,
                  @RequestParam("role") role: String,
                  @RequestParam("industry") industry: String,
                  @RequestParam("budget") budget: String,
                  @RequestParam("subject") subject: String,
                  @RequestParam("message") message: String): String {
        val bodyContent = Div().appendChild(Ul().appendChild(
                Li().appendText("Name: $name"),
                Li().appendText("Email: $email"),
                Li().appendText("Company: $company"),
                Li().appendText("Role: $role"),
                Li().appendText("Industry: $industry"),
                Li().appendText("Budget: $budget"),
                Li().appendText("Subject: $subject"),
                Li().appendText("Message: $message")))
        extMailService.sendHTMLMail(applicationConfiguration.notifyEmail, "MyCollab", listOf(MailRecipientField("haiphucnguyen@gmail.com", "Hai Nguyen")), "MyCollab inquiry", bodyContent.write())
        return "Ok"
    }
}
