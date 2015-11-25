/**
 * This file is part of mycollab-esb.
 *
 * mycollab-esb is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-esb is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-esb.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.esb.impl

import com.esofthead.mycollab.common.dao.CommentMapper
import com.esofthead.mycollab.common.domain.CommentExample
import com.esofthead.mycollab.module.GenericCommand
import com.esofthead.mycollab.module.ecm.service.ResourceService
import com.esofthead.mycollab.module.file.AttachmentUtils
import com.esofthead.mycollab.module.project.ProjectTypeConstants
import com.esofthead.mycollab.module.project.dao.PredecessorMapper
import com.esofthead.mycollab.module.project.domain.PredecessorExample
import com.esofthead.mycollab.module.project.esb.DeleteProjectMilestoneEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component class DeleteProjectMilestoneCommand extends GenericCommand {
    @Autowired private val resourceService: ResourceService = null
    @Autowired private val commentMapper: CommentMapper = null
    @Autowired private val predecessorMapper: PredecessorMapper = null

    @AllowConcurrentEvents
    @Subscribe
    def removedMilestone(event: DeleteProjectMilestoneEvent): Unit = {
        removeRelatedFiles(event.accountId, event.projectId, event.milestoneId)
        removeRelatedComments(event.milestoneId)
        removePredecessorMilestones(event.milestoneId)
    }

    private def removeRelatedFiles(accountId: Integer, projectId: Integer, milestoneId: Integer) {
        val attachmentPath: String = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
            ProjectTypeConstants.MILESTONE, "" + milestoneId)
        resourceService.removeResource(attachmentPath, "", accountId)
    }

    private def removeRelatedComments(milestoneId: Integer) {
        val ex: CommentExample = new CommentExample
        ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.MILESTONE).andExtratypeidEqualTo(milestoneId)
        commentMapper.deleteByExample(ex)
    }

    private def removePredecessorMilestones(milestoneId: Integer): Unit = {
        val ex: PredecessorExample = new PredecessorExample
        ex.or().andSourceidEqualTo(milestoneId).andSourcetypeEqualTo(ProjectTypeConstants.MILESTONE)
        ex.or().andDescidEqualTo(milestoneId).andDesctypeEqualTo(ProjectTypeConstants.MILESTONE)
        predecessorMapper.deleteByExample(ex);
    }
}