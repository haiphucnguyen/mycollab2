package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.maddon.layouts.MHorizontalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public abstract class StandupReportFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private StandupCustomField whatTodayField;

	private StandupCustomField whatYesterdayField;

	private StandupCustomField whatProblemField;

	private final String title;

	public StandupReportFormLayoutFactory(final String title) {
		this.title = title;

	}

	@Override
	public ComponentContainer getLayout() {
		AddViewLayout reportAddLayout = new AddViewLayout(this.title, ProjectAssetsManager.getAsset(ProjectTypeConstants.STANDUP));

		reportAddLayout.addHeaderRight(this.createTopPanel());

		MHorizontalLayout mainLayout = new MHorizontalLayout().withWidth("100%");

		final VerticalLayout layoutField = new VerticalLayout();
		layoutField.addStyleName("standup-edit-layout");
		layoutField.setMargin(new MarginInfo(false, false, true, true));
		layoutField.setWidth("100%");
		final Label whatYesterdayLbl = new Label(AppContext.getMessage(StandupI18nEnum.STANDUP_LASTDAY));
		whatYesterdayLbl.setStyleName("h2");
		layoutField.addComponent(whatYesterdayLbl);
		this.whatYesterdayField = new StandupCustomField();
		layoutField.addComponent(this.whatYesterdayField);

		final Label whatTodayLbl = new Label(AppContext.getMessage(StandupI18nEnum.STANDUP_TODAY));
		whatTodayLbl.setStyleName("h2");
		layoutField.addComponent(whatTodayLbl);
		this.whatTodayField = new StandupCustomField();
		layoutField.addComponent(this.whatTodayField);

		final Label roadblockLbl = new Label(AppContext.getMessage(StandupI18nEnum.STANDUP_ISSUE));
		roadblockLbl.addStyleName("h2");
		roadblockLbl.addStyleName(UIConstants.WORD_WRAP);
		layoutField.addComponent(roadblockLbl);
		this.whatProblemField = new StandupCustomField();
		layoutField.addComponent(this.whatProblemField);

		mainLayout.addComponent(layoutField);
		mainLayout.setExpandRatio(layoutField, 2.0f);

		final VerticalLayout instructionLayout = new VerticalLayout();
		instructionLayout.setStyleName("instruction-box");
		instructionLayout.setSpacing(true);

		final Label instruct1Lbl = new Label(AppContext.getMessage(StandupI18nEnum.HINT1_MSG));

		instruct1Lbl.addStyleName(UIConstants.WORD_WRAP);
		instructionLayout.addComponent(instruct1Lbl);

		final Label instruct1Lbl2 = new Label(AppContext.getMessage(StandupI18nEnum.HINT2_MG));
		instruct1Lbl2.addStyleName(UIConstants.WORD_WRAP);
		instructionLayout.addComponent(instruct1Lbl2);

		instructionLayout.setWidth("300px");
		instructionLayout.setHeight("300px");

		instruct1Lbl.setWidth("85%");
		instruct1Lbl2.setWidth("85%");

		mainLayout.addComponent(instructionLayout);
		mainLayout.setExpandRatio(instructionLayout, 1.0f);
		mainLayout.setComponentAlignment(instructionLayout, Alignment.TOP_CENTER);

		reportAddLayout.addBody(mainLayout);
		return reportAddLayout;
	}

	@Override
	public void attachField(final Object propertyId, final Field<?> field) {
		if (propertyId.equals("whatlastday")) {
			this.whatYesterdayField.setContentComp(field);
		} else if (propertyId.equals("whattoday")) {
			this.whatTodayField.setContentComp(field);
		} else if (propertyId.equals("whatproblem")) {
			this.whatProblemField.setContentComp(field);
		}
	}

	protected abstract Layout createTopPanel();

	private static class StandupCustomField extends CustomComponent {
		private static final long serialVersionUID = 1L;

		public void setContentComp(Component comp) {
			this.setCompositionRoot(comp);
		}
	}
}
