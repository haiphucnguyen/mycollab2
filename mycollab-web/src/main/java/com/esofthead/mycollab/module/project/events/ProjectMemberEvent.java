/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.events;

import java.util.List;

import com.esofthead.mycollab.eventmanager.ApplicationEvent;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectMemberEvent {

	public static class InviteProjectMembers extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		private List<String> inviteEmails;
		private Integer roleId;

		public InviteProjectMembers(Object source, List<String> emails,
				Integer roleId) {
			super(source, null);
			this.inviteEmails = emails;
			this.roleId = roleId;
		}

		public List<String> getInviteEmails() {
			return inviteEmails;
		}

		public Integer getRoleId() {
			return roleId;
		}
	}

	public static class Search extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public Search(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoList extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoList(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoInviteMembers extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoInviteMembers(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoRead extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoRead(Object source, Object data) {
			super(source, data);
		}
	}

	public static class GotoEdit extends ApplicationEvent {
		private static final long serialVersionUID = 1L;

		public GotoEdit(Object source, Object data) {
			super(source, data);
		}
	}
}
