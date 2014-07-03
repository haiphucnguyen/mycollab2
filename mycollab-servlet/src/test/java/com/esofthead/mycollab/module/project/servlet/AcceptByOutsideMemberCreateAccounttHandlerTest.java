package com.esofthead.mycollab.module.project.servlet;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.servlet.GenericServletTest;

public class AcceptByOutsideMemberCreateAccounttHandlerTest extends
		GenericServletTest {

	@InjectMocks
	@Spy
	private AcceptByOutsideMemberCreateAccounttHandler acceptByOutsideMemberHandler;

	@Mock
	private ProjectMemberService projectMemberService;

	@Test
	public void testInvalidPassword() {

	}
}
