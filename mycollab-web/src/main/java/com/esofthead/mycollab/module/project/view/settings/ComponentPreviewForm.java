package com.esofthead.mycollab.module.project.view.settings;

import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.module.project.view.bug.components.ToggleBugSummaryField;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.SimpleComponent;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.DynaFormLayout;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.field.ContainerViewField;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import com.vaadin.data.Property;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class ComponentPreviewForm extends AdvancedPreviewBeanForm<SimpleComponent> {
    @Override
    public void setBean(SimpleComponent bean) {
        setFormLayoutFactory(new DynaFormLayout(ProjectTypeConstants.BUG_COMPONENT, ComponentDefaultFormLayoutFactory.getForm(),
                Component.Field.componentname.name()));
        setBeanFormFieldFactory(new ReadFormFieldFactory(this));
        super.setBean(bean);
    }

    private static class ReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleComponent> {
        private static final long serialVersionUID = 1L;

        public ReadFormFieldFactory(GenericBeanForm<SimpleComponent> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(Object propertyId) {
            SimpleComponent beanItem = attachForm.getBean();
            if (Component.Field.userlead.equalTo(propertyId)) {
                return new ProjectUserFormLinkField(beanItem.getUserlead(), beanItem.getUserLeadAvatarId(),
                        beanItem.getUserLeadFullName());
            } else if (Component.Field.id.equalTo(propertyId)) {
                ContainerViewField containerField = new ContainerViewField();
                containerField.addComponentField(new BugsComp(beanItem));
                return containerField;
            }
            return null;
        }
    }

    private static class BugsComp extends MVerticalLayout {
        private BugSearchCriteria searchCriteria;
        private MVerticalLayout issueLayout;

        BugsComp(SimpleComponent beanItem) {
            withMargin(false).withWidth("100%");
            MHorizontalLayout header = new MHorizontalLayout().withWidth("100%");

            final CheckBox openSelection = new BugStatusCheckbox(OptionI18nEnum.BugStatus.Open, true);
            CheckBox inprogressSelection = new BugStatusCheckbox(OptionI18nEnum.BugStatus.InProgress, true);
            CheckBox reOpenSelection = new BugStatusCheckbox(OptionI18nEnum.BugStatus.ReOpened, true);
            CheckBox verifiedSelection = new BugStatusCheckbox(OptionI18nEnum.BugStatus.Verified, true);
            CheckBox resolvedSelection = new BugStatusCheckbox(OptionI18nEnum.BugStatus.Resolved, true);
            CheckBox wontFixSelection = new BugStatusCheckbox(OptionI18nEnum.BugStatus.WontFix, true);

            Label spacingLbl1 = new Label("");

            header.with(openSelection, inprogressSelection, reOpenSelection, wontFixSelection, verifiedSelection,
                    resolvedSelection, spacingLbl1).alignAll(Alignment.MIDDLE_LEFT).expand(spacingLbl1);

            issueLayout = new MVerticalLayout();

            searchCriteria = new BugSearchCriteria();
            searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
            searchCriteria.setComponentids(new SetSearchField<>(beanItem.getId()));
            searchCriteria.setStatuses(new SetSearchField<>(OptionI18nEnum.BugStatus.Open.name(),
                    OptionI18nEnum.BugStatus.InProgress.name(), OptionI18nEnum.BugStatus.ReOpened.name(),
                    OptionI18nEnum.BugStatus.Verified.name(), OptionI18nEnum.BugStatus.Resolved.name(),
                    OptionI18nEnum.BugStatus.WontFix.name()));
            updateSearchStatus();

            this.with(header, issueLayout);
        }

        private void updateTypeSearchStatus(boolean selection, String type) {
            SetSearchField<String> types = searchCriteria.getStatuses();
            if (types == null) {
                types = new SetSearchField<>();
            }
            if (selection) {
                types.addValue(type);
            } else {
                types.removeValue(type);
            }
            searchCriteria.setStatuses(types);
            updateSearchStatus();
        }

        private void updateSearchStatus() {
            issueLayout.removeAllComponents();
            BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
            int totalCount = bugService.getTotalCount(searchCriteria);
            for (int i = 0; i < (totalCount / 20) + 1; i++) {
                List<SimpleBug> bugs = bugService.findPagableListByCriteria(new SearchRequest<>(searchCriteria, i + 1, 20));
                if (CollectionUtils.isNotEmpty(bugs)) {
                    for (SimpleBug bug : bugs) {
                        ToggleBugSummaryField toggleBugSummaryField = new ToggleBugSummaryField(bug);

                        MHorizontalLayout rowComp = new MHorizontalLayout().withStyleName(UIConstants.HOVER_EFFECT_NOT_BOX);
                        rowComp.setDefaultComponentAlignment(Alignment.TOP_LEFT);

                        String bugPriority = bug.getPriority();
                        Span priorityLink = new Span().appendText(ProjectAssetsManager.getBugPriorityHtml(bugPriority)).setTitle(bugPriority);

                        String avatarLink = StorageFactory.getInstance().getAvatarPath(bug.getAssignUserAvatarId(), 16);
                        Img img = new Img(bug.getAssignuserFullName(), avatarLink).setTitle(bug.getAssignuserFullName());

                        rowComp.with(new ELabel(ProjectAssetsManager.getAsset(ProjectTypeConstants.BUG).getHtml(), ContentMode.HTML)
                                        .withWidthUndefined(), new ELabel(priorityLink.write(), ContentMode.HTML).withWidthUndefined(),
                                new ELabel(img.write(), ContentMode.HTML).withWidthUndefined(),
                                toggleBugSummaryField).expand(toggleBugSummaryField);
                        issueLayout.add(rowComp);
                    }
                }
            }
        }

        private class BugStatusCheckbox extends CheckBox {
            BugStatusCheckbox(final Enum name, boolean defaultValue) {
                super(AppContext.getMessage(name), defaultValue);
                this.addValueChangeListener(new ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                        updateTypeSearchStatus(BugStatusCheckbox.this.getValue(), name.name());
                    }
                });
            }
        }
    }
}
