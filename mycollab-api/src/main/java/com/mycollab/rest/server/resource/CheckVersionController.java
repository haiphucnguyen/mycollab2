package com.mycollab.rest.server.resource;

import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.core.MyCollabVersion;
import com.mycollab.ondemand.module.billing.dao.ProEditionInfoMapper;
import com.mycollab.ondemand.module.billing.domain.ProEditionInfoExample;
import com.mycollab.ondemand.module.support.service.EditionInfoResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/checkupdate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Properties getLatestVersion(@RequestParam("version") String version) {
        Properties props = new Properties();

        String liveVersion = editionInfoResolver.getEditionInfo().getVersion();
        props.put("version", liveVersion);
        props.put("downloadLink", "https://www.mycollab.com/ce-registration/");
        props.put("releaseNotes", String.format("https://community.mycollab.com/releases/release-notes-for-mycollab-%s/",
                MyCollabVersion.getVersion().replace('.', '-')));

        if (version != null && MyCollabVersion.isEditionNewer(liveVersion, version) &&
                MyCollabVersion.isEditionNewer(version, "5.3.4")) {
            props.put("autoDownload", editionInfoResolver.getEditionInfo().getCommunityUpgradeLink());
        }

        return props;
    }

    @Autowired
    private ProEditionInfoMapper proEditionInfoMapper;

    @RequestMapping(value = "/checkpremiumupdate", method = RequestMethod.GET)
    public Properties getLatestPremiumUpdate(@RequestParam("version") String version, @RequestParam("customerId") String customerId) {
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

        return props;
    }
}
