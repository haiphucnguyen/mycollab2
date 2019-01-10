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
package com.mycollab.vaadin.ui;

import com.mycollab.common.domain.OptionVal;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.vaadin.UserUIContext;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ItemCaptionGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public abstract class AbstractOptionValComboBox<E extends Enum<E>> extends ComboBox<OptionVal> {
    private Class<E> enumCls;
    private List<OptionVal> options = new ArrayList<>();

    public AbstractOptionValComboBox(Class<E> enumCls) {
        this.enumCls = enumCls;
        this.setPageLength(20);
        this.setEmptySelectionAllowed(false);
        options = loadOptions();
        this.setItems(options);
        this.setStyleGenerator(itemId -> {
            if (itemId != null) {
                return "" + itemId.hashCode();
            }
            return null;
        });
        this.setItemCaptionGenerator((ItemCaptionGenerator<OptionVal>) option-> {
            String value = option.getTypeval();
            try {
                Enum anEnum = Enum.valueOf(enumCls, value);
                return StringUtils.trim(UserUIContext.getMessage(anEnum), 25, true);
            } catch (Exception e) {
                return StringUtils.trim(value, 25, true);
            }
        });
    }

    abstract protected List<OptionVal> loadOptions();
}
