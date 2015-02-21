package com.esofthead.mycollab.rest.server.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public interface CheckUpdateVersionResource {
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/checkupdate")
    Response getLatestVersion(String currentClientVersion);
}
