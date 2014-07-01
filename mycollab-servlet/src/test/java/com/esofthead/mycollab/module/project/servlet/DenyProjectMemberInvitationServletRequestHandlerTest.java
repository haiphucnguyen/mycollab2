package com.esofthead.mycollab.module.project.servlet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.project.ProjectLinkGenerator;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.template.velocity.TemplateEngine;

@RunWith(MockitoJUnitRunner.class)
public class DenyProjectMemberInvitationServletRequestHandlerTest {

	@InjectMocks
	private DenyProjectMemberInvitationServletRequestHandler denyProjectMemberRequestHandler;

	@Mock
	private TemplateEngine templateEngine;

	@Mock
	private ProjectMemberService projectMemberService;

	@Mock
	private RelayEmailNotificationService relayEmailService;

	@Mock
	private ProjectService projectService;

	@Before
	public void setUp() {
		SiteConfiguration.loadInstance(8080);
	}

	@Test
	public void testCannotFindProject() throws ServletException, IOException {
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);

		String pathInfo = ProjectLinkGenerator.generateDenyInvitationParams(1,
				1, 1, "hainguyen@esofthead.com", "hainguyen@esofthead.com");
		when(request.getPathInfo()).thenReturn(pathInfo);

		denyProjectMemberRequestHandler.onHandleRequest(request, response);
	}
}
