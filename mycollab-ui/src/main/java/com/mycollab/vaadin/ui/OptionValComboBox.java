/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.ui;

import com.mycollab.common.domain.OptionVal;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ItemCaptionGenerator;
import com.vaadin.ui.StyleGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class OptionValComboBox extends ComboBox<OptionVal> {
    private List<OptionVal> options = new ArrayList<>();

    public OptionValComboBox(Class<? extends Enum> enumCls) {
        this.setPageLength(20);
        this.setStyleGenerator((StyleGenerator<OptionVal>) itemId -> {
            if (itemId != null) {
                return "" + itemId.hashCode();
            }
            return null;
        });
        this.setItemCaptionGenerator((ItemCaptionGenerator<OptionVal>) itemId -> {
            String value = itemId.getTypeval();
            try {
                Enum anEnum = Enum.valueOf(enumCls, value);
                return StringUtils.trim(UserUIContext.getMessage(anEnum), 25, true);
            } catch (Exception e) {
                return StringUtils.trim(value, 25, true);
            }
        });
    }
}
