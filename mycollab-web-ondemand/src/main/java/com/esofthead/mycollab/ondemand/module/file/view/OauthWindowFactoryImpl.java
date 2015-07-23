package com.esofthead.mycollab.ondemand.module.file.view;

import com.esofthead.mycollab.premium.module.file.view.OauthWindowFactory;
import com.vaadin.ui.Window;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Component
public class OauthWindowFactoryImpl implements OauthWindowFactory {
    @Override
    public Window newDropBoxAuthWindow() {
        return new DropBoxOAuthWindow();
    }
}
