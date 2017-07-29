package com.mycollab.module.project.esb

import com.mycollab.module.project.domain.ProjectMember

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class DeleteProjectMemberEvent(val members: Array[ProjectMember], val username: String, val accountId: Integer) {}
