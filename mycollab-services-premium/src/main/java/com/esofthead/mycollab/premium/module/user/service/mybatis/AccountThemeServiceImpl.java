/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.user.service.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.persistence.ICrudGenericDAO;
import com.esofthead.mycollab.core.persistence.service.DefaultCrudService;
import com.esofthead.mycollab.module.user.dao.AccountSettingsMapper;
import com.esofthead.mycollab.module.user.dao.AccountThemeMapper;
import com.esofthead.mycollab.module.user.dao.AccountThemeMapperExt;
import com.esofthead.mycollab.module.user.domain.AccountSettings;
import com.esofthead.mycollab.module.user.domain.AccountSettingsExample;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.domain.AccountThemeExample;
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
	private AccountSettingsMapper accountSettingsMapper;

	@Override
	public ICrudGenericDAO<Integer, AccountTheme> getCrudMapper() {
		return userThemeMapper;
	}

	@Override
	public AccountTheme getAccountTheme(int saccountid) {
		AccountSettingsExample accountEx = new AccountSettingsExample();
		accountEx.createCriteria().andSaccountidEqualTo(saccountid);
		List<AccountSettings> accountSettings = accountSettingsMapper
				.selectByExample(accountEx);
		if (accountSettings == null || accountSettings.size() == 0
				|| accountSettings.get(0).getDefaultthemeid() == null) {
			AccountTheme defaultTheme = getDefaultTheme();
			if (defaultTheme != null) {
				AccountTheme result = (AccountTheme) defaultTheme.copy();
				result.setId(null);
				result.setIsdefault(false);
				return result;
			} else {
				return null;
			}
		}

		AccountTheme accountTheme = userThemeMapper
				.selectByPrimaryKey(accountSettings.get(0).getDefaultthemeid());

		return accountTheme;
	}

	@Override
	public AccountTheme getDefaultTheme() {
		AccountThemeExample ex = new AccountThemeExample();
		ex.createCriteria().andIsdefaultEqualTo(true);
		List<AccountTheme> defaultThemes = userThemeMapper.selectByExample(ex);
		if (defaultThemes == null || defaultThemes.size() == 0) {
			return null;
		}

		return defaultThemes.get(0);
	}

	@Override
	public void saveAccountTheme(AccountTheme theme, int saccountid) {
		if (theme.getId() == null) {
			int result = accountThemeMapperExt.saveAccountTheme(theme);
			if (result == 1) {
				AccountSettingsExample ex = new AccountSettingsExample();
				ex.createCriteria().andSaccountidEqualTo(saccountid);
				List<AccountSettings> accountSettings = accountSettingsMapper
						.selectByExample(ex);
				if (accountSettings == null || accountSettings.size() == 0) {
					return;
				}
				AccountSettings accountSetting = accountSettings.get(0);
				accountSetting.setDefaultthemeid(theme.getId());
				accountSettingsMapper.updateByPrimaryKey(accountSetting);
			}
		} else {
			userThemeMapper.updateByPrimaryKeySelective(theme);
		}
	}

}
