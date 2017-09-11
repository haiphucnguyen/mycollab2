package com.mycollab.security

/**
 * Access permission flag
 *
 * @author MyCollab Ltd
 * @since 1.0
 */
class AccessPermissionFlag : PermissionFlag() {
    companion object {
        val NO_ACCESS = 0
        val READ_ONLY = 1
        val READ_WRITE = 2
        val ACCESS = 4

        /**
         * Check whether `flag` implies read permission
         *
         * @param flag
         * @return true of `flag` implies read permission
         */
        fun canRead(flag: Int): Boolean {
            return flag and READ_ONLY == READ_ONLY
                    || flag and READ_WRITE == READ_WRITE
                    || flag and ACCESS == ACCESS
        }

        /**
         * Check whether `flag` implies write permission
         *
         * @param flag
         * @return true of `flag` implies write permission
         */
        fun canWrite(flag: Int): Boolean {
            return flag and READ_WRITE == READ_WRITE || flag and ACCESS == ACCESS
        }

        /**
         * Check whether `flag` implies access permission
         *
         * @param flag
         * @return true of `flag` implies access permission
         */
        fun canAccess(flag: Int): Boolean {
            return flag and ACCESS == ACCESS
        }
    }
}
