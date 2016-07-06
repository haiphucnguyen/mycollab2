package com.mycollab.rest.server.resource;

import com.mycollab.spring.test.service.RootConfigurationTest;
import com.mycollab.test.rule.DbUnitInitializerRule;
import com.mycollab.test.rule.EssentialInitRule;
import com.mycollab.test.rule.ServerLifecycleRule;
import com.jayway.restassured.RestAssured;
import org.junit.ClassRule;
import org.junit.Rule;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ContextConfiguration(classes = {RootConfigurationTest.class})
@ActiveProfiles(profiles = "test")
public class RestServiceTest {
    static {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/api";
    }

    @ClassRule
    public static EssentialInitRule essentialRule = new EssentialInitRule();

    @Rule
    public ServerLifecycleRule serverRule = new ServerLifecycleRule();

    @Rule
    public DbUnitInitializerRule dbRule = new DbUnitInitializerRule();
}
