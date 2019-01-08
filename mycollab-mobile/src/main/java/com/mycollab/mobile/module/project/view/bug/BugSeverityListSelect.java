/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.mobile.module.project.view.bug;

import com.mycollab.mobile.ui.I18NValueListSelect;
import com.mycollab.module.project.i18n.OptionI18nEnum;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
// TODO
public class BugSeverityListSelect extends I18NValueListSelect {
    private static final long serialVersionUID = 1L;

    public BugSeverityListSelect() {
//        this.setNullSelectionAllowed(false);
        this.setCaption(null);
        this.loadData(Arrays.asList(OptionI18nEnum.bug_severities));

//        this.setItemIcon(BugSeverity.Critical.name(), FontAwesome.STAR);
//        this.setItemIcon(BugSeverity.Major.name(), FontAwesome.STAR);
//        this.setItemIcon(BugSeverity.Minor.name(), FontAwesome.STAR);
//        this.setItemIcon(BugSeverity.Trivial.name(), FontAwesome.STAR);
    }
}
