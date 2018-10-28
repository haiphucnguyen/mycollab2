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
package com.mycollab.module.project.view.bug;

import com.mycollab.module.project.i18n.OptionI18nEnum;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;

import java.util.Arrays;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
// TODO
public class BugSeverityComboBox extends I18nValueComboBox {
    private static final long serialVersionUID = 1L;

    public BugSeverityComboBox() {
        this.setEmptySelectionAllowed(false);
        this.loadData(Arrays.asList(OptionI18nEnum.bug_severities));
//
//        this.setItemIcon(BugSeverity.Critical.name(), FontAwesome.STAR);
//        this.setItemIcon(BugSeverity.Major.name(), FontAwesome.STAR);
//        this.setItemIcon(BugSeverity.Minor.name(), FontAwesome.STAR);
//        this.setItemIcon(BugSeverity.Trivial.name(), FontAwesome.STAR);
//
//        this.setItemStyleGenerator((source, itemId) -> {
//            if (itemId != null) {
//                return "bug-severity-" + itemId.toString().toLowerCase();
//            } else {
//                return null;
//            }
//        });
    }

//    @Override
//    public void setPropertyDataSource(Property newDataSource) {
//        Object value = newDataSource.getValue();
//        if (value == null) {
//            newDataSource.setValue(OptionI18nEnum.BugSeverity.Major.name());
//        }
//        super.setPropertyDataSource(newDataSource);
//    }
}
