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
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
@ViewComponent
public class CallReadViewImpl extends AbstractPageView implements CallReadView {

	private static final long serialVersionUID = 1L;

	private CallReadComp callPreview;

	public CallReadViewImpl() {
		super();
		callPreview = new CallReadComp();
		this.addComponent(callPreview);
	}

	@Override
	public HasPreviewFormHandlers<SimpleCall> getPreviewFormHandlers() {
		return callPreview.getPreviewForm();
	}

	@Override
	public SimpleCall getItem() {
		return callPreview.getBeanItem();
	}

	@Override
	public void previewItem(SimpleCall item) {
		callPreview.previewItem(item);

	}
}
