/**
 * This file is part of mycollab-config.
 *
 * mycollab-config is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-config is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-config.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.configuration;

import com.mycollab.spring.AppContextUtil;
import org.joda.time.DateTimeZone;

import java.util.Locale;
import java.util.TimeZone;

import static com.mycollab.configuration.ApplicationProperties.*;

/**
 * Utility class read mycollab system properties when system starts
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SiteConfiguration {

    private static SiteConfiguration instance;

    private Locale defaultLocale;

    private String dropboxCallbackUrl;
    private String ggDriveCallbackUrl;

    private PullMethod pullMethod;

    public static void loadConfiguration() {
        TimeZone.setDefault(DateTimeZone.UTC.toTimeZone());
        DateTimeZone.setDefault(DateTimeZone.UTC);
        ApplicationProperties.loadProps();
        instance = new SiteConfiguration();

        String propLocale = ApplicationProperties.getString(DEFAULT_LOCALE, "en_US");
        try {
            instance.defaultLocale = Locale.forLanguageTag(propLocale);
        } catch (Exception e) {
            instance.defaultLocale = Locale.US;
        }

        String pullMethodValue = ApplicationProperties.getString(ApplicationProperties.PULL_METHOD, "push");
        instance.pullMethod = PullMethod.valueOf(pullMethodValue);


        instance.dropboxCallbackUrl = ApplicationProperties.getString(DROPBOX_AUTH_LINK);
        instance.ggDriveCallbackUrl = ApplicationProperties.getString(GOOGLE_DRIVE_LINK);
    }

    private static SiteConfiguration getInstance() {
        if (instance == null) {
            loadConfiguration();
        }
        return instance;
    }

    public static PullMethod getPullMethod() {
        return getInstance().pullMethod;
    }

    public static boolean isDemandEdition() {
        IDeploymentMode modeService = AppContextUtil.getSpringBean(IDeploymentMode.class);
        return modeService.isDemandEdition();
    }

    public static boolean isCommunityEdition() {
        IDeploymentMode modeService = AppContextUtil.getSpringBean(IDeploymentMode.class);
        return modeService.isCommunityEdition();
    }

    public static Locale getDefaultLocale() {
        return instance.defaultLocale;
    }

    public static String getDropboxCallbackUrl() {
        return getInstance().dropboxCallbackUrl;
    }

    public static String getGGDriveCallbackUrl() {
        return getInstance().ggDriveCallbackUrl;
    }

    public enum PullMethod {
        push, pull
    }
}