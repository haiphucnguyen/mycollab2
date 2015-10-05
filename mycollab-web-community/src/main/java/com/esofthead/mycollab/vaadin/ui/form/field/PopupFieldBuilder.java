package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.FontIcon;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.1.4
 */
public class PopupFieldBuilder {
    private String captionHtml;
    private String description = "Edit";

    public PopupFieldBuilder withCaptionAndIcon(FontIcon icon, String caption) {
        captionHtml = icon.getHtml() + " " + StringUtils.trim(caption, 20, true);
        return this;
    }

    public PopupFieldBuilder withCaption(String caption) {
        this.captionHtml = caption;
        return this;
    }

    public PopupFieldBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public PopupView build() {
        final PopupView view = new PopupView(new PopupView.Content() {
            @Override
            public String getMinimizedValueAsHTML() {
                return captionHtml;
            }

            @Override
            public Component getPopupComponent() {
                MVerticalLayout layout = new MVerticalLayout();
                layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
                Label infoLbl =  new Label(AppContext.getMessage(GenericI18Enum.NOTIFICATION_FEATURE_NOT_AVAILABLE_IN_VERSION));

                Button requestFeatureBtn = new Button("Request the premium edition");
                requestFeatureBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
                BrowserWindowOpener opener = new BrowserWindowOpener("mailto:support@mycollab.com");
                opener.extend(requestFeatureBtn);

                layout.with(infoLbl, requestFeatureBtn);
                return layout;
            }
        });
        view.setDescription(description);
        view.setStyleName("block-popupedit");
        return view;
    }
}
