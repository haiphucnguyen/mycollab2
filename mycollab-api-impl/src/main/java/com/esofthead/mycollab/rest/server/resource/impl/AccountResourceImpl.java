/**
 * This file is part of mycollab-api-impl.
 *
 * mycollab-api-impl is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-api-impl is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-api-impl.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.rest.server.resource.impl;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.rest.server.domain.SignupForm;
import com.esofthead.mycollab.rest.server.resource.AccountResource;
import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Service
public class AccountResourceImpl implements AccountResource {
    private static final Logger LOG = LoggerFactory.getLogger(AccountResourceImpl.class);

    @Autowired
    private BillingService billingService;

    @Override
    public Response signup(@Form SignupForm entity) {
        try {
            LOG.debug("Start handling form request");
            String subDomain = entity.getSubdomain();
            int planId = entity.getPlanId();
            String password = entity.getPassword();
            String email = entity.getEmail();
            String timezoneId = entity.getTimezoneId();
            boolean isEmailVerified = entity.isEmailVerified();

            LOG.debug("Register account with subDomain {}, username {}", subDomain, email);
            billingService.registerAccount(subDomain, planId, email,
                    password, email, timezoneId, isEmailVerified);

            String siteUrl = SiteConfiguration.getSiteUrl(subDomain);
            LOG.debug("Return site url {} to sign up user {}", siteUrl, email);

            Response response = Response.status(200).entity(siteUrl)
                    .type(MediaType.TEXT_PLAIN_TYPE).build();
            response.getHeaders().add("Access-Control-Allow-Origin", "*");
            return response;
        } catch (UserInvalidInputException e) {
            Response response = Response.status(400).entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN_TYPE).build();
            response.getHeaders().add("Access-Control-Allow-Origin", "*");
            return response;
        } catch (Exception e) {
            LOG.error("Error while sign up", e);
            Response response = Response.status(500).entity(e.getMessage())
                    .type(MediaType.TEXT_PLAIN_TYPE).build();
            response.getHeaders().add("Access-Control-Allow-Origin", "*");
            return response;
        }
    }
}
