/**
 * This file is part of mycollab-server-runner.
 *
 * mycollab-server-runner is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-server-runner is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-server-runner.  If not, see <http://www.gnu.org/licenses/>.
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
@Profile("setup")
@Configuration
public class SetupSpringServletRegistor {
    @Bean
    public ServletRegistrationBean assetServlet() {
        return new ServletRegistrationBean(new AssetHttpServletRequestHandler(), "/assets/*");
    }

    @Bean
    public ServletRegistrationBean databaseValidateServlet(){
        return new ServletRegistrationBean(new DatabaseValidateServlet(), "/validate");
    }

    @Bean
    public ServletRegistrationBean emailValidateServlet() {
        return new ServletRegistrationBean(new EmailValidationServlet(), "/emailValidate");
    }

    @Bean
    public ServletRegistrationBean installationSerlvet() {
        return new ServletRegistrationBean(new InstallationServlet(), "/install");
    }

    public ServletRegistrationBean setupServlet(){
        return new ServletRegistrationBean(new SetupServlet(), "");
    }
}
