package com.esofthead.mycollab.pro.module.project.view.reports;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.StandupReportStatistic;
import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsUtil;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.IEditFormHandler;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.web.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd
 * @since 5.3.0
 */
class StandupAddWindow extends Window implements IEditFormHandler<StandupReportWithBLOBs> {
    private StandupReportService standupReportService;
    private AdvancedEditBeanForm<StandupReportWithBLOBs> editForm;
    private Date onDate;

    StandupAddWindow(StandupReportStatistic standupReportStatistic, Date onDate) {
        this.setModal(true);
        this.setClosable(true);
        this.setResizable(false);
        this.center();
        this.setWidth(UIUtils.getBrowserWidth() + "px");
        this.setHeight(UIUtils.getBrowserHeight() + "px");

        this.onDate = onDate;
        standupReportService = ApplicationContextUtil.getSpringBean(StandupReportService.class);
        SimpleStandupReport report = standupReportService.findStandupReportByDateUser(standupReportStatistic.getProjectId(),
                AppContext.getUsername(), onDate, AppContext.getAccountId());
        if (report == null) {
            report = new SimpleStandupReport();
            report.setProjectid(standupReportStatistic.getProjectId());
            report.setSaccountid(AppContext.getAccountId());
            report.setForday(onDate);
            report.setLogby(AppContext.getUsername());
        }

        editForm = new AdvancedEditBeanForm<>();
        editForm.setFormLayoutFactory(new FormLayoutFactory());
        editForm.setBeanFormFieldFactory(new EditFormFieldFactory(editForm));
        editForm.setBean(report);
        Component projectLogo = ProjectAssetsUtil.buildProjectLogo(standupReportStatistic.getProjectKey(), standupReportStatistic.getProjectId(),
                standupReportStatistic.getProjectAvatarId(), 32);
        ELabel projectLbl = ELabel.h2(standupReportStatistic.getProjectName()).withStyleName(UIConstants
                .TEXT_ELLIPSIS);
        setContent(new MVerticalLayout(new MHorizontalLayout(projectLogo, projectLbl).expand(projectLbl), editForm));
        editForm.addFormHandler(this);
    }

    @Override
    public void onSave(StandupReportWithBLOBs standupReport) {
        if (standupReport.getId() == null) {
            standupReportService.saveWithSession(standupReport, AppContext.getUsername());
        } else {
            standupReportService.updateWithSession(standupReport, AppContext.getUsername());
        }
        EventBusFactory.getInstance().post(new StandUpEvent.DisplayStandupInProject(this, standupReport.getProjectid()));
        close();
    }

    @Override
    public void onSaveAndNew(StandupReportWithBLOBs bean) {
        throw new MyCollabException("Not support operation");
    }

    @Override
    public void onCancel() {
        close();
    }

    private static class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<StandupReportWithBLOBs> {
        private static final long serialVersionUID = 1L;

        EditFormFieldFactory(GenericBeanForm<StandupReportWithBLOBs> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (propertyId.equals("whatlastday") || propertyId.equals("whattoday") || propertyId.equals("whatproblem")) {
                final RichTextArea richText = new RichTextArea();
                richText.setWidth("100%");
                return richText;
            }
            return null;
        }
    }

    class FormLayoutFactory extends StandupReportFormLayoutFactory {
        private static final long serialVersionUID = 1L;

        public FormLayoutFactory() {
            super(AppContext.getMessage(StandupI18nEnum.FORM_EDIT_TITLE, AppContext.formatDate(onDate)));
        }

        private ComponentContainer createButtonControls() {
            return (new EditFormControlsGenerator<>(editForm)).createButtonControls(true, false, true);
        }

        @Override
        protected ComponentContainer createTopPanel() {
            return this.createButtonControls();
        }
    }
}
