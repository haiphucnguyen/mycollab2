package com.mycollab.rest.server.resource

import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.ApplicationConfiguration
import com.mycollab.configuration.SiteConfiguration
import com.mycollab.core.Version
import com.mycollab.core.utils.FileUtils
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.ondemand.module.support.dao.CommunityLeadMapper
import com.mycollab.ondemand.module.support.domain.CommunityLead
import com.mycollab.ondemand.module.support.domain.CommunityLeadExample
import com.mycollab.ondemand.module.support.service.EditionInfoResolver
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.joda.time.LocalDate
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.IOException

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Api(value = "Community Campaign API", tags = ["Community"])
@RestController
class CampaignController(private val communityLeadMapper: CommunityLeadMapper,
                         private val editionInfoResolver: EditionInfoResolver,
                         private val extMailService: ExtMailService,
                         private val contentGenerator: IContentGenerator,
                         private val applicationConfiguration: ApplicationConfiguration) {

    @ApiOperation(value = "Get the html page contains link to buy", response = String::class)
    @RequestMapping(method = [(RequestMethod.GET)], path = ["/linktobuy"])
    @Throws(IOException::class)
    fun showLinkToBuy(): String = FileUtils.readFileAsPlainString("buying.html")

    @RequestMapping(method = [(RequestMethod.GET)], path = ["/storeweb"])
    @Throws(IOException::class)
    fun displayStoreWeb(): String = FileUtils.readFileAsPlainString("pricing.html")

    @RequestMapping(path = ["/register-ce"], method = [(RequestMethod.POST)], headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    fun registerCE(@RequestParam("firstname") firstname: String,
                   @RequestParam("lastname") lastname: String,
                   @RequestParam("email") email: String,
                   @RequestParam("role") role: String,
                   @RequestParam("company") company: String,
                   @RequestParam("phone") phone: String,
                   @RequestParam("country") country: String,
                   @RequestParam("edition") edition: String): Map<*, *> {
        val info = editionInfoResolver.editionInfo

        object : Thread() {
            override fun run() {
                val communityLead = CommunityLead()
                communityLead.firstname = firstname
                communityLead.lastname = lastname
                communityLead.email = email
                communityLead.company = company
                communityLead.role = role
                communityLead.phone = phone
                communityLead.country = country
                communityLead.registerdate = LocalDate().toDate()
                communityLead.version = Version.getVersion()
                communityLead.edition = edition

                val ex = CommunityLeadExample()
                ex.createCriteria().andFirstnameEqualTo(firstname).andLastnameEqualTo(lastname).andEmailEqualTo(email)
                        .andVersionEqualTo(Version.getVersion())
                if (communityLeadMapper.countByExample(ex) == 0) {
                    communityLeadMapper.insert(communityLead)
                }

                contentGenerator.putVariable("lastname", lastname)
                contentGenerator.putVariable("version", info.version!!)
                if ("Ultimate" == edition) {
                    contentGenerator.putVariable("downloadLink", String.format("http://api.mycollab.com/download/verify?email=%s&&edition=Ultimate", email))
                } else {
                    contentGenerator.putVariable("downloadLink", String.format("http://api.mycollab.com/download/verify?email=%s", email))
                }

                extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), applicationConfiguration.siteName,
                        listOf(MailRecipientField(email, "$firstname $lastname")),
                        "MyCollab is ready for download", contentGenerator.parseFile("mailDownloadInfo.ftl"))
            }
        }.start()

        val name = "MyCollab-All-${info.version}.zip"
        val link = info.communityDownloadLink
        val altLink = info.altCommunityDownloadLink
        return mapOf("name" to name, "link" to link, "altlink" to altLink)
    }
}
