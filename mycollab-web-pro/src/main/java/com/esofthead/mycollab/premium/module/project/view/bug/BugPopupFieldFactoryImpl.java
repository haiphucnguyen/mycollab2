package com.esofthead.mycollab.premium.module.project.view.bug;

import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.bug.BugPopupFieldFactory;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.form.field.PopupBeanFieldBuilder;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupView;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@ViewComponent
public class BugPopupFieldFactoryImpl implements BugPopupFieldFactory {
    @Override
    public PopupView createBugStatusPopupField(final SimpleBug bug) {
        final PopupView view =  new PopupView(FontAwesome.INFO_CIRCLE.getHtml() + " " + bug.getStatus(), new Label("Under " + "Development"));
        view.setStyleName("block-popupedit");
        view.setDescription("Click to edit");
        return view;
    }

    @Override
    public PopupView createBugMilestonePopupField(final SimpleBug bug) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return ProjectAssetsManager.getAsset(ProjectTypeConstants.MILESTONE).getHtml() + " " + (
                        (MilestoneComboBox) field).getItemCaption(bug.getMilestoneid());
            }
        };
        builder.withBean(bug).withBindProperty("milestoneid").withCaption(AppContext.getMessage(BugI18nEnum.FORM_PHASE))
                .withField(new MilestoneComboBox()).withService(ApplicationContextUtil.getSpringBean(BugService.class))
                .withValue(bug.getMilestoneid());
        return builder.build();
    }

    @Override
    public PopupView createBugDeadlinePopupField(final SimpleBug bug) {
        PopupBeanFieldBuilder builder = new PopupBeanFieldBuilder() {
            @Override
            protected String generateSmallContentAsHtml() {
                return String.format(" %s %s", FontAwesome.CLOCK_O.getHtml(), AppContext.formatPrettyTime(bug.getDueDateRoundPlusOne()));
            }
        };
        builder.withBean(bug).withBindProperty("duedate").withCaption(AppContext.getMessage(BugI18nEnum.FORM_DUE_DATE))
                .withField(new DateField()).withService(ApplicationContextUtil.getSpringBean(BugService.class)).withValue(bug.getDuedate());
        return builder.build();
    }
}
