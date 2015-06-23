package com.esofthead.mycollab.premium.module.user.service.mybatis;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.AccountThemeMapper;
import com.esofthead.mycollab.module.user.dao.AccountThemeMapperExt;
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
@SuppressWarnings("unchecked")
public class AccountThemeServiceImpl extends
        DefaultCrudService<Integer, AccountTheme> implements
        AccountThemeService {

    @Autowired
    private AccountThemeMapper userThemeMapper;

    @Autowired
    private AccountThemeMapperExt accountThemeMapperExt;

    @Override
    public ICrudGenericDAO<Integer, AccountTheme> getCrudMapper() {
        return userThemeMapper;
    }

    @Override
    public AccountTheme getAccountTheme(int saccountid) {
        return null;
    }

    @Override
    public AccountTheme getDefaultTheme() {
        AccountThemeExample ex = new AccountThemeExample();
        ex.createCriteria().andIsdefaultEqualTo(true);
        List<AccountTheme> defaultThemes = userThemeMapper.selectByExample(ex);
        if (defaultThemes == null || defaultThemes.size() == 0) {
            throw new MyCollabException("Can not find default theme");
        }

        return defaultThemes.get(0);
    }

    @Override
    public void saveAccountTheme(AccountTheme theme, int saccountid) {

    }

}
