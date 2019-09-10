/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.vaadin.web.ui;

import com.vaadin.flow.component.UI;
import org.claspina.confirmdialog.ButtonOption;
import org.claspina.confirmdialog.ConfirmDialog;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class ConfirmDialogExt {
    public static ConfirmDialog show(UI parentWindow, String windowCaption, String message, String okCaption,
                                     String cancelCaption, Runnable listener) {
        return ConfirmDialog
                .createQuestion()
                .withCaption(windowCaption)
                .withMessage(message)
                .withOkButton(listener, ButtonOption.focus(), ButtonOption.caption(okCaption))
                .withCancelButton(ButtonOption.caption(cancelCaption));
    }
}
