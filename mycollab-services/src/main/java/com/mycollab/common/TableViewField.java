package com.mycollab.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;

/**
 * @author MyCollab Ltd
 * @since 5.3.1
 */
@JsonSerialize(using = TableViewField.Serializer.class)
@JsonDeserialize(using = TableViewField.DeSerializer.class)
public class TableViewField {
    private Enum<?> descKey;
    private String field;
    private Integer defaultWidth;

    public TableViewField(String field, Integer defaultWidth) {
        this(null, field, defaultWidth);
    }

    public TableViewField(Enum<?> descKey, String field, Integer defaultWidth) {
        this.descKey = descKey;
        this.field = field;
        this.defaultWidth = defaultWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableViewField)) return false;

        TableViewField that = (TableViewField) o;

        return (descKey != null ? descKey.equals(that.descKey) : that.descKey == null) && field.equals(that.field) && defaultWidth.equals(that.defaultWidth);

    }

    @Override
    public int hashCode() {
        int result = descKey != null ? descKey.hashCode() : 0;
        result = 31 * result + field.hashCode();
        result = 31 * result + defaultWidth.hashCode();
        return result;
    }

    public Enum<?> getDescKey() {
        return descKey;
    }

    public void setDescKey(Enum<?> descKey) {
        this.descKey = descKey;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getDefaultWidth() {
        return defaultWidth;
    }

    public void setDefaultWidth(Integer defaultWidth) {
        this.defaultWidth = defaultWidth;
    }

    public static class Serializer extends JsonSerializer<TableViewField> {
        @Override
        public void serialize(TableViewField tableViewField, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("field", tableViewField.field);
            jsonGenerator.writeNumberField("defaultWidth", tableViewField.defaultWidth);
            if (tableViewField.descKey != null) {
                jsonGenerator.writeStringField("desc", tableViewField.descKey.name());
                jsonGenerator.writeStringField("descCls", tableViewField.descKey.getClass().getName());
            }
            jsonGenerator.writeEndObject();
        }
    }

    public static class DeSerializer extends JsonDeserializer<TableViewField> {
        @Override
        public TableViewField deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            JsonNode node = jsonParser.getCodec().readTree(jsonParser);
            String field = node.get("field").asText();
            Integer defaultWidth = node.get("defaultWidth").asInt(200);
            JsonNode descNode = node.get("desc");
            if (descNode != null) {
                try {
                    String descClsName = node.get("descCls").asText();
                    Class descEnumCls = Class.forName(descClsName);
                    Enum descKey = Enum.valueOf(descEnumCls, descNode.asText());
                    return new TableViewField(descKey, field, defaultWidth);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return new TableViewField(field, defaultWidth);
        }
    }
}
