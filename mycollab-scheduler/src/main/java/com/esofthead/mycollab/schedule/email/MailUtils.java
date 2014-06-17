/**
 * This file is part of mycollab-scheduler.
 *
 * mycollab-scheduler is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-scheduler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-scheduler.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.schedule.email;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.i18n.LocaleUtils;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class MailUtils {

	public static String getSiteUrl(Integer sAccountId) {
		String siteUrl = "";
		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			BillingAccountService billingAccountService = ApplicationContextUtil
					.getSpringBean(BillingAccountService.class);
			BillingAccount account = billingAccountService
					.getAccountById(sAccountId);
			if (account != null) {
				siteUrl = SiteConfiguration.getSiteUrl(account.getSubdomain());
			}
		} else {
			siteUrl = SiteConfiguration.getSiteUrl("");
		}
		return siteUrl;
	}

	public static String getAvatarLink(String userAvatarId, int size) {
		return SiteConfiguration.getStorageConfiguration().generateAvatarPath(
				userAvatarId, size);
	}

	private static Map<String, String> cacheFile = new HashMap<String, String>();

	public static String templatePath(String path, String language) {
		return templatePath(path, LocaleUtils.toLocale(language));
	}

	public static String templatePath(String path, Locale locale) {
		String key = (locale != null) ? (path + locale) : (path + Locale.US);
		String filePath = cacheFile.get(key);
		if (filePath != null) {
			return filePath;
		} else {
			int index = path.indexOf("mt");
			if (index == -1) {
				throw new MyCollabException("File type is not supported "
						+ path);
			}
			filePath = path.substring(0, index - 1);
			return String.format("%s%s.mt", filePath, locale);
		}
	}
}
