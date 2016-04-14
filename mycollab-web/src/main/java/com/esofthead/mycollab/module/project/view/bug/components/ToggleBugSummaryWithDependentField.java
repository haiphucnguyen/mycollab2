package com.esofthead.mycollab.module.project.view.bug.components;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.tracker.dao.RelatedBugMapper;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.RelatedBugExample;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.RemoveInlineComponentMarker;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.esofthead.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import org.vaadin.dialogs.ConfirmDialog;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public class ToggleBugSummaryWithDependentField extends CustomField<SimpleBug> {

    private ToggleBugSummaryField toggleBugSummaryField;

    public ToggleBugSummaryWithDependentField(final BugWithBLOBs hostBug, final BugWithBLOBs relatedBug) {
        toggleBugSummaryField = new ToggleBugSummaryField(relatedBug);
        Button unlinkBtn = new Button(null, new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ConfirmDialogExt.show(UI.getCurrent(), AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_TITLE,
                        AppContext.getSiteName()),
                        AppContext.getMessage(GenericI18Enum.DIALOG_DELETE_SINGLE_ITEM_MESSAGE),
                        AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                        AppContext.getMessage(GenericI18Enum.BUTTON_NO), new ConfirmDialog.Listener() {
                            @Override
                            public void onClose(ConfirmDialog confirmDialog) {
                                RelatedBugExample ex = new RelatedBugExample();
                                ex.createCriteria().andBugidEqualTo(hostBug.getId()).andRelatedidEqualTo(relatedBug.getId());
                                RelatedBugMapper bugMapper = ApplicationContextUtil.getSpringBean(RelatedBugMapper.class);
                                bugMapper.deleteByExample(ex);
                                UIUtils.removeChildAssociate(toggleBugSummaryField, RemoveInlineComponentMarker.class);
                            }
                        });
            }
        });
        unlinkBtn.setIcon(FontAwesome.UNLINK);
        unlinkBtn.setDescription("Remove relationship");
        unlinkBtn.addStyleName(ValoTheme.BUTTON_ICON_ALIGN_TOP);
        unlinkBtn.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        toggleBugSummaryField.addControl(unlinkBtn);
    }

    @Override
    protected Component initContent() {
        return toggleBugSummaryField;
    }

    @Override
    public Class<? extends SimpleBug> getType() {
        return SimpleBug.class;
    }
}
