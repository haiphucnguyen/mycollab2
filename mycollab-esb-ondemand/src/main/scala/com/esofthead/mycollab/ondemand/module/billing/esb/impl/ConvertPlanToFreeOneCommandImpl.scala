package com.esofthead.mycollab.ondemand.module.billing.esb.impl

import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.ondemand.module.billing.esb.ConvertPlanToFreeOneEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
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