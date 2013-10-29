package com.esofthead.mycollab.module.crm.view.setting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.ui.components.CustomFieldComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptAll;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.And;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fi.jasoft.dragdroplayouts.DDGridLayout;
import fi.jasoft.dragdroplayouts.DDGridLayout.GridLayoutTargetDetails;
import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.DDVerticalLayout.VerticalLayoutTargetDetails;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.HorizontalLocationIs;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;
import fi.jasoft.dragdroplayouts.events.VerticalLocationIs;
import fi.jasoft.dragdroplayouts.interfaces.DragFilter;

class CustomLayoutDDComp extends HorizontalLayout {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(CustomLayoutDDComp.class);

	private DDVerticalLayout activeFormLayout;
	private DDVerticalLayout deleteFormLayout;

	private DynaForm dynaForm;

	void displayLayoutCustom(DynaForm dynaForm) {
		this.removeAllComponents();
		this.dynaForm = dynaForm;
		this.setSpacing(true);
		this.setWidth("100%");

		activeFormLayout = new DDVerticalLayout();
		activeFormLayout.setSpacing(true);
		activeFormLayout.setWidth("100%");

		deleteFormLayout = new DDVerticalLayout();
		deleteFormLayout.setWidth("100%");

		this.addComponent(activeFormLayout);
		this.setExpandRatio(activeFormLayout, 1.0f);

		this.addComponent(deleteFormLayout);
		this.setExpandRatio(deleteFormLayout, 1.0f);

		boolean hasDeletedSection = false;

		int sectionCount = dynaForm.getSectionCount();
		for (int i = 0; i < sectionCount; i++) {
			DynaSection section = dynaForm.getSection(i);
			if (!section.isDeletedSection()) {
				SectionLayoutComp sectionLayout = new SectionLayoutComp(section);
				activeFormLayout.addComponent(sectionLayout);
			} else {
				SectionLayoutComp sectionLayout = new SectionLayoutComp(section);
				deleteFormLayout.addComponent(sectionLayout);
				hasDeletedSection = true;
			}
		}

		if (!hasDeletedSection) {
			DynaSection deleteSection = new DynaSectionBuilder()
					.layoutType(LayoutType.ONE_COLUMN).header("Removed fields")
					.deleteSection(true).build();
			SectionLayoutComp sectionLayout = new SectionLayoutComp(
					deleteSection);
			deleteFormLayout.addComponent(sectionLayout);
		}

		activeFormLayout.setComponentVerticalDropRatio(0.3f);
		activeFormLayout.setDragMode(LayoutDragMode.CLONE);
		activeFormLayout.setDropHandler(new DropHandler() {
			private static final long serialVersionUID = 1L;

			@Override
			public AcceptCriterion getAcceptCriterion() {
				return AcceptAll.get();
			}

			@Override
			public void drop(DragAndDropEvent event) {
				VerticalLayoutTargetDetails details = (VerticalLayoutTargetDetails) event
						.getTargetDetails();
				LayoutBoundTransferable transferable = (LayoutBoundTransferable) event
						.getTransferable();
				Component srcComp = transferable.getComponent();
				if (srcComp instanceof SectionLayoutComp) {
					SectionLayoutComp overComp = (SectionLayoutComp) details
							.getOverComponent();
					int srcIndex = activeFormLayout.getComponentIndex(srcComp);
					int destIndex = activeFormLayout
							.getComponentIndex(overComp);

					if (srcIndex != destIndex) {
						activeFormLayout.replaceComponent(overComp, srcComp);
					}
				}
				log.debug("Target {}", event.getTargetDetails());
			}
		});
	}

	private static class SectionLayoutComp extends VerticalLayout {
		private static final long serialVersionUID = 1L;
		private DynaSection section;

