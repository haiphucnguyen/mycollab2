package com.esofthead.mycollab.premium.module.user.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.AccountThemeMapper;
import com.esofthead.mycollab.module.user.dao.AccountThemeMapperExt;
import com.esofthead.mycollab.module.user.domain.AccountSettings;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.domain.AccountThemeExample;
import com.esofthead.mycollab.module.user.service.AccountSettingService;
import com.esofthead.mycollab.module.user.service.AccountThemeService;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
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

	@Autowired
	private AccountSettingService accountSettingService;

	@Override
	public ICrudGenericDAO<Integer, AccountTheme> getCrudMapper() {
		return userThemeMapper;
	}

	@Override
	public AccountTheme getAccountTheme(int saccountid) {
		AccountSettings accountSetting = accountSettingService
				.findAccountSetting(saccountid);

		if (accountSetting.getDefaultthemeid() == null) {
			AccountTheme defaultTheme = getDefaultTheme();
			if (defaultTheme != null) {
				AccountTheme result = (AccountTheme) defaultTheme.copy();
				result.setId(null);
				result.setIsdefault(false);
				return result;
			} else {
				throw new MyCollabException("Can not find default theme");
			}
		} else {
			AccountTheme accountTheme = userThemeMapper
					.selectByPrimaryKey(accountSetting.getDefaultthemeid());
			return accountTheme;
		}
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
		if (theme.getId() == null) {
			int result = accountThemeMapperExt.saveAccountTheme(theme);
			if (result == 1) {

				AccountSettings accountSetting = accountSettingService
						.findAccountSetting(saccountid);
				accountSetting.setDefaultthemeid(theme.getId());
				accountSettingService.updateWithSession(accountSetting, "");
			}
		} else {
			userThemeMapper.updateByPrimaryKey(theme);
		}
	}

}
