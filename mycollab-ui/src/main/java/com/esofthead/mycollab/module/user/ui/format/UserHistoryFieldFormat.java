package com.esofthead.mycollab.module.user.ui.format;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.html.FormatUtils;
import com.esofthead.mycollab.module.mail.MailUtils;
import com.esofthead.mycollab.module.user.AccountLinkGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.utils.HistoryFieldFormat;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Img;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.esofthead.mycollab.core.utils.StringUtils.isBlank;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public class UserHistoryFieldFormat implements HistoryFieldFormat {
    private static final Logger LOG = LoggerFactory.getLogger(UserHistoryFieldFormat.class);

    @Override
    public String toString(String value) {
        return toString(value, true, AppContext.getMessage(GenericI18Enum
                .FORM_EMPTY));
    }

    @Override
    public String toString(String value, Boolean displayAsHtml, String msgIfBlank) {
        if (isBlank(value)) {
            return msgIfBlank;
        }

        try {
            UserService userService = ApplicationContextUtil.getSpringBean(UserService.class);
            SimpleUser user = userService.findUserByUserNameInAccount(value, AppContext.getAccountId());
            if (user != null) {
                if (displayAsHtml) {
                    String userAvatarLink = MailUtils.getAvatarLink(user.getAvatarid(), 16);
                    Img img = FormatUtils.newImg("avatar", userAvatarLink);

                    String userLink = AccountLinkGenerator.generatePreviewFullUserLink(
                            MailUtils.getSiteUrl(AppContext.getAccountId()), user.getUsername());

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
