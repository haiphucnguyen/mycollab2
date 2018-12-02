package com.mycollab.rest.server.resource

import com.mycollab.configuration.ApplicationConfiguration
import com.mycollab.core.Version
import com.mycollab.core.utils.FileUtils
import com.mycollab.core.utils.StringUtils
import com.mycollab.module.mail.service.ExtMailService
import com.mycollab.module.mail.service.IContentGenerator
import com.mycollab.ondemand.configuration.EditionInfo
import com.mycollab.ondemand.module.support.dao.CommunityLeadMapper
import com.mycollab.ondemand.module.support.domain.CommunityLead
import com.mycollab.ondemand.module.support.domain.CommunityLeadExample
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.joda.time.LocalDate
import org.springframework.web.bind.annotation.*
import java.io.IOException

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
    fun registerCE(@RequestParam("firstname", required = false) firstname: String,
                   @RequestParam("lastname", required = false) lastname: String,
                   @RequestParam("email", required = false) email: String,
                   @RequestParam("role", required = false) role: String,
                   @RequestParam("company", required = false) company: String,
                   @RequestParam("phone", required = false) phone: String,
                   @RequestParam("country", required = false) country: String,
                   @RequestParam("edition") edition: String): Map<*, *> {

        object : Thread() {
            override fun run() {
                if (StringUtils.isNotBlank(firstname) && StringUtils.isNotBlank(lastname) &&
                        StringUtils.isNotBlank(email)) {
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

//                contentGenerator.putVariable("lastname", lastname)
//                contentGenerator.putVariable("version", editionInfo.version)
//                if ("Ultimate" == edition) {
//                    contentGenerator.putVariable("downloadLink", "https://api.mycollab.com/download/verify?email=$email&&edition=Ultimate")
//                } else {
//                    contentGenerator.putVariable("downloadLink", "https://api.mycollab.com/download/verify?email=$email")
//                }
//
//                extMailService.sendHTMLMail(applicationConfiguration.notifyEmail, applicationConfiguration.siteName,
//                        listOf(MailRecipientField(email, "$firstname $lastname")),
//                        "MyCollab is ready for download", contentGenerator.parseFile("mailDownloadInfo.ftl"))
                }
            }
        }.start()

        val name = "MyCollab-All-${editionInfo.version}.zip"
        val link = if ("Ultimate" == edition) editionInfo.premiumDownloadLink else editionInfo.communityDownloadLink
        val altLink = ""
        return mapOf("name" to name, "link" to link, "link" to altLink)
    }
}
