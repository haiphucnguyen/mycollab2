package com.esofthead.mycollab.module.project.esb

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
trait DeleteProjectBugCommand {
    /**
     *
     * @param username
     * @param accountId
     * @param projectId
     * @param bugId
     */
    def bugRemoved(username: String, accountId: Int, projectId: Int, bugId: Int): Unit;
}
