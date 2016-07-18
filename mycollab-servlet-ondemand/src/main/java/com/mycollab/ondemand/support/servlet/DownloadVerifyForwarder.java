package com.mycollab.ondemand.support.servlet;

import com.mycollab.core.ResourceNotFoundException;
import com.mycollab.ondemand.module.support.dao.CommunityLeadMapper;
import com.mycollab.ondemand.module.support.domain.CommunityLead;
import com.mycollab.ondemand.module.support.domain.CommunityLeadExample;
import com.mycollab.ondemand.module.support.domain.EditionInfo;
import com.mycollab.ondemand.module.support.service.EditionInfoResolver;
import com.mycollab.servlet.GenericHttpServlet;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@WebServlet(name = "verifyDownloadUserAndForward", urlPatterns = "/download/verify")
public class DownloadVerifyForwarder extends GenericHttpServlet {

    @Autowired
    private CommunityLeadMapper communityLeadMapper;

    @Autowired
    private EditionInfoResolver editionInfoResolver;

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String edition = request.getParameter("edition");
        final EditionInfo info = editionInfoResolver.getEditionInfo();

        CommunityLeadExample ex = new CommunityLeadExample();
        ex.createCriteria().andEmailEqualTo(username);
        if (communityLeadMapper.countByExample(ex) > 0) {
            CommunityLead lead = new CommunityLead();
            lead.setValid(true);
            communityLeadMapper.updateByExampleSelective(lead, ex);
            if ("Ultimate".equals(edition)) {
                response.sendRedirect(info.getPremiumDownloadLink());
            } else {
                response.sendRedirect(info.getCommunityDownloadLink());
            }
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
