/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.dao.ContentJcrDao;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SimpleComment extends Comment {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(SimpleComment.class);

	private String ownerAvatarId;
	private String ownerFullName;
	private List<Content> attachments;

	public String getOwnerFullName() {
		if (ownerFullName == null || ownerFullName.trim().equals("")) {
			String displayName = getCreateduser();
			int index = (displayName != null) ? displayName.indexOf("@") : 0;
			if (index > 0) {
				return displayName.substring(0, index);
			}
		}
		return ownerFullName;
	}

	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}

	public String getOwnerAvatarId() {
		return ownerAvatarId;
	}

	public void setOwnerAvatarId(String ownerAvatarId) {
		this.ownerAvatarId = ownerAvatarId;
	}

	public List<Content> getAttachments() {
		try {
			if (attachments == null) {
				ContentJcrDao contentJcr = ApplicationContextUtil
						.getSpringBean(ContentJcrDao.class);

				String commentPath = "";
				if (CommentType.PRJ_BUG.toString().equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectBugCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentType.PRJ_MESSAGE.toString().equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectMessageCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentType.PRJ_MILESTONE.toString().equals(
						getType())) {
					commentPath = AttachmentUtils
							.getProjectMilestoneCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentType.PRJ_PROBLEM.toString().equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectProblemCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentType.PRJ_RISK.toString().equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectRiskCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentType.PRJ_TASK.toString().equals(getType())) {
					commentPath = AttachmentUtils
							.getProjectTaskCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentType.PRJ_TASK_LIST.toString().equals(
						getType())) {
					commentPath = AttachmentUtils
							.getProjectTaskListCommentAttachmentPath(
									getSaccountid(), getExtratypeid(),
									getTypeid(), getId());
				} else if (CommentType.CRM_NOTE.toString().equals(getType())) {
					commentPath = AttachmentUtils
							.getCrmNoteCommentAttachmentPath(getSaccountid(),
									getTypeid(), getId());
				} else {
					throw new MyCollabException("Do not support comment type "
							+ getType());
				}

				attachments = contentJcr.getContents(commentPath);
			}
		} catch (Exception e) {
			log.error("Error while get attachments of comment " + getId()
					+ "---" + getSaccountid() + "---" + getExtratypeid()
					+ "---" + getTypeid(), e);
		}

		if (attachments == null) {
			attachments = new ArrayList<Content>();
		}
		return attachments;
	}
}
