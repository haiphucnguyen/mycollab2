package com.mycollab.ondemand.module.billing

import com.mycollab.core.UserInvalidInputException

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
class ExistedSubDomainException(errorMsg: String) : UserInvalidInputException(errorMsg)
