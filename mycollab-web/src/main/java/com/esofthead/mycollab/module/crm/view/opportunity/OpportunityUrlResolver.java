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
package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class OpportunityUrlResolver extends CrmUrlResolver {
	public OpportunityUrlResolver() {
		this.addSubResolver("list", new OpportunityListUrlResolver());
		this.addSubResolver("add", new OpportunityAddUrlResolver());
		this.addSubResolver("edit", new OpportunityEditUrlResolver());
		this.addSubResolver("preview", new OpportunityPreviewUrlResolver());
	}

	public static class OpportunityListUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new OpportunityEvent.GotoList(this, null));
		}
	}

	public static class OpportunityAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new OpportunityEvent.GotoAdd(this, new Account()));
		}
	}

	public static class OpportunityEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int opportunintyId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new OpportunityEvent.GotoEdit(this, opportunintyId));
		}
	}

	public static class OpportunityPreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int opportunintyId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new OpportunityEvent.GotoRead(this, opportunintyId));
		}
	}
}
