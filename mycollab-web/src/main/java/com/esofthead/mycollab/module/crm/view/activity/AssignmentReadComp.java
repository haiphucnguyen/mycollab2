package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.form.view.DynaFormLayout;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
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
class AssignmentReadComp extends AbstractPreviewItemComp<SimpleTask> {
	private static final long serialVersionUID = 1L;

	protected NoteListItems noteListItems;

	AssignmentReadComp() {
		super(MyCollabResource.newResource("icons/22/crm/task.png"));
	}

	@Override
	protected AdvancedPreviewBeanForm<SimpleTask> initPreviewForm() {
		return new AdvancedPreviewBeanForm<SimpleTask>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void showHistory() {
				final AssignmentHistoryLogWindow historyLog = new AssignmentHistoryLogWindow(
						ModuleNameConstants.CRM, CrmTypeConstants.TASK);
				historyLog.loadHistory(beanItem.getId());
				UI.getCurrent().addWindow(historyLog);
			}
		};
	}

	@Override
	protected ComponentContainer createButtonControls() {
		return new CrmPreviewFormControlsGenerator<SimpleTask>(previewForm)
				.createButtonControls(RolePermissionCollections.CRM_TASK);
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
		return new DynaFormLayout(CrmTypeConstants.TASK,
				AssignmentDefaultFormLayoutFactory.getForm());
	}

	@Override
	protected AbstractBeanFieldGroupViewFieldFactory<SimpleTask> initBeanFormFieldFactory() {
		return new AssignmentReadFormFieldFactory(previewForm);
	}

	protected void displayNotes() {
		noteListItems.showNotes(CrmTypeConstants.TASK, beanItem.getId());
	}

}
