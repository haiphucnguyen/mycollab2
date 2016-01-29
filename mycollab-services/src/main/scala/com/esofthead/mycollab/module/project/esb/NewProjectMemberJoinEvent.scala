package com.esofthead.mycollab.module.project.esb

/**
  * @author MyCollab Ltd
  * @since 5.2.6
  */
class NewProjectMemberJoinEvent(val username: String, val projectId: Integer, val sAccountId: Integer, val isNewAccountCreated: Boolean) {
  
}
