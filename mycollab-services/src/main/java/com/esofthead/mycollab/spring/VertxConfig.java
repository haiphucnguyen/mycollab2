package com.esofthead.mycollab.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.VertxFactory;

/**
 * @author MyCollab Ltd.
 * @since 5.0.6
 */
@Configuration
@Profile("production")
public class VertxConfig {
    @Bean
    public Vertx vertX() {
        Vertx vertX = VertxFactory.newVertx();
        return vertX;
    }
}
