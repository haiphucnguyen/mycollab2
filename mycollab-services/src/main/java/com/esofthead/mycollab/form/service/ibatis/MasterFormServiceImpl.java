package com.esofthead.mycollab.form.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.form.dao.FormSectionMapperExt;
import com.esofthead.mycollab.form.domain.SimpleFormSection;
import com.esofthead.mycollab.form.service.MasterFormService;
import com.esofthead.mycollab.form.view.builder.DynaFormBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;

@Service
public class MasterFormServiceImpl implements MasterFormService {

	@Autowired
	private FormSectionMapperExt formSectionMapperExt;

	@Override
	public DynaForm findCustomForm(@CacheKey Integer sAccountId,
			String moduleName) {
		List<SimpleFormSection> sections = formSectionMapperExt.findSections(
				sAccountId, moduleName);

		if (sections == null && sections.size() == 0) {
			return null;
		} else {
			DynaFormBuilder builder = new DynaFormBuilder();
			return builder.build();
		}
	}

}
