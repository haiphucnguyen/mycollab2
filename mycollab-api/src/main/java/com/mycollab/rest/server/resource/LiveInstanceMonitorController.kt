package com.mycollab.rest.server.resource

import com.mycollab.common.domain.LiveInstance
import com.mycollab.common.domain.LiveInstanceExample
import com.mycollab.pro.common.dao.LiveInstanceMapper
import org.joda.time.DateTime
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@RestController
class LiveInstanceMonitorController(private val liveInstanceMapper: LiveInstanceMapper) {

    @RequestMapping(value = "/checkInstance", method = arrayOf(RequestMethod.POST))
    fun checkInstance(@RequestBody instance: LiveInstance): String {
        val sysId = instance.sysid
        val ex = LiveInstanceExample()
        ex.createCriteria().andSysidEqualTo(sysId)
        when {
            liveInstanceMapper.countByExample(ex) == 0L -> {
                instance.installeddate = DateTime().toDate()
                instance.lastupdateddate = DateTime().toDate()
                liveInstanceMapper.insert(instance)
            }
            else -> {
                val liveInstances = liveInstanceMapper.selectByExample(ex)
                if (liveInstances.isNotEmpty()) {
                    val oldInstance = liveInstances[0]
                    instance.id = oldInstance.id
                    instance.installeddate = oldInstance.installeddate
                    instance.lastupdateddate = DateTime().toDate()
                    liveInstanceMapper.updateByPrimaryKey(instance)
                }
            }
        }
        return "Ok"
    }
}
