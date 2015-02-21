package com.esofthead.mycollab.rest.server.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
@Path("/checkupdate")
public interface CheckUpdateVersionResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response getLatestVersion();
}
