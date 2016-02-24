package com.esofthead.mycollab.ondemand.module.user.service.mybatis;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.AccountThemeMapper;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.domain.AccountThemeExample;
import com.esofthead.mycollab.module.user.service.AccountThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@Service
public class AccountThemeServiceImpl extends DefaultCrudService<Integer, AccountTheme> implements AccountThemeService {

    @Autowired
    private AccountThemeMapper accountThemeMapper;

    @Override
    public ICrudGenericDAO<Integer, AccountTheme> getCrudMapper() {
        return accountThemeMapper;
    }

    @Override
    public AccountTheme findTheme(Integer sAccountId) {
        AccountThemeExample ex = new AccountThemeExample();
        ex.createCriteria().andSaccountidEqualTo(sAccountId);
        List<AccountTheme> accountThemes = accountThemeMapper.selectByExample(ex);
        if (accountThemes != null && accountThemes.size() > 0) {
            return accountThemes.get(0);
        }

        return null;
    }

    @Override
    public AccountTheme findDefaultTheme(Integer sAccountId) {
        AccountThemeExample ex = new AccountThemeExample();
        ex.createCriteria().andIsdefaultEqualTo(Boolean.TRUE);
        List<AccountTheme> accountThemes = accountThemeMapper.selectByExample(ex);
        if (accountThemes != null && accountThemes.size() > 0) {
            return accountThemes.get(0);
        }

        return null;
    }

    @Override
    public void removeTheme(@CacheKey Integer sAccountId) {
        AccountThemeExample ex = new AccountThemeExample();
        ex.createCriteria().andSaccountidEqualTo(sAccountId);
        accountThemeMapper.deleteByExample(ex);
    }
}
