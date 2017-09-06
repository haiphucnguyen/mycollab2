package com.mycollab.module.project.esb

import com.mycollab.module.project.domain.Message

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class DeleteProjectMessageEvent(val messages: Array[Message], val username: String, val accountId: Integer) {}
