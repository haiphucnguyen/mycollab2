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
/**
 * This file is part of mycollab-ui.
 *
 * mycollab-ui is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-ui is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-ui.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.servlet;

import com.codahale.metrics.servlets.HealthCheckServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@WebServlet(name = "Healthcheck Servlets", urlPatterns = "/healthcheck", asyncSupported = true, loadOnStartup = 0,
        initParams = {@WebInitParam(name = "show-jvm-metrics", value = "true")})
public class SimpleHealthcheckServlet extends HealthCheckServlet {
}
