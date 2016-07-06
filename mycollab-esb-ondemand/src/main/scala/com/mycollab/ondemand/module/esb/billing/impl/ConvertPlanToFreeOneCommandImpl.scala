package com.mycollab.ondemand.module.esb.billing.impl

import com.mycollab.ondemand.module.billing.esb.ConvertPlanToFreeOneEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import com.mycollab.module.esb.GenericCommand
import org.springframework.stereotype.Component

/**
 * @author MyCollab Ltd.
 * @since 4.5.1
 */
@Component class ConvertPlanToFreeOneCommandImpl extends GenericCommand {
    @AllowConcurrentEvents
    @Subscribe
    def convert(event: ConvertPlanToFreeOneEvent): Unit = {

    }
}