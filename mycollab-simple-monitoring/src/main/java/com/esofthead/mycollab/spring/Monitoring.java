package com.esofthead.mycollab.spring;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@Configuration
public class Monitoring {
    @Bean
    public MetricRegistry metricRegistry() {
        MetricRegistry registry = new MetricRegistry();
        return registry;
    }

    @Bean
    public HealthCheckRegistry healthCheckRegistry() {
        HealthCheckRegistry registry = new HealthCheckRegistry();
        return registry;
    }
}
