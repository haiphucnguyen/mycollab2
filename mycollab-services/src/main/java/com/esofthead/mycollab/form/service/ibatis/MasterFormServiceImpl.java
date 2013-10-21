package com.esofthead.mycollab.form.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.form.dao.FormSectionMapperExt;
import com.esofthead.mycollab.form.domain.FormSectionField;
import com.esofthead.mycollab.form.domain.SimpleFormSection;
import com.esofthead.mycollab.form.service.MasterFormService;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;

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
			DynaForm form = new DynaForm();

			for (SimpleFormSection section : sections) {
				DynaSection dySection = new DynaSection();
				if (section.getLayouttype() == 0) {
					dySection.setLayoutType(LayoutType.ONE_COLUMN);
				} else {
					dySection.setLayoutType(LayoutType.TWO_COLUMN);
				}

				dySection.setHeader(section.getName());
				dySection.setOrderIndex(section.getLayoutindex());

				List<FormSectionField> fields = section.getFields();
				if (fields != null && fields.size() > 0) {
					for (FormSectionField field : fields) {
						String fieldtype = field.getFieldtype();
						
					}
				}
			}

			return form;
		}
	}

}
