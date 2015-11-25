package com.esofthead.mycollab.module.project.view.milestone;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.i18n.OptionI18nEnum;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.LayoutEvents;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.teemu.VaadinIcons;

/**
 * @author MyCollab Ltd
 * @since 5.2.3
 */
public class ToogleMilestoneSummaryField extends CssLayout {
    private boolean isRead = true;
    private ELabel milestoneLbl;
    private SimpleMilestone milestone;
    private int maxLength;

    ToogleMilestoneSummaryField(final SimpleMilestone milestone) {
        this(milestone, Integer.MAX_VALUE);
    }

    ToogleMilestoneSummaryField(final SimpleMilestone milestone, int maxLength) {
        this.milestone = milestone;
        this.maxLength = maxLength;
        this.setWidth("100%");
        milestoneLbl = new ELabel(buildMilestoneLink(), ContentMode.HTML).withStyleName(ValoTheme.LABEL_H3).withWidthUndefined();
        milestoneLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        milestoneLbl.addStyleName(UIConstants.LABEL_WORD_WRAP);
        this.addComponent(milestoneLbl);
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.MILESTONES)) {
            this.addStyleName("editable-field");
            this.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                    if (event.getClickedComponent() == milestoneLbl) {
                        return;
                    }
                    if (isRead) {
                        ToogleMilestoneSummaryField.this.removeComponent(milestoneLbl);
                        final TextField editField = new TextField();
                        editField.setValue(milestone.getName());
                        editField.setWidth("100%");
                        editField.focus();
                        ToogleMilestoneSummaryField.this.addComponent(editField);
                        ToogleMilestoneSummaryField.this.removeStyleName("editable-field");
                        editField.addValueChangeListener(new Property.ValueChangeListener() {
                            @Override
                            public void valueChange(Property.ValueChangeEvent event) {
                                updateFieldValue(editField);
                            }
                        });
                        editField.addBlurListener(new FieldEvents.BlurListener() {
                            @Override
                            public void blur(FieldEvents.BlurEvent event) {
                                updateFieldValue(editField);
                            }
                        });
                        isRead = !isRead;
                    }

                }
            });
        }
    }

    private void updateFieldValue(TextField editField) {
        ToogleMilestoneSummaryField.this.removeComponent(editField);
        ToogleMilestoneSummaryField.this.addComponent(milestoneLbl);
        ToogleMilestoneSummaryField.this.addStyleName("editable-field");
        String newValue = editField.getValue();
        if (StringUtils.isNotBlank(newValue) && !newValue.equals(milestone.getName())) {
            milestone.setName(newValue);
            milestoneLbl.setValue(buildMilestoneLink());
            MilestoneService milestoneService = ApplicationContextUtil.getSpringBean(MilestoneService.class);
            milestoneService.updateWithSession(milestone, AppContext.getUsername());
        }

        isRead = !isRead;
    }

    private String buildMilestoneLink() {
        Div milestoneDiv = new Div().appendText(VaadinIcons.CALENDAR_BRIEFCASE.getHtml() + " ").appendChild(new A
                (ProjectLinkBuilder.generateMilestonePreviewFullLink(milestone.getProjectid(), milestone.getId()))
                .appendText(StringUtils.trim(milestone.getName(), maxLength, true)))
                .appendText(" (" + AppContext.getMessage(OptionI18nEnum.MilestoneStatus.class, milestone.getStatus()) + ")");
        return milestoneDiv.write();
    }
}
