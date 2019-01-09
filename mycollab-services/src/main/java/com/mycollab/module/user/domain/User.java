/*Domain class of table s_user*/
package com.mycollab.module.user.domain;

import com.mycollab.core.arguments.ValuedBean;
import com.mycollab.db.metadata.Column;
import com.mycollab.db.metadata.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_user")
@Alias("User")
public class User extends ValuedBean {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.username
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("username")
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.firstname
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("firstname")
    private String firstname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.lastname
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("lastname")
    private String lastname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.nickname
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("nickname")
    private String nickname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.birthday
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Column("birthday")
    private LocalDate birthday;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.password
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=100, message="Field value is too long")
    @Column("password")
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.email
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=255, message="Field value is too long")
    @Column("email")
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.website
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=255, message="Field value is too long")
    @Column("website")
    private String website;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.registeredTime
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Column("registeredTime")
    private LocalDateTime registeredtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.lastAccessedTime
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Column("lastAccessedTime")
    private LocalDateTime lastaccessedtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.company
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=255, message="Field value is too long")
    @Column("company")
    private String company;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.timezone
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("timezone")
    private String timezone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.language
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("language")
    private String language;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.country
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("country")
    private String country;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.workPhone
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=20, message="Field value is too long")
    @Column("workPhone")
    private String workphone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.homePhone
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=20, message="Field value is too long")
    @Column("homePhone")
    private String homephone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.facebookAccount
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("facebookAccount")
    private String facebookaccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.twitterAccount
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("twitterAccount")
    private String twitteraccount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.skypeContact
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("skypeContact")
    private String skypecontact;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.avatarId
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=90, message="Field value is too long")
    @Column("avatarId")
    private String avatarid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.status
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("status")
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.requestAd
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Column("requestAd")
    private Boolean requestad;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.YYMMDDFormat
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("YYMMDDFormat")
    private String yymmddformat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.humanDateFormat
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("humanDateFormat")
    private String humandateformat;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_user.MMDDFormat
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    @Length(max=45, message="Field value is too long")
    @Column("MMDDFormat")
    private String mmddformat;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (!obj.getClass().isAssignableFrom(getClass())) { return false;}
        User item = (User)obj;
        return new EqualsBuilder().append(username, item.username).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1357, 413).append(username).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.username
     *
     * @return the value of s_user.username
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.lastname
     *
     * @return the value of s_user.lastname
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.birthday
     *
     * @return the value of s_user.birthday
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.birthday
     *
     * @param birthday the value for s_user.birthday
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.password
     *
     * @return the value of s_user.password
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public LocalDateTime getRegisteredtime() {
        return registeredtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.registeredTime
     *
     * @param registeredtime the value for s_user.registeredTime
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public void setRegisteredtime(LocalDateTime registeredtime) {
        this.registeredtime = registeredtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.lastAccessedTime
     *
     * @return the value of s_user.lastAccessedTime
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public LocalDateTime getLastaccessedtime() {
        return lastaccessedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.lastAccessedTime
     *
     * @param lastaccessedtime the value for s_user.lastAccessedTime
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public void setLastaccessedtime(LocalDateTime lastaccessedtime) {
        this.lastaccessedtime = lastaccessedtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.company
     *
     * @return the value of s_user.company
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
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
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public void setRequestad(Boolean requestad) {
        this.requestad = requestad;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.YYMMDDFormat
     *
     * @return the value of s_user.YYMMDDFormat
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public String getYymmddformat() {
        return yymmddformat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.YYMMDDFormat
     *
     * @param yymmddformat the value for s_user.YYMMDDFormat
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public void setYymmddformat(String yymmddformat) {
        this.yymmddformat = yymmddformat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.humanDateFormat
     *
     * @return the value of s_user.humanDateFormat
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public String getHumandateformat() {
        return humandateformat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.humanDateFormat
     *
     * @param humandateformat the value for s_user.humanDateFormat
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public void setHumandateformat(String humandateformat) {
        this.humandateformat = humandateformat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_user.MMDDFormat
     *
     * @return the value of s_user.MMDDFormat
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public String getMmddformat() {
        return mmddformat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_user.MMDDFormat
     *
     * @param mmddformat the value for s_user.MMDDFormat
     *
     * @mbg.generated Tue Jan 08 20:44:15 CST 2019
     */
    public void setMmddformat(String mmddformat) {
        this.mmddformat = mmddformat;
    }

    public enum Field {
        username,
        firstname,
        lastname,
        nickname,
        birthday,
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
        requestad,
        yymmddformat,
        humandateformat,
        mmddformat;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}