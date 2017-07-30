package com.mycollab.module.user.ui.format;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.html.FormatUtils;
import com.mycollab.module.mail.MailUtils;
import com.mycollab.module.user.AccountLinkGenerator;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.service.UserService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.formatter.HistoryFieldFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.mycollab.core.utils.StringUtils.isBlank;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class UserHistoryFieldFormat implements HistoryFieldFormat {
    private static final Logger LOG = LoggerFactory.getLogger(UserHistoryFieldFormat.class);

    @Override
    public String toString(String value) {
        return toString(value, true, UserUIContext.getMessage(GenericI18Enum.FORM_EMPTY));
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        if (isBlank(value)) {
            return msgIfBlank;
        }

        try {
            UserService userService = AppContextUtil.getSpringBean(UserService.class);
            SimpleUser user = userService.findUserByUserNameInAccount(value, AppUI.getAccountId());
            if (user != null) {
                if (displayAsHtml) {
                    String userAvatarLink = MailUtils.getAvatarLink(user.getAvatarid(), 16);
                    Img img = FormatUtils.newImg("avatar", userAvatarLink);

                    String userLink = AccountLinkGenerator.generatePreviewFullUserLink(
                            MailUtils.getSiteUrl(AppUI.getAccountId()), user.getUsername());

                    A link = FormatUtils.newA(userLink, user.getDisplayName());
                    return FormatUtils.newLink(img, link).write();
                } else {
                    return user.getDisplayName();
                }
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }
        return value;
    }
}
