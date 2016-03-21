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
/*Domain class of table s_currency*/
package com.esofthead.mycollab.common.domain;

import com.esofthead.mycollab.core.arguments.ValuedBean;
import com.esofthead.mycollab.core.db.metadata.Column;
import com.esofthead.mycollab.core.db.metadata.Table;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

@SuppressWarnings("ucd")
@Table("s_currency")
public class Currency extends ValuedBean {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.id
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    @Column("id")
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.shortname
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("shortname")
    private String shortname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.isocode
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("isocode")
    private String isocode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.symbol
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    @Length(max=45, message="Field value is too long")
    @Column("symbol")
    private String symbol;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.conversionrate
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    @Column("conversionrate")
    private Double conversionrate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column s_currency.fullname
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    @Length(max=100, message="Field value is too long")
    @Column("fullname")
    private String fullname;

    private static final long serialVersionUID = 1;

    public final boolean equals(Object obj) {
        if (obj == null) { return false;}
        if (obj == this) { return true;}
        if (obj.getClass() != getClass()) { return false;}
        Currency item = (Currency)obj;
        return new EqualsBuilder().append(id, item.id).build();
    }

    public final int hashCode() {
        return new HashCodeBuilder(1189, 429).append(id).build();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.id
     *
     * @return the value of s_currency.id
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.id
     *
     * @param id the value for s_currency.id
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.shortname
     *
     * @return the value of s_currency.shortname
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public String getShortname() {
        return shortname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.shortname
     *
     * @param shortname the value for s_currency.shortname
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.isocode
     *
     * @return the value of s_currency.isocode
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public String getIsocode() {
        return isocode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.isocode
     *
     * @param isocode the value for s_currency.isocode
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public void setIsocode(String isocode) {
        this.isocode = isocode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.symbol
     *
     * @return the value of s_currency.symbol
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.symbol
     *
     * @param symbol the value for s_currency.symbol
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.conversionrate
     *
     * @return the value of s_currency.conversionrate
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public Double getConversionrate() {
        return conversionrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.conversionrate
     *
     * @param conversionrate the value for s_currency.conversionrate
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public void setConversionrate(Double conversionrate) {
        this.conversionrate = conversionrate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column s_currency.fullname
     *
     * @return the value of s_currency.fullname
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column s_currency.fullname
     *
     * @param fullname the value for s_currency.fullname
     *
     * @mbggenerated Mon Mar 21 16:11:45 ICT 2016
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public enum Field {
        id,
        shortname,
        isocode,
        symbol,
        conversionrate,
        fullname;

        public boolean equalTo(Object value) {
            return name().equals(value);
        }
    }
}