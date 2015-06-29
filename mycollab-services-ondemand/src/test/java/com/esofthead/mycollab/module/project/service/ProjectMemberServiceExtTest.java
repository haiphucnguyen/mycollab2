package com.esofthead.mycollab.module.project.service;

import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.UserExistedException;
import com.esofthead.mycollab.module.user.dao.UserAccountMapper;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.UserAccount;
import com.esofthead.mycollab.module.user.domain.UserAccountExample;
import com.esofthead.mycollab.test.DataSet;
import com.esofthead.mycollab.test.service.IntergrationServiceTest;
import org.junit.Assert;
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

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Test(expected = UserExistedException.class)
    @DataSet
    public void testAcceptProjectInvitationByNewUserCauseErrorByHavingExistingUser() {
        projectMemberService.acceptProjectInvitationByNewUser(
                "hainguyen@esofthead.com", "123456", 1, 1, 1);
    }

    @Test
    @DataSet
    public void testAcceptProjectInvitation() {
        projectMemberService.acceptProjectInvitationByNewUser(
                "linhduong@esofthead.com", "abc123", 1, 1, 1);

        SimpleProjectMember member = projectMemberService.findMemberByUsername(
                "linhduong@esofthead.com", 1, 1);

        UserAccountExample accountEx = new UserAccountExample();
        accountEx.createCriteria()
                .andUsernameEqualTo("linhduong@esofthead.com");
        List<UserAccount> users = userAccountMapper.selectByExample(accountEx);
        Assert.assertEquals(1, users.size());

        UserAccount userAccount = users.get(0);
        Assert.assertEquals("linhduong@esofthead.com",
                userAccount.getUsername());
        Assert.assertEquals(new Integer(1), userAccount.getAccountid());
        Assert.assertEquals(new Integer(1), userAccount.getRoleid());

        Assert.assertEquals("linhduong@esofthead.com", member.getEmail());
        Assert.assertEquals("linhduong@esofthead.com", member.getUsername());
    }

    @Test
    @DataSet
    public void testGetActiveMembersInProjectEmpty() {
        List<SimpleUser> activeUsersInProject = projectMemberService.getActiveUsersInProject(2, 1);
        assertThat(activeUsersInProject.size()).isEqualTo(0);
    }
}
