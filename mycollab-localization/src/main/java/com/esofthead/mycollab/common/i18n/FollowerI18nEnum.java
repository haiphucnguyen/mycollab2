/**
 * This file is part of mycollab-localization.
 *
 * mycollab-localization is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-localization is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-localization.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.common.i18n;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.Locale;
import ch.qos.cal10n.LocaleData;

@BaseName("localization/common-follower")
@LocaleData(value = { @Locale("en-US"), @Locale("ja-JP") }, defaultCharset = "UTF-8")
public enum FollowerI18nEnum {
	DIALOG_WATCHERS_TITLE,
	
	BUTTON_BACK_TO_WORKBOARD,
	BUTTON_FOLLOW,
	BUTTON_UNFOLLOW,
	
	FORM_PROJECT_NAME,
	FORM_SUMMARY,
	
	OPT_FOLLOWER_NAME,
	OPT_FOLLOWER_CREATE_DATE,
	OPT_MY_FOLLOWING_TICKETS,
	OPT_NUM_FOLLOWERS,
	OPT_SUB_INFO_WATCHERS,
}
