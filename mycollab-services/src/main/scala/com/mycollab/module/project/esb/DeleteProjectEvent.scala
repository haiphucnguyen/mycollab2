package com.mycollab.module.project.esb

import com.mycollab.module.project.domain.Project

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
class DeleteProjectEvent(val projects: Array[Project], val accountId: Integer) {}