		public SectionLayoutComp(DynaSection section) {
			this.section = section;
			this.addStyleName(UIConstants.CUSTOM_FIELD_SECTION);

			CssLayout headerWrapper = new CssLayout();
			headerWrapper.addStyleName("header-wrapper");
			headerWrapper.setWidth("100%");

			Label headerTitleLbl = new Label(section.getHeader());
			headerTitleLbl.setStyleName("h2");

			if (!section.isDeletedSection()) {
				HorizontalLayout header = new HorizontalLayout();
				header.setWidth("100%");

				header.addComponent(headerTitleLbl);
				header.setExpandRatio(headerTitleLbl, 1.0f);

				Button editTitleBtn = new Button();
				editTitleBtn.setDescription("Edit Section's Title");
				editTitleBtn.setIcon(MyCollabResource
						.newResource("icons/16/edit.png"));
				editTitleBtn.setStyleName("link");
				header.addComponent(editTitleBtn);

				Button deleteSectionBtn = new Button();
				deleteSectionBtn.setDescription("Remove this Section");
				deleteSectionBtn.setIcon(MyCollabResource
						.newResource("icons/16/delete.png"));
				deleteSectionBtn.setStyleName("link");
				header.addComponent(deleteSectionBtn);

				headerWrapper.addComponent(header);
			} else {
				headerWrapper.addComponent(headerTitleLbl);
			}
			this.addComponent(headerWrapper);

			final DDGridLayout destGridLayout;

			if (section.getLayoutType() == LayoutType.ONE_COLUMN) {
				destGridLayout = new DDGridLayout(1,
						section.getFieldCount() + 1);
			} else if (section.getLayoutType() == LayoutType.TWO_COLUMN) {
				destGridLayout = new DDGridLayout(2,
						(section.getFieldCount() + 3) / 2);
			} else {
				throw new MyCollabException(
						"Does not support form layout except 1 or 2 columns");
			}

			destGridLayout.setWidth("100%");
			destGridLayout.setMargin(true);
			destGridLayout.setSpacing(true);
			destGridLayout.setComponentHorizontalDropRatio(0);
			destGridLayout.setComponentVerticalDropRatio(0);
			destGridLayout.setDragMode(LayoutDragMode.CLONE);
			destGridLayout.setDragFilter(new CustomFieldDragFilter());

			destGridLayout.setDropHandler(new DropHandler() {
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

						Component destComp = destGridLayout.getComponent(
								destColumn, destRow);

						if (destComp == null) {
							srcGridLayout.removeComponent(srcComp);
							destGridLayout.addComponent(srcComp, destColumn,
									destRow);

							removeEmptyRow(srcGridLayout, srcRow);
						} else {
							if (srcGridLayout == destGridLayout
									&& srcColumn == destColumn
									&& srcRow == destRow) {
								// do nothing
							} else {
								// swap component source and dest component
								srcGridLayout.removeComponent(srcComp);
								if (((CustomFieldComponent) destComp)
										.isEmptyField()) {
									destGridLayout.insertRow(destGridLayout
											.getRows() - 1);
								} else {
									destGridLayout.removeComponent(destComp);
									srcGridLayout.addComponent(destComp,
											srcColumn, srcRow);
								}

								destGridLayout.addComponent(srcComp,
										destColumn, destRow);

								removeEmptyRow(srcGridLayout, srcRow);
							}

						}
					}
				}
			});

			this.addComponent(destGridLayout);

			int fieldCount = section.getFieldCount();
			for (int j = 0; j < fieldCount; j++) {
				AbstractDynaField field = section.getField(j);
				CustomFieldComponent fieldBtn = new CustomFieldComponent(field);
				fieldBtn.setWidth("100%");
				if (field.isMandatory())
					fieldBtn.setMandatory(true);
				if (section.getLayoutType() == LayoutType.ONE_COLUMN) {
					log.debug("Add field " + field.getDisplayName()
							+ " in (colum, row) " + 0 + ", " + j);
					destGridLayout.addComponent(fieldBtn, 0, j);
				} else if (section.getLayoutType() == LayoutType.TWO_COLUMN) {
					log.debug("Add field " + field.getDisplayName()
							+ " in (colum, row) " + (j % 2) + ", " + (j / 2));
					destGridLayout.addComponent(fieldBtn, j % 2, j / 2);
				}
			}

			CustomFieldComponent emptyField = new CustomFieldComponent(null);
			emptyField.setWidth("100%");

			if (section.getLayoutType() == LayoutType.ONE_COLUMN) {
				log.debug("Add empty field in (column, row) " + 0 + ", "
						+ (destGridLayout.getRows() - 1));
				destGridLayout.addComponent(emptyField, 0,
						destGridLayout.getRows() - 1);
			} else if (section.getLayoutType() == LayoutType.TWO_COLUMN) {
				log.debug("Add empty field in (column, row) " + 0 + ", "
						+ (destGridLayout.getRows() - 1));
				destGridLayout.addComponent(emptyField, 0,
						destGridLayout.getRows() - 1, 1,
						destGridLayout.getRows() - 1);
			}
		}

		public void removeEmptyRow(DDGridLayout srcGridLayout, int srcRow) {
			// Check if there is any remaining item in this row
			// if not, remove row
			int remainItem = 0;
			for (int i = srcGridLayout.getColumns(); i >= 0; i--) {
				if (srcGridLayout.getComponent(i, srcRow) != null)
					remainItem++;
			}
			if (remainItem == 0) {
				srcGridLayout.removeRow(srcRow);
			}
		}

		public class CustomFieldDragFilter implements DragFilter {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isDraggable(Component component) {
				if (component instanceof CustomFieldComponent) {
					if (((CustomFieldComponent) component).isEmptyField())
						return false;
					return true;
				}

				return false;
			}

		}
	}

}
