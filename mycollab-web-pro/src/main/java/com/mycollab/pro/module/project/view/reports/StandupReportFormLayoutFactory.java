package com.mycollab.pro.module.project.view.reports;

import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.StandupI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.web.ui.AddViewLayout;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class StandupReportFormLayoutFactory extends AbstractFormLayoutFactory {
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
        AddViewLayout reportAddLayout = new AddViewLayout(title, ProjectAssetsManager.getAsset(ProjectTypeConstants.STANDUP));
        reportAddLayout.addHeaderRight(this.createTopPanel());

        MHorizontalLayout mainLayout = new MHorizontalLayout().withFullWidth();
        final MVerticalLayout layoutField = new MVerticalLayout().withMargin(new MarginInfo(false, false, true,
                false)).withFullWidth();

        final ELabel whatYesterdayLbl = ELabel.h3(AppContext.getMessage(StandupI18nEnum.STANDUP_LASTDAY));
        layoutField.addComponent(whatYesterdayLbl);
        whatYesterdayField = new StandupCustomField();
        layoutField.addComponent(whatYesterdayField);

        final ELabel whatTodayLbl = ELabel.h3(AppContext.getMessage(StandupI18nEnum.STANDUP_TODAY));
        layoutField.with(new Label(""), whatTodayLbl);
        whatTodayField = new StandupCustomField();
        layoutField.addComponent(whatTodayField);

        final ELabel roadblockLbl = ELabel.h3(AppContext.getMessage(StandupI18nEnum.STANDUP_ISSUE));
        roadblockLbl.addStyleName(UIConstants.LABEL_WORD_WRAP);
        layoutField.with(new Label(""), roadblockLbl);
        whatProblemField = new StandupCustomField();
        layoutField.addComponent(whatProblemField);

        mainLayout.addComponent(layoutField);
        mainLayout.setExpandRatio(layoutField, 2.0f);

        final VerticalLayout instructionLayout = new VerticalLayout();
        instructionLayout.setStyleName("instruction-box");
        instructionLayout.setSpacing(true);

        final Label instruct1Lbl = new Label(AppContext.getMessage(StandupI18nEnum.HINT1_MSG));
        instructionLayout.addComponent(instruct1Lbl);

        final Label instruct1Lbl2 = new Label(AppContext.getMessage(StandupI18nEnum.HINT2_MG));
        instructionLayout.addComponent(instruct1Lbl2);

        instructionLayout.setWidth("300px");

        mainLayout.addComponent(instructionLayout);
        mainLayout.setExpandRatio(instructionLayout, 1.0f);
        mainLayout.setComponentAlignment(instructionLayout, Alignment.TOP_CENTER);

        reportAddLayout.addBody(mainLayout);
        return reportAddLayout;
    }

    @Override
    protected Component onAttachField(Object propertyId, final Field<?> field) {
        if (propertyId.equals("whatlastday")) {
            whatYesterdayField.setContentComp(field);
        } else if (propertyId.equals("whattoday")) {
            whatTodayField.setContentComp(field);
        } else if (propertyId.equals("whatproblem")) {
            whatProblemField.setContentComp(field);
        }
        return field;
    }

    protected abstract ComponentContainer createTopPanel();

    private static class StandupCustomField extends CustomComponent {
        private static final long serialVersionUID = 1L;

        public void setContentComp(Component comp) {
            setCompositionRoot(comp);
        }
    }
}