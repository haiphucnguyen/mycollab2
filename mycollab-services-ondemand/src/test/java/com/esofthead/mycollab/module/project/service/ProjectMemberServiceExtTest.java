package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectMemberServiceExtTest extends IntergrationServiceTest {

    @Autowired
    private ProjectMemberService projectMemberService;

    @Test
    @DataSet
    public void testGetActiveMembersInProjectEmpty() {
        List<SimpleUser> activeUsersInProject = projectMemberService.getActiveUsersInProject(2, 1);
        assertThat(activeUsersInProject.size()).isEqualTo(0);
    }
}
