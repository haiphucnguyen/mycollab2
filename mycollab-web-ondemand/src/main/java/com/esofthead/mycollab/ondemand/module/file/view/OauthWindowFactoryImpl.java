package com.esofthead.mycollab.ondemand.module.file.view;

import com.esofthead.mycollab.premium.module.file.view.AbstractCloudDriveOAuthWindow;
import com.esofthead.mycollab.premium.module.file.view.OauthWindowFactory;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
@Component
public class OauthWindowFactoryImpl implements OauthWindowFactory {
    @Override
    public AbstractCloudDriveOAuthWindow newDropBoxAuthWindow() {
        return new DropBoxOAuthWindow();
    }
}
