/**
 * This file is part of mycollab-api.
 *
 * mycollab-api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-api.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class RemoteServiceProxy {
	public static <T> T build(String uri, Class<T> remoteService) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(uri);
		ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
		return rtarget.proxy(remoteService);
	}
}
