package com.esofthead.mycollab.form.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.form.domain.SimpleFormSection;

public interface FormSectionMapperExt {
	List<SimpleFormSection> findSections(
			@Param("accountId") Integer sAccountId,
			@Param("modueName") String moduleName);
}
