/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.user;

/**
 * 
 * @author haiphucnguyen
 */
public class RolePermissionCollections {

	public static final String CRM_ACCOUNT = "Account";
	public static final String CRM_CONTACT = "Contact";
	public static final String CRM_CAMPAIGN = "Campaign";
	public static final String CRM_LEAD = "Lead";
	public static final String CRM_OPPORTUNITY = "Opportunity";
	public static final String CRM_CASE = "Case";
	public static final String CRM_TASK = "Task";
	public static final String CRM_MEETING = "Meeting";
	public static final String CRM_CALL = "Call";
	public static final String CRM_DOCUMENT = "Document";

	public static final String USER_USER = "User";
	public static final String USER_ROLE = "Role";

	public static final String CREATE_NEW_PROJECT = "CreateNewProject";

	public static final String PUBLIC_DOCUMENT_ACCESS = "PublicDocumentAccess";

	public static final PermissionDefItem[] CRM_PERMISSIONS_ARR = {
			new PermissionDefItem(CRM_ACCOUNT, "Account",
					AccessPermissionFlag.class),
			new PermissionDefItem(CRM_CONTACT, "Contact",
					AccessPermissionFlag.class),
			new PermissionDefItem(CRM_CAMPAIGN, "Campaign",
					AccessPermissionFlag.class),
			new PermissionDefItem(CRM_LEAD, "Lead", AccessPermissionFlag.class),
			new PermissionDefItem(CRM_OPPORTUNITY, "Opportunity",
					AccessPermissionFlag.class),
			new PermissionDefItem(CRM_CASE, "Case", AccessPermissionFlag.class),
			new PermissionDefItem(CRM_TASK, "Task", AccessPermissionFlag.class),
			new PermissionDefItem(CRM_MEETING, "Meeting",
					AccessPermissionFlag.class),
			new PermissionDefItem(CRM_CALL, "Call", AccessPermissionFlag.class) };

	public static final PermissionDefItem[] USER_PERMISSION_ARR = {
			new PermissionDefItem(USER_USER, "User", AccessPermissionFlag.class),
			new PermissionDefItem(USER_ROLE, "Role", AccessPermissionFlag.class) };

	public static final PermissionDefItem[] PROJECT_PERMISSION_ARR = { new PermissionDefItem(
			CREATE_NEW_PROJECT, "Create New Project",
			BooleanPermissionFlag.class) };

	public static final PermissionDefItem[] DOCUMENT_PERMISSION_ARR = { new PermissionDefItem(
			PUBLIC_DOCUMENT_ACCESS, "Public Documents",
			AccessPermissionFlag.class) };
}
