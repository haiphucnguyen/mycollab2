package com.mycollab.module.user.esb

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
class SendUserInvitationEvent(val invitee: String, val password:String, val inviter: String, val subdomain: String, val sAccountId: Integer) {
    
}
