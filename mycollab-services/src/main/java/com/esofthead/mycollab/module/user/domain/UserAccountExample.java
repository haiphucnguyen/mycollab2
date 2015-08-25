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
package com.esofthead.mycollab.module.user.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ucd")
public class UserAccountExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public UserAccountExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    @SuppressWarnings("ucd")
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andAccountidIsNull() {
            addCriterion("accountId is null");
            return (Criteria) this;
        }

        public Criteria andAccountidIsNotNull() {
            addCriterion("accountId is not null");
            return (Criteria) this;
        }

        public Criteria andAccountidEqualTo(Integer value) {
            addCriterion("accountId =", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotEqualTo(Integer value) {
            addCriterion("accountId <>", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidGreaterThan(Integer value) {
            addCriterion("accountId >", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidGreaterThanOrEqualTo(Integer value) {
            addCriterion("accountId >=", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidLessThan(Integer value) {
            addCriterion("accountId <", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidLessThanOrEqualTo(Integer value) {
            addCriterion("accountId <=", value, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidIn(List<Integer> values) {
            addCriterion("accountId in", values, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotIn(List<Integer> values) {
            addCriterion("accountId not in", values, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidBetween(Integer value1, Integer value2) {
            addCriterion("accountId between", value1, value2, "accountid");
            return (Criteria) this;
        }

        public Criteria andAccountidNotBetween(Integer value1, Integer value2) {
            addCriterion("accountId not between", value1, value2, "accountid");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerIsNull() {
            addCriterion("isAccountOwner is null");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerIsNotNull() {
            addCriterion("isAccountOwner is not null");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerEqualTo(Boolean value) {
            addCriterion("isAccountOwner =", value, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerNotEqualTo(Boolean value) {
            addCriterion("isAccountOwner <>", value, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerGreaterThan(Boolean value) {
            addCriterion("isAccountOwner >", value, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerGreaterThanOrEqualTo(Boolean value) {
            addCriterion("isAccountOwner >=", value, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerLessThan(Boolean value) {
            addCriterion("isAccountOwner <", value, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerLessThanOrEqualTo(Boolean value) {
            addCriterion("isAccountOwner <=", value, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerIn(List<Boolean> values) {
            addCriterion("isAccountOwner in", values, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerNotIn(List<Boolean> values) {
            addCriterion("isAccountOwner not in", values, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerBetween(Boolean value1, Boolean value2) {
            addCriterion("isAccountOwner between", value1, value2, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andIsaccountownerNotBetween(Boolean value1, Boolean value2) {
            addCriterion("isAccountOwner not between", value1, value2, "isaccountowner");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNull() {
            addCriterion("roleId is null");
            return (Criteria) this;
        }

        public Criteria andRoleidIsNotNull() {
            addCriterion("roleId is not null");
            return (Criteria) this;
        }

        public Criteria andRoleidEqualTo(Integer value) {
            addCriterion("roleId =", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotEqualTo(Integer value) {
            addCriterion("roleId <>", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThan(Integer value) {
            addCriterion("roleId >", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("roleId >=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThan(Integer value) {
            addCriterion("roleId <", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidLessThanOrEqualTo(Integer value) {
            addCriterion("roleId <=", value, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidIn(List<Integer> values) {
            addCriterion("roleId in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotIn(List<Integer> values) {
            addCriterion("roleId not in", values, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidBetween(Integer value1, Integer value2) {
            addCriterion("roleId between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRoleidNotBetween(Integer value1, Integer value2) {
            addCriterion("roleId not between", value1, value2, "roleid");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeIsNull() {
            addCriterion("registeredTime is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeIsNotNull() {
            addCriterion("registeredTime is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeEqualTo(Date value) {
            addCriterion("registeredTime =", value, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeNotEqualTo(Date value) {
            addCriterion("registeredTime <>", value, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeGreaterThan(Date value) {
            addCriterion("registeredTime >", value, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("registeredTime >=", value, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeLessThan(Date value) {
            addCriterion("registeredTime <", value, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeLessThanOrEqualTo(Date value) {
            addCriterion("registeredTime <=", value, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeIn(List<Date> values) {
            addCriterion("registeredTime in", values, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeNotIn(List<Date> values) {
            addCriterion("registeredTime not in", values, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeBetween(Date value1, Date value2) {
            addCriterion("registeredTime between", value1, value2, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisteredtimeNotBetween(Date value1, Date value2) {
            addCriterion("registeredTime not between", value1, value2, "registeredtime");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusIsNull() {
            addCriterion("registerStatus is null");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusIsNotNull() {
            addCriterion("registerStatus is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusEqualTo(String value) {
            addCriterion("registerStatus =", value, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusNotEqualTo(String value) {
            addCriterion("registerStatus <>", value, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusGreaterThan(String value) {
            addCriterion("registerStatus >", value, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusGreaterThanOrEqualTo(String value) {
            addCriterion("registerStatus >=", value, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusLessThan(String value) {
            addCriterion("registerStatus <", value, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusLessThanOrEqualTo(String value) {
            addCriterion("registerStatus <=", value, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusLike(String value) {
            addCriterion("registerStatus like", value, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusNotLike(String value) {
            addCriterion("registerStatus not like", value, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusIn(List<String> values) {
            addCriterion("registerStatus in", values, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusNotIn(List<String> values) {
            addCriterion("registerStatus not in", values, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusBetween(String value1, String value2) {
            addCriterion("registerStatus between", value1, value2, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andRegisterstatusNotBetween(String value1, String value2) {
            addCriterion("registerStatus not between", value1, value2, "registerstatus");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeIsNull() {
            addCriterion("lastAccessedTime is null");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeIsNotNull() {
            addCriterion("lastAccessedTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeEqualTo(Date value) {
            addCriterion("lastAccessedTime =", value, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeNotEqualTo(Date value) {
            addCriterion("lastAccessedTime <>", value, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeGreaterThan(Date value) {
            addCriterion("lastAccessedTime >", value, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastAccessedTime >=", value, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeLessThan(Date value) {
            addCriterion("lastAccessedTime <", value, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeLessThanOrEqualTo(Date value) {
            addCriterion("lastAccessedTime <=", value, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeIn(List<Date> values) {
            addCriterion("lastAccessedTime in", values, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeNotIn(List<Date> values) {
            addCriterion("lastAccessedTime not in", values, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeBetween(Date value1, Date value2) {
            addCriterion("lastAccessedTime between", value1, value2, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andLastaccessedtimeNotBetween(Date value1, Date value2) {
            addCriterion("lastAccessedTime not between", value1, value2, "lastaccessedtime");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceIsNull() {
            addCriterion("registrationSource is null");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceIsNotNull() {
            addCriterion("registrationSource is not null");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceEqualTo(String value) {
            addCriterion("registrationSource =", value, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceNotEqualTo(String value) {
            addCriterion("registrationSource <>", value, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceGreaterThan(String value) {
            addCriterion("registrationSource >", value, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceGreaterThanOrEqualTo(String value) {
            addCriterion("registrationSource >=", value, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceLessThan(String value) {
            addCriterion("registrationSource <", value, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceLessThanOrEqualTo(String value) {
            addCriterion("registrationSource <=", value, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceLike(String value) {
            addCriterion("registrationSource like", value, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceNotLike(String value) {
            addCriterion("registrationSource not like", value, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceIn(List<String> values) {
            addCriterion("registrationSource in", values, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceNotIn(List<String> values) {
            addCriterion("registrationSource not in", values, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceBetween(String value1, String value2) {
            addCriterion("registrationSource between", value1, value2, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andRegistrationsourceNotBetween(String value1, String value2) {
            addCriterion("registrationSource not between", value1, value2, "registrationsource");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitIsNull() {
            addCriterion("lastModuleVisit is null");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitIsNotNull() {
            addCriterion("lastModuleVisit is not null");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitEqualTo(String value) {
            addCriterion("lastModuleVisit =", value, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitNotEqualTo(String value) {
            addCriterion("lastModuleVisit <>", value, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitGreaterThan(String value) {
            addCriterion("lastModuleVisit >", value, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitGreaterThanOrEqualTo(String value) {
            addCriterion("lastModuleVisit >=", value, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitLessThan(String value) {
            addCriterion("lastModuleVisit <", value, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitLessThanOrEqualTo(String value) {
            addCriterion("lastModuleVisit <=", value, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitLike(String value) {
            addCriterion("lastModuleVisit like", value, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitNotLike(String value) {
            addCriterion("lastModuleVisit not like", value, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitIn(List<String> values) {
            addCriterion("lastModuleVisit in", values, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitNotIn(List<String> values) {
            addCriterion("lastModuleVisit not in", values, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitBetween(String value1, String value2) {
            addCriterion("lastModuleVisit between", value1, value2, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andLastmodulevisitNotBetween(String value1, String value2) {
            addCriterion("lastModuleVisit not between", value1, value2, "lastmodulevisit");
            return (Criteria) this;
        }

        public Criteria andInviteuserIsNull() {
            addCriterion("inviteUser is null");
            return (Criteria) this;
        }

        public Criteria andInviteuserIsNotNull() {
            addCriterion("inviteUser is not null");
            return (Criteria) this;
        }

        public Criteria andInviteuserEqualTo(String value) {
            addCriterion("inviteUser =", value, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserNotEqualTo(String value) {
            addCriterion("inviteUser <>", value, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserGreaterThan(String value) {
            addCriterion("inviteUser >", value, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserGreaterThanOrEqualTo(String value) {
            addCriterion("inviteUser >=", value, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserLessThan(String value) {
            addCriterion("inviteUser <", value, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserLessThanOrEqualTo(String value) {
            addCriterion("inviteUser <=", value, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserLike(String value) {
            addCriterion("inviteUser like", value, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserNotLike(String value) {
            addCriterion("inviteUser not like", value, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserIn(List<String> values) {
            addCriterion("inviteUser in", values, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserNotIn(List<String> values) {
            addCriterion("inviteUser not in", values, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserBetween(String value1, String value2) {
            addCriterion("inviteUser between", value1, value2, "inviteuser");
            return (Criteria) this;
        }

        public Criteria andInviteuserNotBetween(String value1, String value2) {
            addCriterion("inviteUser not between", value1, value2, "inviteuser");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_user_account
     *
     * @mbggenerated do_not_delete_during_merge Tue Aug 25 14:46:04 ICT 2015
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table s_user_account
     *
     * @mbggenerated Tue Aug 25 14:46:04 ICT 2015
     */
    @SuppressWarnings("ucd")
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}