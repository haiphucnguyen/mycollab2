/**
 * mycollab-server-runner - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * mycollab-server-runner - Parent pom providing dependency and plugin management for applications
		built with Maven
 * Copyright © 2017 MyCollab (support@mycollab.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
@Configuration
@Profile("production")
public class ContextStartedListener implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger LOG = LoggerFactory.getLogger(ContextStartedListener.class);

    private boolean isStarted = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOG.info("MyCollab is ready for usage");
        isStarted = true;
    }

    @Bean
    public ServletRegistrationBean checkStartedServlet() {
        return new ServletRegistrationBean(new CheckAppStartedServlet(), "/checkStarted");
    }

    class CheckAppStartedServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            if (isStarted) {
                response.getWriter().write("Started");
            } else {
                response.getWriter().write("NotStarted");
            }
        }
    }
}
