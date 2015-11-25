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
@RequestMapping("/checkupdate")
public class CheckVersionController {

    @RequestMapping(method = RequestMethod.GET)
    public String getLatestVersion(@RequestParam("version") String version) {
        Properties props = new Properties();
        props.put("version", MyCollabVersion.getVersion());
        props.put("downloadLink", "http://community.mycollab.com/download/");
        props.put("releaseNotes", "http://community.mycollab.com/release-notes/");

        if (version != null && MyCollabVersion.isEditionNewer(MyCollabVersion.getVersion(), version)) {
            props.put("autoDownload", String.format("http://sourceforge" +
                    ".net/projects/mycollab/files/MyCollab_%s/upgrade.zip/download", MyCollabVersion.getVersion()));
        }

        Gson gson = new Gson();
        return gson.toJson(props);
    }
}
