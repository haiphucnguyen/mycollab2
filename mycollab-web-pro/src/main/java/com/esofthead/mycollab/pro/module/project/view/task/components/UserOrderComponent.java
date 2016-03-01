package com.esofthead.mycollab.pro.module.project.view.task.components;

import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.utils.SortedArrayMap;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.view.task.components.AbstractUserOrderComponent;
import com.esofthead.mycollab.module.project.view.task.components.TaskRowRenderer;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Text;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.9
 */
@ViewComponent
public class UserOrderComponent extends AbstractUserOrderComponent {
    private SortedArrayMap<String, GroupComponent> userAvailables = new SortedArrayMap<>();
    private GroupComponent unspecifiedTasks;

    @Override
    public void insertTasks(List<SimpleTask> tasks) {
        for (SimpleTask task : tasks) {
            String assignUser = task.getAssignuser();
            if (assignUser != null) {
                if (userAvailables.containsKey(assignUser)) {
                    GroupComponent groupComponent = userAvailables.get(assignUser);
                    groupComponent.insertTask(task);
                } else {
                    GroupComponent groupComponent = new GroupComponent(task);
                    userAvailables.put(assignUser, groupComponent);
                    int index = userAvailables.getKeyIndex(assignUser);
                    if (index > -1) {
                        addComponent(groupComponent, index);
                    } else {
                        addComponent(groupComponent);
                    }

                    groupComponent.insertTask(task);
                }
            } else {
                if (unspecifiedTasks == null) {
                    unspecifiedTasks = new GroupComponent();
                    addComponent(unspecifiedTasks, 0);
                }
                unspecifiedTasks.insertTask(task);
            }
        }
    }

    private static class GroupComponent extends VerticalLayout {
        private CssLayout wrapBody;
        private Label headerLbl;

        GroupComponent(SimpleTask task) {
            initComponent();
            Img img = new Img("", StorageFactory.getInstance().getAvatarPath(task.getAssignUserAvatarId(), 32));
            Div userDiv = new Div().appendChild(img, new Text(" " + task.getAssignUserFullName()));
            headerLbl.setValue(userDiv.write());
        }

        GroupComponent() {
            initComponent();
            headerLbl.setValue("Unassigned");
        }

        private void initComponent() {
            this.setMargin(new MarginInfo(true, false, true, false));
            wrapBody = new CssLayout();
            wrapBody.setStyleName("tasklist");
            headerLbl = ELabel.h3("");
            this.addComponent(headerLbl);
            this.addComponent(wrapBody);
        }

        void insertTask(SimpleTask task) {
            wrapBody.addComponent(new TaskRowRenderer(task));
        }
    }
}
