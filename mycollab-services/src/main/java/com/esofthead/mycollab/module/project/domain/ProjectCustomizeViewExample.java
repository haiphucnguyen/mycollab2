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
package com.esofthead.mycollab.module.project.domain;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ucd")
public class ProjectCustomizeViewExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    public ProjectCustomizeViewExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
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
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
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

        public Criteria andProjectidIsNull() {
            addCriterion("projectId is null");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNotNull() {
            addCriterion("projectId is not null");
            return (Criteria) this;
        }

        public Criteria andProjectidEqualTo(Integer value) {
            addCriterion("projectId =", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotEqualTo(Integer value) {
            addCriterion("projectId <>", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThan(Integer value) {
            addCriterion("projectId >", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThanOrEqualTo(Integer value) {
            addCriterion("projectId >=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThan(Integer value) {
            addCriterion("projectId <", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThanOrEqualTo(Integer value) {
            addCriterion("projectId <=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidIn(List<Integer> values) {
            addCriterion("projectId in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotIn(List<Integer> values) {
            addCriterion("projectId not in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidBetween(Integer value1, Integer value2) {
            addCriterion("projectId between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotBetween(Integer value1, Integer value2) {
            addCriterion("projectId not between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageIsNull() {
            addCriterion("displayMessage is null");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageIsNotNull() {
            addCriterion("displayMessage is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageEqualTo(Boolean value) {
            addCriterion("displayMessage =", value, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageNotEqualTo(Boolean value) {
            addCriterion("displayMessage <>", value, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageGreaterThan(Boolean value) {
            addCriterion("displayMessage >", value, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayMessage >=", value, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageLessThan(Boolean value) {
            addCriterion("displayMessage <", value, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageLessThanOrEqualTo(Boolean value) {
            addCriterion("displayMessage <=", value, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageIn(List<Boolean> values) {
            addCriterion("displayMessage in", values, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageNotIn(List<Boolean> values) {
            addCriterion("displayMessage not in", values, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageBetween(Boolean value1, Boolean value2) {
            addCriterion("displayMessage between", value1, value2, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymessageNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayMessage not between", value1, value2, "displaymessage");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneIsNull() {
            addCriterion("displayMilestone is null");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneIsNotNull() {
            addCriterion("displayMilestone is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneEqualTo(Boolean value) {
            addCriterion("displayMilestone =", value, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneNotEqualTo(Boolean value) {
            addCriterion("displayMilestone <>", value, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneGreaterThan(Boolean value) {
            addCriterion("displayMilestone >", value, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayMilestone >=", value, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneLessThan(Boolean value) {
            addCriterion("displayMilestone <", value, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneLessThanOrEqualTo(Boolean value) {
            addCriterion("displayMilestone <=", value, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneIn(List<Boolean> values) {
            addCriterion("displayMilestone in", values, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneNotIn(List<Boolean> values) {
            addCriterion("displayMilestone not in", values, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneBetween(Boolean value1, Boolean value2) {
            addCriterion("displayMilestone between", value1, value2, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaymilestoneNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayMilestone not between", value1, value2, "displaymilestone");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskIsNull() {
            addCriterion("displayTask is null");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskIsNotNull() {
            addCriterion("displayTask is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskEqualTo(Boolean value) {
            addCriterion("displayTask =", value, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskNotEqualTo(Boolean value) {
            addCriterion("displayTask <>", value, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskGreaterThan(Boolean value) {
            addCriterion("displayTask >", value, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayTask >=", value, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskLessThan(Boolean value) {
            addCriterion("displayTask <", value, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskLessThanOrEqualTo(Boolean value) {
            addCriterion("displayTask <=", value, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskIn(List<Boolean> values) {
            addCriterion("displayTask in", values, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskNotIn(List<Boolean> values) {
            addCriterion("displayTask not in", values, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskBetween(Boolean value1, Boolean value2) {
            addCriterion("displayTask between", value1, value2, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaytaskNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayTask not between", value1, value2, "displaytask");
            return (Criteria) this;
        }

        public Criteria andDisplaybugIsNull() {
            addCriterion("displayBug is null");
            return (Criteria) this;
        }

        public Criteria andDisplaybugIsNotNull() {
            addCriterion("displayBug is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaybugEqualTo(Boolean value) {
            addCriterion("displayBug =", value, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaybugNotEqualTo(Boolean value) {
            addCriterion("displayBug <>", value, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaybugGreaterThan(Boolean value) {
            addCriterion("displayBug >", value, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaybugGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayBug >=", value, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaybugLessThan(Boolean value) {
            addCriterion("displayBug <", value, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaybugLessThanOrEqualTo(Boolean value) {
            addCriterion("displayBug <=", value, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaybugIn(List<Boolean> values) {
            addCriterion("displayBug in", values, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaybugNotIn(List<Boolean> values) {
            addCriterion("displayBug not in", values, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaybugBetween(Boolean value1, Boolean value2) {
            addCriterion("displayBug between", value1, value2, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaybugNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayBug not between", value1, value2, "displaybug");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupIsNull() {
            addCriterion("displayStandup is null");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupIsNotNull() {
            addCriterion("displayStandup is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupEqualTo(Boolean value) {
            addCriterion("displayStandup =", value, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupNotEqualTo(Boolean value) {
            addCriterion("displayStandup <>", value, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupGreaterThan(Boolean value) {
            addCriterion("displayStandup >", value, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayStandup >=", value, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupLessThan(Boolean value) {
            addCriterion("displayStandup <", value, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupLessThanOrEqualTo(Boolean value) {
            addCriterion("displayStandup <=", value, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupIn(List<Boolean> values) {
            addCriterion("displayStandup in", values, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupNotIn(List<Boolean> values) {
            addCriterion("displayStandup not in", values, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupBetween(Boolean value1, Boolean value2) {
            addCriterion("displayStandup between", value1, value2, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplaystandupNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayStandup not between", value1, value2, "displaystandup");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemIsNull() {
            addCriterion("displayProblem is null");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemIsNotNull() {
            addCriterion("displayProblem is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemEqualTo(Boolean value) {
            addCriterion("displayProblem =", value, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemNotEqualTo(Boolean value) {
            addCriterion("displayProblem <>", value, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemGreaterThan(Boolean value) {
            addCriterion("displayProblem >", value, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayProblem >=", value, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemLessThan(Boolean value) {
            addCriterion("displayProblem <", value, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemLessThanOrEqualTo(Boolean value) {
            addCriterion("displayProblem <=", value, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemIn(List<Boolean> values) {
            addCriterion("displayProblem in", values, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemNotIn(List<Boolean> values) {
            addCriterion("displayProblem not in", values, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemBetween(Boolean value1, Boolean value2) {
            addCriterion("displayProblem between", value1, value2, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayproblemNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayProblem not between", value1, value2, "displayproblem");
            return (Criteria) this;
        }

        public Criteria andDisplayriskIsNull() {
            addCriterion("displayRisk is null");
            return (Criteria) this;
        }

        public Criteria andDisplayriskIsNotNull() {
            addCriterion("displayRisk is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayriskEqualTo(Boolean value) {
            addCriterion("displayRisk =", value, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplayriskNotEqualTo(Boolean value) {
            addCriterion("displayRisk <>", value, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplayriskGreaterThan(Boolean value) {
            addCriterion("displayRisk >", value, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplayriskGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayRisk >=", value, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplayriskLessThan(Boolean value) {
            addCriterion("displayRisk <", value, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplayriskLessThanOrEqualTo(Boolean value) {
            addCriterion("displayRisk <=", value, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplayriskIn(List<Boolean> values) {
            addCriterion("displayRisk in", values, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplayriskNotIn(List<Boolean> values) {
            addCriterion("displayRisk not in", values, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplayriskBetween(Boolean value1, Boolean value2) {
            addCriterion("displayRisk between", value1, value2, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplayriskNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayRisk not between", value1, value2, "displayrisk");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingIsNull() {
            addCriterion("displayTimeLogging is null");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingIsNotNull() {
            addCriterion("displayTimeLogging is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingEqualTo(Boolean value) {
            addCriterion("displayTimeLogging =", value, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingNotEqualTo(Boolean value) {
            addCriterion("displayTimeLogging <>", value, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingGreaterThan(Boolean value) {
            addCriterion("displayTimeLogging >", value, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayTimeLogging >=", value, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingLessThan(Boolean value) {
            addCriterion("displayTimeLogging <", value, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingLessThanOrEqualTo(Boolean value) {
            addCriterion("displayTimeLogging <=", value, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingIn(List<Boolean> values) {
            addCriterion("displayTimeLogging in", values, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingNotIn(List<Boolean> values) {
            addCriterion("displayTimeLogging not in", values, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingBetween(Boolean value1, Boolean value2) {
            addCriterion("displayTimeLogging between", value1, value2, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaytimeloggingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayTimeLogging not between", value1, value2, "displaytimelogging");
            return (Criteria) this;
        }

        public Criteria andDisplaypageIsNull() {
            addCriterion("displayPage is null");
            return (Criteria) this;
        }

        public Criteria andDisplaypageIsNotNull() {
            addCriterion("displayPage is not null");
            return (Criteria) this;
        }

        public Criteria andDisplaypageEqualTo(Boolean value) {
            addCriterion("displayPage =", value, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplaypageNotEqualTo(Boolean value) {
            addCriterion("displayPage <>", value, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplaypageGreaterThan(Boolean value) {
            addCriterion("displayPage >", value, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplaypageGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayPage >=", value, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplaypageLessThan(Boolean value) {
            addCriterion("displayPage <", value, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplaypageLessThanOrEqualTo(Boolean value) {
            addCriterion("displayPage <=", value, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplaypageIn(List<Boolean> values) {
            addCriterion("displayPage in", values, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplaypageNotIn(List<Boolean> values) {
            addCriterion("displayPage not in", values, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplaypageBetween(Boolean value1, Boolean value2) {
            addCriterion("displayPage between", value1, value2, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplaypageNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayPage not between", value1, value2, "displaypage");
            return (Criteria) this;
        }

        public Criteria andDisplayfileIsNull() {
            addCriterion("displayFile is null");
            return (Criteria) this;
        }

        public Criteria andDisplayfileIsNotNull() {
            addCriterion("displayFile is not null");
            return (Criteria) this;
        }

        public Criteria andDisplayfileEqualTo(Boolean value) {
            addCriterion("displayFile =", value, "displayfile");
            return (Criteria) this;
        }

        public Criteria andDisplayfileNotEqualTo(Boolean value) {
            addCriterion("displayFile <>", value, "displayfile");
            return (Criteria) this;
        }

        public Criteria andDisplayfileGreaterThan(Boolean value) {
            addCriterion("displayFile >", value, "displayfile");
            return (Criteria) this;
        }

        public Criteria andDisplayfileGreaterThanOrEqualTo(Boolean value) {
            addCriterion("displayFile >=", value, "displayfile");
            return (Criteria) this;
        }

        public Criteria andDisplayfileLessThan(Boolean value) {
            addCriterion("displayFile <", value, "displayfile");
            return (Criteria) this;
        }

        public Criteria andDisplayfileLessThanOrEqualTo(Boolean value) {
            addCriterion("displayFile <=", value, "displayfile");
            return (Criteria) this;
        }

        public Criteria andDisplayfileIn(List<Boolean> values) {
            addCriterion("displayFile in", values, "displayfile");
            return (Criteria) this;
        }

        public Criteria andDisplayfileNotIn(List<Boolean> values) {
            addCriterion("displayFile not in", values, "displayfile");
            return (Criteria) this;
        }

        public Criteria andDisplayfileBetween(Boolean value1, Boolean value2) {
            addCriterion("displayFile between", value1, value2, "displayfile");
            return (Criteria) this;
        }

        public Criteria andDisplayfileNotBetween(Boolean value1, Boolean value2) {
            addCriterion("displayFile not between", value1, value2, "displayfile");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated do_not_delete_during_merge Thu Sep 17 14:19:57 ICT 2015
     */
    @SuppressWarnings("ucd")
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table m_prj_customize_view
     *
     * @mbggenerated Thu Sep 17 14:19:57 ICT 2015
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