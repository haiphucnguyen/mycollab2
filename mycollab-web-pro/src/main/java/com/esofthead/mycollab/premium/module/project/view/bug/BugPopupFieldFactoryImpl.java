package com.esofthead.mycollab.premium.module.project.view.bug;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.BugI18nEnum;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.ui.components.CommentDisplay;
import com.esofthead.mycollab.module.project.view.bug.BugPopupFieldFactory;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.schedule.email.project.BugRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.LazyPopupView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.form.field.PopupBeanFieldBuilder;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.PopupView;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
@ViewComponent
public class BugPopupFieldFactoryImpl implements BugPopupFieldFactory {

    @Override
    public PopupView createBugCommentsPopupField(SimpleBug bug) {
        BugCommentsPopupView view = new BugCommentsPopupView(bug);
        view.setDescription("Click to edit");
        return view;
    }

    private static class BugCommentsPopupView extends LazyPopupView {
        private SimpleBug bug;

        public BugCommentsPopupView(SimpleBug bug) {
            super("");
            this.bug = bug;
            if (bug.getNumComments() == null) {
                this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " 0");
            } else {
                this.setMinimizedValueAsHTML(FontAwesome.COMMENT_O.getHtml() + " " + bug.getNumComments());
            }
        }

        @Override
        protected void doShow() {
            CommentDisplay commentDisplay = new CommentDisplay(ProjectTypeConstants.BUG, CurrentProjectVariables.getProjectId(),
                    BugRelayEmailNotificationAction.class);
            MVerticalLayout layout = getWrapContent();
            layout.removeAllComponents();
            layout.with(commentDisplay);
            commentDisplay.loadComments(bug.getId() + "");
        }
    }

    @Override
    public PopupView createBugStatusPopupField(final SimpleBug bug) {
        final PopupView view = new BugStatusPopupView(bug);
        view.setDescription("Click to edit");
        return view;
    }

    private static class BugStatusPopupView extends LazyPopupView {
        private SimpleBug beanItem;

        BugStatusPopupView(SimpleBug bug) {
            super(FontAwesome.INFO_CIRCLE.getHtml() + " " + bug.getStatus());
            this.beanItem = bug;
        }

        @Override
        protected void doShow() {
            MVerticalLayout content = getWrapContent();
            content.removeAllComponents();
            if (OptionI18nEnum.BugStatus.Open.name().equals(beanItem.getStatus()) ||
                    OptionI18nEnum.BugStatus.ReOpened.name().equals(beanItem.getStatus())) {
                Button startProgressBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_START_PROGRESS), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                    }
                });
                startProgressBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

                Button resolveBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_RESOLVED), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                    }
                });
                resolveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

                Button wontFixBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_WONTFIX), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                    }
                });
                wontFixBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
                content.with(startProgressBtn, resolveBtn, wontFixBtn);
            } else if (OptionI18nEnum.BugStatus.InProgress.name().equals(beanItem.getStatus())) {
                Button stopProgressBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_STOP_PROGRESS), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                    }
                });
                stopProgressBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

                Button resolveBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_RESOLVED), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                    }
                });
                resolveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
                content.with(stopProgressBtn, resolveBtn);
            } else if (OptionI18nEnum.BugStatus.Verified.name().equals(beanItem.getStatus())) {
                Button reopenBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_REOPEN), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                    }
                });
                reopenBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
                content.with(reopenBtn);
            } else if (OptionI18nEnum.BugStatus.Resolved.name().equals(beanItem.getStatus())) {
                Button reopenBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_REOPEN), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                    }
                });
                reopenBtn.addStyleName(UIConstants.THEME_GREEN_LINK);

                Button approveNCloseBtn = new Button(AppContext.getMessage(BugI18nEnum.BUTTON_APPROVE_CLOSE), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                    }
                });
                approveNCloseBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
                content.with(reopenBtn, approveNCloseBtn);
            } else if (OptionI18nEnum.BugStatus.Resolved.name().equals(beanItem.getStatus())) {
                Button reopenBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_REOPEN), new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {

                    }
                });
                reopenBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
                content.with(reopenBtn);
            }
        }
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
