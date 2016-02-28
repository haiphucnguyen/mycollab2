package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.core.MyCollabVersion;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@RestController
public class CheckVersionController {

    @RequestMapping(value = "/checkupdate", method = RequestMethod.GET)
    public String getLatestVersion(@RequestParam("version") String version) {
        Properties props = new Properties();

        props.put("version", MyCollabVersion.getVersion());
        props.put("downloadLink", "https://www.mycollab.com/ce-registration/");
        props.put("releaseNotes", "https://community.mycollab.com/releases/release-notes-for-mycollab-5-2-8/");

        if (version != null && MyCollabVersion.isEditionNewer(MyCollabVersion.getVersion(), version) &&
                MyCollabVersion.isEditionNewer(version, "5.2.4")) {
            props.put("autoDownload", String.format("https://github.com/MyCollab/mycollab/releases/download/Release_%s/upgrade.zip",
                    MyCollabVersion.getVersion()));
        }

        Gson gson = new Gson();
        return gson.toJson(props);
    }
}
