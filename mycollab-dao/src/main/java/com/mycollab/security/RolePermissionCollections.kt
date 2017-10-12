/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.security

import com.google.common.collect.ImmutableList
import com.mycollab.module.crm.i18n.*
import com.mycollab.module.user.accountsettings.localization.RoleI18nEnum
import com.mycollab.module.user.accountsettings.localization.UserI18nEnum

/**
 * Keep all permissions of MyCollab
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
object RolePermissionCollections {
    @JvmField
    val CRM_ACCOUNT = "Account"

    @JvmField
    val CRM_CONTACT = "Contact"

    @JvmField
    val CRM_CAMPAIGN = "Campaign"

    @JvmField
    val CRM_LEAD = "Lead"

    @JvmField
    val CRM_OPPORTUNITY = "Opportunity"

    @JvmField
    val CRM_CASE = "Case"

    @JvmField
    val CRM_TASK = "Task"

    @JvmField
    val CRM_MEETING = "Meeting"

    @JvmField
    val CRM_CALL = "Call"

    @JvmField
    val CRM_DOCUMENT = "Document"

    @JvmField
    val ACCOUNT_USER = "User"

    @JvmField
    val ACCOUNT_ROLE = "Role"

    @JvmField
    val ACCOUNT_BILLING = "Billing"

    @JvmField
    val ACCOUNT_THEME = "Theme"

    @JvmField
    val CREATE_NEW_PROJECT = "CreateNewProject"

    @JvmField
    val GLOBAL_PROJECT_SETTINGS = "GlobalProjectSettings"

    @JvmField
    val PUBLIC_DOCUMENT_ACCESS = "PublicDocumentAccess"

    @JvmField
    val CRM_PERMISSIONS_ARR: List<PermissionDefItem> = ImmutableList.of(
            PermissionDefItem(CRM_ACCOUNT, AccountI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(CRM_CONTACT, ContactI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(CRM_CAMPAIGN, CampaignI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(CRM_LEAD, LeadI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(CRM_OPPORTUNITY, OpportunityI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(CRM_CASE, CaseI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(CRM_TASK, TaskI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(CRM_MEETING, MeetingI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(CRM_CALL, CallI18nEnum.SINGLE, AccessPermissionFlag::class.java))

    @JvmField
    val ACCOUNT_PERMISSION_ARR: List<PermissionDefItem> = ImmutableList.of(
            PermissionDefItem(ACCOUNT_USER, UserI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(ACCOUNT_ROLE, RoleI18nEnum.SINGLE, AccessPermissionFlag::class.java),
            PermissionDefItem(ACCOUNT_BILLING, RoleI18nEnum.OPT_BILLING_MANAGEMENT, BooleanPermissionFlag::class.java),
            PermissionDefItem(ACCOUNT_THEME, RoleI18nEnum.OPT_THEME, BooleanPermissionFlag::class.java))

    @JvmField
    val PROJECT_PERMISSION_ARR: List<PermissionDefItem> = ImmutableList.of(PermissionDefItem(
            CREATE_NEW_PROJECT, RoleI18nEnum.OPT_CREATE_NEW_PROJECT, BooleanPermissionFlag::class.java), PermissionDefItem(
            GLOBAL_PROJECT_SETTINGS, RoleI18nEnum.OPT_GLOBAL_PROJECT_SETTINGS, BooleanPermissionFlag::class.java))

    @JvmField
    val DOCUMENT_PERMISSION_ARR: List<PermissionDefItem> = ImmutableList.of(PermissionDefItem(
            PUBLIC_DOCUMENT_ACCESS, RoleI18nEnum.OPT_PUBLIC_DOCUMENTS, AccessPermissionFlag::class.java))
}
