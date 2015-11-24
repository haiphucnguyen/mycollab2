package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.data.Property;
import com.vaadin.event.FieldEvents;
import com.vaadin.event.LayoutEvents;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;

import java.util.UUID;

/**
 * @author MyCollab Ltd
 * @since 5.2.3
 */
public class ToogleBugSummaryField extends CssLayout {
    private boolean isRead = true;
    private Label bugLinkLbl;
    private SimpleBug bug;
    private int maxLength;

    public ToogleBugSummaryField(final SimpleBug bug) {
        this(bug, Integer.MAX_VALUE);
    }

    public ToogleBugSummaryField(final SimpleBug bug, int trimCharacters) {
        this.bug = bug;
        this.maxLength = trimCharacters;
        bugLinkLbl = new Label(buildBugLink(), ContentMode.HTML);

        if (bug.isCompleted()) {
            bugLinkLbl.addStyleName("completed");
            bugLinkLbl.removeStyleName("overdue pending");
        } else if (bug.isOverdue()) {
            bugLinkLbl.addStyleName("overdue");
            bugLinkLbl.removeStyleName("completed pending");
        }

        bugLinkLbl.addStyleName(UIConstants.LABEL_WORD_WRAP);
        this.addComponent(bugLinkLbl);
        if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.BUGS)) {
            this.addStyleName("editable-field");
            this.addLayoutClickListener(new LayoutEvents.LayoutClickListener() {
                @Override
                public void layoutClick(LayoutEvents.LayoutClickEvent event) {
                    if (isRead) {
                        ToogleBugSummaryField.this.removeComponent(bugLinkLbl);
                        final TextField editField = new TextField();
                        editField.setValue(bug.getSummary());
                        editField.setWidth("100%");
                        editField.focus();
                        ToogleBugSummaryField.this.addComponent(editField);
                        ToogleBugSummaryField.this.removeStyleName("editable-field");
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
        ToogleBugSummaryField.this.removeComponent(editField);
        ToogleBugSummaryField.this.addComponent(bugLinkLbl);
        ToogleBugSummaryField.this.addStyleName("editable-field");
        String newValue = editField.getValue();
        if (StringUtils.isNotBlank(newValue) && !newValue.equals(bug.getSummary())) {
            bug.setSummary(newValue);
            bugLinkLbl.setValue(buildBugLink());
            BugService bugService = ApplicationContextUtil.getSpringBean(BugService.class);
            bugService.updateWithSession(bug, AppContext.getUsername());
        }

        isRead = !isRead;
    }

    private String buildBugLink() {
        String uid = UUID.randomUUID().toString();

        String linkName = String.format("[#%d] - %s", bug.getBugkey(), StringUtils.trim(bug.getSummary(), maxLength, true));
        A taskLink = new A().setId("tag" + uid).setHref(ProjectLinkBuilder.generateBugPreviewFullLink(bug.getBugkey(),
                CurrentProjectVariables.getShortName())).appendText(linkName).setStyle("display:inline");

        taskLink.setAttribute("onmouseover", TooltipHelper.projectHoverJsFunction(uid, ProjectTypeConstants.BUG, bug.getId() + ""));
        taskLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction(uid));

        Div resultDiv = new DivLessFormatter().appendChild(taskLink, DivLessFormatter.EMPTY_SPACE(), TooltipHelper.buildDivTooltipEnable(uid));
        return resultDiv.write();
    }
}
