package com.esofthead.mycollab.config;

//import java.util.Properties;
import java.util.Properties;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.EncryptableProperties;

public class CommonConfig {

    private static final String CLASS_NAME = "db.driverClassName";
    private static final String URL = "db.url";
    private static final String USER_NAME = "db.username";
    private static final String PASSWORD = "db.password";
    private static final String CONFIG_FILE = "resources.properties";
    private static final String DECRYPT_PASS = "esofthead321";
    private String className;
    private String url;
    private String userName;
    private String password;

    public String getClassName() {
        return className;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    private CommonConfig(String className, String url, String userName,
            String password) {
        this.className = className;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    public static final CommonConfig loadConfig() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(DECRYPT_PASS);
        Properties properties = new EncryptableProperties(encryptor);
        try {
            properties.load(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(CONFIG_FILE));
            String className = properties.getProperty(CLASS_NAME);
            String url = properties.getProperty(URL);
            String userName = properties.getProperty(USER_NAME);
            String password = properties.getProperty(PASSWORD);

            return new CommonConfig(className, url, userName, password);
        } catch (Exception e) {
        }
        return null;
    }
}
