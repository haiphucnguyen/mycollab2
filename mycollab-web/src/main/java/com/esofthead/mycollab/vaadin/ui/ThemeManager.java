/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.service.AccountThemeService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.vaadin.server.Page;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class ThemeManager {

	public static void loadUserTheme(int saccountid) {
		AccountThemeService themeService = ApplicationContextUtil
				.getSpringBean(AccountThemeService.class);

		AccountTheme accountTheme = themeService.getAccountTheme(saccountid);

		if (accountTheme == null)
			return;

		/* Top Menu */

		if (accountTheme.getTopmenubg() != null) {
			Page.getCurrent()
					.getStyles()
					.add("#topNavigation { background-color: #"
							+ accountTheme.getTopmenubg() + "; }");
		}

		if (accountTheme.getTopmenubgselected() != null) {
			Page.getCurrent()
					.getStyles()
					.add("#topNavigation .service-menu.v-buttongroup .v-button.selected { background-color: #"
							+ accountTheme.getTopmenubgselected() + "; }");
		}

		if (accountTheme.getTopmenutext() != null) {
			Page.getCurrent()
					.getStyles()
					.add("#topNavigation .v-button-caption { color: #"
							+ accountTheme.getTopmenutext() + "; }");
		}

		if (accountTheme.getTopmenutextselected() != null) {
			Page.getCurrent()
					.getStyles()
					.add("#topNavigation .service-menu.v-buttongroup .v-button.selected .v-button-caption { color: #"
							+ accountTheme.getTopmenutextselected() + "; }");
		}

		/* Vertical Tabsheet */

		if (accountTheme.getVtabsheetbg() != null) {
			Page.getCurrent()
					.getStyles()
					.add(".verticaltabsheet-fix { background-color: #"
							+ accountTheme.getVtabsheetbg() + "; }");
		}

		if (accountTheme.getVtabsheetbgselected() != null) {
			Page.getCurrent()
					.getStyles()
					.add(".vertical-tabsheet .v-button-tab.tab-selected > .v-button-wrap { background-color: #"
							+ accountTheme.getVtabsheetbgselected() + "; }");
		}

		if (accountTheme.getVtabsheettext() != null) {
			Page.getCurrent()
					.getStyles()
					.add(".vertical-tabsheet .v-button-tab > .v-button-wrap > .v-button-caption { color: #"
							+ accountTheme.getVtabsheettext() + "; }");
		}

		if (accountTheme.getVtabsheettextselected() != null) {
			Page.getCurrent()
					.getStyles()
					.add(".vertical-tabsheet .v-button-tab.tab-selected > .v-button-wrap > .v-button-caption { color: #"
							+ accountTheme.getVtabsheettextselected() + "; }");
		}

		/* Tabsheet */

		// if (userTheme.getTopmenubg() != null) {
		// Page.getCurrent()
		// .getStyles()
		// .add("#topNavigation { background-color: #"
		// + userTheme.getTopmenubg() + "; }");
		// }
		//
		// if (userTheme.getTopmenubg() != null) {
		// Page.getCurrent()
		// .getStyles()
		// .add("#topNavigation { background-color: #"
		// + userTheme.getTopmenubg() + "; }");
		// }
	}
}
