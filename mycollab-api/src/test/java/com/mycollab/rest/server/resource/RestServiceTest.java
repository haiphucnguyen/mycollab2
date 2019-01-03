package com.mycollab.rest.server.resource;

import com.jayway.restassured.RestAssured;
import com.mycollab.test.spring.RootConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ContextConfiguration(classes = {RootConfiguration.class})
@ActiveProfiles(profiles = "test")
public class RestServiceTest {
    static {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/api";
    }
}
