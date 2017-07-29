package com.mycollab.common.service;

import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.mycollab.module.user.service.UserService;
import com.mycollab.test.DataSet;
import com.mycollab.test.service.IntegrationServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest extends IntegrationServiceTest {
    @Autowired
    private UserService userService;

    @DataSet
    @Test
    public void testGetListUser() {
        UserSearchCriteria criteria = new UserSearchCriteria();
        criteria.setSaccountid(new NumberSearchField(1));
        List<SimpleUser> users = userService.findPageableListByCriteria(new BasicSearchRequest<>(criteria));
        assertThat(users.size()).isEqualTo(4);
        assertThat(users).extracting("username").contains(
                "hainguyen@esofthead.com", "linhduong@esofthead.com",
                "huynguyen@esofthead.com", "test@esofthead.com");
    }

    @DataSet
    @Test
    public void updateUserEmail() {
        SimpleUser user = userService.findUserByUserNameInAccount("hainguyen@esofthead.com", 1);
        assertThat(user.getEmail()).isEqualTo("hainguyen@esofthead.com");

        user.setEmail("hannguyen@esofthead.com");
        userService.updateUserAccount(user, 1);

        SimpleUser anotherUser = userService.findUserByUserNameInAccount("hannguyen@esofthead.com", 1);
        assertThat(anotherUser.getEmail()).isEqualTo("hannguyen@esofthead.com");
        assertThat(anotherUser.getLastname()).isEqualTo("Hai");
    }

    @DataSet
    @Test
    public void testGetTotalActiveUsersInAccount() {
        int totalActiveUsersInAccount = userService.getTotalActiveUsersInAccount(1);
        assertThat(totalActiveUsersInAccount).isEqualTo(3);
    }

    @DataSet
    @Test
    public void testFindUserByUsernameInAccount() {
        SimpleUser user = userService.findUserByUserNameInAccount("hainguyen@esofthead.com", 1);
        assertThat(user.getUsername()).isEqualTo("hainguyen@esofthead.com");
        assertThat(user.getAccountId()).isEqualTo(1);
        assertThat(user.getFirstname()).isEqualTo("Nguyen");
        assertThat(user.getLastname()).isEqualTo("Hai");
    }
}
