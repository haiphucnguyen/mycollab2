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
/**
 * This file is part of mycollab-core.
 *
 * mycollab-core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-core.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.core.arguments;

import java.util.Arrays;
import java.util.Collection;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 * @param <T>
 */
public class SetSearchField<T> extends SearchField {
	private static final long serialVersionUID = 1L;
	public T[] values;

	public SetSearchField() {
		this(AND, (Collection) Arrays.asList());
	}

	@SuppressWarnings("unchecked")
	public SetSearchField(String oper, Collection<T> values) {
		this(oper, (T[]) values.toArray());
	}

	public SetSearchField(T... values) {
		this(SearchField.AND, values);
	}

	public SetSearchField(String oper, T... values) {
		this.values = values;
		this.operation = oper;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(T[] values) {
		this.values = values;
	}
}
