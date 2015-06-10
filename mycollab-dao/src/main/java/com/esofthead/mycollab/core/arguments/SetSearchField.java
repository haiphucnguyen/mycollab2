/**
 * This file is part of mycollab-dao.
 *
 * mycollab-dao is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-dao is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-dao.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.core.arguments;

import com.esofthead.mycollab.core.IgnoreException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @param <T>
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SetSearchField<T> extends SearchField {
    private static final long serialVersionUID = 1L;
    private Set<T> values = new HashSet<>();

    public SetSearchField() {
    }

    public SetSearchField(T... values) {
        this(SearchField.AND, values);
    }

    public SetSearchField(String oper, T... vals) {
        if (!ArrayUtils.isEmpty(vals)) {
            CollectionUtils.addAll(values, vals);
        }

        this.operation = oper;
    }

    public Set<T> getValues() {
        if (values == null || values.size() == 0) {
            throw new IgnoreException("You must select one option");
        }
        return values;
    }

    public void setValues(Set<T> values) {
        this.values = values;
    }

    public void addValue(T value) {
        values.add(value);
    }

    public void removeValue(T value) {
        values.remove(value);
    }
}
