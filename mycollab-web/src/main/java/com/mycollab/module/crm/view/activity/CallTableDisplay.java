package com.mycollab.module.crm.view.activity;

import com.mycollab.common.TableViewField;
import com.mycollab.module.crm.CrmLinkBuilder;
import com.mycollab.module.crm.domain.SimpleCall;
import com.mycollab.module.crm.domain.criteria.CallSearchCriteria;
import com.mycollab.module.crm.i18n.CallI18nEnum;
import com.mycollab.module.crm.i18n.OptionI18nEnum.CallStatus;
import com.mycollab.module.crm.service.CallService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.LabelLink;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.table.DefaultPagedBeanTable;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Label;
import org.vaadin.viritin.button.MButton;

import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 1.0.0
 */
public class CallTableDisplay extends DefaultPagedBeanTable<CallService, CallSearchCriteria, SimpleCall> {
    private static final long serialVersionUID = 1L;

    public CallTableDisplay(TableViewField requireColumn, List<TableViewField> displayColumns) {
        super(AppContextUtil.getSpringBean(CallService.class), SimpleCall.class, requireColumn, displayColumns);

        this.addGeneratedColumn("subject", (source, itemId, columnId) -> {
            final SimpleCall call = getBeanByIndex(itemId);

            LabelLink b = new LabelLink(call.getSubject(), CrmLinkBuilder.generateCallPreviewLinkFul(call.getId()));
            if (CallStatus.Held.name().equals(call.getStatus())) {
                b.addStyleName(WebThemes.LINK_COMPLETED);
            }
            return b;
        });

        this.addGeneratedColumn("isClosed", (source, itemId, columnId) -> {
            final SimpleCall call = getBeanByIndex(itemId);
            MButton b = new MButton("", clickEvent -> fireTableEvent(new TableClickEvent(CallTableDisplay.this, call, "isClosed")))
                    .withIcon(FontAwesome.TRASH_O).withStyleName(WebThemes.BUTTON_LINK);
            b.setDescription(UserUIContext.getMessage(CallI18nEnum.OPT_CLOSE_THIS_CALL));
            return b;
        });

        this.addGeneratedColumn("startdate", (source, itemId, columnId) -> {
            final SimpleCall call = getBeanByIndex(itemId);
            return new Label(UserUIContext.formatDateTime(call.getStartdate()));
        });

        this.addGeneratedColumn("status", (source, itemId, columnId) -> {
            final SimpleCall call = getBeanByIndex(itemId);
            return ELabel.i18n(call.getStatus(), CallStatus.class);
        });
    }
}
