package com.mycollab.test.spring;

import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.test.rule.DbUnitInitializerRule;
import com.mycollab.test.rule.EssentialInitRule;
import org.junit.ClassRule;
import org.junit.Rule;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ContextConfiguration(classes = RootConfigurationTest.class)
@ActiveProfiles(profiles = "test")
public class IntegrationServiceTest {

    @MockBean
    private ServerConfiguration serverConfiguration;

    @ClassRule
    public static final EssentialInitRule essentialRule = new EssentialInitRule();

    @Rule
    public DbUnitInitializerRule dbRule = new DbUnitInitializerRule();
}
