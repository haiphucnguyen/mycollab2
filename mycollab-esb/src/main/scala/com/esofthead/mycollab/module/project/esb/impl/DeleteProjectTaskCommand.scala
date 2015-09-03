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
import com.esofthead.mycollab.module.project.esb.DeleteProjectTaskEvent
import com.google.common.eventbus.{AllowConcurrentEvents, Subscribe}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component class DeleteProjectTaskCommand extends GenericCommand {
    @Autowired private val resourceService: ResourceService = null
    @Autowired private val commentMapper: CommentMapper = null
    @Autowired private val predecessorMapper: PredecessorMapper = null

    @AllowConcurrentEvents
    @Subscribe
    def removedTask(event: DeleteProjectTaskEvent): Unit = {
        for (task <- event.tasks) {
            removeRelatedFiles(event.accountId, task.getProjectid, task.getId)
            removeRelatedComments(task.getId)
            removePredecessorTasks(task.getId)
        }

    }

    private def removeRelatedFiles(accountId: Integer, projectId: Integer, taskId: Integer) {
        val attachmentPath: String = AttachmentUtils.getProjectEntityAttachmentPath(accountId, projectId,
            ProjectTypeConstants.TASK, "" + taskId)
        resourceService.removeResource(attachmentPath, "", accountId)
    }

    private def removeRelatedComments(taskId: Integer) {
        val ex: CommentExample = new CommentExample
        ex.createCriteria.andTypeEqualTo(ProjectTypeConstants.TASK).andExtratypeidEqualTo(taskId)
        commentMapper.deleteByExample(ex)
    }

    private def removePredecessorTasks(taskId: Integer): Unit = {
        val ex: PredecessorExample = new PredecessorExample
        ex.or().andSourceidEqualTo(taskId).andTypeEqualTo (ProjectTypeConstants.TASK)
        ex.or().andDescidEqualTo(taskId).andTypeEqualTo(ProjectTypeConstants.TASK)
        predecessorMapper.deleteByExample(ex);
    }
}