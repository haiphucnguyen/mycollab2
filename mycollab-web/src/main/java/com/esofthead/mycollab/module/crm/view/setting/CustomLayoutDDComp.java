package com.esofthead.mycollab.module.crm.view.setting;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.form.view.builder.type.AbstractDynaField;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.ui.components.CustomFieldComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.And;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import fi.jasoft.dragdroplayouts.DDGridLayout;
import fi.jasoft.dragdroplayouts.DDGridLayout.GridLayoutTargetDetails;
import fi.jasoft.dragdroplayouts.DDVerticalLayout;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.HorizontalLocationIs;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;
import fi.jasoft.dragdroplayouts.events.VerticalLocationIs;

class CustomLayoutDDComp extends DDVerticalLayout {
    private static final long serialVersionUID = 1L;

    private DynaForm dynaForm;

    void displayLayoutCustom(DynaForm dynaForm) {
        this.removeAllComponents();
        this.dynaForm = dynaForm;
        this.setSpacing(true);

        int sectionCount = dynaForm.getSectionCount();
        for (int i = 0; i < sectionCount; i++) {
            DynaSection section = dynaForm.getSection(i);
            SectionLayoutComp sectionLayout = new SectionLayoutComp(section);
            this.addComponent(sectionLayout);
        }
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

            Label header = new Label(section.getHeader());
            header.setStyleName("h2");
            headerWrapper.addComponent(header);
            this.addComponent(headerWrapper);

            final DDGridLayout destGridLayout;

            if (section.getLayoutType() == LayoutType.ONE_COLUMN) {
                destGridLayout = new DDGridLayout(2, section.getFieldCount());
            } else if (section.getLayoutType() == LayoutType.TWO_COLUMN) {
                destGridLayout = new DDGridLayout(2,
                        section.getFieldCount() / 2);
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
                        } else {
                            // swap component source and dest component
                            srcGridLayout.removeComponent(srcComp);
                            destGridLayout.removeComponent(destComp);
                            destGridLayout.addComponent(srcComp, destColumn,
                                    destRow);
                            srcGridLayout.addComponent(destComp, srcColumn,
                                    srcRow);
                        }
                    }
                }
            });

            this.addComponent(destGridLayout);

            int fieldCount = section.getFieldCount();
            for (int j = 0; j < fieldCount; j++) {
                AbstractDynaField field = section.getField(j);
                CustomFieldComponent fieldBtn = new CustomFieldComponent(
                        field.getDisplayName());
                fieldBtn.setWidth("100%");
                if (section.getLayoutType() == LayoutType.ONE_COLUMN) {
                    destGridLayout.addComponent(fieldBtn, 0, j);
                } else if (section.getLayoutType() == LayoutType.TWO_COLUMN) {
                    destGridLayout.addComponent(fieldBtn, j % 2, j / 2);
                }
            }
        }
    }

}
