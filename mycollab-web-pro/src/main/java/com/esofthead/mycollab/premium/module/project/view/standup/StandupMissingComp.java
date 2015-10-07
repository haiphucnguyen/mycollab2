package com.esofthead.mycollab.premium.module.project.view.standup;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.project.i18n.StandupI18nEnum;
import com.esofthead.mycollab.module.project.service.StandupReportService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class StandupMissingComp extends MVerticalLayout {
    private static final long serialVersionUID = 5332956503787026253L;

    private VerticalLayout bodyWrap;
    private Label headerLbl;

    public StandupMissingComp() {
        this.withSpacing(false).withMargin(false);
        headerLbl = new Label(AppContext.getMessage(StandupI18nEnum.STANDUP_MEMBER_NOT_REPORT));
        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(false, true, false, true)).
                withHeight("34px").withWidth("100%").with(headerLbl).
                withAlign(headerLbl, Alignment.MIDDLE_LEFT).withStyleName("panel-header");

        bodyWrap = new MVerticalLayout().withStyleName("panel-body");
        this.with(header, bodyWrap).withWidth("100%");
    }

    public void search(Date date) {
        bodyWrap.removeAllComponents();
        StandupReportService searchService = ApplicationContextUtil.getSpringBean(StandupReportService.class);
        List<SimpleUser> someGuys = searchService.findUsersNotDoReportYet(
                CurrentProjectVariables.getProjectId(), date, AppContext.getAccountId());
        if (someGuys.size() == 0) {
            bodyWrap.addComponent(new Label(AppContext.getMessage(GenericI18Enum.EXT_NO_ITEM)));
        } else {
            Iterator<SimpleUser> iterator = someGuys.iterator();
            while (iterator.hasNext()) {
                SimpleUser user = iterator.next();
                Label rowUser = new Label(buildMemberLink(user), ContentMode.HTML);
                bodyWrap.addComponent(rowUser);
            }
        }
    }

    private String buildMemberLink(SimpleUser user) {
        String uid = UUID.randomUUID().toString();
        DivLessFormatter div = new DivLessFormatter();
        Img userAvatar = new Img("", StorageFactory.getInstance().getAvatarPath(user.getAvatarid(), 16));
        A userLink = new A().setId("tag" + uid).setHref(ProjectLinkBuilder.generateProjectMemberFullLink(
                CurrentProjectVariables.getProjectId(), user.getUsername()));

        userLink.setAttribute("onmouseover", TooltipHelper.userHoverJsFunction(uid, user.getUsername()));
        userLink.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction(uid));
        userLink.appendText(StringUtils.trim(user.getDisplayName(), 30, true));

        div.appendChild(userAvatar, DivLessFormatter.EMPTY_SPACE(), userLink, DivLessFormatter.EMPTY_SPACE(),
                TooltipHelper.buildDivTooltipEnable(uid));
        return div.write();
    }

}
