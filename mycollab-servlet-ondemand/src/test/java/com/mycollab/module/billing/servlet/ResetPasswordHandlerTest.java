package com.mycollab.module.billing.servlet;

import com.mycollab.core.UserInvalidInputException;
import com.mycollab.module.servlet.GenericServletTest;
import com.mycollab.module.user.domain.User;
import com.mycollab.module.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ResetPasswordHandlerTest extends GenericServletTest {
    @InjectMocks
    @Spy
    private ResetPasswordHandler resetPasswordHandler;

    @Mock
    private UserService userService;

    @Test
    public void testInvalidPassword() {
        when(request.getParameter("username")).thenReturn("hainguyen@esofthead.com");
        when(request.getParameter("password")).thenReturn("123456");
        Assertions.assertThrows(UserInvalidInputException.class, () -> resetPasswordHandler.onHandleRequest(request, response));
    }

    @Test
    public void testResetPasswordOfInactiveUser() {
        when(request.getParameter("username")).thenReturn("hainguyen@esofthead.com");
        when(request.getParameter("password")).thenReturn("abc123");
        when(userService.findUserByUserName(anyString())).thenReturn(null);

        Assertions.assertThrows(UserInvalidInputException.class, () -> resetPasswordHandler.onHandleRequest(request, response));
    }

    @Test
    public void testResetEmailSuccessful() throws ServletException, IOException {
        when(request.getParameter("username")).thenReturn("hainguyen@esofthead.com");
        when(request.getParameter("password")).thenReturn("abc123");
        when(userService.findUserByUserName(anyString())).thenReturn(new User());
        resetPasswordHandler.onHandleRequest(request, response);

        ArgumentCaptor<String> strArgument = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<User> userArgument = ArgumentCaptor.forClass(User.class);
        verify(userService, times(1)).updateWithSession(userArgument.capture(), strArgument.capture());
    }
}
