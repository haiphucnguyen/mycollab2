package com.mycollab.pro.module.project.view.assignments.gantt;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.domain.TaskPredecessor;
import com.mycollab.module.project.i18n.GanttI18nEnum;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.pro.module.project.events.GanttEvent;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
class PredecessorWindow extends MWindow {
    private static final String ROW_WIDTH = "50px";
    private static final String TASK_WIDTH = "300px";
    private static final String PRE_TYPE_WIDTH = "140px";
    private static final String LAG_WIDTH = "80px";

    private PredecessorsLayout predecessorsLayout;

    private GanttTreeTable taskTreeTable;
    private GanttItemWrapper ganttItemWrapper;

    PredecessorWindow(final GanttTreeTable taskTreeTable, final GanttItemWrapper ganttItemWrapper) {
        super(UserUIContext.getMessage(GanttI18nEnum.OPT_EDIT_PREDECESSORS));
        this.withModal(true).withResizable(false).withWidth("650px").withCenter();
        this.taskTreeTable = taskTreeTable;
        this.ganttItemWrapper = ganttItemWrapper;

        MVerticalLayout content = new MVerticalLayout();
        this.setContent(content);
        ELabel headerLbl = ELabel.h2(String.format("%s %d: %s", UserUIContext.getMessage(GanttI18nEnum.OPT_ROW),
                ganttItemWrapper.getGanttIndex(), ganttItemWrapper.getName())).withStyleName(UIConstants.TEXT_ELLIPSIS);
        content.add(headerLbl);

        CssLayout preWrapper = new CssLayout();
        content.with(preWrapper);

        MHorizontalLayout headerLayout = new MHorizontalLayout();
        headerLayout.addComponent(new ELabel(UserUIContext.getMessage(GanttI18nEnum.OPT_ROW)).withWidth(ROW_WIDTH));
        headerLayout.addComponent(new ELabel(UserUIContext.getMessage(TaskI18nEnum.SINGLE)).withWidth(TASK_WIDTH));
        headerLayout.addComponent(new ELabel(UserUIContext.getMessage(GanttI18nEnum.OPT_DEPENDENCY)).withWidth(PRE_TYPE_WIDTH));
        headerLayout.addComponent(new ELabel(UserUIContext.getMessage(GanttI18nEnum.OPT_LAG)).withWidth(LAG_WIDTH));
        predecessorsLayout = new PredecessorsLayout();
        new Restrain(predecessorsLayout).setMaxHeight("600px");

        preWrapper.addComponent(headerLayout);
        preWrapper.addComponent(predecessorsLayout);

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebUIConstants.BUTTON_OPTION);

