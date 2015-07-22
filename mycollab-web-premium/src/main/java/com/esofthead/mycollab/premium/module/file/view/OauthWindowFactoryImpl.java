package com.esofthead.mycollab.premium.module.file.view;

import com.esofthead.mycollab.core.UnsupportedFeatureException;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public class OauthWindowFactoryImpl implements OauthWindowFactory {
    @Override
    public AbstractCloudDriveOAuthWindow newDropBoxAuthWindow() {
        throw new UnsupportedFeatureException("This feature is not supported in this edition");
    }
}
