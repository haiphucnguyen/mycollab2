package com.mycollab.module.billing.esb

/**
  * @author MyCollab Ltd
  * @since 5.1.1
  */
class AccountCreatedEvent(val accountId: Integer, val initialUser: String, val createSampleData: Boolean) {}
