/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
/*Domain class of table s_user*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import java.util.Date;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_user")
public class User extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.username
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.firstname
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("firstname")
    private String firstname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.middlename
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("middlename")
    private String middlename;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.lastname
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("lastname")
    private String lastname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.nickname
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("nickname")
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.dateofbirth
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Column("dateofbirth")
    private Date dateofbirth;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.password
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=100, message="Field value is too long")
    @Column("password")
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.email
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("email")
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.website
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("website")
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.registeredTime
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Column("registeredTime")
    private Date registeredtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.lastAccessedTime
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Column("lastAccessedTime")
    private Date lastaccessedtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.company
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=255, message="Field value is too long")
    @Column("company")
    private String company;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.timezone
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("timezone")
    private String timezone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.language
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("language")
    private String language;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.country
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("country")
    private String country;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.workPhone
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=20, message="Field value is too long")
    @Column("workPhone")
    private String workphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.homePhone
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=20, message="Field value is too long")
    @Column("homePhone")
    private String homephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.facebookAccount
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("facebookAccount")
    private String facebookaccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.twitterAccount
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("twitterAccount")
    private String twitteraccount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.skypeContact
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("skypeContact")
    private String skypecontact;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.avatarId
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=90, message="Field value is too long")
    @Column("avatarId")
    private String avatarid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.status
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.requestAd
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    @Column("requestAd")
    private Boolean requestad;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        User item = (User)obj;
        return new EqualsBuilder().append(username, item.username).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1329, 1475).append(username).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.username
     *
     * @return the value of s_user.username
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.username
     *
     * @param username the value for s_user.username
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.firstname
     *
     * @return the value of s_user.firstname
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.firstname
     *
     * @param firstname the value for s_user.firstname
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.middlename
     *
     * @return the value of s_user.middlename
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.middlename
     *
     * @param middlename the value for s_user.middlename
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.lastname
     *
     * @return the value of s_user.lastname
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.lastname
     *
     * @param lastname the value for s_user.lastname
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.nickname
     *
     * @return the value of s_user.nickname
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.nickname
     *
     * @param nickname the value for s_user.nickname
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.dateofbirth
     *
     * @return the value of s_user.dateofbirth
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public Date getDateofbirth() {
        return dateofbirth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.dateofbirth
     *
     * @param dateofbirth the value for s_user.dateofbirth
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setDateofbirth(Date dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.password
     *
     * @return the value of s_user.password
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.password
     *
     * @param password the value for s_user.password
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.email
     *
     * @return the value of s_user.email
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.email
     *
     * @param email the value for s_user.email
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.website
     *
     * @return the value of s_user.website
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.website
     *
     * @param website the value for s_user.website
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.registeredTime
     *
     * @return the value of s_user.registeredTime
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public Date getRegisteredtime() {
        return registeredtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.registeredTime
     *
     * @param registeredtime the value for s_user.registeredTime
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setRegisteredtime(Date registeredtime) {
        this.registeredtime = registeredtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.lastAccessedTime
     *
     * @return the value of s_user.lastAccessedTime
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public Date getLastaccessedtime() {
        return lastaccessedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.lastAccessedTime
     *
     * @param lastaccessedtime the value for s_user.lastAccessedTime
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setLastaccessedtime(Date lastaccessedtime) {
        this.lastaccessedtime = lastaccessedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.company
     *
     * @return the value of s_user.company
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getCompany() {
        return company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.company
     *
     * @param company the value for s_user.company
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.timezone
     *
     * @return the value of s_user.timezone
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getTimezone() {
        return timezone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.timezone
     *
     * @param timezone the value for s_user.timezone
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.language
     *
     * @return the value of s_user.language
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getLanguage() {
        return language;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.language
     *
     * @param language the value for s_user.language
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.country
     *
     * @return the value of s_user.country
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.country
     *
     * @param country the value for s_user.country
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.workPhone
     *
     * @return the value of s_user.workPhone
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getWorkphone() {
        return workphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.workPhone
     *
     * @param workphone the value for s_user.workPhone
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.homePhone
     *
     * @return the value of s_user.homePhone
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getHomephone() {
        return homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.homePhone
     *
     * @param homephone the value for s_user.homePhone
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.facebookAccount
     *
     * @return the value of s_user.facebookAccount
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getFacebookaccount() {
        return facebookaccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.facebookAccount
     *
     * @param facebookaccount the value for s_user.facebookAccount
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setFacebookaccount(String facebookaccount) {
        this.facebookaccount = facebookaccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.twitterAccount
     *
     * @return the value of s_user.twitterAccount
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getTwitteraccount() {
        return twitteraccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.twitterAccount
     *
     * @param twitteraccount the value for s_user.twitterAccount
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setTwitteraccount(String twitteraccount) {
        this.twitteraccount = twitteraccount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.skypeContact
     *
     * @return the value of s_user.skypeContact
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getSkypecontact() {
        return skypecontact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.skypeContact
     *
     * @param skypecontact the value for s_user.skypeContact
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setSkypecontact(String skypecontact) {
        this.skypecontact = skypecontact;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.avatarId
     *
     * @return the value of s_user.avatarId
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getAvatarid() {
        return avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.avatarId
     *
     * @param avatarid the value for s_user.avatarId
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setAvatarid(String avatarid) {
        this.avatarid = avatarid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.status
     *
     * @return the value of s_user.status
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.status
     *
     * @param status the value for s_user.status
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.requestAd
     *
     * @return the value of s_user.requestAd
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public Boolean getRequestad() {
        return requestad;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.requestAd
     *
     * @param requestad the value for s_user.requestAd
     *
     * @mbggenerated Wed Oct 28 16:06:50 ICT 2015
     */
    public void setRequestad(Boolean requestad) {
        this.requestad = requestad;
    }

    public enum Field {
        username,
        firstname,
        middlename,
        lastname,
        nickname,
        dateofbirth,
        password,
        email,
        website,
        registeredtime,
        lastaccessedtime,
        company,
        timezone,
        language,
        country,
        workphone,
        homephone,
        facebookaccount,
        twitteraccount,
        skypecontact,
        avatarid,
        status,
        requestad;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}