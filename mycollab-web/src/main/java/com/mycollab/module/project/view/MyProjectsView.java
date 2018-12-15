package com.mycollab.module.project.view;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Div;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.project.ProjectLinkBuilder;
import com.mycollab.module.project.ProjectLinkGenerator;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleProject;
import com.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.mycollab.module.project.fielddef.ProjectTableFieldDef;
import com.mycollab.module.project.service.ProjectService;
import com.mycollab.module.project.ui.ProjectAssetsUtil;
import com.mycollab.vaadin.TooltipHelper;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.event.HasMassItemActionHandler;
import com.mycollab.vaadin.ui.DefaultMassItemActionHandlerContainer;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.SelectionOptionButton;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.table.DefaultPagedGrid;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ComponentRenderer;
import com.vaadin.ui.renderers.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringView(name = MyProjectsView.VIEW_NAME)
public class MyProjectsView extends MVerticalLayout implements View {
    public static final String VIEW_NAME = "projects";

    private ProjectSearchPanel projectSearchPanel;
    private SelectionOptionButton selectOptionButton;
    private Grid<SimpleProject> projectGrid;
    private VerticalLayout bodyLayout;
    private DefaultMassItemActionHandlerContainer tableActionControls;
    private Label selectedItemsNumberLabel = new Label();

    @Autowired
    private MyProjectsPresenter myProjectsPresenter;

    @Autowired
    private ProjectService projectService;

    @PostConstruct
    public void init() {
        myProjectsPresenter.initView(this);
        projectSearchPanel = new ProjectSearchPanel();
        with(projectSearchPanel);

        bodyLayout = new VerticalLayout();
        this.addComponent(bodyLayout);

        generateDisplayTable();
    }

    private void generateDisplayTable() {
        projectGrid = new Grid<>();

//        gridItem.addGeneratedColumn("selected", (source, itemId, columnId) -> {
//            final SimpleProject item = gridItem.getBeanByIndex(itemId);
//            final CheckBoxDecor cb = new CheckBoxDecor("", item.isSelected());
//            cb.setImmediate(true);
//            cb.addValueChangeListener(valueChangeEvent -> gridItem.fireSelectItemEvent(item));
//            item.setExtraData(cb);
//            return cb;
//        });
//
//
//
        projectGrid.addColumn(project -> ProjectLinkBuilder.generateProjectMemberHtmlLink(project.getId(), project.getMemlead(),
                project.getLeadFullName(), project.getLeadAvatarId(), true), new HtmlRenderer()).setCaption("Lead");

        projectGrid.addColumn(project -> {
            A projectLink = new A(ProjectLinkGenerator.generateProjectLink(project.getId())).appendText(project.getName());
            projectLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(ProjectTypeConstants.PROJECT,
                    project.getId() + ""));
            projectLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction());
            A url;
            if (StringUtils.isNotBlank(project.getHomepage())) {
                url = new A(project.getHomepage(), "_blank").appendText(project.getHomepage()).setCSSClass(UIConstants.META_INFO);
            } else {
                url = new A("").appendText(UserUIContext.getMessage(GenericI18Enum.OPT_UNDEFINED));
            }

            Div projectDiv = new Div().appendChild(projectLink, new Br(), url);
            ELabel b = ELabel.html(projectDiv.write());
            return new MHorizontalLayout(ProjectAssetsUtil.projectLogoComp(project
                    .getShortname(), project.getId(), project.getAvatarid(), 32), b)
                    .expand(b).alignAll(Alignment.MIDDLE_LEFT).withMargin(false);
        }, new ComponentRenderer()).setCaption("Name");
//
//        gridItem.addGeneratedColumn(Project.Field.accountid.name(), (source, itemId, columnId) -> {
//            SimpleProject project = gridItem.getBeanByIndex(itemId);
//            if (project.getAccountid() != null) {
//                LabelLink b = new LabelLink(project.getClientName(),
//                        ProjectLinkGenerator.generateClientPreviewLink(project.getAccountid()));
//                b.setIconLink(CrmAssetsManager.getAsset(CrmTypeConstants.ACCOUNT));
//                return b;
//            } else {
//                return new Label();
//            }
//        });
//
        projectGrid.addColumn(SimpleProject::getPlanstartdate).setCaption("Start date");
        projectGrid.addColumn(SimpleProject::getPlanenddate).setCaption("End date");
//
//        gridItem.addGeneratedColumn(Project.Field.projectstatus.name(), (source, itemId, columnId) -> {
//            SimpleProject project = gridItem.getBeanByIndex(itemId);
//            return ELabel.i18n(project.getProjectstatus(), StatusI18nEnum.class);
//        });
//
//        gridItem.addGeneratedColumn(Project.Field.createdtime.name(), (source, itemId, columnId) -> {
//            SimpleProject project = gridItem.getBeanByIndex(itemId);
//            return new Label(UserUIContext.formatDate(project.getCreatedtime()));
//        });

        projectGrid.setWidth("100%");

        bodyLayout.addComponent(constructTableActionControls());
        bodyLayout.addComponent(projectGrid);
    }

    private ComponentContainer constructTableActionControls() {
        MHorizontalLayout layout = new MHorizontalLayout().withFullWidth();
//        layout.addStyleName(WebThemes.TABLE_ACTION_CONTROLS);
//
//        selectOptionButton = new SelectionOptionButton(projectGrid);
//        selectOptionButton.setWidthUndefined();
//        layout.addComponent(selectOptionButton);
//
//        tableActionControls = new DefaultMassItemActionHandlerContainer();
//
//        tableActionControls.addDownloadPdfActionItem();
//        tableActionControls.addDownloadExcelActionItem();
//        tableActionControls.addDownloadCsvActionItem();
//
//        tableActionControls.setVisible(false);
//        tableActionControls.setWidthUndefined();
//
//        layout.addComponent(tableActionControls);
//        selectedItemsNumberLabel.setWidth("100%");
//        layout.with(selectedItemsNumberLabel).withAlign(selectedItemsNumberLabel, Alignment.MIDDLE_CENTER).expand(selectedItemsNumberLabel);
//
//        MButton customizeViewBtn = new MButton("", clickEvent -> UI.getCurrent().addWindow(new ProjectListCustomizeWindow(projectGrid)))
//                .withStyleName(WebThemes.BUTTON_ACTION).withIcon(VaadinIcons.ADJUST);
//        customizeViewBtn.setDescription(UserUIContext.getMessage(GenericI18Enum.OPT_LAYOUT_OPTIONS));
//        layout.with(customizeViewBtn).withAlign(customizeViewBtn, Alignment.MIDDLE_RIGHT);

        return layout;
    }

    public HasMassItemActionHandler getPopupActionHandlers() {
        return tableActionControls;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
        myProjectsPresenter.displayMyProjects(searchCriteria);
    }

    public Grid getProjectGrid() {
        return projectGrid;
    }
}
