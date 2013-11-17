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
package com.esofthead.mycollab.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.esofthead.mycollab.core.MyCollabException;

/**
 * Utility class for processing class meta data.
 * 
 * @author haiphucnguyen
 * 
 */
public class ClassUtils {
	public static boolean instanceOf(Object o, Class<?>... classes) {
		for (Class<?> cls : classes) {
			if (cls.isInstance(o)) {
				return true;
			}
		}
		return false;
	}

	public static Class<?> getInterfaceInstanceOf(Class cls, Class superCls) {
		Class[] interfaces = cls.getInterfaces();
		for (Class inter : interfaces) {
			if (superCls.isAssignableFrom(inter)) {
				return inter;
			}
		}

		return null;
	}

	public static List<Field> getInheritedFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			fields.addAll(Arrays.asList(c.getDeclaredFields()));
		}
		return fields;
	}

	public static Field[] getAllFields(Class<?> type) {
		List<Field> fields = new ArrayList<Field>();
		populateFields(type, fields);
		return fields.toArray(new Field[0]);
	}

	private static void populateFields(Class<?> type, List<Field> fields) {
		if (type != null && type != null) {
			Field[] declaredFields = type.getDeclaredFields();
			for (Field declaredField : declaredFields) {
				if (!Modifier.isStatic(declaredField.getModifiers())) {
					fields.add(declaredField);
				}
			}

			populateFields(type.getSuperclass(), fields);
		}
	}

	public static Field getField(Class<?> type, String fieldname) {
		for (Class<?> c = type; c != null; c = c.getSuperclass()) {
			try {
				return c.getDeclaredField(fieldname);
			} catch (Exception e) {
				// do nothing
			}
		}

		throw new MyCollabException("Can not find field " + fieldname
				+ " of class " + type.getName());
	}
}
