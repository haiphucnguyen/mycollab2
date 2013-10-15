package com.esofthead.mycollab.module.project.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class ProjectMemberServiceTest extends ServiceTest {
	@Autowired
	private ProjectMemberService projectMemberService;

	@DataSet
	@Test
	public void testAcceptProjectMemberAcceptInvitation() {
		projectMemberService.acceptProjectInvitationByNewUser(
				"baohan@esofthead.com", "123", 1, 1, 1);

		SimpleProjectMember member = projectMemberService.findMemberByUsername(
				"baohan@esofthead.com", 1, 1);
		Assert.assertEquals("baohan@esofthead.com", member.getUsername());
	}
}
