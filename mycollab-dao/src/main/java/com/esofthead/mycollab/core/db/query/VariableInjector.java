/**
 * This file is part of mycollab-dao.
 *
 * mycollab-dao is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-dao is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-dao.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.core.db.query;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
@JsonSerialize(using = VariableInjector.Serializer.class)
@JsonDeserialize(using = VariableInjector.Deserializer.class)
public interface VariableInjector<T> {
    Object eval();

    Class<T> getType();

    boolean isArray();

    boolean isCollection();

    VariableInjector LAST_WEEK = new VariableInjector() {
        @Override
        public Object eval() {
            LocalDate date = new LocalDate();
            date = date.minusWeeks(-1);
            LocalDate minDate = date.dayOfWeek().withMinimumValue();
            LocalDate maxDate = date.dayOfWeek().withMaximumValue();
            return new Date[]{minDate.toDate(), maxDate.toDate()};
        }

        @Override
        public Class getType() {
            return Date[].class;
        }

        @Override
        public boolean isArray() {
            return true;
        }

        @Override
        public boolean isCollection() {
            return false;
        }
    };

    VariableInjector THIS_WEEK = new VariableInjector() {
        @Override
        public Object eval() {
            LocalDate date = new LocalDate();
            LocalDate minDate = date.dayOfWeek().withMinimumValue();
            LocalDate maxDate = date.dayOfWeek().withMaximumValue();
            return new Date[]{minDate.toDate(), maxDate.toDate()};
        }

        @Override
        public Class getType() {
            return Date[].class;
        }

        @Override
        public boolean isArray() {
            return true;
        }

        @Override
        public boolean isCollection() {
            return false;
        }
    };

    class Serializer extends JsonSerializer<VariableInjector> {
        @Override
        public void serialize(VariableInjector variableInjector, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeStartObject();
            Object value = variableInjector.eval();
            jsonGenerator.writeObjectField("value", value);
            jsonGenerator.writeEndObject();
        }
    }

    class Deserializer extends JsonDeserializer<VariableInjector> {
        @Override
        public VariableInjector deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            JsonNode valueNode = node.get("value");
            if (valueNode.isArray()) {
                ArrayNode arrNode = (ArrayNode)valueNode;
                String[] values = new String[arrNode.size()];
                for (int i=0; i<arrNode.size(); i++) {
                    Array.set(values, i, arrNode.get(i).asText());
                }
                return ConstantValueInjector.valueOf(values);
            }
            return null;
        }
    }
}
