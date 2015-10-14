/**
 * This file is part of mycollab-mobile.
 * <p/>
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.crm.view.activity;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.form.view.DynaFormLayout;
import com.esofthead.mycollab.mobile.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.mobile.module.crm.ui.CrmPreviewFormControlsGenerator;
import com.esofthead.mycollab.mobile.module.crm.ui.CrmRelatedItemsScreenData;
import com.esofthead.mycollab.mobile.module.crm.ui.NotesList;
import com.esofthead.mycollab.mobile.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.mobile.ui.AbstractPreviewItemComp;
import com.esofthead.mycollab.mobile.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.mobile.ui.IconConstants;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.i18n.CrmCommonI18nEnum;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;

/**
 *
 * @author MyCollab Ltd.
 * @since 4.1
 *
 */

@ViewComponent
public class MeetingReadViewImpl extends AbstractPreviewItemComp<SimpleMeeting> implements MeetingReadView {
    private static final long serialVersionUID = -2176361946750466546L;
    private NotesList associateNotes;

    @Override
    public HasPreviewFormHandlers<SimpleMeeting> getPreviewFormHandlers() {
        return this.previewForm;
    }

    @Override
    protected void initRelatedComponents() {
        associateNotes = new NotesList(
                AppContext.getMessage(CrmCommonI18nEnum.M_TITLE_RELATED_NOTES));
    }

    @Override
    protected void afterPreviewItem() {
        associateNotes.showNotes(CrmTypeConstants.MEETING, beanItem.getId());
    }

    @Override
    protected String initFormTitle() {
        return beanItem.getSubject();
    }

    @Override
    protected AdvancedPreviewBeanForm<SimpleMeeting> initPreviewForm() {
        return new AdvancedPreviewBeanForm<>();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.MEETING, MeetingDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupViewFieldFactory<SimpleMeeting> initBeanFormFieldFactory() {
        return new MeetingReadFormFieldFactory(this.previewForm);
    }

    @Override
    protected ComponentContainer createButtonControls() {
        return new CrmPreviewFormControlsGenerator<>(this.previewForm).createButtonControls(RolePermissionCollections.CRM_MEETING);
    }

    @Override
    protected ComponentContainer createBottomPanel() {
        HorizontalLayout toolbarLayout = new HorizontalLayout();
        toolbarLayout.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        toolbarLayout.setSpacing(true);

        Button relatedNotes = new Button();
        relatedNotes.setCaption("<span aria-hidden=\"true\" data-icon=\""
                + IconConstants.CRM_DOCUMENT
                + "\"></span><div class=\"screen-reader-text\">"
                + "Notes" + "</div>");
        relatedNotes.setHtmlContentAllowed(true);
        relatedNotes.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 8846423206027007038L;

            @Override
            public void buttonClick(ClickEvent event) {
                EventBusFactory.getInstance().post(
                        new ActivityEvent.GoToRelatedItems(MeetingReadViewImpl.this, new CrmRelatedItemsScreenData(associateNotes)));
            }
        });
        toolbarLayout.addComponent(relatedNotes);
        return toolbarLayout;
    }

}
