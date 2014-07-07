package com.esofthead.mycollab.module.project.service.ibatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.UserExistedException;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.MyCollabClassRunner;
import com.esofthead.mycollab.test.service.ServiceTest;

@RunWith(MyCollabClassRunner.class)
public class ProjectMemberServiceImplTest extends ServiceTest {

	@Autowired
	private ProjectMemberService projectMemberService;

	@Test(expected = UserExistedException.class)
	@DataSet
	public void testAcceptProjectInvitationByNewUser() {
		projectMemberService.acceptProjectInvitationByNewUser(
				"hainguyen@esofthead.com", "123456", 1, 1, 1);
	}
}
