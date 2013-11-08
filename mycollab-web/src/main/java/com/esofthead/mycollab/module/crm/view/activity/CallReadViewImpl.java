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
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class CallReadViewImpl extends AbstractView implements CallReadView {

    private static final long serialVersionUID = 1L;
    
    private CallPreviewBuilder callPreview;

    public CallReadViewImpl() {
        super();
        callPreview = new CallPreviewBuilder.ReadView();
        this.addComponent(callPreview);
    }

    @Override
    public void previewItem(SimpleCall call) {
    	callPreview.call = call;
    	callPreview.previewItem(call);
    }

    @Override
    public HasPreviewFormHandlers<SimpleCall> getPreviewFormHandlers() {
        return callPreview.getPreviewForm();
    }

    @Override
    public SimpleCall getItem() {
        return callPreview.getCall();
    }
}
