package com.esofthead.mycollab.rest.server.resource.impl;

import com.esofthead.mycollab.core.MyCollabVersion;
import com.esofthead.mycollab.rest.server.resource.CheckUpdateVersionResource;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Properties;

@Service
public class CheckUpdateVersionResourceImpl implements CheckUpdateVersionResource {
    @Override
    public Response getLatestVersion(String currentClientVersion) {
        Properties props = new Properties();
        props.put("version", MyCollabVersion.getVersion());
        props.put("downloadLink", "http://community.mycollab.com/download/");
        Response response = Response.status(200).entity(props)
                .type(MediaType.APPLICATION_JSON_TYPE).build();
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        return response;
    }
}
