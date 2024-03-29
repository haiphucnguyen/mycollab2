package com.mycollab.rest.server.resource

import com.mycollab.common.domain.MailRecipientField
import com.mycollab.configuration.ApplicationConfiguration
import com.mycollab.core.Version
import com.mycollab.core.utils.FileUtils
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.ondemand.configuration.EditionInfo
import com.mycollab.ondemand.module.support.dao.CommunityLeadMapper
import com.mycollab.ondemand.module.support.domain.CommunityLead
import com.mycollab.ondemand.module.support.domain.CommunityLeadExample
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.time.LocalDateTime

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Api(value = "Community Campaign API", tags = ["Community"])
@RestController
class CampaignController(private val communityLeadMapper: CommunityLeadMapper,
                         private val editionInfo: EditionInfo,
                         private val extMailService: ExtMailService,
                         private val contentGenerator: IContentGenerator,
                         private val applicationConfiguration: ApplicationConfiguration) {

    @ApiOperation(value = "Get the html page contains link to buy", response = String::class)
    @RequestMapping(method = [(RequestMethod.GET)], path = ["/linktobuy", "api/linktobuy"])
    @Throws(IOException::class)
    fun showLinkToBuy() = FileUtils.readFileAsPlainString("buying.html")

    @RequestMapping(method = [(RequestMethod.GET)], path = ["/storeweb", "api/storeweb"])
    @Throws(IOException::class)
    fun displayStoreWeb() = FileUtils.readFileAsPlainString("pricing.html")

    @CrossOrigin
    @RequestMapping(path = ["/register-ce"], method = [(RequestMethod.POST)], headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    fun registerCE(@RequestParam("firstname") firstname: String,
                   @RequestParam("lastname") lastname: String,
                   @RequestParam("email") email: String,
                   @RequestParam("role") role: String,
                   @RequestParam("company") company: String,
                   @RequestParam("phone") phone: String,
                   @RequestParam("country") country: String,
                   @RequestParam("edition") edition: String): Map<*, *> {

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
                communityLead.registerdate = LocalDateTime.now()
                communityLead.version = Version.getVersion()
                communityLead.edition = edition

                val ex = CommunityLeadExample()
                ex.createCriteria().andFirstnameEqualTo(firstname).andLastnameEqualTo(lastname).andEmailEqualTo(email)
                        .andVersionEqualTo(Version.getVersion())
                if (communityLeadMapper.countByExample(ex) == 0L) {
                    communityLeadMapper.insert(communityLead)
                }

                contentGenerator.putVariable("lastname", lastname)
                contentGenerator.putVariable("version", editionInfo.version)

                extMailService.sendHTMLMail(applicationConfiguration.notifyEmail, applicationConfiguration.siteName,
                        listOf(MailRecipientField(email, "$firstname $lastname")),
                        "MyCollab is ready for download", contentGenerator.parseFile("mailDownloadInfo.ftl"))
            }
        }.start()

        val name = "MyCollab-All-${editionInfo.version}.zip"
        val link = editionInfo.communityDownloadLink
        val altLink = editionInfo.premiumDownloadLink
        return mapOf("name" to name, "link" to link, "altlink" to altLink)
    }
}
