package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.configuration.EnDecryptHelper;
import com.esofthead.mycollab.core.MyCollabVersion;
import com.esofthead.mycollab.module.support.dao.ProEditionInfoMapper;
import com.esofthead.mycollab.module.support.domain.ProEditionInfoExample;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private EditionInfoResolver editionInfoResolver;

    @RequestMapping(value = "/checkupdate", method = RequestMethod.GET)
    public String getLatestVersion(@RequestParam("version") String version) {
        Properties props = new Properties();

        String liveVersion = editionInfoResolver.getEditionInfo().getVersion();
        props.put("version", liveVersion);
        props.put("downloadLink", "https://www.mycollab.com/ce-registration/");
        props.put("releaseNotes", String.format("https://community.mycollab.com/releases/release-notes-for-mycollab-%s/",
                MyCollabVersion.getVersion().replace('.', '-')));

        if (version != null && MyCollabVersion.isEditionNewer(liveVersion, version) &&
                MyCollabVersion.isEditionNewer(version, "5.3.3")) {
            props.put("autoDownload", editionInfoResolver.getEditionInfo().getCommunityUpgradeLink());
        }

        Gson gson = new Gson();
        return gson.toJson(props);
    }

    @Autowired
    private ProEditionInfoMapper proEditionInfoMapper;

    @RequestMapping(value = "/checkpremiumupdate", method = RequestMethod.GET)
    public String getLatestPremiumUpdate(@RequestParam("version") String version, @RequestParam("customerId") String customerId) {
        Properties props = new Properties();
        String liveVersion = editionInfoResolver.getEditionInfo().getVersion();
        props.put("version", liveVersion);
        props.put("downloadLink", "https://www.mycollab.com/ee-registration/");
        props.put("releaseNotes", String.format("https://community.mycollab.com/releases/release-notes-for-mycollab-%s/",
                MyCollabVersion.getVersion().replace('.', '-')));
        ProEditionInfoExample ex = new ProEditionInfoExample();
        ex.createCriteria().andIdEqualTo(Integer.parseInt(EnDecryptHelper.decryptText(customerId)));
        boolean isExistCustomer = proEditionInfoMapper.countByExample(ex) > 0;
        if (isExistCustomer && version != null && MyCollabVersion.isEditionNewer(liveVersion, version)) {
            props.put("autoDownload", editionInfoResolver.getEditionInfo().getPremiumUpgradeLink());
        }

        Gson gson = new Gson();
        return gson.toJson(props);
    }
}
