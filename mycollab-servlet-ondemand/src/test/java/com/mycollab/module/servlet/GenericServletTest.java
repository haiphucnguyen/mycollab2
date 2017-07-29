package com.mycollab.module.servlet;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

import static org.mockito.Mockito.when;

/**
 * @author MyCollab Ltd
 * @since 1.0.0
 */
public class GenericServletTest {

    @Mock
    protected HttpServletRequest request;

    @Mock
    protected HttpServletResponse response;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(response.getLocale()).thenReturn(Locale.US);
    }
}
