package com.mycollab.security

import com.fasterxml.jackson.annotation.JsonProperty
import com.mycollab.core.arguments.ValuedBean
import com.mycollab.core.utils.JsonDeSerializer
import java.util.*

/**
 * Map contains all permissions in MyCollab, it is used to all permissions if
 * logged in user.
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
class PermissionMap : ValuedBean() {

    @JsonProperty("perMap")
    private val perMap = HashMap<String, Int>()

    /**
     * @param permissionItem
     * @param value
     */
    fun addPath(permissionItem: String, value: Int) {
        perMap.put(permissionItem, value)
    }

    /**
     * @param permissionItem
     * @return
     */
    fun getPermissionFlag(permissionItem: String): Int? {
        val value = perMap[permissionItem]
        return value ?: AccessPermissionFlag.NO_ACCESS
    }

    /**
     * @param permissionItem
     * @return
     */
    operator fun get(permissionItem: String): Int? {
        return perMap[permissionItem]
    }

    /**
     * @param permissionItem
     * @return
     */
    fun canBeYes(permissionItem: String): Boolean {
        val value = perMap[permissionItem]
        return value != null && BooleanPermissionFlag.beTrue(value as Int?)
    }

    /**
     * @param permissionItem
     * @return
     */
    fun canBeFalse(permissionItem: String): Boolean {
        val value = perMap[permissionItem]
        return value != null && BooleanPermissionFlag.beFalse(value as Int?)
    }

    /**
     * @param permissionItem
     * @return
     */
    fun canRead(permissionItem: String): Boolean {
        val value = perMap[permissionItem]
        return value != null && AccessPermissionFlag.canRead((value as Int?)!!)
    }

    /**
     * @param permissionItem
     * @return
     */
    fun canWrite(permissionItem: String): Boolean {
        val value = perMap[permissionItem]
        return value != null && AccessPermissionFlag.canWrite((value as Int?)!!)
    }

    /**
     * @param permissionItem
     * @return
     */
    fun canAccess(permissionItem: String): Boolean {
        val value = perMap[permissionItem]
        return value != null && AccessPermissionFlag.canAccess((value as Int?)!!)
    }

    /**
     * @return
     */
    fun toJsonString(): String {
        return JsonDeSerializer.toJson(this)
    }

    companion object {
        private val serialVersionUID = 1L

        /**
         * @param json
         * @return
         */
        fun fromJsonString(json: String): PermissionMap {
            return JsonDeSerializer.fromJson(json, PermissionMap::class.java)
        }

        /**
         * @return
         */
        fun buildAdminPermissionCollection(): PermissionMap {
            val permissionMap = PermissionMap()
            for (element in RolePermissionCollections.CRM_PERMISSIONS_ARR) {
                permissionMap.addPath(element.key, AccessPermissionFlag.ACCESS)
            }

            for (element in RolePermissionCollections.ACCOUNT_PERMISSION_ARR) {
                if (element.key == RolePermissionCollections.ACCOUNT_BILLING || element.key == RolePermissionCollections.ACCOUNT_THEME) {
                    permissionMap.addPath(element.key, BooleanPermissionFlag.TRUE)
                } else {
                    permissionMap.addPath(element.key, AccessPermissionFlag.ACCESS)
                }
            }

            for (element in RolePermissionCollections.PROJECT_PERMISSION_ARR) {
                permissionMap.addPath(element.key, BooleanPermissionFlag.TRUE)
            }

            for (element in RolePermissionCollections.DOCUMENT_PERMISSION_ARR) {
                permissionMap.addPath(element.key, AccessPermissionFlag.ACCESS)
            }
            return permissionMap
        }

        /**
         * @return
         */
        fun buildEmployeePermissionCollection(): PermissionMap {
            val permissionMap = PermissionMap()
            for (element in RolePermissionCollections.CRM_PERMISSIONS_ARR) {
                permissionMap.addPath(element.key, AccessPermissionFlag.READ_ONLY)
            }

            for (element in RolePermissionCollections.ACCOUNT_PERMISSION_ARR) {
                if (element.key == RolePermissionCollections.ACCOUNT_BILLING || element.key == RolePermissionCollections.ACCOUNT_THEME) {
                    permissionMap.addPath(element.key, BooleanPermissionFlag.FALSE)
                } else {
                    permissionMap.addPath(element.key, AccessPermissionFlag.READ_ONLY)
                }
            }

            for (element in RolePermissionCollections.PROJECT_PERMISSION_ARR) {
                if (RolePermissionCollections.CREATE_NEW_PROJECT.equals(element.key)) {
                    permissionMap.addPath(element.key, BooleanPermissionFlag.TRUE)
                } else {
                    permissionMap.addPath(element.key, BooleanPermissionFlag.FALSE)
                }
            }

            for (element in RolePermissionCollections.DOCUMENT_PERMISSION_ARR) {
                permissionMap.addPath(element.key, AccessPermissionFlag.READ_WRITE)
            }
            return permissionMap
        }

        /**
         * @return
         */
        fun buildGuestPermissionCollection(): PermissionMap {
            val permissionMap = PermissionMap()
            for (element in RolePermissionCollections.CRM_PERMISSIONS_ARR) {
                permissionMap.addPath(element.key, AccessPermissionFlag.NO_ACCESS)
            }

            for (element in RolePermissionCollections.ACCOUNT_PERMISSION_ARR) {
                if (element.key == RolePermissionCollections.ACCOUNT_BILLING || element.key == RolePermissionCollections.ACCOUNT_THEME) {
                    permissionMap.addPath(element.key, BooleanPermissionFlag.FALSE)
                } else {
                    permissionMap.addPath(element.key, AccessPermissionFlag.NO_ACCESS)
                }
            }

            for (element in RolePermissionCollections.PROJECT_PERMISSION_ARR) {
                permissionMap.addPath(element.key, BooleanPermissionFlag.FALSE)
            }

            for (element in RolePermissionCollections.DOCUMENT_PERMISSION_ARR) {
                permissionMap.addPath(element.key, AccessPermissionFlag.NO_ACCESS)
            }
            return permissionMap
        }
    }
}
