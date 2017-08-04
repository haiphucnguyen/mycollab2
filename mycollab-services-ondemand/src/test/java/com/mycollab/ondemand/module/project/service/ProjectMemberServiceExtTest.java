package com.mycollab.ondemand.module.project.service;

import com.mycollab.module.project.service.ProjectMemberService;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.test.DataSet;
import com.mycollab.test.spring.IntegrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectMemberServiceExtTest extends IntegrationServiceTest {

    @Autowired
    private ProjectMemberService projectMemberService;

    @Test
    @DataSet
    public void testGetActiveMembersInProjectEmpty() {
        List<SimpleUser> activeUsersInProject = projectMemberService.getActiveUsersInProject(2, 1);
        assertThat(activeUsersInProject.size()).isEqualTo(0);
    }
}
