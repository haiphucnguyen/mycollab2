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
package com.mycollab.mobile.ui;

import com.mycollab.core.utils.StringUtils;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.vaadin.event.HasSearchHandlers;
import com.mycollab.vaadin.event.SearchHandler;
import com.vaadin.ui.TextField;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mycollab Ltd
 * @since 5.2.5
 */
// TODO
public abstract class SearchInputField<S extends SearchCriteria> extends TextField implements HasSearchHandlers<S> {
    private List<SearchHandler<S>> searchHandlers;

    public SearchInputField() {
        this.setStyleName("searchinputfield");
        this.setPlaceholder("Search");
//        this.setTextChangeEventMode(TextChangeEventMode.TIMEOUT);
//        this.setTextChangeTimeout(3000);
        this.addValueChangeListener(valueChangeEvent -> {
            String value = getValue();
            if (StringUtils.isNotBlank(value)) {
                final S searchCriteria = fillUpSearchCriteria(value);
                notifySearchHandler(searchCriteria);
            }
        });
//        ResetButtonForTextField resetBtn = ResetButtonForTextField.extend(this);
//        resetBtn.addResetButtonClickedListener(() -> {
//            final S searchCriteria = fillUpSearchCriteria(null);
//            notifySearchHandler(searchCriteria);
//        });
    }

    @Override
    public void addSearchHandler(SearchHandler<S> handler) {
        if (searchHandlers == null) {
            searchHandlers = new ArrayList<>();
        }
        searchHandlers.add(handler);
    }

    @Override
    public void notifySearchHandler(final S criteria) {
        if (searchHandlers != null) {
            for (SearchHandler<S> handler : searchHandlers) {
                handler.onSearch(criteria);
            }
        }
    }

    abstract protected S fillUpSearchCriteria(String value);

    @Override
    public void setTotalCountNumber(Integer totalCountNumber) {

    }
}
