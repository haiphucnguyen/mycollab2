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
/*Domain class of table s_account_theme*/
package com.esofthead.mycollab.module.user.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_account_theme")
public class AccountTheme extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.id
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.topMenuBg
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("topMenuBg")
    private String topmenubg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.topMenuBgSelected
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("topMenuBgSelected")
    private String topmenubgselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.topMenuText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("topMenuText")
    private String topmenutext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.topMenuTextSelected
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("topMenuTextSelected")
    private String topmenutextselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.tabsheetBg
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("tabsheetBg")
    private String tabsheetbg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.tabsheetBgSelected
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("tabsheetBgSelected")
    private String tabsheetbgselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.tabsheetText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("tabsheetText")
    private String tabsheettext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.tabsheetTextSelected
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("tabsheetTextSelected")
    private String tabsheettextselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.vTabsheetBg
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("vTabsheetBg")
    private String vtabsheetbg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.vTabsheetBgSelected
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("vTabsheetBgSelected")
    private String vtabsheetbgselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.vTabsheetText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("vTabsheetText")
    private String vtabsheettext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.vTabsheetTextSelected
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("vTabsheetTextSelected")
    private String vtabsheettextselected;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.actionBtn
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("actionBtn")
    private String actionbtn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.actionBtnText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("actionBtnText")
    private String actionbtntext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.optionBtn
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("optionBtn")
    private String optionbtn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.optionBtnText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("optionBtnText")
    private String optionbtntext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.dangerBtn
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("dangerBtn")
    private String dangerbtn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.dangerBtnText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Length(max=6, message="Field value is too long")
    @Column("dangerBtnText")
    private String dangerbtntext;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.isDefault
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Column("isDefault")
    private Boolean isdefault;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_account_theme.sAccountId
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    @Column("sAccountId")
    private Integer saccountid;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        AccountTheme item = (AccountTheme)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(515, 1983).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.id
     *
     * @return the value of s_account_theme.id
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.topMenuBg
     *
     * @return the value of s_account_theme.topMenuBg
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setVtabsheettextselected(String vtabsheettextselected) {
        this.vtabsheettextselected = vtabsheettextselected;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.actionBtn
     *
     * @return the value of s_account_theme.actionBtn
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public String getActionbtn() {
        return actionbtn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.actionBtn
     *
     * @param actionbtn the value for s_account_theme.actionBtn
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setActionbtn(String actionbtn) {
        this.actionbtn = actionbtn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.actionBtnText
     *
     * @return the value of s_account_theme.actionBtnText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public String getActionbtntext() {
        return actionbtntext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.actionBtnText
     *
     * @param actionbtntext the value for s_account_theme.actionBtnText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setActionbtntext(String actionbtntext) {
        this.actionbtntext = actionbtntext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.optionBtn
     *
     * @return the value of s_account_theme.optionBtn
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public String getOptionbtn() {
        return optionbtn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.optionBtn
     *
     * @param optionbtn the value for s_account_theme.optionBtn
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setOptionbtn(String optionbtn) {
        this.optionbtn = optionbtn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.optionBtnText
     *
     * @return the value of s_account_theme.optionBtnText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public String getOptionbtntext() {
        return optionbtntext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.optionBtnText
     *
     * @param optionbtntext the value for s_account_theme.optionBtnText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setOptionbtntext(String optionbtntext) {
        this.optionbtntext = optionbtntext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.dangerBtn
     *
     * @return the value of s_account_theme.dangerBtn
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public String getDangerbtn() {
        return dangerbtn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.dangerBtn
     *
     * @param dangerbtn the value for s_account_theme.dangerBtn
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setDangerbtn(String dangerbtn) {
        this.dangerbtn = dangerbtn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.dangerBtnText
     *
     * @return the value of s_account_theme.dangerBtnText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public String getDangerbtntext() {
        return dangerbtntext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.dangerBtnText
     *
     * @param dangerbtntext the value for s_account_theme.dangerBtnText
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setDangerbtntext(String dangerbtntext) {
        this.dangerbtntext = dangerbtntext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.isDefault
     *
     * @return the value of s_account_theme.isDefault
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public Boolean getIsdefault() {
        return isdefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_account_theme.isDefault
     *
     * @param isdefault the value for s_account_theme.isDefault
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setIsdefault(Boolean isdefault) {
        this.isdefault = isdefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_account_theme.sAccountId
     *
     * @return the value of s_account_theme.sAccountId
     *
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
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
     * @mbggenerated Fri Oct 09 11:22:02 ICT 2015
     */
    public void setSaccountid(Integer saccountid) {
        this.saccountid = saccountid;
    }

    public enum Field {
        id,
        topmenubg,
        topmenubgselected,
        topmenutext,
        topmenutextselected,
        tabsheetbg,
        tabsheetbgselected,
        tabsheettext,
        tabsheettextselected,
        vtabsheetbg,
        vtabsheetbgselected,
        vtabsheettext,
        vtabsheettextselected,
        actionbtn,
        actionbtntext,
        optionbtn,
        optionbtntext,
        dangerbtn,
        dangerbtntext,
        isdefault,
        saccountid;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}