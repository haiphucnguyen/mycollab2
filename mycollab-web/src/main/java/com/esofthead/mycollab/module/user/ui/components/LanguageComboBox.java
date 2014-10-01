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
package com.esofthead.mycollab.module.user.ui.components;

import java.util.List;
import java.util.Locale;

import com.esofthead.mycollab.common.i18n.LangI18Enum;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.ComboBox;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1
 * 
 */
public class LanguageComboBox extends ComboBox {
	private static final long serialVersionUID = 1L;

	public LanguageComboBox() {
		super();
		this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);

		List<Locale> supportedLanguage = SiteConfiguration
				.getSupportedLanguages();
		for (Locale locale : supportedLanguage) {
			String language = locale.toString();
			this.addItem(language);
			this.setItemCaption(language, AppContext.getMessage(Enum.valueOf(
					LangI18Enum.class, language)));
		}
	}
}
