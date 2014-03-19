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
package com.esofthead.mycollab.module.crm.ui.components;

import org.vaadin.peter.buttongroup.ButtonGroup;

import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.SplitButton;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public class CrmPreviewFormControlsGenerator<T> {
	public static int BACK_BTN_PRESENTED = 2;
	public static int EDIT_BTN_PRESENTED = 4;
	public static int DELETE_BTN_PRESENTED = 8;
	public static int CLONE_BTN_PRESENTED = 16;
	public static int PREVIOUS_BTN_PRESENTED = 32;
	public static int NEXT_BTN_PRESENTED = 64;
	public static int HISTORY_BTN_PRESENTED = 128;

	private Button editBtn, deleteBtn, cloneBtn, previousItem,
	nextItemBtn;
	private AdvancedPreviewBeanForm<T> previewForm;

	private VerticalLayout popupButtonsControl;

	private SplitButton editButtons;
	private HorizontalLayout layout;

	public CrmPreviewFormControlsGenerator(
			final AdvancedPreviewBeanForm<T> editForm) {
		this.previewForm = editForm;

		layout = new HorizontalLayout();

		editButtons = new SplitButton();
		editButtons.setCaption("Option");
		editButtons.setWidth(Sizeable.SIZE_UNDEFINED, Sizeable.Unit.PIXELS);
		editButtons.addStyleName(UIConstants.THEME_GRAY_LINK);
	}

	public void insertToControlBlock(Button button) {
		layout.addComponent(button, 0);
	}

	public HorizontalLayout createButtonControls(final String permissionItem) {
		return createButtonControls(EDIT_BTN_PRESENTED
				| DELETE_BTN_PRESENTED | CLONE_BTN_PRESENTED
				| PREVIOUS_BTN_PRESENTED | NEXT_BTN_PRESENTED, permissionItem);
	}

	public HorizontalLayout createButtonControls(int buttonEnableFlags,
			final String permissionItem) {

		layout.setStyleName("control-buttons");
		layout.setSpacing(true);
		layout.setSizeUndefined();

		boolean canRead = true;
		boolean canWrite = true;
		boolean canAccess = true;
		if (permissionItem != null) {
			canRead = AppContext.canRead(permissionItem);
			canWrite = AppContext.canWrite(permissionItem);
			canAccess = AppContext.canAccess(permissionItem);
		}

		popupButtonsControl = new VerticalLayout();
		popupButtonsControl.setMargin(new MarginInfo( false,true, false, true));
		popupButtonsControl.setSpacing(true);

		if ((buttonEnableFlags & EDIT_BTN_PRESENTED) == EDIT_BTN_PRESENTED) {
			editBtn = new Button(GenericBeanForm.EDIT_ACTION,
					new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					final T item = previewForm.getBean();
					previewForm.fireEditForm(item);
				}
			});
			editBtn.setIcon(MyCollabResource
					.newResource("icons/16/edit.png"));
			editBtn.setStyleName("link");
			editBtn.setEnabled(canWrite);
			popupButtonsControl.addComponent(editBtn);
		}		

		if ((buttonEnableFlags & DELETE_BTN_PRESENTED) == DELETE_BTN_PRESENTED) {
			deleteBtn = new Button(GenericBeanForm.DELETE_ACTION,
					new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					final T item = previewForm.getBean();
					previewForm.fireDeleteForm(item);
				}
			});
			deleteBtn.setIcon(MyCollabResource
					.newResource("icons/16/delete2.png"));
			deleteBtn.setStyleName(UIConstants.THEME_RED_LINK);
			layout.addComponent(deleteBtn);
			deleteBtn.setEnabled(canAccess);
		}

		if ((buttonEnableFlags & CLONE_BTN_PRESENTED) == CLONE_BTN_PRESENTED) {
			cloneBtn = new Button(GenericBeanForm.CLONE_ACTION,
					new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					final T item = previewForm.getBean();
					previewForm.fireCloneForm(item);
				}
			});
			cloneBtn.setIcon(MyCollabResource.newResource("icons/16/clone.png"));
			cloneBtn.setStyleName("link");
			popupButtonsControl.addComponent(cloneBtn);
		}

		editButtons.setContent(popupButtonsControl);

		layout.addComponent(editButtons);

		ButtonGroup navigationBtns = new ButtonGroup();
		navigationBtns.setStyleName("navigation-btns");

		if ((buttonEnableFlags & PREVIOUS_BTN_PRESENTED) == PREVIOUS_BTN_PRESENTED) {
			previousItem = new Button("<", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					final T item = previewForm.getBean();
					previewForm.fireGotoPrevious(item);
				}
			});
			previousItem.setStyleName(UIConstants.THEME_GREEN_LINK);
			previousItem.setDescription("Read previous item");
			navigationBtns.addButton(previousItem);
			previousItem.setEnabled(canRead);
		}

		if ((buttonEnableFlags & NEXT_BTN_PRESENTED) == NEXT_BTN_PRESENTED) {
			nextItemBtn = new Button(">", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					final T item = previewForm.getBean();
					previewForm.fireGotoNextItem(item);
				}
			});
			nextItemBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
			nextItemBtn.setDescription("Read next item");
			navigationBtns.addButton(nextItemBtn);
			nextItemBtn.setEnabled(canRead);
		}

		layout.addComponent(navigationBtns);

		return layout;
	}
}
