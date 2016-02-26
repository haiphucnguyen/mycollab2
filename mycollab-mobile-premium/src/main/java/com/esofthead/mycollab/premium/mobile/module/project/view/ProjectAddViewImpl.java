package com.esofthead.mycollab.premium.mobile.module.project.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.i18n.OptionI18nEnum;
import com.esofthead.mycollab.mobile.module.project.view.ProjectAddView;
import com.esofthead.mycollab.mobile.ui.AbstractEditItemComp;
import com.esofthead.mycollab.mobile.ui.FormSectionBuilder;
import com.esofthead.mycollab.mobile.ui.I18nValueComboBox;
import com.esofthead.mycollab.mobile.ui.grid.GridFormLayoutHelper;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.i18n.ProjectI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.addon.touchkit.ui.UrlField;
import com.vaadin.ui.*;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
@ViewComponent
public class ProjectAddViewImpl extends AbstractEditItemComp<SimpleProject> implements ProjectAddView {
    @Override
    protected String initFormTitle() {
        return beanItem.getId() == null ? "New Project" : beanItem.getName();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new EditFormLayoutFactory();
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleProject> initBeanFormFieldFactory() {
        return new EditFormFieldFactory(this.editForm);
    }

    private class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleProject> {
        private static final long serialVersionUID = 1L;

        public EditFormFieldFactory(GenericBeanForm<SimpleProject> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (Project.Field.name.equalTo(propertyId)) {
                final TextField tf = new TextField();
                tf.setRequired(true);
                return tf;
            } else if (Project.Field.shortname.equalTo(propertyId)) {
                final TextField tf = new TextField();
                tf.setRequired(true);
                return tf;
            } else if (Project.Field.homepage.equalTo(propertyId)) {
                return new UrlField();
            } else if (Project.Field.projectstatus.equalTo(propertyId)) {
                return new ProjectStatusComboBox();
            } else if (Project.Field.planstartdate.equalTo(propertyId) || Project.Field.planenddate.equalTo(propertyId)) {
                return new DatePicker();
            } else if (Project.Field.description.equalTo(propertyId)) {
                final TextArea field = new TextArea("", "");
                field.setNullRepresentation("");
                return field;
            }
            return null;
        }
    }

    public class EditFormLayoutFactory implements IFormLayoutFactory {
        private static final long serialVersionUID = -9159483523170247666L;

        private GridFormLayoutHelper informationLayout;

        @Override
        public ComponentContainer getLayout() {
            final VerticalLayout layout = new VerticalLayout();
            layout.setMargin(false);
            layout.addComponent(FormSectionBuilder.build("Information"));

            informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(1, 7);
            layout.addComponent(informationLayout.getLayout());
            layout.setComponentAlignment(informationLayout.getLayout(), Alignment.BOTTOM_CENTER);
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field<?> field) {
            if (Project.Field.name.equalTo(propertyId)) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_NAME), 0, 0);
            } else if (Project.Field.shortname.equalTo(propertyId)) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_SHORT_NAME), 0, 1);
            } else if (Project.Field.homepage.equalTo(propertyId)) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_HOME_PAGE), 0, 2);
            } else if (Project.Field.projectstatus.equalTo(propertyId)) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_STATUS), 0, 3);
            } else if (Project.Field.planstartdate.equalTo(propertyId)) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_START_DATE), 0, 4);
            } else if (Project.Field.planenddate.equalTo(propertyId)) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_END_DATE), 0, 5);
            } else if (Project.Field.description.equalTo(propertyId)) {
                informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 6);
            }
        }
    }

    private static class ProjectStatusComboBox extends I18nValueComboBox {
        private static final long serialVersionUID = 1L;

        public ProjectStatusComboBox() {
            super(false, OptionI18nEnum.StatusI18nEnum.Open, OptionI18nEnum.StatusI18nEnum.Closed);
        }
    }
}
