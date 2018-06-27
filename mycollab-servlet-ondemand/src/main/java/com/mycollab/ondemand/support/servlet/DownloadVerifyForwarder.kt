package com.mycollab.ondemand.support.servlet

import com.mycollab.core.ResourceNotFoundException
import com.mycollab.ondemand.module.support.dao.CommunityLeadMapper
import com.mycollab.ondemand.module.support.domain.CommunityLead
import com.mycollab.ondemand.module.support.domain.CommunityLeadExample
import com.mycollab.ondemand.configuration.EditionInfo
import com.mycollab.ondemand.module.support.service.EmailReferenceService
import com.mycollab.servlet.GenericHttpServlet
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@WebServlet(name = "verifyDownloadUserAndForward", urlPatterns = ["/download/verify"])
class DownloadVerifyForwarder(private val communityLeadMapper: CommunityLeadMapper,
                              private val editionInfo: EditionInfo,
                              private val emailReferenceService: EmailReferenceService) : GenericHttpServlet() {

    @Throws(ServletException::class, IOException::class)
    override fun onHandleRequest(request: HttpServletRequest, response: HttpServletResponse) {
        val email = request.getParameter("email")
        val edition = request.getParameter("edition")

        val ex = CommunityLeadExample()
        ex.createCriteria().andEmailEqualTo(email)
        if (communityLeadMapper.countByExample(ex) > 0) {
            val lead = CommunityLead()
            lead.valid = true
            communityLeadMapper.updateByExampleSelective(lead, ex)
            if ("Ultimate" == edition) {
                response.sendRedirect(editionInfo.premiumDownloadLink)
            } else {
                response.sendRedirect(editionInfo.communityDownloadLink)
            }
            emailReferenceService.save(email)
        } else {
            throw ResourceNotFoundException("Can not find email: $email")
        }
    }
}
