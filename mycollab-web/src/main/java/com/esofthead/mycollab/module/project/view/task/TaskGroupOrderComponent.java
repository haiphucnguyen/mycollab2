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
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.JavaScriptFunction;
import com.vaadin.ui.Notification;
import elemental.json.JsonArray;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
abstract class TaskGroupOrderComponent extends CssLayout {
    abstract void insertTasks(List<SimpleTask> tasks);

    @Override
    public void attach() {
        JavaScript.getCurrent().addFunction("loadMoreTasks",
                new JavaScriptFunction() {
                    @Override
                    public void call(JsonArray arguments) {
                        Notification.show("Received call");
                    }
                });
    }

    @Override
    public void detach() {
        JavaScript.getCurrent().removeFunction("loadMoreTasks");
    }
}
