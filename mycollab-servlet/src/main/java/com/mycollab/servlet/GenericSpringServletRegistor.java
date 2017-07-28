/**
 * This file is part of mycollab-servlet.
 *
 * mycollab-servlet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-servlet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-servlet.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Configuration
@Profile("production")
public class GenericSpringServletRegistor {
    @Bean
    public ServletRegistrationBean errorServlet() {
        return new ServletRegistrationBean(new AppExceptionServletHandler(), "/error");
    }

    @Bean
    public ServletRegistrationBean tooltipServlet(){
        return new ServletRegistrationBean(new TooltipGeneratorServletRequestHandler(), "/tooltip/*");
    }

    @Bean
    public ServletRegistrationBean upgradeServlet() {
        return new ServletRegistrationBean(new UpgradeServlet(), "/upgrade");
    }

    @Bean
    public ServletRegistrationBean upgradeStatus() {
        return new ServletRegistrationBean(new UpgradeStatusServlet(), "/upgrade_status");
    }
}
