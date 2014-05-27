/*Domain class of table s_account_theme*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;

@com.esofthead.mycollab.core.db.metadata.Table("s_account_theme")
public class AccountTheme extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.id
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.sAccountId
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @com.esofthead.mycollab.core.db.metadata.Column("sAccountId")
    private Integer saccountid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.logoPath
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=255, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("logoPath")
    private String logopath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.topMenuBg
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("topMenuBg")
    private String topmenubg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.topMenuBgSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("topMenuBgSelected")
    private String topmenubgselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.topMenuText
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("topMenuText")
    private String topmenutext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.topMenuTextSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("topMenuTextSelected")
    private String topmenutextselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.tabsheetBg
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("tabsheetBg")
    private String tabsheetbg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.tabsheetBgSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("tabsheetBgSelected")
    private String tabsheetbgselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.tabsheetText
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("tabsheetText")
    private String tabsheettext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.tabsheetTextSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("tabsheetTextSelected")
    private String tabsheettextselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.vTabsheetBg
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("vTabsheetBg")
    private String vtabsheetbg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.vTabsheetBgSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("vTabsheetBgSelected")
    private String vtabsheetbgselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.vTabsheetText
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("vTabsheetText")
    private String vtabsheettext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.vTabsheetTextSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    @org.hibernate.validator.constraints.Length(max=6, message="Field value is too long")
    @com.esofthead.mycollab.core.db.metadata.Column("vTabsheetTextSelected")
    private String vtabsheettextselected;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.id
     *
     * @return the value of s_account_theme.id
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.id
     *
     * @param id the value for s_account_theme.id
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.sAccountId
     *
     * @return the value of s_account_theme.sAccountId
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public Integer getSaccountid() {
        return saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.sAccountId
     *
     * @param saccountid the value for s_account_theme.sAccountId
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.logoPath
     *
     * @return the value of s_account_theme.logoPath
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getLogopath() {
        return logopath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.logoPath
     *
     * @param logopath the value for s_account_theme.logoPath
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setLogopath(String logopath) {
        this.logopath = logopath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.topMenuBg
     *
     * @return the value of s_account_theme.topMenuBg
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getTopmenubg() {
        return topmenubg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.topMenuBg
     *
     * @param topmenubg the value for s_account_theme.topMenuBg
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setTopmenubg(String topmenubg) {
        this.topmenubg = topmenubg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.topMenuBgSelected
     *
     * @return the value of s_account_theme.topMenuBgSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getTopmenubgselected() {
        return topmenubgselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.topMenuBgSelected
     *
     * @param topmenubgselected the value for s_account_theme.topMenuBgSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setTopmenubgselected(String topmenubgselected) {
        this.topmenubgselected = topmenubgselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.topMenuText
     *
     * @return the value of s_account_theme.topMenuText
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getTopmenutext() {
        return topmenutext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.topMenuText
     *
     * @param topmenutext the value for s_account_theme.topMenuText
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setTopmenutext(String topmenutext) {
        this.topmenutext = topmenutext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.topMenuTextSelected
     *
     * @return the value of s_account_theme.topMenuTextSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getTopmenutextselected() {
        return topmenutextselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.topMenuTextSelected
     *
     * @param topmenutextselected the value for s_account_theme.topMenuTextSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setTopmenutextselected(String topmenutextselected) {
        this.topmenutextselected = topmenutextselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.tabsheetBg
     *
     * @return the value of s_account_theme.tabsheetBg
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getTabsheetbg() {
        return tabsheetbg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.tabsheetBg
     *
     * @param tabsheetbg the value for s_account_theme.tabsheetBg
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setTabsheetbg(String tabsheetbg) {
        this.tabsheetbg = tabsheetbg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.tabsheetBgSelected
     *
     * @return the value of s_account_theme.tabsheetBgSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getTabsheetbgselected() {
        return tabsheetbgselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.tabsheetBgSelected
     *
     * @param tabsheetbgselected the value for s_account_theme.tabsheetBgSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setTabsheetbgselected(String tabsheetbgselected) {
        this.tabsheetbgselected = tabsheetbgselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.tabsheetText
     *
     * @return the value of s_account_theme.tabsheetText
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getTabsheettext() {
        return tabsheettext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.tabsheetText
     *
     * @param tabsheettext the value for s_account_theme.tabsheetText
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setTabsheettext(String tabsheettext) {
        this.tabsheettext = tabsheettext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.tabsheetTextSelected
     *
     * @return the value of s_account_theme.tabsheetTextSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getTabsheettextselected() {
        return tabsheettextselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.tabsheetTextSelected
     *
     * @param tabsheettextselected the value for s_account_theme.tabsheetTextSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setTabsheettextselected(String tabsheettextselected) {
        this.tabsheettextselected = tabsheettextselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.vTabsheetBg
     *
     * @return the value of s_account_theme.vTabsheetBg
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getVtabsheetbg() {
        return vtabsheetbg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.vTabsheetBg
     *
     * @param vtabsheetbg the value for s_account_theme.vTabsheetBg
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setVtabsheetbg(String vtabsheetbg) {
        this.vtabsheetbg = vtabsheetbg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.vTabsheetBgSelected
     *
     * @return the value of s_account_theme.vTabsheetBgSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getVtabsheetbgselected() {
        return vtabsheetbgselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.vTabsheetBgSelected
     *
     * @param vtabsheetbgselected the value for s_account_theme.vTabsheetBgSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setVtabsheetbgselected(String vtabsheetbgselected) {
        this.vtabsheetbgselected = vtabsheetbgselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.vTabsheetText
     *
     * @return the value of s_account_theme.vTabsheetText
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getVtabsheettext() {
        return vtabsheettext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.vTabsheetText
     *
     * @param vtabsheettext the value for s_account_theme.vTabsheetText
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setVtabsheettext(String vtabsheettext) {
        this.vtabsheettext = vtabsheettext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.vTabsheetTextSelected
     *
     * @return the value of s_account_theme.vTabsheetTextSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public String getVtabsheettextselected() {
        return vtabsheettextselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.vTabsheetTextSelected
     *
     * @param vtabsheettextselected the value for s_account_theme.vTabsheetTextSelected
     *
     * @mbggenerated Tue May 27 09:33:57 ICT 2014
     */
    public void setVtabsheettextselected(String vtabsheettextselected) {
        this.vtabsheettextselected = vtabsheettextselected;
    }
}