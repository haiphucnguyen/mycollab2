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

import com.mycollab.core.MyCollabException;
import com.mycollab.core.arguments.ValuedBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Email configuration of MyCollab
 *
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix = "mail")
public class EmailConfiguration extends ValuedBean implements Cloneable {

    private String host;

    private String user;

    private String password;

    private Integer port = 25;

    private Boolean isTLS = false;
    private Boolean isSSL = false;
    private String notifyEmail;
    private String errorReportEmail;

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public Integer getPort() {
        return port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public boolean isTLS() {
        return isTLS;
    }

    public void setIsTLS(Boolean TLS) {
        isTLS = TLS;
    }

    public boolean isSSL() {
        return isSSL;
    }

    public void setIsSSL(Boolean SSL) {
        isSSL = SSL;
    }

    public EmailConfiguration clone() {
        try {
            return (EmailConfiguration) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new MyCollabException(e);
        }
    }

    public String getNotifyEmail() {
        return notifyEmail;
    }

    public void setNotifyEmail(String notifyEmail) {
        this.notifyEmail = notifyEmail;
    }

    public String getErrorReportEmail() {
        return errorReportEmail;
    }

    public void setErrorReportEmail(String errorReportEmail) {
        this.errorReportEmail = errorReportEmail;
    }
}
