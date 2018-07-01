package com.mycollab.rest.server.resource

import com.mycollab.core.ResourceNotFoundException
import com.mycollab.ondemand.configuration.EditionInfo
import com.mycollab.ondemand.module.support.dao.CommunityLeadMapper
import com.mycollab.ondemand.module.support.domain.CommunityLead
import com.mycollab.ondemand.module.support.domain.CommunityLeadExample
import com.mycollab.ondemand.module.support.service.EmailReferenceService
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.servlet.annotation.WebServlet

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@RestController
@WebServlet(name = "verifyDownloadUserAndForward", urlPatterns = ["/download/verify"])
class DownloadVerifyController(private val communityLeadMapper: CommunityLeadMapper,
                               private val editionInfo: EditionInfo,
                               private val emailReferenceService: EmailReferenceService) {

    @RequestMapping(value = "/download/verify", method = [RequestMethod.GET])
    fun verifyAndDownload(@RequestParam("email") email: String,
                          @RequestParam("edition", required = false) edition: String?): ResponseEntity<Resource> {

        val ex = CommunityLeadExample()
        ex.createCriteria().andEmailEqualTo(email)
        if (communityLeadMapper.countByExample(ex) > 0) {
            val lead = CommunityLead()
            lead.valid = true
            communityLeadMapper.updateByExampleSelective(lead, ex)
            val res = if ("Ultimate" == edition) UrlResource(editionInfo.premiumDownloadLink) else UrlResource(editionInfo.communityDownloadLink)
            emailReferenceService.save(email)
            val headers = HttpHeaders()
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate")
            headers.add("Pragma", "no-cache")
            headers.add("Expires", "0")
            headers.add("Content-Disposition", "attachment; filename=MyCollab-${editionInfo.version}.zip")
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(res.contentLength())
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(res)

        } else {
            throw ResourceNotFoundException("Can not find email: $email")
        }
    }
}
