/**
 * This file is part of mycollab-web.
 * <p/>
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.premium.module.crm.view.setting.customlayout;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.form.view.builder.type.DynaSection.LayoutType;
import com.esofthead.mycollab.module.crm.view.setting.ICrmCustomView;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.grid.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class CreateSectionWindow extends Window {
    private static final long serialVersionUID = 1L;

    public CreateSectionWindow(final ICrmCustomView customView) {
        super("New Section");
        center();
        this.setWidth("600px");
        this.setResizable(false);
        this.setModal(true);

        MVerticalLayout contentLayout = new MVerticalLayout().withMargin(false).withSpacing(false);
        this.setContent(contentLayout);

        GridFormLayoutHelper layoutHelper = GridFormLayoutHelper.defaultFormLayoutHelper(1, 2);

        final TextField sectionName = new TextField();
        layoutHelper.addComponent(sectionName, "Name", 0, 0);

        final SectionLayoutComboBox sectionLayoutComboBox = new SectionLayoutComboBox();
        layoutHelper.addComponent(sectionLayoutComboBox, "Column Layout", 0, 1);

        contentLayout.addComponent(layoutHelper.getLayout());

        MHorizontalLayout controlLayout = new MHorizontalLayout().withMargin(true);

        Button saveBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_SAVE),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        DynaSection section = new DynaSection();
                        section.setDeletedSection(false);
                        section.setHeader(sectionName.getValue());
                        section.setLayoutType((LayoutType) sectionLayoutComboBox
                                .getValue());

                        customView.addActiveSection(section);
                        CreateSectionWindow.this.close();
                    }
                });
        saveBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
        saveBtn.setIcon(FontAwesome.SAVE);


        Button cancelBtn = new Button(
                AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(ClickEvent event) {
                        CreateSectionWindow.this.close();
                    }
                });
        cancelBtn.setStyleName(UIConstants.THEME_GRAY_LINK);

        controlLayout.with(saveBtn, cancelBtn);

        contentLayout.with(controlLayout).withAlign(controlLayout, Alignment.MIDDLE_RIGHT);
    }

    private static class SectionLayoutComboBox extends ComboBox {
        private static final long serialVersionUID = 1L;

        public SectionLayoutComboBox() {
            this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);
            this.setNullSelectionAllowed(false);
            this.addItem(LayoutType.ONE_COLUMN);
            this.setItemCaption(LayoutType.ONE_COLUMN, "One Column");

            this.addItem(LayoutType.TWO_COLUMN);
            this.setItemCaption(LayoutType.TWO_COLUMN, "Two Columns");
            this.select(LayoutType.ONE_COLUMN);
        }
    }

}
