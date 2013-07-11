package com.esofthead.mycollab.iexporter;

public interface ObjectEntityConverter<U, E> {
	E convert(Class<E> cls, U unit);
}
