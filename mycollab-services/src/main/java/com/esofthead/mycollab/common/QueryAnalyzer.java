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
package com.esofthead.mycollab.common;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.db.query.CacheParamMapper;
import com.esofthead.mycollab.core.db.query.Param;
import com.esofthead.mycollab.core.db.query.SearchFieldInfo;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
public class QueryAnalyzer {
    private static final Logger LOG = LoggerFactory.getLogger(QueryAnalyzer.class);

    public static String toQueryParams(List<SearchFieldInfo> searchFieldInfos) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addSerializer(Param.class, new ParamSerializer());
            mapper.registerModule(module);
            String value = mapper.writeValueAsString(searchFieldInfos);
            return UrlEncodeDecoder.encode(value);
        } catch (IOException e) {
            throw new MyCollabException(e);
        }
    }

    public static <S extends SearchCriteria> S fromQueryParams(String query, String type, S searchCriteria) {
        S newCriteria = BeanUtility.deepClone(searchCriteria);
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Param.class, new ParamDeserializer(type));
            mapper.registerModule(module);
            List<SearchFieldInfo> list = mapper.readValue(UrlEncodeDecoder.decode(query), new TypeReference<List<SearchFieldInfo>>() {
            });
            for (SearchFieldInfo searchFieldInfo : list) {
                newCriteria.addExtraField(searchFieldInfo.buildSearchField());
            }
        } catch (Exception e) {
            LOG.error("Error", e);
        }
        return newCriteria;
    }

    public static class ParamSerializer extends JsonSerializer<Param> {
        @Override
        public void serialize(Param param, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("id", param.getId());
            jsonGenerator.writeEndObject();
        }
    }

    public static class ParamDeserializer extends JsonDeserializer<Param> {
        private String type;

        ParamDeserializer(String type) {
            this.type = type;
        }

        @Override
        public Param deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            String id = node.get("id").asText();
            CacheParamMapper.ValueParam valueParam = CacheParamMapper.getValueParam(type, id);
            if (valueParam != null) {
                return valueParam.getParam();
            } else {
                throw new MyCollabException("Invalid query");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String query =
                "W3sicHJlZml4T3BlciI6IkFORCIsInBhcmFtIjp7ImlkIjoiYXNzaWdudXNlciJ9LCJjb21wYXJlT3BlciI6ImJlbG9uZyB0byIsInZhcmlhYmxlSW5qZWN0b3IiOnsidmFsdWUiOlsiaGFpbmd1eWVuQG15Y29sbGFiLmNvbSIsImhhaW5ndXllbkBlc29mdGhlYWQuY29tIl19fSx7InByZWZpeE9wZXIiOiJBTkQiLCJwYXJhbSI6eyJpZCI6InN0YXR1cyJ9LCJjb21wYXJlT3BlciI6ImJlbG9uZyB0byIsInZhcmlhYmxlSW5qZWN0b3IiOnsidmFsdWUiOlsiSW5Qcm9ncmVzcyIsIkNvbG9yIDIiXX19XQ";
        query = UrlEncodeDecoder.decode(query);
        System.out.println("Query: " + query);
        fromQueryParams(query, ProjectTypeConstants.TASK, new TaskSearchCriteria());

    }
}
