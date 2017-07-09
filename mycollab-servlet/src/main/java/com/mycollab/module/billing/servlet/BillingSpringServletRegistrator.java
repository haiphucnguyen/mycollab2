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
package com.mycollab.module.billing.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Configuration
public class BillingSpringServletRegistrator {
    @Bean
    public ServletRegistrationBean confirmEmailServlet() {
        return new ServletRegistrationBean(new ConfirmEmailHandler(), "/user/confirm_signup/*");
    }


    @Bean
    public ServletRegistrationBean resetPasswordServlet() {
        return new ServletRegistrationBean(new ResetPasswordHandler(), "/user/recoverypassword/action/*");
    }

    @Bean
    public ServletRegistrationBean resetPasswordPage() {
        return new ServletRegistrationBean(new ResetPasswordHandler(), "/user/recoverypassword/*");
    }
}
