package com.mycollab.module.project.view.settings;

import com.mycollab.db.arguments.NumberSearchField;
import com.mycollab.db.arguments.SetSearchField;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugStatus;
import com.mycollab.module.project.view.bug.BugRowRenderer;
import com.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.mycollab.module.tracker.domain.Component;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.module.tracker.domain.SimpleComponent;
import com.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.mycollab.module.tracker.service.BugService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.field.RichTextViewField;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.DefaultBeanPagedList;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.field.ContainerViewField;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class ComponentPreviewForm extends AdvancedPreviewBeanForm<SimpleComponent> {
    @Override
    public void setBean(SimpleComponent bean) {
        setFormLayoutFactory(new DefaultDynaFormLayout(ProjectTypeConstants.INSTANCE.getBUG_COMPONENT(), ComponentDefaultFormLayoutFactory.getForm(),
                Component.Field.name.name()));
        setBeanFormFieldFactory(new ReadFormFieldFactory(this));
        super.setBean(bean);
    }

    private static class ReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleComponent> {
        private static final long serialVersionUID = 1L;

        ReadFormFieldFactory(GenericBeanForm<SimpleComponent> form) {
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
            } else if (Component.Field.description.equalTo(propertyId)) {
                return new RichTextViewField(beanItem.getDescription());
            }
            return null;
        }
    }

    private static class BugsComp extends MVerticalLayout {
        private BugSearchCriteria searchCriteria;
        private DefaultBeanPagedList<BugService, BugSearchCriteria, SimpleBug> bugList;

        BugsComp(SimpleComponent beanItem) {
            withMargin(false).withFullWidth();
            MHorizontalLayout header = new MHorizontalLayout().withFullWidth();

            final CheckBox openSelection = new BugStatusCheckbox(BugStatus.Open, true);
            CheckBox reOpenSelection = new BugStatusCheckbox(BugStatus.ReOpen, true);
            CheckBox verifiedSelection = new BugStatusCheckbox(BugStatus.Verified, true);
            CheckBox resolvedSelection = new BugStatusCheckbox(BugStatus.Resolved, true);

            Label spacingLbl1 = new Label("");

            header.with(openSelection, reOpenSelection, verifiedSelection,
                    resolvedSelection, spacingLbl1).alignAll(Alignment.MIDDLE_LEFT).expand(spacingLbl1);

            bugList = new DefaultBeanPagedList(AppContextUtil.getSpringBean(BugService.class), new BugRowRenderer());
            bugList.setControlStyle("");
            bugList.setMargin(new MarginInfo(true, true, true, false));

            searchCriteria = new BugSearchCriteria();
            searchCriteria.setProjectId(new NumberSearchField(CurrentProjectVariables.getProjectId()));
            searchCriteria.setComponentids(new SetSearchField<>(beanItem.getId()));
            searchCriteria.setStatuses(new SetSearchField<>(BugStatus.Open.name(), BugStatus.ReOpen.name(),
                    BugStatus.Verified.name(), BugStatus.Resolved.name()));
            updateSearchStatus();

            this.with(header, bugList);
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
            bugList.setSearchCriteria(searchCriteria);
        }

        private class BugStatusCheckbox extends CheckBox {
            BugStatusCheckbox(final Enum name, boolean defaultValue) {
                super(UserUIContext.getMessage(name), defaultValue);
                this.addValueChangeListener(valueChangeEvent -> updateTypeSearchStatus(BugStatusCheckbox.this.getValue(), name.name()));
            }
        }
    }
}
