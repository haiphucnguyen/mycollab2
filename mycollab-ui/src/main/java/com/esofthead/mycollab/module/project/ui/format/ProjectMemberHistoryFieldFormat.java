package com.esofthead.mycollab.module.project.ui.format;

import com.esofthead.mycollab.configuration.StorageFactory;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.html.DivLessFormatter;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkBuilder;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.TooltipHelper;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.utils.HistoryFieldFormat;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;
import com.hp.gagawa.java.elements.Span;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public final class ProjectMemberHistoryFieldFormat implements HistoryFieldFormat {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectMemberHistoryFieldFormat.class);

    @Override
    public String toString(String value) {
        if (StringUtils.isBlank(value)) {
            return new Span().write();
        }

        try {
            UserService userService = ApplicationContextUtil.getSpringBean(UserService.class);
            SimpleUser user = userService.findUserByUserNameInAccount(value, AppContext.getAccountId());
            if (user != null) {
                String uid = UUID.randomUUID().toString();
                Img userAvatar = new Img("", StorageFactory.getInstance().getAvatarPath(user.getAvatarid(), 16));
                A link = new A().setId("tag" + uid).setHref(ProjectLinkBuilder.generateProjectMemberFullLink
                        (CurrentProjectVariables.getProjectId(),
                                user.getUsername())).appendText(StringUtils.trim(user.getDisplayName(), 30, true));
                link.setAttribute("onmouseover", TooltipHelper.userHoverJsFunction(uid, user.getUsername()));
                link.setAttribute("onmouseleave", TooltipHelper.itemMouseLeaveJsFunction(uid));
                return new DivLessFormatter().appendChild(userAvatar, DivLessFormatter.EMPTY_SPACE(), link,
                        DivLessFormatter.EMPTY_SPACE(), TooltipHelper.buildDivTooltipEnable(uid)).write();
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }
        return value;
    }

}
