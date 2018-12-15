package com.mycollab.rest.server.resource

import com.hp.gagawa.java.elements.Div
import com.hp.gagawa.java.elements.Li
import com.hp.gagawa.java.elements.Ul
import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.ApplicationConfiguration
import com.mycollab.module.mail.service.ExtMailService
import org.springframework.web.bind.annotation.*

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
@RequestMapping(value = ["/testimonial"])
class TestimonialController(private val extMailService: ExtMailService,
                            private val applicationConfiguration: ApplicationConfiguration) {

    @RequestMapping(method = [(RequestMethod.POST)], headers = ["Content-Type=application/x-www-form-urlencoded"])
    @CrossOrigin
    fun submit(@RequestParam("company") company: String, @RequestParam("displayname") displayname: String,
               @RequestParam("jobrole") jobrole: String, @RequestParam("email") email: String,
               @RequestParam("website") website: String, @RequestParam("testimonial") testimonial: String) {
        val bodyContent = Div().appendChild(Ul().appendChild(
                Li().appendText("Display name: $displayname"),
                Li().appendText("Company: $company"),
                Li().appendText("Role: $jobrole"),
                Li().appendText("Email: $email"),
                Li().appendText("Website: $website"),
                Li().appendText("Testimonial: $testimonial")))
        extMailService.sendHTMLMail(applicationConfiguration.notifyEmail, "MyCollab",
                listOf(MailRecipientField("haiphucnguyen@gmail.com", "Hai Nguyen")),
                "New testimonial for you", bodyContent.write())
    }
}
