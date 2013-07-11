package com.esofthead.mycollab.iexporter;

import java.util.Map;

public interface CSVObjectEntityConverter<E> extends
		ObjectEntityConverter<Map<String,String[]>, E> {

}
