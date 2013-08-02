package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.module.crm.domain.Account;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.view.CrmUrlResolver;
import com.esofthead.mycollab.vaadin.events.EventBus;

public class CaseUrlResolver extends CrmUrlResolver {
	public CaseUrlResolver() {
		this.addSubResolver("list", new CaseListUrlResolver());
		this.addSubResolver("add", new CaseAddUrlResolver());
		this.addSubResolver("edit", new CaseEditUrlResolver());
		this.addSubResolver("preview", new CasePreviewUrlResolver());
	}

	public static class CaseListUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance()
					.fireEvent(new CaseEvent.GotoList(this, null));
		}
	}

	public static class CaseAddUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			EventBus.getInstance().fireEvent(
					new CaseEvent.GotoAdd(this, new Account()));
		}
	}

	public static class CaseEditUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int caseId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new CaseEvent.GotoEdit(this, caseId));
		}
	}

	public static class CasePreviewUrlResolver extends CrmUrlResolver {
		@Override
		protected void handlePage(String... params) {
			String decodeUrl = UrlEncodeDecoder.decode(params[0]);
			int caseId = Integer.parseInt(decodeUrl);
			EventBus.getInstance().fireEvent(
					new CaseEvent.GotoRead(this, caseId));
		}
	}
}