        MButton saveBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_SAVE), clickEvent -> {
            List<TaskPredecessor> predecessors = predecessorsLayout.buildPredecessors();
            EventBusFactory.getInstance().post(new GanttEvent.ModifyPredecessors(ganttItemWrapper, predecessors));
            close();
        }).withIcon(FontAwesome.SAVE).withStyleName(WebUIConstants.BUTTON_ACTION);

        MHorizontalLayout buttonControls = new MHorizontalLayout(cancelBtn, saveBtn);
        content.with(buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
    }

    private boolean hasRelationship(GanttItemWrapper item1, GanttItemWrapper item2) {
        GanttItemContainer container = taskTreeTable.getRawContainer();
        return container.hasCircularRelationship(item1, item2);
    }

    private class PredecessorsLayout extends VerticalLayout {
        PredecessorsLayout() {
            this.setSpacing(true);
            List<TaskPredecessor> predecessors = ganttItemWrapper.getTask().getPredecessors();
            if (!CollectionUtils.isEmpty(predecessors)) {
                for (TaskPredecessor predecessor : predecessors) {
                    this.addComponent(new PredecessorInputLayout(predecessor));
                }
            }
            this.addComponent(new PredecessorInputLayout());
        }

        List<TaskPredecessor> buildPredecessors() {
            List<TaskPredecessor> predecessors = new ArrayList<>();
            for (Component comp : this) {
                if (comp instanceof PredecessorInputLayout) {
                    PredecessorInputLayout predecessorInput = (PredecessorInputLayout) comp;
                    TaskPredecessor predecessor = predecessorInput.buildPredecessor();
                    if (predecessor != null) {
                        predecessors.add(predecessor);
                    }
                }
            }
            return predecessors;
        }

        boolean hasEmptyRow() {
            if (this.getComponentCount() > 0) {
                PredecessorInputLayout component = (PredecessorInputLayout) this.getComponent(getComponentCount() - 1);
                return component.isEmptyPredecessor();
            } else {
                return false;
            }
        }

        private class PredecessorInputLayout extends MHorizontalLayout {
            private TextField rowField;
            private AssignmentComboBox assignmentComboBox;
            private PredecessorComboBox predecessorComboBox;
            private TextField lagField;

            PredecessorInputLayout() {
                this(null);
            }

            PredecessorInputLayout(TaskPredecessor taskPredecessor) {
                rowField = new TextField();
                rowField.setWidth(ROW_WIDTH);
                rowField.addBlurListener(blurEvent -> {
                    String value = rowField.getValue();
                    try {
                        int rowValue = Integer.parseInt(value);
                        GanttItemWrapper item = taskTreeTable.getRawContainer().getItemByGanttIndex(rowValue);
                        if (item != null) {
                            if (hasRelationship(item, ganttItemWrapper)) {
                                NotificationUtil.showErrorNotification(UserUIContext.getMessage(GanttI18nEnum.ERROR_CIRCULAR_DEPENDENCY));
                            } else {
                                if (item.isTask()) {
                                    assignmentComboBox.setValue(item);
                                    if (predecessorComboBox.getValue() == null) {
                                        predecessorComboBox.setValue(TaskPredecessor.FS);
                                    }

                                    if (!PredecessorsLayout.this.hasEmptyRow()) {
                                        PredecessorsLayout.this.addComponent(new PredecessorInputLayout());
                                    }
                                } else {
                                    NotificationUtil.showWarningNotification("The predecessor must be a task");
                                }
                            }
                        } else {
                            rowField.setValue("");
                            predecessorComboBox.setValue(null);
                        }
                    } catch (NumberFormatException e) {
                        rowField.setValue("");
                    }
                });
                this.addComponent(rowField);

                assignmentComboBox = new AssignmentComboBox();
                assignmentComboBox.setWidth(TASK_WIDTH);
                assignmentComboBox.addValueChangeListener(valueChangeEvent -> {
                    GanttItemWrapper item = (GanttItemWrapper) assignmentComboBox.getValue();
                    if (item == null) {
                        rowField.setValue("");
                        predecessorComboBox.setValue(null);
                    } else {
                        if (hasRelationship(item, ganttItemWrapper)) {
                            NotificationUtil.showErrorNotification(UserUIContext.getMessage(GanttI18nEnum.ERROR_CIRCULAR_DEPENDENCY));
                            assignmentComboBox.setValue(null);
                        } else {
                            rowField.setValue(item.getGanttIndex() + "");
                        }
                    }
                });
                assignmentComboBox.addBlurListener(blurEvent -> {
                    GanttItemWrapper item = (GanttItemWrapper) assignmentComboBox.getValue();
                    if (item != null) {
                        PredecessorsLayout.this.addComponent(new PredecessorInputLayout());
                    }
                });
                this.addComponent(assignmentComboBox);

                predecessorComboBox = new PredecessorComboBox();
                predecessorComboBox.setWidth(PRE_TYPE_WIDTH);
                this.addComponent(predecessorComboBox);

                lagField = new TextField();
                lagField.setWidth(LAG_WIDTH);
                this.addComponent(lagField);

                Button deleteBtn = new Button("", clickEvent -> {
                    if (PredecessorsLayout.this.getComponentCount() == 1) {
                    } else {
                        PredecessorsLayout.this.removeComponent(PredecessorInputLayout.this);
                    }
                });
                deleteBtn.setIcon(FontAwesome.TRASH_O);
                deleteBtn.addStyleName(WebUIConstants.BUTTON_ICON_ONLY);
                this.addComponent(deleteBtn);

                if (taskPredecessor != null) {
                    rowField.setValue(taskPredecessor.getGanttIndex() + "");
                    predecessorComboBox.setValue(taskPredecessor.getPredestype());
                    GanttItemWrapper item = taskTreeTable.getRawContainer().getItemByGanttIndex(taskPredecessor.getGanttIndex());
                    if (item != null) {
                        assignmentComboBox.setValue(item);
                    }
                    if (taskPredecessor.getLagday() == null) {
                        lagField.setValue("");
                    } else {
                        lagField.setValue(taskPredecessor.getLagday() + "");
                    }
                }
            }

            TaskPredecessor buildPredecessor() {
                GanttItemWrapper item = (GanttItemWrapper) assignmentComboBox.getValue();
                if (item != null) {
                    if (hasRelationship(item, ganttItemWrapper)) {
                        throw new UserInvalidInputException(UserUIContext.getMessage(GanttI18nEnum.ERROR_CIRCULAR_DEPENDENCY));
                    } else {
                        TaskPredecessor predecessor = new TaskPredecessor();
                        predecessor.setGanttIndex(item.getGanttIndex());
                        predecessor.setLagday(getLagDay());
                        predecessor.setPredestype(getPreDesType());
                        predecessor.setSourceid(ganttItemWrapper.getTask().getId());
                        predecessor.setDescid(item.getTask().getId());
                        predecessor.setSourcetype(item.getType());
                        predecessor.setDesctype(ganttItemWrapper.getType());
                        return predecessor;
                    }
                }
                return null;
            }

            Integer getLagDay() {
                try {
                    return Integer.parseInt(lagField.getValue());
                } catch (Exception e) {
                    return 0;
                }
            }

            String getPreDesType() {
                String preType = (String) predecessorComboBox.getValue();
                return (preType == null) ? TaskPredecessor.FS : preType;
            }

            boolean isEmptyPredecessor() {
                return rowField.getValue().trim().equals("");
            }
        }

        private class AssignmentComboBox extends ComboBox {
            AssignmentComboBox() {
                this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
                this.setFilteringMode(FilteringMode.CONTAINS);
                GanttItemContainer beanItemContainer = taskTreeTable.getRawContainer();
                Collection itemIds = beanItemContainer.getItemIds();
                for (Object itemId : itemIds) {
                    GanttItemWrapper item = (GanttItemWrapper) itemId;
                    if (item.isTask()) {
                        this.addItem(item);
                        this.setItemCaption(item, String.format("[%s %d]: %s", UserUIContext.getMessage(GanttI18nEnum.OPT_ROW),
                                item.getGanttIndex(), StringUtils.trim(item.getName(), 50, true)));
                    }
                }
            }
        }
    }

    private static class PredecessorComboBox extends ComboBox {
        PredecessorComboBox() {
            this.setNullSelectionAllowed(false);
            this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
            this.addItem(TaskPredecessor.FS);
            this.setItemCaption(TaskPredecessor.FS, UserUIContext.getMessage(GanttI18nEnum.OPT_FS));

            this.addItem(TaskPredecessor.SF);
            this.setItemCaption(TaskPredecessor.SF, UserUIContext.getMessage(GanttI18nEnum.OPT_SF));

            this.addItem(TaskPredecessor.SS);
            this.setItemCaption(TaskPredecessor.SS, UserUIContext.getMessage(GanttI18nEnum.OPT_SS));

            this.addItem(TaskPredecessor.FF);
            this.setItemCaption(TaskPredecessor.FF, UserUIContext.getMessage(GanttI18nEnum.OPT_FF));
            this.setValue(TaskPredecessor.FS);
        }
    }
}
