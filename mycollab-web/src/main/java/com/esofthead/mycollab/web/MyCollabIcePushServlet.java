/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.icepush.servlet.MainServlet;
import org.vaadin.artur.icepush.ICEPush;
import org.vaadin.artur.icepush.JavascriptProvider;

import com.vaadin.terminal.gwt.server.ApplicationServlet;

/**
 * Modified version of the {@link org.vaadin.artur.icepush.ICEPushServlet}.
 */
public class MyCollabIcePushServlet extends ApplicationServlet {

	private MainServlet ICEPushServlet;

	private JavascriptProvider javascriptProvider;

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		try {
			super.init(servletConfig);
		} catch (ServletException e) {
			if (e.getMessage().equals(
					"Application not specified in servlet parameters")) {
				// Ignore if application is not specified to allow the same
				// servlet to be used for only push in portals
			} else {
				throw e;
			}
		}

		this.ICEPushServlet = new MainServlet(servletConfig.getServletContext());

		try {
			String codeBasePath = servletConfig.getServletContext()
					.getContextPath();
			String codeLocation = servletConfig
					.getInitParameter("codeBasePath");
			if (codeLocation != null)
				codeBasePath += codeLocation;
			if (codeBasePath.endsWith("/"))
				codeBasePath = codeBasePath.substring(0,
						codeBasePath.length() - 2);
			this.javascriptProvider = new JavascriptProvider(codeBasePath);
			ICEPush.setCodeJavascriptLocation(this.javascriptProvider
					.getCodeLocation());
		} catch (IOException e) {
			throw new ServletException("Error initializing JavascriptProvider",
					e);
		}
	}

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null
				&& pathInfo.equals("/" + this.javascriptProvider.getCodeName())) {
			// Serve icepush.js
			serveIcePushCode(response);
			return;
		}

		if (request.getRequestURI().endsWith(".icepush")) {
			// Push request
			try {
				this.ICEPushServlet.service(request, response);
			} catch (ServletException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			// Vaadin request
			super.service(request, response);
		}
	}

	private void serveIcePushCode(HttpServletResponse response)
			throws IOException {
		String icepushJavscript = this.javascriptProvider.getJavaScript();
		response.setHeader("Content-Type", "text/javascript");
		response.getOutputStream().write(icepushJavscript.getBytes());
	}

	@Override
	public void destroy() {
		super.destroy();
		this.ICEPushServlet.shutdown();
	}
}
