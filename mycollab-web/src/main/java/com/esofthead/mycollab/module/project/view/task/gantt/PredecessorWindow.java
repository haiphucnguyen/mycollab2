/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.task.gantt;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.project.domain.TaskPredecessor;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.combobox.FilteringMode;
import com.vaadin.ui.*;
import org.apache.commons.collections.CollectionUtils;
import org.vaadin.jouni.restrain.Restrain;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
class PredecessorWindow extends Window {
    private static final String ROW_WDITH = "50px";
    private static final String TASK_WIDTH = "300px";
    private static final String PRE_TYPE_WIDTH = "140px";
    private static final String LAG_WIDTH = "80px";

    private PredecessorsLayout predecessorsLayout;

    private GanttTreeTable taskTreeTable;
    private GanttItemWrapper ganttItemWrapper;

    PredecessorWindow(GanttTreeTable taskTreeTable, final GanttItemWrapper ganttItemWrapper) {
        super("Edit predecessors");
        this.setModal(true);
        this.setResizable(false);
        this.setWidth("650px");
        this.center();
        this.taskTreeTable = taskTreeTable;
        this.ganttItemWrapper = ganttItemWrapper;

        MVerticalLayout content = new MVerticalLayout();
        this.setContent(content);
        Label headerLbl = new Label(String.format("Row %d: %s", ganttItemWrapper.getGanttIndex(), ganttItemWrapper.getName()));
        headerLbl.addStyleName("h2");
        content.add(headerLbl);

        CssLayout preWrapper = new CssLayout();
        content.with(preWrapper);

        MHorizontalLayout headerLayout = new MHorizontalLayout();
        headerLayout.addComponent(new ELabel("Row").withWidth(ROW_WDITH));
        headerLayout.addComponent(new ELabel("Task").withWidth(TASK_WIDTH));
        headerLayout.addComponent(new ELabel("Dependency").withWidth(PRE_TYPE_WIDTH));
        headerLayout.addComponent(new ELabel("Lag").withWidth(LAG_WIDTH));
        predecessorsLayout = new PredecessorsLayout();
        new Restrain(predecessorsLayout).setMaxHeight("600px");

        preWrapper.addComponent(headerLayout);
        preWrapper.addComponent(predecessorsLayout);

        MHorizontalLayout buttonControls = new MHorizontalLayout();
        content.with(buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);

        Button cancelBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                PredecessorWindow.this.close();
            }
        });
        cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);

        Button saveBtn = new Button(AppContext.getMessage(GenericI18Enum.BUTTON_SAVE), new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                List<TaskPredecessor> predecessors = predecessorsLayout.buildPredecessors();
                if (adjustTaskAndItsPredeccessors()) {
                    ProjectTaskService projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
                    projectTaskService.massUpdatePredecessors(ganttItemWrapper.getId(), predecessors, AppContext.getAccountId());
                    ganttItemWrapper.getTask().setPredecessors(predecessors);
                    PredecessorWindow.this.close();
                } else {
                    throw new UserInvalidInputException("Invalid constraints");
                }
            }
        });
        saveBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
        buttonControls.with(cancelBtn, saveBtn);
    }

    private boolean adjustTaskAndItsPredeccessors() {
        return true;
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
            Iterator<Component> iter = this.iterator();
            while (iter.hasNext()) {
                Component comp = iter.next();
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

        private class PredecessorInputLayout extends MHorizontalLayout {
            private TextField rowField;
            private TaskComboBox taskComboBox;
            private PredecessorComboBox predecessorComboBox;
            private TextField lagField;

            PredecessorInputLayout() {
                this(null);
            }

            PredecessorInputLayout(TaskPredecessor taskPredecessor) {
                rowField = new TextField();
                rowField.setWidth(ROW_WDITH);
                rowField.addBlurListener(new FieldEvents.BlurListener() {
                    @Override
                    public void blur(FieldEvents.BlurEvent event) {
                        String value = rowField.getValue();
                        try {
                            int rowValue = Integer.parseInt(value);
                            GanttItemWrapper item = getGanttItemByRow(rowValue);
                            if (item != null) {
                                taskComboBox.setValue(item);
                                if (predecessorComboBox.getValue() == null) {
                                    predecessorComboBox.setValue(TaskPredecessor.FS);
                                }
                                PredecessorsLayout.this.addComponent(new PredecessorInputLayout());
                            } else {
                                predecessorComboBox.setValue(null);
                            }
                        } catch (NumberFormatException e) {
                            rowField.setValue("");
                        }
                    }
                });
                this.addComponent(rowField);

                taskComboBox = new TaskComboBox();
                taskComboBox.setWidth(TASK_WIDTH);
                taskComboBox.addValueChangeListener(new Property.ValueChangeListener() {
                    @Override
                    public void valueChange(Property.ValueChangeEvent event) {
                        GanttItemWrapper item = (GanttItemWrapper) taskComboBox.getValue();
                        if (item == null) {
                            rowField.setValue("");
                            predecessorComboBox.setValue(null);
                        } else {
                            rowField.setValue(item.getGanttIndex() + "");
                        }
                    }
                });
                taskComboBox.addBlurListener(new FieldEvents.BlurListener() {
                    @Override
                    public void blur(FieldEvents.BlurEvent event) {
                        GanttItemWrapper item = (GanttItemWrapper) taskComboBox.getValue();
                        if (item != null) {
                            PredecessorsLayout.this.addComponent(new PredecessorInputLayout());
                        }
                    }
                });
                this.addComponent(taskComboBox);

                predecessorComboBox = new PredecessorComboBox();
                predecessorComboBox.setWidth(PRE_TYPE_WIDTH);
                this.addComponent(predecessorComboBox);

                lagField = new TextField();
                lagField.setWidth(LAG_WIDTH);
                this.addComponent(lagField);

                Button deleteBtn = new Button();
                deleteBtn.addClickListener(new Button.ClickListener() {
                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        if (PredecessorsLayout.this.getComponentCount() == 1) {
                            return;
                        } else {
                            PredecessorsLayout.this.removeComponent(PredecessorInputLayout.this);
                        }
                    }
                });
                deleteBtn.setIcon(FontAwesome.TRASH_O);
                deleteBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);
                this.addComponent(deleteBtn);

                if (taskPredecessor != null) {
                    rowField.setValue(taskPredecessor.getGanttIndex() + "");
                    predecessorComboBox.setValue(taskPredecessor.getPredestype());
                    GanttItemWrapper item = getGanttItemByRow(taskPredecessor.getGanttIndex());
                    if (item != null) {
                        taskComboBox.setValue(item);
                    }
                    if (taskPredecessor.getLagday() == null) {
                        lagField.setValue("");
                    } else {
                        lagField.setValue(taskPredecessor.getLagday() + "");
                    }
                }
            }

            private GanttItemWrapper getGanttItemByRow(int rowIndex) {
                BeanItemContainer<GanttItemWrapper> beanItemContainer = taskTreeTable.getRawContainerDataSource();
                List<GanttItemWrapper> items = beanItemContainer.getItemIds();
                for (GanttItemWrapper item : items) {
                    if (rowIndex == item.getGanttIndex()) {
                        return item;
                    }
                }
                return null;
            }

            TaskPredecessor buildPredecessor() {
                GanttItemWrapper item = (GanttItemWrapper) taskComboBox.getValue();
                if (item != null && item.getTask().getId() != ganttItemWrapper.getTask().getId()) {
                    TaskPredecessor predecessor = new TaskPredecessor();
                    predecessor.setGanttIndex(item.getGanttIndex());
                    predecessor.setLagday(getLagDay());
                    predecessor.setPredestype(getPreDesType());
                    predecessor.setSourceid(ganttItemWrapper.getTask().getId());
                    predecessor.setDescid(item.getTask().getId());
                    predecessor.setStartDate(item.getStartDate());
                    predecessor.setEndDate(item.getEndDate());
                    predecessor.setDueDate(item.getDueDate());
                    return predecessor;
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
        }

        private class TaskComboBox extends ComboBox {
            TaskComboBox() {
                this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
                this.setFilteringMode(FilteringMode.CONTAINS);
                BeanItemContainer<GanttItemWrapper> beanItemContainer = taskTreeTable.getRawContainerDataSource();
                List<GanttItemWrapper> itemIds = beanItemContainer.getItemIds();
                for (GanttItemWrapper item : itemIds) {
                    this.addItem(item);
                    this.setItemCaption(item, String.format("[Row %d]: %s", item.getGanttIndex(), StringUtils.trim(item
                            .getName(), 50, true)));
                }
            }
        }
    }

    private static class PredecessorComboBox extends ComboBox {
        PredecessorComboBox() {
            this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
            this.addItem(TaskPredecessor.FS);
            this.setItemCaption(TaskPredecessor.FS, "Finish to Start (FS)");

            this.addItem(TaskPredecessor.SF);
            this.setItemCaption(TaskPredecessor.SF, "Start to Finish (SF)");

            this.addItem(TaskPredecessor.SS);
            this.setItemCaption(TaskPredecessor.SS, "Start to Start (SS)");

            this.addItem(TaskPredecessor.FF);
            this.setItemCaption(TaskPredecessor.FF, "Finish to Finish (FF)");
        }
    }
}
