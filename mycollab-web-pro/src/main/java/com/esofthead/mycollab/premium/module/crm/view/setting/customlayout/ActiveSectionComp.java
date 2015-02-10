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
package com.esofthead.mycollab.premium.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.And;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import fi.jasoft.dragdroplayouts.DDGridLayout;
import fi.jasoft.dragdroplayouts.DDGridLayout.GridLayoutTargetDetails;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.HorizontalLocationIs;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;
import fi.jasoft.dragdroplayouts.events.VerticalLocationIs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ActiveSectionComp extends GenericSectionComp {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(ActiveSectionComp.class);

	public ActiveSectionComp(DynaSection section) {
		super(section);

		constructLayout();
	}

	public void constructLayout() {
		this.removeAllComponents();
		CssLayout headerWrapper = new CssLayout();
		headerWrapper.addStyleName("header-wrapper");
		headerWrapper.setWidth("100%");

		Label headerTitleLbl = new Label(originSection.getHeader());
		headerTitleLbl.setStyleName("h2");

		HorizontalLayout header = new HorizontalLayout();
		header.setWidth("100%");

		header.addComponent(headerTitleLbl);
		header.setExpandRatio(headerTitleLbl, 1.0f);

		Button editTitleBtn = new Button();
		editTitleBtn.setDescription("Edit Section's Title");
		editTitleBtn.setIcon(FontAwesome.EDIT);
		editTitleBtn.setStyleName("link");
		header.addComponent(editTitleBtn);

		Button deleteSectionBtn = new Button();
		deleteSectionBtn.setDescription("Remove this Section");
		deleteSectionBtn.setIcon(FontAwesome.TRASH_O);
		deleteSectionBtn.addStyleName(UIConstants.BUTTON_ICON_ONLY);
		header.addComponent(deleteSectionBtn);

		headerWrapper.addComponent(header);
		this.addComponent(headerWrapper);

		if (originSection.getLayoutType() == LayoutType.ONE_COLUMN) {
			dragLayout = new DDGridLayout(1, originSection.getFieldCount() + 1);
		} else if (originSection.getLayoutType() == LayoutType.TWO_COLUMN) {
			dragLayout = new DDGridLayout(2,
					(originSection.getFieldCount() + 3) / 2);
		} else {
			throw new MyCollabException(
					"Does not support attachForm layout except 1 or 2 columns");
		}

		dragLayout.setWidth("100%");
		dragLayout.setMargin(true);
		dragLayout.setSpacing(true);
		dragLayout.setComponentHorizontalDropRatio(0);
		dragLayout.setComponentVerticalDropRatio(0);
		dragLayout.setDragMode(LayoutDragMode.CLONE);
		dragLayout.setDragFilter(new CustomFieldDragFilter());

		dragLayout.setDropHandler(new DropHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public AcceptCriterion getAcceptCriterion() {
				return new And(VerticalLocationIs.MIDDLE,
						HorizontalLocationIs.CENTER);
			}

			@Override
			public void drop(DragAndDropEvent event) {
				GridLayoutTargetDetails details = (GridLayoutTargetDetails) event
						.getTargetDetails();
				LayoutBoundTransferable transferable = (LayoutBoundTransferable) event
						.getTransferable();

				int destColumn = details.getOverColumn();
				int destRow = details.getOverRow();

				Component srcComp = transferable.getComponent();
				Component parentSrcComp = srcComp.getParent();

				if (parentSrcComp instanceof DDGridLayout) {
					DDGridLayout srcGridLayout = (DDGridLayout) parentSrcComp;
					int srcColumn = srcGridLayout.getComponentArea(srcComp)
							.getColumn1();
					int srcRow = srcGridLayout.getComponentArea(srcComp)
							.getRow1();

					Component destComp = dragLayout.getComponent(destColumn,
							destRow);

					if (destComp == null) {
						srcGridLayout.removeComponent(srcComp);
						dragLayout.addComponent(srcComp, destColumn, destRow);

						removeEmptyRow(srcGridLayout, srcRow);
					} else {
						if (srcGridLayout == dragLayout
								&& srcColumn == destColumn && srcRow == destRow) {
							// do nothing
						} else {
							// swap component source and dest component
							srcGridLayout.removeComponent(srcComp);
							if (((CustomFieldComponent) destComp)
									.isEmptyField()) {
								dragLayout.insertRow(dragLayout.getRows() - 1);
							} else {
								dragLayout.removeComponent(destComp);
								srcGridLayout.addComponent(destComp, srcColumn,
										srcRow);
							}

							dragLayout.addComponent(srcComp, destColumn,
									destRow);

							removeEmptyRow(srcGridLayout, srcRow);
						}

					}
				}
			}
		});

		this.addComponent(dragLayout);

		int fieldCount = originSection.getFieldCount();
		for (int j = 0; j < fieldCount; j++) {
			AbstractDynaField field = originSection.getField(j);
			CustomFieldComponent fieldBtn = new CustomFieldComponent(field);
			fieldBtn.setWidth("100%");
			if (field.isMandatory())
				fieldBtn.setMandatory(true);
			if (originSection.getLayoutType() == LayoutType.ONE_COLUMN) {
				LOG.debug("Add field " + field.getDisplayName()
						+ " in (colum, row) " + 0 + ", " + j);
				dragLayout.addComponent(fieldBtn, 0, j);
			} else if (originSection.getLayoutType() == LayoutType.TWO_COLUMN) {
				LOG.debug("Add field " + field.getDisplayName()
						+ " in (colum, row) " + (j % 2) + ", " + (j / 2));
				dragLayout.addComponent(fieldBtn, j % 2, j / 2);
			}
		}

		CustomFieldComponent emptyField = new CustomFieldComponent(null);
		emptyField.setWidth("100%");

		if (originSection.getLayoutType() == LayoutType.ONE_COLUMN) {
			LOG.debug("Add empty field in (column, row) " + 0 + ", "
					+ (dragLayout.getRows() - 1));
			dragLayout.addComponent(emptyField, 0, dragLayout.getRows() - 1);
		} else if (originSection.getLayoutType() == LayoutType.TWO_COLUMN) {
			LOG.debug("Add empty field in (column, row) " + 0 + ", "
					+ (dragLayout.getRows() - 1));
			dragLayout.addComponent(emptyField, 0, dragLayout.getRows() - 1, 1,
					dragLayout.getRows() - 1);
		}
	}

	public DynaSection rebuildSection() {
		DynaSection section = new DynaSection();
		section.setDeletedSection(false);
		section.setHeader(originSection.getHeader());
		section.setLayoutType(originSection.getLayoutType());

		for (int col = 0; col < dragLayout.getColumns(); col++) {
			for (int row = 0; row < dragLayout.getRows(); row++) {
				Component component = dragLayout.getComponent(col, row);
				if (component != null
						&& component instanceof CustomFieldComponent
						&& !((CustomFieldComponent) component).isEmptyField()) {
					AbstractDynaField field = ((CustomFieldComponent) component)
							.getField();
					field.setFieldIndex(row * dragLayout.getColumns() + col);
					section.addField(field);
				}
			}
		}

		originSection = section;
		return originSection;
	}
}
