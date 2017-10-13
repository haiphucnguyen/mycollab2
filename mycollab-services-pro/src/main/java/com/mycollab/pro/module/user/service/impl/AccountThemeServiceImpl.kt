package com.mycollab.pro.module.user.service.impl

import com.mycollab.core.cache.CacheKey
import com.mycollab.db.persistence.ICrudGenericDAO
import com.mycollab.db.persistence.service.DefaultCrudService
import com.mycollab.module.user.dao.AccountThemeMapper
import com.mycollab.module.user.domain.AccountTheme
import com.mycollab.module.user.domain.AccountThemeExample
import com.mycollab.module.user.service.AccountThemeService
import org.springframework.stereotype.Service

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
class AccountThemeServiceImpl(private val accountThemeMapper: AccountThemeMapper) : DefaultCrudService<Int, AccountTheme>(), AccountThemeService {


    override val crudMapper: ICrudGenericDAO<Int, AccountTheme>
        get() = accountThemeMapper as ICrudGenericDAO<Int, AccountTheme>

    override fun findTheme(sAccountId: Int): AccountTheme? {
        val ex = AccountThemeExample()
        ex.createCriteria().andSaccountidEqualTo(sAccountId)
        val accountThemes = accountThemeMapper.selectByExample(ex)
        return if (accountThemes != null && accountThemes.size > 0) {
            accountThemes[0]
        } else null

    }

    override fun findDefaultTheme(sAccountId: Int): AccountTheme? {
        val ex = AccountThemeExample()
        ex.createCriteria().andIsdefaultEqualTo(java.lang.Boolean.TRUE)
        val accountThemes = accountThemeMapper.selectByExample(ex)
        return if (accountThemes.isNotEmpty()) {
            accountThemes[0]
        } else null

    }

    override fun removeTheme(@CacheKey sAccountId: Int) {
        val ex = AccountThemeExample()
        ex.createCriteria().andSaccountidEqualTo(sAccountId)
        accountThemeMapper.deleteByExample(ex)
    }
}
