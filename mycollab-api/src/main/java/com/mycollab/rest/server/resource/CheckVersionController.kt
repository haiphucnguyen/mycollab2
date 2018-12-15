package com.mycollab.rest.server.resource

import com.mycollab.core.Version
import com.mycollab.ondemand.configuration.EditionInfo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.1.2
 */
@Api(value = "Versions", tags = ["Support"])
@RestController
class CheckVersionController(private val editionInfo: EditionInfo) {

    @ApiOperation(value = "Check version whether it is the latest version. If it is not, return the latest version information", response = String::class)
    @RequestMapping(value = ["/checkupdate"], method = [(RequestMethod.GET)], produces = [(MediaType.APPLICATION_JSON_VALUE)])
    fun getLatestVersion(@RequestParam("version", required = false) version: String?): Properties {
        val props = Properties()

        val liveVersion = editionInfo.version
        props["version"] = liveVersion
        props["downloadLink"] = "https://www.mycollab.com/ce-registration/"
        props["releaseNotes"] = "https://community.mycollab.com/releases/release-notes-for-mycollab-${Version.getVersion().replace('.', '-')}/"

        if (version != null && Version.isEditionNewer(liveVersion, version) &&
                Version.isEditionNewer(version, "5.3.4")) {
            props["autoDownload"] = editionInfo.communityUpgradeLink
        }

        return props
    }

    @RequestMapping(value = ["/checkpremiumupdate"], method = [(RequestMethod.GET)])
    fun getLatestPremiumUpdate(@RequestParam("version") version: String, @RequestParam("customerId") customerId: String): Properties {
        val props = Properties()
        val liveVersion = editionInfo.version
        props["version"] = liveVersion
        props["downloadLink"] = "https://www.mycollab.com/ee-registration/"
        props["releaseNotes"] = "https://community.mycollab.com/releases/release-notes-for-mycollab-${Version.getVersion().replace('.', '-')}/"
        props["autoDownload"] = editionInfo.premiumUpgradeLink
        return props
    }
}
