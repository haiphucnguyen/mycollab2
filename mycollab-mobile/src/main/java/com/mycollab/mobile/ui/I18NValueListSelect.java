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
package com.mycollab.mobile.ui;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
// TODO: revisr thisd class
public class I18NValueListSelect extends ValueListSelect {
    private static final long serialVersionUID = 1L;

    public I18NValueListSelect() {
        super();
    }

    public I18NValueListSelect(boolean nullIsAllowable, Enum<?>... keys) {
//        setNullSelectionAllowed(nullIsAllowable);
//        loadData(Arrays.asList(keys));
    }

    public final void loadData(List<? extends Enum<?>> values) {
//        this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
//
//        for (Enum<?> entry : values) {
//            this.addItem(entry.name());
//            this.setItemCaption(entry.name(), UserUIContext.getMessage(entry));
//        }
//
//        if (!this.isNullSelectionAllowed() && getItemIds().size() > 0) {
//            this.select(this.getItemIds().iterator().next());
//        }
    }

    // TODO: check set binding field
//    @Override
//    public void setPropertyDataSource(Property newDataSource) {
//        Object value = newDataSource.getValue();
//        if (value == null) {
//            newDataSource.setValue(StatusI18nEnum.Open.name());
//        }
//        super.setPropertyDataSource(newDataSource);
//    }
}
