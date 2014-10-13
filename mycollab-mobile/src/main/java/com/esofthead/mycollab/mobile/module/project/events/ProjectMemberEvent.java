package com.esofthead.mycollab.mobile.module.project.events;

import java.io.Serializable;
import java.util.List;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.2
 * 
 */
public class ProjectMemberEvent {

	public static class InviteProjectMembers implements Serializable {
		private static final long serialVersionUID = 1L;

		private List<String> inviteEmails;
		private Integer roleId;
		private String inviteMessage;

		public InviteProjectMembers(List<String> emails, Integer roleId,
				String inviteMessage) {
			this.inviteEmails = emails;
			this.roleId = roleId;
			this.inviteMessage = inviteMessage;
		}

		public List<String> getInviteEmails() {
			return inviteEmails;
		}

		public Integer getRoleId() {
			return roleId;
		}

		public String getInviteMessage() {
			return inviteMessage;
		}

		public void setInviteMessage(String inviteMessage) {
			this.inviteMessage = inviteMessage;
		}
	}

	public static class Search extends ApplicationEvent {

		private static final long serialVersionUID = -4614125940848752982L;

		public Search(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoList extends ApplicationEvent {

		private static final long serialVersionUID = 5713208630722347339L;

		public GotoList(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoInviteMembers extends ApplicationEvent {

		private static final long serialVersionUID = 8393204762541970672L;

		public GotoInviteMembers(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoRead extends ApplicationEvent {

		private static final long serialVersionUID = -7306650933057476044L;

		public GotoRead(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoEdit extends ApplicationEvent {

		private static final long serialVersionUID = -5237825362340624200L;

		public GotoEdit(Object source, Object data) {
			super(source, data);
		}
	}
}
