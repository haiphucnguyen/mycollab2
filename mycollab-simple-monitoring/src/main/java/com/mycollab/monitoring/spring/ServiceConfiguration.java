/**
 * This file is part of mycollab-simple-monitoring.
 *
 * mycollab-simple-monitoring is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-simple-monitoring is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-simple-monitoring.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.monitoring.spring;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheckRegistry;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@Configuration
public class ServiceConfiguration {
    @Autowired
    private DataSource dataSource;

    @Bean
    public MetricRegistry metricRegistry() {
        MetricRegistry registry = new MetricRegistry();
        HikariDataSource hirakiDataSource = (HikariDataSource) dataSource;
        hirakiDataSource.setMetricRegistry(registry);
        return registry;
    }

    @Bean
    public HealthCheckRegistry healthCheckRegistry() {
        HealthCheckRegistry registry = new HealthCheckRegistry();
        HikariDataSource hirakiDataSource = (HikariDataSource) dataSource;
        hirakiDataSource.setHealthCheckRegistry(registry);
        return registry;
    }
}
