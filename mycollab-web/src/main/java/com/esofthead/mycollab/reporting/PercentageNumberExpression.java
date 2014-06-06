/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.reporting;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import net.sf.dynamicreports.report.definition.ReportParameters;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.1.2
 * 
 */
public class PercentageNumberExpression extends AbstractFieldExpression {
	private static final long serialVersionUID = 1L;

	public PercentageNumberExpression(String field) {
		super(field);
	}

	@Override
	public String evaluate(ReportParameters reportParameters) {
		DecimalFormat df = new DecimalFormat("#");
		df.setRoundingMode(RoundingMode.HALF_EVEN);
		Double percentValue = reportParameters.getValue(field);
		return df.format(percentValue) + "%";
	}

}
