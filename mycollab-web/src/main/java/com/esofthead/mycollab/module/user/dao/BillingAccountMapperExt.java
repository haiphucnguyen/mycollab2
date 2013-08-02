package com.esofthead.mycollab.module.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;

public interface BillingAccountMapperExt {
	SimpleBillingAccount getBillingAccountById(int accountId);

	List<String> getSubdomainsOfUser(@Param("username") String username);
}
