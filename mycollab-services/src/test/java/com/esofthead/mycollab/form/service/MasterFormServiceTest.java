package com.esofthead.mycollab.form.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class MasterFormServiceTest extends ServiceTest {

	@Autowired
	private MasterFormService masterFormService;

	@DataSet
	@Test
	public void testGetForm() {
		DynaForm form = masterFormService.findCustomForm(1, "Account");
		Assert.assertNotNull(form);
	}
}
