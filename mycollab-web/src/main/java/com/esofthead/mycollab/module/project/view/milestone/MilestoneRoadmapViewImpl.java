package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.ui.components.ProjectViewHeader;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractLazyPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.ProgressBarIndicator;
import com.esofthead.mycollab.vaadin.ui.ToggleButtonGroup;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.hp.gagawa.java.elements.Img;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import org.vaadin.teemu.VaadinIcons;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
@ViewComponent
public class MilestoneRoadmapViewImpl extends AbstractLazyPageView implements MilestoneRoadmapView {
    private Button createBtn;

    private MilestoneService milestoneService = ApplicationContextUtil.getSpringBean(MilestoneService.class);
    private MilestoneSearchCriteria baseCriteria;

    public MilestoneRoadmapViewImpl() {
    }

    @Override
    protected void displayView() {
        initUI();
        createBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES));

        new Thread() {
            public void run() {
                UI.getCurrent().access(new Runnable() {
                    @Override
                    public void run() {
                        baseCriteria = new MilestoneSearchCriteria();
                        baseCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
                        List<SimpleMilestone> milestones = milestoneService.findPagableListByCriteria(new SearchRequest<>(baseCriteria, 0, Integer.MAX_VALUE));
                        for (SimpleMilestone milestone : milestones) {
                            MilestoneRoadmapViewImpl.this.addComponent(new MilestoneBlock(milestone));
                        }
                    }
                });
            }
        }.start();
    }

    private void initUI() {
        ProjectViewHeader headerText = new ProjectViewHeader(ProjectTypeConstants.MILESTONE,
                AppContext.getMessage(MilestoneI18nEnum.VIEW_LIST_TITLE));

        MHorizontalLayout header = new MHorizontalLayout()
                .withStyleName("hdr-view").withWidth("100%").withMargin(true)
                .with(headerText, createHeaderRight())
                .withAlign(headerText, Alignment.MIDDLE_LEFT).expand(headerText);
        this.addComponent(header);
    }

    private HorizontalLayout createHeaderRight() {
        MHorizontalLayout layout = new MHorizontalLayout();

        createBtn = new Button(AppContext.getMessage(MilestoneI18nEnum.BUTTON_NEW_PHASE), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                EventBusFactory.getInstance().post(new MilestoneEvent.GotoAdd(MilestoneRoadmapViewImpl.this, null));
            }
        });
        createBtn.setIcon(FontAwesome.PLUS);
        createBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        createBtn.setEnabled(CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES));
        layout.with(createBtn);

        Button kanbanBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                EventBusFactory.getInstance().post(new MilestoneEvent.GotoList(MilestoneRoadmapViewImpl.this, null));
            }
        });
        kanbanBtn.setDescription("Kanban View");
        kanbanBtn.setIcon(FontAwesome.TH);

        Button roadmapBtn = new Button();
        roadmapBtn.setDescription("Roadmap");
        roadmapBtn.setIcon(VaadinIcons.CUBE);

        ToggleButtonGroup viewButtons = new ToggleButtonGroup();
        viewButtons.addButton(roadmapBtn);
        viewButtons.addButton(kanbanBtn);
        viewButtons.setDefaultButton(roadmapBtn);
        layout.with(viewButtons);

        return layout;
    }

    private static class MilestoneBlock extends MVerticalLayout {
        MilestoneBlock(final SimpleMilestone milestone) {
            this.setStyleName("roadmap-block");
            MButton milestoneLink = new MButton(milestone.getName()).withStyleName(UIConstants.THEME_LINK)
                    .withListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent clickEvent) {
                            EventBusFactory.getInstance().post(new MilestoneEvent.GotoRead(MilestoneBlock.this, milestone));
                        }
                    });
            milestoneLink.setIcon(VaadinIcons.CALENDAR_BRIEFCASE);
            milestoneLink.addStyleName("milestoneLink");
            milestoneLink.setWidth("100%");
            this.addComponent(milestoneLink);

            MHorizontalLayout metaBlock = new MHorizontalLayout();
            metaBlock.addComponent(new ELabel(AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE) + ": " +
                    new Img("", StorageFactory.getInstance().getAvatarPath(milestone.getOwnerAvatarId(), 16)).write() + " "
                    + StringUtils.trim(milestone.getOwnerFullName(), 20, true), ContentMode.HTML).withStyleName("block"));
            metaBlock.addComponent(new ELabel("Start: " + AppContext.formatDate(milestone.getStartdate()))
                    .withStyleName("block"));
            metaBlock.addComponent(new ELabel("End: " + AppContext.formatDate(milestone.getEnddate())).withStyleName
                    ("block"));
            metaBlock.addComponent(new ELabel(FontAwesome.MONEY.getHtml() + " " + (milestone.getTotalBugBillableHours() + milestone
                    .getTotalTaskBillableHours()), ContentMode.HTML).withStyleName("block"));
            metaBlock.addComponent(new ELabel(FontAwesome.GIFT.getHtml() + " " + (milestone.getTotalBugNonBillableHours() + milestone
                    .getTotalTaskNonBillableHours()), ContentMode.HTML).withStyleName("block"));
            this.add(metaBlock);

            ELabel descriptionLbl = new ELabel(StringUtils.formatRichText(milestone.getDescription()), ContentMode.HTML);
            this.addComponent(descriptionLbl);

            MHorizontalLayout progressLayout = new MHorizontalLayout();
            int openAssignments = milestone.getNumOpenBugs() + milestone.getNumOpenTasks();
            int totalAssignments = milestone.getNumBugs() + milestone.getNumTasks();
            ProgressBarIndicator progressBarIndicator = new ProgressBarIndicator(totalAssignments, (totalAssignments - openAssignments), false);
            Button viewIssuesBtn = new Button("View issues", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {

                }
            });
            viewIssuesBtn.setStyleName(UIConstants.THEME_LINK);
            progressLayout.withWidth("800px").with(progressBarIndicator, viewIssuesBtn);
            this.addComponent(progressLayout);
        }
    }
}
