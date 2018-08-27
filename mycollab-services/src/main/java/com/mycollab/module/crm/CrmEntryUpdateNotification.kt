package com.mycollab.module.crm

import com.mycollab.core.AbstractNotification

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class CrmEntryUpdateNotification(val type:String, val typeId:String, val message: String) : AbstractNotification(AbstractNotification.NEWS)