package com.mycollab.module.project.esb

import com.mycollab.module.tracker.domain.BugWithBLOBs

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class DeleteProjectBugEvent(val bugs: Array[BugWithBLOBs], val username: String, val accountId: Integer) {}
