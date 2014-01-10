package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.ui.components.AbstractPreviewItemComp;
import com.esofthead.mycollab.module.crm.ui.components.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.module.crm.ui.components.NoteListItems;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.TabsheetLazyLoadComp;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
class MeetingReadComp extends AbstractPreviewItemComp<SimpleMeeting> {
	private static final long serialVersionUID = 1L;

	protected NoteListItems noteListItems;

	MeetingReadComp() {
		super(MyCollabResource.newResource("icons/22/crm/meeting.png"));
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleMeeting> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleMeeting>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				final MeetingHistoryLogWindow historyLog = new MeetingHistoryLogWindow(
						ModuleNameConstants.CRM, CrmTypeConstants.MEETING);
				historyLog.loadHistory(beanItem.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new CrmPreviewFormControlsGenerator<SimpleMeeting>(previewForm)
				.createButtonControls(RolePermissionCollections.CRM_MEETING);
	}

	@Override
	protected ComponentContainer createBottomPanel() {
		final TabsheetLazyLoadComp tabContainer = new TabsheetLazyLoadComp();
		tabContainer.setWidth("100%");

		tabContainer.addTab(noteListItems, "Notes",
				MyCollabResource.newResource("icons/16/crm/note.png"));
		return tabContainer;
	}

	@Override
	protected void onPreviewItem() {
		displayNotes();
	}

	@Override
	protected String initFormTitle() {
		return beanItem.getSubject();
	}

	@Override
	protected void initRelatedComponents() {
		noteListItems = new NoteListItems("Notes");
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new DynaFormLayout(CrmTypeConstants.MEETING,
				MeetingDefaultFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleMeeting> initBeanFormFieldFactory() {
		return new MeetingReadFormFieldFactory(previewForm);
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.MEETING, beanItem.getId());
	}
}
