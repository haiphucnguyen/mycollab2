package com.esofthead.mycollab.rest.server.resource.impl;

import com.esofthead.mycollab.core.MyCollabVersion;
import com.esofthead.mycollab.rest.server.resource.CheckUpdateVersionResource;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Properties;

@Service
public class CheckUpdateVersionResourceImpl implements CheckUpdateVersionResource {
    @Override
    public Response getLatestVersion(String version) {
        Properties props = new Properties();
        props.put("version", MyCollabVersion.getVersion());
        props.put("downloadLink", "http://community.mycollab.com/download/");
        props.put("releaseNotes", "http://community.mycollab.com/release-notes/");

        if (version != null && MyCollabVersion.isEditionNewer(MyCollabVersion.getVersion(), version)) {
            props.put("autoDownload", String.format("https://github" +
                    ".com/MyCollab/mycollab/releases/download/Release_%s/upgrade.zip", MyCollabVersion.getVersion()));
        }

        Gson gson = new Gson();
        Response response = Response.status(200).entity(gson.toJson(props)).type(MediaType.APPLICATION_JSON_TYPE).build();
        response.getHeaders().add("Access-Control-Allow-Origin", "*");
        return response;
    }
}
