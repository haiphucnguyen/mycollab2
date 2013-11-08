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

import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;

@ViewComponent
public class MeetingReadViewImpl extends AbstractView implements
		MeetingReadView {

	private static final long serialVersionUID = 1L;
	private MeetingPreviewBuilder previewForm;

	public MeetingReadViewImpl() {
		super();
		previewForm = new MeetingPreviewBuilder.ReadView();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleMeeting meeting) {
		previewForm.previewItem(meeting);
	}

	@Override
	public HasPreviewFormHandlers<Meeting> getPreviewFormHandlers() {
		return previewForm.getPreviewForm();
	}

	@Override
	public SimpleMeeting getItem() {
		return previewForm.getMeeting();
	}
}
