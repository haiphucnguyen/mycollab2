package com.esofthead.mycollab.core.db.query;

import org.joda.time.LocalDate;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public interface VariableInjecter {
    VariableInjecter LAST_WEEK = new VariableInjecter() {
        @Override
        public Object eval() {
            LocalDate date = new LocalDate(new Date());
            date = date.minusWeeks(-1);
            LocalDate minDate = date.dayOfWeek().withMinimumValue();
            LocalDate maxDate = date.dayOfWeek().withMaximumValue();
            return new Date[]{minDate.toDate(), maxDate.toDate()};
        }
    };

    VariableInjecter THIS_WEEK = new VariableInjecter() {
        @Override
        public Object eval() {
            LocalDate date = new LocalDate(new Date());
            LocalDate minDate = date.dayOfWeek().withMinimumValue();
            LocalDate maxDate = date.dayOfWeek().withMaximumValue();
            return new Date[]{minDate.toDate(), maxDate.toDate()};
        }
    };

    VariableInjecter THIS_MONTH = new VariableInjecter() {
        @Override
        public Object eval() {
            LocalDate date = new LocalDate(new Date());
            LocalDate minDate = date.dayOfMonth().withMinimumValue();
            LocalDate maxDate = date.dayOfMonth().withMaximumValue();
            return new Date[]{minDate.toDate(), maxDate.toDate()};
        }
    };


    Object eval();
}
