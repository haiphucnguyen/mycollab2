package com.mycollab.pro.module.crm.view.setting.customlayout;

import com.mycollab.form.view.builder.type.AbstractDynaField;
import com.mycollab.form.view.builder.type.DynaSection;
import com.vaadin.event.dd.DragAndDropEvent;
import com.vaadin.event.dd.DropHandler;
import com.vaadin.event.dd.acceptcriteria.AcceptCriterion;
import com.vaadin.event.dd.acceptcriteria.And;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import fi.jasoft.dragdroplayouts.DDGridLayout;
import fi.jasoft.dragdroplayouts.DDGridLayout.GridLayoutTargetDetails;
import fi.jasoft.dragdroplayouts.client.ui.LayoutDragMode;
import fi.jasoft.dragdroplayouts.events.HorizontalLocationIs;
import fi.jasoft.dragdroplayouts.events.LayoutBoundTransferable;
import fi.jasoft.dragdroplayouts.events.VerticalLocationIs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
class DeleteSectionComp extends GenericSectionComp {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(DeleteSectionComp.class);

    DeleteSectionComp(DynaSection section) {
        super(section);

        this.addStyleName("deleteSection");
        this.setWidth("450px");

        CssLayout headerWrapper = new CssLayout();
        headerWrapper.addStyleName("header-wrapper");
        headerWrapper.setWidth("100%");

//		Label headerTitleLbl = new Label(section.getHeader());
        Label headerTitleLbl = new Label("Not implemented yet");
        headerTitleLbl.setStyleName(ValoTheme.LABEL_H3);

        HorizontalLayout header = new HorizontalLayout();
        header.setWidth("100%");

        headerWrapper.addComponent(headerTitleLbl);
        this.addComponent(headerWrapper);

        CssLayout contentWrapper = new CssLayout();
        contentWrapper.setWidth("100%");
        contentWrapper.addStyleName("content-wrapper");

        dragLayout = new DDGridLayout(1, section.getFieldCount() + 1);

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

                if (srcComp instanceof CustomFieldComponent) {
                    if (parentSrcComp instanceof DDGridLayout) {
                        DDGridLayout srcGridLayout = (DDGridLayout) parentSrcComp;
                        int srcRow = srcGridLayout.getComponentArea(srcComp)
                                .getRow1();

                        Component destComp = dragLayout.getComponent(
                                destColumn, destRow);

                        if (destComp == null) {
                            srcGridLayout.removeComponent(srcComp);
                            dragLayout.addComponent(srcComp, destColumn,
                                    destRow);

                            removeEmptyRow(srcGridLayout, srcRow);
                        } else {
                            if (srcGridLayout == dragLayout) {
                                // do nothing
                            } else {
                                srcGridLayout.removeComponent(srcComp);

                                dragLayout.insertRow(0);
                                dragLayout.addComponent(srcComp, 0, 0);

                                removeEmptyRow(srcGridLayout, srcRow);
                            }

                        }
                    }
                } else if (srcComp instanceof ActiveSectionComp) {
                    ActiveFormSectionArea parentSection = (ActiveFormSectionArea) srcComp
                            .getParent();
                    parentSection.removeComponent(srcComp);
                    parentSection.markAsDirtyRecursive();

                    addDeleteActiveSectionComp((ActiveSectionComp) srcComp);
                }

            }
        });

        contentWrapper.addComponent(dragLayout);
        this.addComponent(contentWrapper);

        int fieldCount = section.getFieldCount();
        for (int j = 0; j < fieldCount; j++) {
            AbstractDynaField field = section.getField(j);
            CustomFieldComponent fieldBtn = new CustomFieldComponent(field);
            fieldBtn.setWidth("100%");
            LOG.debug("Add field " + field.getDisplayName()
                    + " in (colum, row) " + 0 + ", " + j);
            dragLayout.addComponent(fieldBtn, 0, j);
        }

        CustomFieldComponent emptyField = new CustomFieldComponent(null);
        emptyField.setWidth("100%");

        LOG.debug("Add empty field in (column, row) " + 0 + ", "
                + (dragLayout.getRows() - 1));
        dragLayout.addComponent(emptyField, 0, dragLayout.getRows() - 1);
    }

    public void addDeleteActiveSectionComp(ActiveSectionComp activeSectionComp) {
        List<AbstractDynaField> deleteFields = activeSectionComp.getFields();

        for (int i = 0; i < deleteFields.size(); i++) {
            AbstractDynaField field = deleteFields.get(i);
            CustomFieldComponent fieldBtn = new CustomFieldComponent(field);
            fieldBtn.setWidth("100%");
            dragLayout.insertRow(0);
            dragLayout.addComponent(fieldBtn, 0, 0);
        }
    }

    public DynaSection rebuildSection() {
        DynaSection section = new DynaSection();
        section.setDeletedSection(true);
        section.setHeader(originSection.getHeader());
        section.setLayoutType(originSection.getLayoutType());

        for (int row = 0; row < dragLayout.getRows(); row++) {
            Component component = dragLayout.getComponent(0, row);
            if (component != null && component instanceof CustomFieldComponent
                    && !((CustomFieldComponent) component).isEmptyField()) {
                AbstractDynaField field = ((CustomFieldComponent) component)
                        .getField();
                field.setFieldIndex(row);
                section.fields(field);
            }
        }

        return section;
    }

}
