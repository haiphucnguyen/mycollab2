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

import com.mycollab.core.utils.FileUtils;
import com.mycollab.spring.AppContextUtil;
import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import org.joda.time.DateTimeZone;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.mycollab.configuration.ApplicationProperties.*;

/**
 * Utility class read mycollab system properties when system starts
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class SiteConfiguration {

    private static SiteConfiguration instance;

    private String siteName;
    private String serverAddress;
    private Locale defaultLocale;
    private String endecryptPassword;
    private String dropboxCallbackUrl;
    private String ggDriveCallbackUrl;
    private String appUrl;

    private String facebookUrl;
    private String twitterUrl;
    private String googleUrl;
    private String linkedinUrl;

    private PullMethod pullMethod;
    private Configuration freemarkerConfiguration;

    public static void loadConfiguration() {
        TimeZone.setDefault(DateTimeZone.UTC.toTimeZone());
        DateTimeZone.setDefault(DateTimeZone.UTC);
        int serverPort = Integer.parseInt(System.getProperty(ApplicationProperties.MYCOLLAB_PORT, "8080"));
        ApplicationProperties.loadProps();
        instance = new SiteConfiguration();

        instance.siteName = ApplicationProperties.getString(SITE_NAME, "MyCollab");
        instance.serverAddress = ApplicationProperties.getString(SERVER_ADDRESS, "localhost");
        String propLocale = ApplicationProperties.getString(DEFAULT_LOCALE, "en_US");
        try {
            instance.defaultLocale = Locale.forLanguageTag(propLocale);
        } catch (Exception e) {
            instance.defaultLocale = Locale.US;
        }

        String pullMethodValue = ApplicationProperties.getString(ApplicationProperties.PULL_METHOD, "push");
        instance.pullMethod = PullMethod.valueOf(pullMethodValue);

        instance.endecryptPassword = ApplicationProperties.getString(BI_ENDECRYPT_PASSWORD, "esofthead321");

        instance.dropboxCallbackUrl = ApplicationProperties.getString(DROPBOX_AUTH_LINK);
        instance.ggDriveCallbackUrl = ApplicationProperties.getString(GOOGLE_DRIVE_LINK);

        instance.facebookUrl = ApplicationProperties.getString(FACEBOOK_URL, "https://www.facebook.com/mycollab2");
        instance.twitterUrl = ApplicationProperties.getString(TWITTER_URL, "https://twitter.com/mycollabdotcom");
        instance.googleUrl = ApplicationProperties.getString(GOOGLE_URL, "https://plus.google.com/u/0/b/112053350736358775306/+Mycollab/about/p/pub");
        instance.linkedinUrl = ApplicationProperties.getString(LINKEDIN_URL, "http://www.linkedin.com/company/mycollab");

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);
        configuration.setDefaultEncoding("UTF-8");
        try {
            List<TemplateLoader> loaders = new ArrayList<>();
            File i18nFolder = new File(FileUtils.getUserFolder(), "i18n");
            File confFolder1 = new File(FileUtils.getUserFolder(), "conf");
            File confFolder2 = new File(FileUtils.getUserFolder(), "src/main/conf");
            if (i18nFolder.exists()) {
                loaders.add(new FileTemplateLoader(i18nFolder));
            }
            if (confFolder1.exists()) {
                loaders.add(new FileTemplateLoader(confFolder1));
            }
            if (confFolder2.exists()) {
                loaders.add(new FileTemplateLoader(confFolder2));
            }
            loaders.add(new ClassTemplateLoader(SiteConfiguration.class.getClassLoader(), ""));
            configuration.setTemplateLoader(new MultiTemplateLoader(loaders.toArray(new TemplateLoader[loaders.size()])));
            instance.freemarkerConfiguration = configuration;
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private static SiteConfiguration getInstance() {
        if (instance == null) {
            loadConfiguration();
        }
        return instance;
    }

    public static String getAppUrl() {
        return getInstance().appUrl;
    }

    public static String getFacebookUrl() {
        return getInstance().facebookUrl;
    }

    public static String getTwitterUrl() {
        return getInstance().twitterUrl;
    }

    public static String getGoogleUrl() {
        return getInstance().googleUrl;
    }

    public static String getLinkedinUrl() {
        return getInstance().linkedinUrl;
    }

    public static String getDefaultSiteName() {
        return getInstance().siteName;
    }

    public static PullMethod getPullMethod() {
        return getInstance().pullMethod;
    }

    public static String getSiteUrl(String subDomain) {
        IDeploymentMode modeService = AppContextUtil.getSpringBean(IDeploymentMode.class);
        return modeService.getSiteUrl(subDomain);
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

    public static String getEnDecryptPassword() {
        return getInstance().endecryptPassword;
    }

    public static String getServerAddress() {
        return getInstance().serverAddress;
    }

    public static Configuration freemarkerConfiguration() {
        return getInstance().freemarkerConfiguration;
    }

    public static Map<String, String> defaultUrls() {
        Map<String, String> defaultUrls = new HashMap<>();
        defaultUrls.put("facebook_url", SiteConfiguration.getFacebookUrl());
        defaultUrls.put("google_url", SiteConfiguration.getGoogleUrl());
        defaultUrls.put("linkedin_url", SiteConfiguration.getLinkedinUrl());
        defaultUrls.put("twitter_url", SiteConfiguration.getTwitterUrl());
        return defaultUrls;
    }

    public enum PullMethod {
        push, pull
    }
}